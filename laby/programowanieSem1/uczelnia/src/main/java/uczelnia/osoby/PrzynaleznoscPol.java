package uczelnia.osoby;

public enum PrzynaleznoscPol {
    STUDENT,
    PRACOWNIK_ADMINISTRACYJNY,
    PRACOWNIK_BADAWCZO_DYDAKTYCZNY, 
    KURS;

    @Override
    public String toString() {
        switch(this) {
            case STUDENT: return "Student";
            case PRACOWNIK_ADMINISTRACYJNY: return "Pracownik administracyjny";
            case PRACOWNIK_BADAWCZO_DYDAKTYCZNY: return "Pracownik badawczo-dydaktyczny";
            case KURS: return "Kurs";
            default: return "Nieznany";
        }
    }
}
