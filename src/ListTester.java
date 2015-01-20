/**
 * Created by khang on 1/14/2015.
 */
public class ListTester {
    public static void main(String[] args) {
        //MyArrayList<Integer> lst = new MyArrayList<>();
        MyLinkedList<Integer> lst = new MyLinkedList<>();

        // add 0-9
        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }

        // at pos 0, add 20-29 (kind of like prepend 29-20)
        for (int i = 20; i < 30; i++) {
            lst.add(0, i);
        }

        // removes the 29 at pos 0
        lst.remove(0);

        // removes the 9 at the end
        lst.remove(lst.size() - 1);

        System.out.println(lst);

        java.util.Iterator<Integer> itr = lst.iterator();

        while (itr.hasNext()) {
            itr.next();
            itr.remove();
            System.out.println(lst);
        }

        // Testing contains.
        {
            System.out.println("Testing contains. . .");
            System.out.println("1. empty list:");
            System.out.println(lst.contains(7));
            System.out.println("2. --1 item--");
            lst.add(5);
            System.out.println("2.1. true:");
            System.out.println(lst.contains(5));
            System.out.println("2.2. false:");
            System.out.println(lst.contains(7));
            System.out.println("3. --multiple items--");
            for (int i = 0; i < 50; i += 2) {
                lst.add(i);
            }
            System.out.println("List contents: ");
            System.out.println(lst);
            System.out.println("3.1. contains(48) is true:");
            System.out.println(lst.contains(48));
            System.out.println("3.2. contains(7) is false:");
            System.out.println(lst.contains(7));
        }


    }
}
