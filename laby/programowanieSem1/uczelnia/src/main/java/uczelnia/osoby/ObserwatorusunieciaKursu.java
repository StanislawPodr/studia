package uczelnia.osoby;

import uczelnia.osoby.add.OsobyList;

public class ObserwatorusunieciaKursu implements Obserwator{

    @Override
    public void update(Kursy kurs) {
        OsobyList.getInstance().usunStudentowKursu(kurs);
    }

}
