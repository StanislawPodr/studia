.data 
x: .word 2147483647
y: .word 2
wyn.: .space 4
status: .space 4

.text 
.globl main
main:
	lw $t0, x #zapisanie do rejestru mno¿nej
	lw $t1, y #zapisanie do rejestru mno¿nika
	li $t2, 1 #zapisanie maski maj¹cej na celu wykonanie operacji na konkretnym bicie mno¿nika
	li $t3, 31 #licznik o ile bitów nale¿y przesun¹æ 
	li $t9, 0 #ustawiamy przepe³nienie na fa³sz
loop:
	and $t4, $t1, $t2 #wyizolowanie bitu z mno¿nika który nas interesuje dziêki masce
	sllv $t4, $t4, $t3 #przesuniêcie na lewy koniec liczby bitu który nas interesuje (o licznik przesuniêæ)
	sra $t4, $t4, 31 #wype³nienie wszytskich znaków bitem znacz¹cym
	and $t4, $t4, $t0 #sprawdzenie czy nale¿y dodaæ nasz¹ liczbê (jeœli istotny bit mia³ 0 to bedz¹ 32 zera, jeœli 1 to dodajemy liczbê)
	li $t5, 31 #za³adowanie liczby która po odjêciu $t3 ma wskazywaæ ile trzeba przesun¹æ ¿eby zrobiæ "schodki" dla kolejnych liczb
	sub $t5, $t5, $t3 #po odjêciu - musimy przed dodaniem przesun¹æ o tyle  w lewo
	subi $t3, $t3, 1 #zmniejszenie licznika przesuniêæ w lewo o 1
check_after_shift:
	beq $t5, $zero, skip_check_for_overflow #pêtla while wykonuj¹ca siê tyle razy ile potrzebujemy schodkówfunkcji
	subi $t5, $t5, 1 #dekrementacja $t5 (licznika dla pêtli)
	bgez  $t4, skip_check_for_shift #jak nie bêdzie przepe³nienia ($t4 >= 0) to nie zmieniamy $t9
	li $t9, 1 #ustawiamy przepe³nienie na prawda
skip_check_for_shift:
	sll $t4, $t4, 1 #przesuniêcie i zrobienie "schodka"
	j check_after_shift #robimy pêtlê
skip_check_for_overflow:	
	addu $t7, $t6, $t4 #dodanie kolejnej czêœci do wyniku, aby sprawdziæ przepe³nienie wynik dajemy tymczasowo do nowego rejestru
	xor $t8, $t6, $t4 #zrobienie xor-a na ka¿dej czêœci sumy (wynik ujemny gdy znaki s¹ ró¿e)
	beq $t4, 0, skip_check_after_addition #je¿eli dodajemy 0 to na pewno nie bêdzie przepe³nienia
	bltz $t8, skip_check_after_addition #je¿eli znaki s¹ ró¿ne to przepe³nienie nie wyst¹pi
	xor $t8, $t7, $t6 #wynik ujemny, gdy znaki wyniku i danych siê ró¿ni¹ (przepe³nienie wyst¹pi³o)
	bgtz $t8, skip_check_after_addition #gdy $t8 wiêksze od zera to nie ma przepe³nienia
	li $t9, 1 #ustawiamy przepe³nienie na prawda
skip_check_after_addition:	
	move $t6, $t7 #przenosimy wynik spowrotem do rejestru $t6 gdzie powinien siê znaleŸæ
	sll $t2, $t2, 1 #przesuniêcie maski o 1 w lewo (rozpatrujemy kolejny bit)
	bne $t2, $zero, loop #gdy wartoœæ maski == zero nale¿y nie wykonywaæ pêtli
	
	li $v0, 1 #print int
	la $t0, wyn. #przypisujemy adres pamiêci z wyn. do $t0
	sw $t6, 0($t0) #zapis wyniku do etykiety wyn.
	move $a0, $t6 #przeniesienie wyniku do a0
	syscall 
	move $a0, $t9
	syscall
	li $v0, 10
	syscall
	
	
	
	

