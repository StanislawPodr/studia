.macro print_int %val
    move $a0, %val
    li $v0, 1              # Kod syscall: print integer
    syscall
.end_macro



#=============================================
.eqv 		STACK_SIZE 	2048
#=============================================
.data
# obszar na zapamiêtanie adresu stosu systemowego
sys_stack_addr: .word 	0
# deklaracja w³asnego obszaru stosu
stack: 		.space 		STACK_SIZE
# deklaracja tablicy 10 elementowej
global_array: 	.word 	1, 2, 3, 4, 5, 6, 7, 8, 9, 10
# ============================================
.text
# czynnoœci inicjalizacyjne
sw	$sp, 	sys_stack_addr 		# zachowanie adresu stosu systemowego
la	 $sp,	 stack+STACK_SIZE 	# zainicjowanie obszaru stosu

main:
	subi	$sp,	$sp,	12 	# 3 s³owa (1 zmienna lokalna i 2 argumenty funkcji
	la	$t0,	global_array	
	sw	$t0,	4($sp)
	li	$t0,	10
	sw	$t0,	($sp)	
	jal 	sum			#wywo³ujemy funkcjê
	lw	$t0,	($sp)
	addi	$sp,	$sp,	12	# zdejmujemy ze stosu wynik funkcji i argumenty do niej
	sw	$t0,	($sp)
	print_int	$t0
	
	lw 	$sp, 	sys_stack_addr 		# odtworzenie wskaŸnika stosu systemowego
	li 	$v0, 	10
	syscall

sum:
	subi	$sp,	$sp,	8	#robimy miejsce na rejestr i powrót z funkcji
	sw	$ra,	($sp)		#zapoisujemy adres powrotu
	subi	$sp,	$sp,	8	#robimy miejsce na zmienne lokalne
	li	$t1,	0
	sw	$t1,	($sp)		#ustawiamy wartoœæ s
	lw	$t0,	16($sp)		#³adujemy drugi argument do funkcji (by³ na szczycie stosu przed odjêciem 16)
	subi	$t0,	$t0,	1
	sw	$t0,	4($sp)		#ustawiamy wartoœæ i
	while_loop:
		bltz  	$t0,	end_loop
		lw	$t2,	20($sp)		#wczytujemy pierwszy argument funkcji (adres tablicy)
		sll	$t3,	$t0,	2
		add	$t2,	$t2,	$t3	#przesuwamy na indeks i w tablicy
		lw	$t2,	($t2)
		add	$t1,	$t1,	$t2	#edytujemy wartoœæ s
		sw	$t1,	($sp)		#zapisujemy s
		subi	$t0,	$t0, 	1	#dekrementujemy i
		sw	$t0,	4($sp)		#zapisujemy i
		j while_loop
	end_loop:
		sw	$t1,	12($sp)		#zapisujemy wartoœæ zwracan¹
		addi	$sp,	$sp,	8	#zdejmujemy zmienne lokalne
		lw	$ra,	($sp)
		addi	$sp,	$sp,	4	#zdejmujemy adres powrotu i zapisujemy do $ra
		jr	$ra
	



