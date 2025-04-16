package part1;

public class Demo {
    public static void main(String[] args) {
        SimpleHashSet<Integer> map = new SimpleHashSet<>();
        SimpleLinkedList<String> simpleList = new SimpleLinkedList<>();

        map.add(2);
        map.add(1);
        boolean isExistsItem = map.contains(3);
        System.out.println("map contains 3 : " + isExistsItem);
        isExistsItem = map.contains(2);
        System.out.println("map contains 2 : " + isExistsItem);
        map.remove(2);
        isExistsItem = map.contains(2);
        System.out.println("map contains 2 : " + isExistsItem);
        map.add(2);
        map.add(2);
        System.out.println("size : " + map.size());
        map.showItems();

        System.out.println("\nSimpleLinkedList demo : ");
        simpleList.add("1");
        simpleList.add("2");
        simpleList.add("3");
        simpleList.add("4");
        simpleList.add("5");
        simpleList.add("6");
        simpleList.add("7");
        simpleList.addAll("test1", "test2", "test3");
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
        simpleList.showLinkedlistValues();
    }
}
