.macro	find_value	%register,	%x,	%y
	move	$t0,	$s2
	mul	$t1,	%x,	8
	add	$t0,	$t0,	$t1
	lw	$t2,	($t0)		#wczytanie adresu pocz¹tku wiersza x
	mul	$t1,	%y,	4
	add	$t2,	$t2,	$t1	#przesuniêcie go o y do punktu (x, y)
	move	%register,	$t2
.end_macro 


.macro read_int %reg
	li 	$v0, 	5
	syscall
	move 	%reg, 	$v0
.end_macro

.macro	print	%print
	li	$v0,	1
	move	$a0,	%print
	syscall 
.end_macro




.data
RAM:	.space		4096


.text 
	read_int	$s0	#wczytaj liczbê wierszy
	read_int	$s1	#wczytaj liczbê kolumn
	la	$s2,	RAM	#wczytanie naszej pamiêci RAM
	mul	$t0,	$s0,	8	#8 bajtów na ka¿dy blok (pocz¹tek i koniec)
	add	$s3,	$t0,	$s2	#koniec bloku referencji
	
	#	$s0 -> liczba wierszy
	#	$s1 -> liczba kolumn
	#	$s2 -> adres pocz¹tku pamiêci
	#	$s3 -> pierwszy adres na pamiêæ
	
	#stworzenie wskaŸników do konkretnych wierszy
	mul	$t1,	$s1,	4	#co ile roz³o¿one s¹ wiersze (liczba kolumn x4)
	move	$t2,	$s2		#gdzie w RAM zapisujemykonkretn¹ referencjê na wiersz
	move	$t3,	$s3		#od tego miejsca zapisujemy dane do tablicy
	
	#	$t1 -> liczba o jak¹ oddalone w pamiêci s¹ od siebie wiersze
	#	$t2 -> adres konkretnej referencji na wiersz
	#	$t3 -> adres pocz¹tku wiersza w tablicy
	allocation:
		sw	$t3,	($t2)	#zapisujemy referencjê na pole $t2
		
		addu	$t3,	$t3,	$t1
		addi	$t2,	$t2,	4
		sw	$t3,	($t2)
		addi	$t2,	$t2,	4
		bgt 	$s3,	$t2,	allocation	#dopóki nie doszliœmy do koñca pamiêci na referencje
	
	
	#po utworzeniu tablicy musimy j¹ zainicjowaæ 
	move	$t0,	$s2		#adres pocz¹tku wiersza
	li	$t3,	0
	#	$t0 -> wskaŸnik na adres pierwszego elementu konkretnego wiersza
	#	$t1 -> adres elementu tablicy w wierszu o adresie $t0
	#	$t2 -> adres koñca wiersza $t0
	#	$t3 -> counter setek (+100 na ka¿dy wiersz)
	#	$t4 -> iterator elementu w wierszu
	#	$t5 -> nasz wynik dla konkretnej komórki w RAM ($t3 + $t4)
	initialisation:
		lw	$t1,	($t0)
		lw	$t2,	4($t0)
		li	$t4,	1
		loop:
			add	$t5,	$t3,	$t4
			sw	$t5,	($t1)
			addi	$t1,	$t1,	4
			addi	$t4,	$t4,	1
			blt	$t1,	$t2, 	loop
		addi	$t0,	$t0,	8
		addi	$t3,	$t3,	100
		blt	$t0,	$s3,	initialisation
	
	
	#program widoczny dla u¿ytkownika (zapis i odczyt z tablicy)
	#0 -> zapis, 1 -> odczyt, 2 -> exit
	while_true:
		read_int	$t9
		beq 	$t9,	2,	exit
		
		read_int	$a0	#wczytanie x
		read_int	$a1	#wczytanie y
		
		bnez  	$t9,	skip_save
		jal	save
	skip_save:
		bne	$t9,	1,	while_true
		jal	read
		j	while_true
	
	#wyjœcie z programu
	exit:
		li	$v0,	10	
		syscall
	
	
	save:
		find_value	$t5,	$a0,	$a1
		read_int	$t0
		sw	$t0,	($t5)
		jr	$ra
	
	read:
		find_value	$t5,	$a0,	$a1
		lw	$t0,	($t5)
		print	$t0
		jr	$ra
		








	
		
		
	
	
