
public class App {

    static double potegi(double a, long k) throws Exception {
        if (k < 0)
            throw new Exception("k musi być >= 0");
        double result = 1;
        if (k > 0) {
            result = a;
            long sumOfIterationsTok = 1;
            while (sumOfIterationsTok != k) {
                double resultIterator = a;
                long i = 1;
                while (sumOfIterationsTok + i <= k) {
                    result *= resultIterator;
                    resultIterator *= resultIterator;
                    sumOfIterationsTok += i;
                    i += i;
                }
            }

        }
        return result;
    }

    static long silnia(long x) throws Exception {
        if (x < 0)
            throw new Exception("x musi być >= 0");
        long silnia = 1;
        for (long i = 2; i <= x; i++) {
            silnia *= i;
        }
        return silnia;
    }

    static double e_x_sp(long maxK, int x) {
        double sum = x + 1;
        double plus = x;
        for (long k = 2; k <= maxK; k++) {
            plus = plus * (x / (double) k);
            sum += plus;
        }

        return sum;
    }

    static void sin_x_sp(long maxK, double x) throws Exception {
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

    static void cos_x_sp(long maxK, double x) throws Exception
    {
        double sum = 0;
        if (maxK < 0)
            throw new Exception("maksymalne K musi być większe od 0");
        for(long i = 0; i<maxK; i++)
        {
            if (i % 2 == 0)
                sum += potegi(x, 2*i) / silnia(2*i);
            else
                sum -= potegi(x, 2*i) / silnia(2*i);
        }
        System.out.println("cos x:");
        System.out.println(sum);
        if (Double.isInfinite(sum) || Double.isNaN(sum))
            throw new Exception("Za duża dokładność");
    }

    public static void main(String[] args) throws Exception {

        sin_x_sp(5, 3.14);
        cos_x_sp(100, 3.1415/3);
    }
}
