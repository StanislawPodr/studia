public class SieveOfEratosthenes {
    private static int[] numall = new int[100];
    private static int[] primes = new int[100];

    public static void main(String[] args) {
        int start = -1;
        int end = 100;
        int prime = 2;

        for (int i = 1; i <= end; i++) {
            numall[i - 1] = i;
        }

        numall[0] = 0; 

        boolean noMorePrimes = false; // w assemblerze nie ma flagi ale należałoby
        // bez niej użyć breake czego raczej nie chcemy
        while(!noMorePrimes) {
            int iterator = start;
            int indexForNextPrime = iterator + prime; 
            iterator += prime * prime; 
            while(iterator < end) {
                numall[iterator] = 0;
                iterator += prime;
            }
            do {
                indexForNextPrime++;
                noMorePrimes = indexForNextPrime >= end;
            } while(!noMorePrimes && numall[indexForNextPrime] == 0);
            prime = indexForNextPrime + 1;
        }

        int whichPrime = 0;
        for(int i = start + 1; i < end; i++) {
            if(numall[i] != 0) {
                primes[whichPrime] = numall[i];
                whichPrime++;
            }
        }

        for(int i = 0; i < end; i++) {
            System.out.println(primes[i]);
        }
       
    }
}
