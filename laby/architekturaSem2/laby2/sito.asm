.data
N:		.word		400		#N=100
numall:		.space		400
primes:		.space		400

.text
#Pocz�tek programu, alokacja danych
	la	$s2,	N
	lw	$s2,	($s2)			#ilo�� bajt�w
	la	$s0,	numall			#adres tablicy do algorytmu
	li	$t0,	8			#o ile mamy skaka� (pierwsza liczba pierwsza x4)
	add	$s1,	$s2,	$s0	
	
	

	move	$t1,	$s0			#ustawiamy iterator
	li	$t2,	1			#�adujemy index dla liczb naturalnych
table_association:				#uzupe�nianie tablicy
	sw	$t2,	($t1)
	addi	$t1,	$t1,	4
	addi	$t2,	$t2,	1	
	bne	$t1,	$s1,	table_association
	
			
						
	addi	$s0,	$s0,	-4		#nasza tablica jest ($s0 - $s1). Dla u�atwienia, mo�naby teoretycznie u�ywa� offsetu dla mniejszej liczby instrukcji
	sw	$zero,	4($s0)			#jedynka nie jest liczb� pierwsz�
#p�tla znajduj�ca liczby pierwsze

	# $t0 -> liczba pierwsza x4
	# $t1 -> iterator do szukania wielokrotności liczb pierwszych
	# $t2 -> iterator do szukania kolejnej liczby pierwszej
	# $t3 -> zmienna pomocnicza (do przechowywania wyniku mnożenia i wartości spod zadanego adresu)
next_prime:
	move	$t1,	$s0			#ustawiamy iterator tablicy na jej pocz�tek
	add	$t2,	$t1, 	$t0		#zapisujemy na szukanie kolejnej liczby pierwszej
	mul	$t3,	$t0,	$t0	
	srl	$t3,	$t3,	2		# jak t_0 to przesunięcie adresu więc jest nx4. Jak podnosimy do kwadratu to musimy potem podzielić na 4
	add	$t1,	$t1,	$t3		# zaczynamy od liczby pierwszej n^2 numeru w tablicy
	eliminate_multiple:
		bgt	$t1,	$s1,	find_prime	#jak wyszli�my z tablicy to skaczemy
		sw	$zero,	($t1)			#zapisujemy do tablicy 0, co oznacza, �e ten element nie b�dzie liczb� pierwsz�
		add	$t1,	$t1,	$t0		#wykonujemy przesuni�cie o nasz� liczb� pierwsz� x 4
		j eliminate_multiple
		
	find_prime:
		addi	$t2,	$t2,	4			#inkrementujemy po tablicy w poszukiwaniu liczby r�nej od 0
		bge 	$t2,	$s1, 	prime_not_found #je�eli wyszli�my z zakresu (nie ma wi�cej liczb pierwszych)
		lw	$t3,	($t2)
		beq  	$t3,	$zero,	find_prime	#je�eli liczba jest 0 to musimy i�� dalej po tablicy
	sub	$t0,	$t2,	$s0
	j next_prime					#znaleziona liczba pierwsza wi�c mo�na powt�rzy�

prime_not_found:

	la	$t0,	primes				#dodajemy do wynikowej tablicy
	move	$t1,	$s0
add_primes:
	addi	$t1,	$t1,	4
	beq	$t1,	$s1,	end
	lw	$t2,	($t1)
	beq 	$t2,	$zero,	not_prime
	sw	$t2,	($t0)
	addi	$t0,	$t0,	4
not_prime:
	j	add_primes
	
end:
	li	$v0, 	10          		# terminate program
	syscall

	
	
	
	
	
	
	
	
