
public class App {
    public static void main(String[] args) {
        OneWaySquareList<Integer> list = new OneWaySquareList<>();
        list.add(1);
        list.add(2);    
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(null);
        list.add(null);

        list.print();

        list.remove(4);
        list.remove(0);
        //list.clear();

        list.set(0, null);
        list.add(5, 100);
        System.out.println(list.contains(null));
        System.out.println(list.get(5));
        System.out.println(list.indexOf(100));

       list.print();

        // System.out.println("-------------------------");

        // OneWaySquareList<Integer> list2 = new OneWaySquareList<>();
        // list2.add(1);
        // list2.add(2); 
        // list2.add(3);
        // //list2.add("e");
        // //list2.add(null);

        // //list2.remove("c");
        // //list2.remove(null);
        // list2.remove((Integer) 2);

        // //System.out.println(list2);

        // list2.print();


    }
}
