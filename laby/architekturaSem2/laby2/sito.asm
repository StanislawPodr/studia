.data
N:		.word		404		#N=100
numall:		.space		404
primes:		.space		404

.text
#Pocz¹tek programu, alokacja danych
	la	$s2,	N
	lw	$s2,	($s2)			#iloœæ bajtów
	la	$s0,	numall			#adres tablicy do algorytmu
	li	$t0,	8			#o ile mamy skakaæ (pierwsza liczba pierwsza x4)
	add	$s1,	$s2,	$s0	
	
	

	move	$t1,	$s0			#ustawiamy iterator
	li	$t2,	1			#³adujemy index dla liczb naturalnych
table_association:				#uzupe³nianie tablicy
	sw	$t2,	($t1)
	addi	$t1,	$t1,	4
	addi	$t2,	$t2,	1	
	bne	$t1,	$s1,	table_association
	
			
						
	addi	$s0,	$s0,	-4		#nasza tablica jest ($s0 - $s1). Dla u³atwienia, mo¿naby teoretycznie u¿ywaæ offsetu dla mniejszej liczby instrukcji
	sw	$zero,	4($s0)			#jedynka nie jest liczb¹ pierwsz¹
#pêtla znajduj¹ca liczby pierwsze
next_prime:
	move	$t1,	$s0			#ustawiamy iterator tablicy na jej pocz¹tek
	add	$t1,	$t1,	$t0		#ustawiamy na nasz¹ liczbê pierwsz¹ (któr¹ chcemy pomin¹æ)
	move	$t2,	$t1			#zapisujemy na szukanie kolejnej liczby pierwszej
	eliminate_multiple:
		add	$t1,	$t1,	$t0		#wykonujemy przesuniêcie o nasz¹ liczbê pierwsz¹ x 4
		bge	$t1,	$s1,	find_prime	#jak wyszliœmy z tablicy to skaczemy
		sw	$zero,	($t1)			#zapisujemy do tablicy 0, co oznacza, ¿e ten element nie bêdzie liczb¹ pierwsz¹
		j eliminate_multiple
		
	find_prime:
		addi	$t2,	$t2,	4			#inkrementujemy po tablicy w poszukiwaniu liczby ró¿nej od 0
		beq	$t2,	$s1, 	prime_not_found #je¿eli wyszliœmy z zakresu (nie ma wiêcej liczb pierwszych)
		lw	$t3,	($t2)
		beq  	$t3,	$zero,	find_prime	#je¿eli liczba jest 0 to musimy iœæ dalej po tablicy
	sub	$t0,	$t2,	$s0
	j next_prime					#znaleziona liczba pierwsza wiêc mo¿na powtórzyæ

prime_not_found:

	la	$t0,	primes				#dodajemy do wynikowej tablicy
	move	$t1,	$s0
add_primes:
	addi	$t1,	$t1,	4
	beq	$t1,	$s1,	end
	lw	$t2,	($t1)
	bne	$t2,	$zero,	prime
	la	$ra,	comeback
comeback:
	j	add_primes
	

end:
	li	$v0, 	10          		# terminate program
	syscall

	
	
	



prime:
	sw	$t2,	($t0)
	addi	$t0,	$t0,	4
	jr	$ra
	
	
	
	
	
