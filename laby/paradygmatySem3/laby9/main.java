class Main {
    private static int countElementsWithoutDuplicates(int tab[], int elem) {
        int i = 0;
        while (i < tab.length && tab[i] != elem) {
            i++;
        }

        if(i < tab.length) {
            i++;
        }

        int size = i;
        while(i < tab.length) {
            if (tab[i] != elem) {
                size++;
            }
            i++;
        }

        return size;
    }

    public static int[] removeDuplicates(int tab[], int elem) {
        if (tab == null) {
            throw new IllegalArgumentException();
        }

        int[] result = new int[countElementsWithoutDuplicates(tab, elem)];

        int from = 0, res = 0;
        while (from < tab.length && elem != tab[from]) {
            result[res++] = tab[from++];
        }

        if(from < tab.length) {
            result[res++] = tab[from++];
        }

        while (from < tab.length) {
            if(tab[from] != elem) {
                result[res++] = tab[from];
            }
            from++;
        }

        return result;
    }

    public static int[] insert(int tab[], int elem) {
        if (tab == null) {
            throw new IllegalArgumentException();
        }

        int[] result = new int [tab.length + 1];
        
        int i = 0;
        while(i < tab.length && tab[i] < elem) {
            result[i] = tab[i];
            i++;
        }

        result[i] = elem;

        while(i < tab.length) {
            result[i + 1] = tab[i];
            i++;
        }

        return result;
    }


    public static void main(String args[]) {
        ///////////////////////////////////////////////////
        /// TEST removeDuplicates/////////////////////////
        ////////////////////////////////////////////////
        
        int table1[] = {1, 2, 3, 3, 3, 7};
        int res1[] = removeDuplicates(table1, 3);
        for (int elem : res1) System.out.print(elem + " ");
        System.out.println();

        int table2[] = {3, 3, 3, 7, 8, 9};
        int res2[] = removeDuplicates(table2, 3);
        for (int elem : res2) System.out.print(elem + " ");
        System.out.println();

        int table3[] = {1, 2, 3, 3};
        int res3[] = removeDuplicates(table3, 3);
        for (int elem : res3) System.out.print(elem + " ");
        System.out.println();

        int table4[] = {};
        int res4[] = removeDuplicates(table4, 3);
        for (int elem : res4) System.out.print(elem + " ");
        System.out.println();

        ///////////////////////////////////////////////////
        /// TEST insert/////////////////////////
        ////////////////////////////////////////////////
        
        int table5[] = {1, 2, 3, 3, 3, 7};
        int res5[] = insert(table5, 3);
        for (int elem : res5) System.out.print(elem + " ");
        System.out.println();

        int table6[] = {1, 3, 5, 7};
        int res6[] = insert(table6, 4);
        for (int elem : res6) System.out.print(elem + " ");
        System.out.println();

        int table7[] = {};
        int res7[] = insert(table7, 3);
        for (int elem : res7) System.out.print(elem + " ");
        System.out.println();

        int table8[] = {1, 2};
        int res8[] = insert(table8, -1);
        for (int elem : res8) System.out.print(elem + " ");
        System.out.println();

        int table9[] = {10, 11};
        int res9[] = insert(table9, 12);
        for (int elem : res9) System.out.print(elem + " ");
        System.out.println();
        
    }
}