package part1;

public class Demo {
    public static void main(String[] args) {
        SimpleHashSet<Integer> simpleHashSet = new SimpleHashSet<>();
        SimpleLinkedList<String> simpleList = new SimpleLinkedList<>();

        System.out.println("SimpleHashSet demo :");
        simpleHashSet.add(2);
        simpleHashSet.add(22);
        simpleHashSet.add(3);
        simpleHashSet.add(32);
        simpleHashSet.add(23);
        simpleHashSet.add(24);
        simpleHashSet.add(2322);
        simpleHashSet.add(243);
        simpleHashSet.add(2312);
        simpleHashSet.add(2456);
        simpleHashSet.add(24567);
        simpleHashSet.showItems();
        System.out.println("size : " + simpleHashSet.size());
        System.out.println("simpleHashSet contains 243, 23, 333 : " + simpleHashSet.contains(243)
                + ", " + simpleHashSet.contains(23) + ", " + simpleHashSet.contains(333));
        System.out.println("simpleHashSet add duplicate values (243, 23), new values (333, 301) : "
                + simpleHashSet.add(243) + ", " + simpleHashSet.add(23) + ", "
                + simpleHashSet.add(333) + ", " + simpleHashSet.add(301));
        System.out.println("size : " + simpleHashSet.size());
        simpleHashSet.showItems();
        System.out.println("simpleHashSet contains 243, 23, 333, 301 : "
                + simpleHashSet.contains(243) + ", " + simpleHashSet.contains(23) + ", "
                + simpleHashSet.contains(333) + ", " + simpleHashSet.contains(301));
        System.out.println("simpleHashSet delete exists values (22, 243, 2312), not exists 999 : "
                + simpleHashSet.remove(22) + ", " + simpleHashSet.remove(243) + ", "
                + simpleHashSet.remove(2312) + ", " + simpleHashSet.remove(999));
        System.out.println("size : " + simpleHashSet.size());
        simpleHashSet.showItems();

        System.out.println("\nSimpleLinkedList demo : ");
        simpleList.add("1");
        simpleList.add("2");
        simpleList.add("3");
        simpleList.add("4");
        simpleList.add("5");
        simpleList.add("6");
        simpleList.add("7");
        String[] items = {"test1", "test2", "test3"};
        simpleList.addAll(items);
        int size = simpleList.size();
        simpleList.showLinkedlistValues();
        System.out.println("------------------------");
        String item = simpleList.get(2);
        System.out.println("item : " + item + " size : " + size);
        simpleList.remove(item);
        String last = simpleList.getLast();
        String first = simpleList.getFirst();
        System.out.println(first);
        simpleList.setFirst("newFirst");
        String newFirst = simpleList.getFirst();
        System.out.println(newFirst);
        System.out.println(last);
        System.out.println(simpleList.size());
        simpleList.remove("test3");
        System.out.println(simpleList.getLast());
        simpleList.showLinkedlistValues();
    }
}
