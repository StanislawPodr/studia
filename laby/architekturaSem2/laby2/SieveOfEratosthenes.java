public class SieveOfEratosthenes {
    private static int[] numall = new int[100];
    private static int[] primes = new int[100];

    public static void main(String[] args) {
        int s0 = 0;
        int s1 = numall.length;
        int t0 = 2;

        for (int i = 0; i < s1; i++) {
            numall[i] = i;
        }

        numall[0] = 0;
        numall[1] = 0;

        nextPrime:
        while (true) {
            for (int t1 = t0 + t0; t1 < s1; t1 += t0) {
                numall[t1] = 0;
            }

            int t2_iter = t0 + 1;
            while (true) {
                if (t2_iter >= s1) break nextPrime;
                if (numall[t2_iter] != 0) {
                    t0 = t2_iter;
                    break;
                }
                t2_iter++;
            }
        }

        int t0_primes = 0;
        for (int i = 2; i < s1; i++) {
            if (numall[i] != 0) {
                primes[t0_primes++] = i;
            }
        }

        System.out.println("Liczby pierwsze:");
        for (int i = 0; i < t0_primes; i++) {
            System.out.print(primes[i] + " ");
        }
    }
}
