.data 
x: .word 536
y: .word 10000000
wyn.: .space 4
status: .space 4

.text 
.globl main
main:
	lw $t0, x #zapisanie do rejestru mno�nej
	lw $t1, y #zapisanie do rejestru mno�nika
	li $t2, 1 #zapisanie maski maj�cej na celu wykonanie operacji na konkretnym bicie mno�nika
	li $t3, 31 #licznik o ile bit�w nale�y przesun�� 
	li $t9, 0 #ustawiamy przepe�nienie na fa�sz
	li $t6, 0 #ustawiamy wynik na 0
loop:
	and $t4, $t1, $t2 #wyizolowanie bitu z mno�nika kt�ry nas interesuje dzi�ki masce
	sllv $t4, $t4, $t3 #przesuni�cie na lewy koniec liczby bitu kt�ry nas interesuje (o licznik przesuni��)
	sra $t4, $t4, 31 #wype�nienie wszytskich znak�w bitem znacz�cym
	and $t4, $t4, $t0 #sprawdzenie czy nale�y doda� nasz� liczb� (je�li istotny bit mia� 0 to bedz� 32 zera, je�li 1 to dodajemy liczb�)
	li $t5, 31 #za�adowanie liczby kt�ra po odj�ciu $t3 ma wskazywa� ile trzeba przesun�� �eby zrobi� "schodki" dla kolejnych liczb
	sub $t5, $t5, $t3 #po odj�ciu - musimy przed dodaniem przesun�� o tyle  w lewo
	subi $t3, $t3, 1 #zmniejszenie licznika przesuni�� w lewo o 1
check_after_shift:
	beq $t5, $zero, skip_check_for_overflow #p�tla while wykonuj�ca si� tyle razy ile potrzebujemy schodk�wfunkcji
	subi $t5, $t5, 1 #dekrementacja $t5 (licznika dla p�tli)
	bgez  $t4, skip_check_for_shift #jak nie b�dzie przepe�nienia ($t4 >= 0) to nie zmieniamy $t9
	li $t9, 1 #ustawiamy przepe�nienie na prawda
skip_check_for_shift:
	sll $t4, $t4, 1 #przesuni�cie i zrobienie "schodka"
	j check_after_shift #robimy p�tl�
skip_check_for_overflow:	
	addu $t7, $t6, $t4 #dodanie kolejnej cz�ci do wyniku, aby sprawdzi� przepe�nienie wynik dajemy tymczasowo do nowego rejestru
	#beq $t4, 0, skip_check_after_addition #je�eli dodajemy 0 to na pewno nie b�dzie przepe�nienia
	xor $t8, $t7, $t6 #wynik ujemny, gdy znaki wyniku i danych si� r�ni� (przepe�nienie wyst�pi�o)
	bgez  $t8, skip_check_after_addition #gdy $t8 wi�ksze od zera to nie ma przepe�nienia
	li $t9, 1 #ustawiamy przepe�nienie na prawda
skip_check_after_addition:	
	move $t6, $t7 #przenosimy wynik spowrotem do rejestru $t6 gdzie powinien si� znale��
	sll $t2, $t2, 1 #przesuni�cie maski o 1 w lewo (rozpatrujemy kolejny bit)
	bne $t2, $zero, loop #gdy warto�� maski == zero nale�y nie wykonywa� p�tli
	
	li $v0, 1 #print int
	la $t0, wyn. #przypisujemy adres pami�ci z wyn. do $t0
	sw $t6, 0($t0) #zapis wyniku do etykiety wyn.
	move $a0, $t6 #przeniesienie wyniku do a0
	syscall 
	la $t0, status
	sw $t9, 0($t0)
	move $a0, $t9
	syscall
	li $v0, 10
	syscall
	
	
	
	

