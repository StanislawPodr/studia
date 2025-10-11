public class App {
    public static void main(String[] args) throws Exception {
        Osoba [] ludzie = new Student[3 + 3];
        PrzedmiotyStudenta [] przedmiotyStudenta1 = new PrzedmiotyStudenta[2]; 
        PrzedmiotyStudenta [] przedmiotyStudenta2 = new PrzedmiotyStudenta[3]; 
        PrzedmiotyStudenta [] przedmiotyStudenta3 = new PrzedmiotyStudenta[4]; 
        przedmiotyStudenta1[0] = new PrzedmiotyStudenta("Analiza", "Kamil", "Grzegorz");
        przedmiotyStudenta1[1] = new PrzedmiotyStudenta("Algebra", "Łukasz", "Grzegorz");
        przedmiotyStudenta2[0] = new PrzedmiotyStudenta("Algebra", "Łukasz", "Grzegorz");
        przedmiotyStudenta2[1] = new PrzedmiotyStudenta("Algebra", "Łukasz", "Grzegorz");
        przedmiotyStudenta2[2] = new PrzedmiotyStudenta("Algebra", "Łukasz", "Grzegorz");
        przedmiotyStudenta3[0] = new PrzedmiotyStudenta("Algebra", "Łukasz", "Grzegorz");
        przedmiotyStudenta3[1] = new PrzedmiotyStudenta("Algebra", "Łukasz", "Grzegorz");
        przedmiotyStudenta3[2] = new PrzedmiotyStudenta("Algebra", "Łukasz", "Grzegorz");
        przedmiotyStudenta3[3] = new PrzedmiotyStudenta("Algebra", "Łukasz", "Grzegorz");
        ludzie[0] = new Student("Jan", "Kowalski", "112", 1, przedmiotyStudenta1);
        ludzie[1] = new Student("Jan", "Kowal", "111", 2, przedmiotyStudenta2);
        ludzie[2] = new Student("Roch", "Kowalski", "100", 2, przedmiotyStudenta3);
        ludzie[3] = new Wykladowca("Maciej", "Brzeczyszczykiewicz", 2);
        ludzie[4] = new Wykladowca("Maciej", "Nowak", 19);
        ludzie[5] = new Wykladowca("Aleksander", "Kot", 10);
        System.out.println(ludzie[0].getImie());
    }
}
