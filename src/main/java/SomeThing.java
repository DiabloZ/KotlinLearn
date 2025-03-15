import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;

class SomeThing {
    public static void main(String[] args) {
        Hashtable<SomeOne, Integer> table = new Hashtable<>();
        table.put(new SomeOne("1"), 1);
        table.put(new SomeOne("2"), 1);
        table.put(new SomeOne("3"), 1);
        table.values();
        Hashtable<Integer, SomeOne> table2 = new Hashtable<>();
        table2.put(1, new SomeOne("1"));
        table2.put(1, new SomeOne("2"));
        table2.put(1, new SomeOne("3"));
        table2.values();
        LinkedHashMap< Integer, SomeOne> hm = new LinkedHashMap();
        hm.put(1, new SomeOne("1"));
        hm.put(1, new SomeOne("2"));
        hm.put(1, new SomeOne("3"));
        hm.values();

        LinkedHashMap< SomeOne, Integer> hm2 = new LinkedHashMap();

        hm2.put(new SomeOne("1"), 1);
        hm2.values();
    }

    static class SomeOne{
        String some;
        SomeOne(String some){
            this.some = some;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
