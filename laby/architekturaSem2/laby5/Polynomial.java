public class Polynomial {
    private float[] coefficients;

    public Polynomial(float[] coefficients) {
        this.coefficients = coefficients;
    }

    public double evaluate(float x) {
        if(coefficients == null || coefficients.length == 0) {
            throw new IllegalArgumentException("Coefficients cannot be null or empty");
        }

        double result = coefficients[0]; 
        for (int i = 1; i < coefficients.length; i++) {
            result *= x;
            result += coefficients[i];
        }
        return result;
    }

    public static void main(String[] args) {
        float[] coeffs = {2.3f, 3.45f, 7.67f, 5.32f}; 
        Polynomial poly = new Polynomial(coeffs);
        float x = 2.0f;
        System.out.println("W(" + x + ") = " + poly.evaluate(x)); 
    }
}