/**
 * Created by khang on 1/14/2015.
 */
public class ListTester {
    public static void main(String[] args) {
        //MyArrayList<Integer> lst = new MyArrayList<>();
        MyLinkedList<Integer> lst = new MyLinkedList<>();

        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }

        for (int i = 20; i < 30; i++) {
            lst.add(0, i);
        }

        lst.remove(0);
        lst.remove(lst.size() - 1);

        System.out.println(lst);

        java.util.Iterator<Integer> itr = lst.iterator();

        while (itr.hasNext()) {
            itr.next();
            itr.remove();
            System.out.println(lst);
        }
    }
}
