public class App {
    static class Debug {
        static void fields(Object obj) throws IllegalAccessException {

            if (obj == null) {
                throw new IllegalArgumentException("Object shouldn't be null");
            }

            var dbg = obj.getClass();

            System.out.println(
                    "class name: " + dbg.getCanonicalName() + "\n");

            var fields = dbg.getDeclaredFields();
            for (var field : fields) {
                field.setAccessible(true);

                System.out.println(
                        "field " + field + " " + field.get(obj) + "\n");
            }
        }
    }

    public static class Point {
        public Point(int xv, int yv) {
            this.xv = xv;
            this.yv = yv;
        }

        int xv;
        int yv;
    }

    public static void main(String[] args) throws Exception {
        var p = new Point(3, 4);
        Debug.fields(p);
        final var nxtp = new Point(-1, 9);
        Debug.fields(nxtp);
        class WithNull extends Debug {
            Object a = null;
        }
        WithNull with = new WithNull();
        Debug.fields(with);
    }
}
