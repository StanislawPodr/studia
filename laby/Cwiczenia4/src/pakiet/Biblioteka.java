package pakiet;

public class Biblioteka { 
    private Biblioteka(){}
    public static void sin(long maxK, double x) throws Exception {
        double sum = x;
        double power = x;
        long silnia = 1;
        if (maxK < 0)
            throw new Exception("maksymalne K musi być większe od 0");
        for (long k = 1; k <= maxK; k++) {
            power = power * x * x;
            if (Double.isInfinite(power) || Double.isNaN(power))
                throw new Exception("Za duża dokładność");
            for (long i = k * 2; i < k * 2 + 2; i += 1) {
                silnia *= i;
            }

            if (k % 2 == 0)
                sum += power / silnia;
            else
                sum -= power / silnia;

        }
        System.out.println("sin x:");
        System.out.println(sum);
        if (Double.isInfinite(sum) || Double.isNaN(sum))
            throw new Exception("Za duża dokładność");
    }

    public static double e(long maxK, int x) {
        double sum = x + 1;
        double plus = x;
        for (long k = 2; k <= maxK; k++) {
            plus = plus * (x / (double) k);
            sum += plus;
        }

        return sum;
    }
}
