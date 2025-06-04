.macro print_double %reg
    mov.d 	$f12, %reg
    li 		$v0, 3           
    syscall
.end_macro

.macro	read_float %reg
    li 		$v0, 6        
    syscall
    mov.s 	%reg, $f0
.end_macro
	

.data
coefs: 		.float		2.3 3.45 7.67 5.32
degree: 	.word 		3

.text
main:	
	la	$a0,	coefs
	lw	$a1,	degree
	read_float	$f12
	jal	eval_poly
	print_double	$f0
	li	$v0,	10
	syscall

eval_poly:
	move	$t0,	$a0
	move	$t1,	$a1
	cvt.d.s $f20,	$f12
	l.s	$f22,	($t0)
	cvt.d.s $f0,	$f22
	#	$t0 ->	adres na tablicê wartoœci wielomianu
	#	$t1 -> stopieñ wielomianu (potem counter dla pêtli)
	#	$f20 -> argument dla wielomianu (double)
	#	$f0-$f1 -> zwracany wynik w postaci double
	#	$f22 ->	nasz float bêd¹cy wspó³czynnikiem potêgi w wielomianie
	
	for_each:
		beqz   	$t1,	end_loop
		addi	$t0,	$t0,	4
		mul.d	$f0,	$f0,	$f20	# mno¿ymy wynik razy nasze x
		l.s 	$f22,	($t0)	
		cvt.d.s $f22,	$f22
		add.d	$f0,	$f0,	$f22	# dodajemy kolejny wspó³czynnik i mamy (((ax + b)x + c)x...) + n	
		subi	$t1,	$t1,	1
		j	for_each
	end_loop:
	jr	$ra
