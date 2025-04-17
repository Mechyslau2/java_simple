package part2;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StudentCollection<V> {
    private ArrayList<V> studentsCollection = new ArrayList<>();

    public StudentCollection add(V student) {
        studentsCollection.add(student);
        return this;
    }

    public void showStudents() {
        if (studentsCollection.isEmpty()) {
            System.out.println("Student collection is empty");
        } else {
            studentsCollection.stream().forEach(System.out::println);
        }
    }

    public ArrayList<Book> getBooksByStudentName(String name) {
        return (ArrayList<Book>) studentsCollection.stream().filter((s) -> name == ((Student) s).getName())
                .flatMap((s) -> ((Student) s).getBooks().stream()).collect(Collectors.toList());
    }

    public ArrayList<Book> getAllBooks() {
        return (ArrayList<Book>) studentsCollection.stream().flatMap((s) -> ((Student) s).getBooks().stream())
                .collect(Collectors.toList());
    }

    public ArrayList<Book> getUniqueStudentBook(String stundetName) {
        return (ArrayList<Book>) studentsCollection.stream().filter((s) -> stundetName == ((Student) s).getName())
                .flatMap((s) -> ((Student) s).getBooks().stream().distinct()).collect(Collectors.toList());
    }

    public ArrayList<Book> getOnlyUniqueBooks() {
        return (ArrayList<Book>) studentsCollection.stream()
                .flatMap((s) -> ((Student) s).getBooks().stream().distinct()).collect(Collectors.toList());
    }

    public ArrayList<Book> getAllUniqueBooks() {
        return (ArrayList<Book>) studentsCollection.stream()
                .flatMap((s) -> ((Student) s).getBooks().stream().distinct()).collect(Collectors.toList());
    }

    public ArrayList<Book> getSortedBooksByStudentName(String name) {
        return (ArrayList<Book>) studentsCollection.stream().filter((s) -> name == ((Student) s).getName())
                .flatMap((s) -> ((Student) s).getBooks().stream().sorted()).collect(Collectors.toList());
    }

    public ArrayList<Book> filterStudentBooksByYearEdition(String studentName, int higerThenYear) {
        return (ArrayList<Book>) studentsCollection.stream().filter((s) -> studentName == ((Student) s).getName())
                .flatMap((s) -> ((Student) s).getBooks().stream()
                        .filter((b) -> ((Book) b).getYearEdition() > higerThenYear))
                .collect(Collectors.toList());
    }

    public ArrayList<Book> getLimitStudentBooks(String studentName, int limitBook) {
        return (ArrayList<Book>) studentsCollection.stream().filter((s) -> studentName == ((Student) s).getName())
                .flatMap((s) -> ((Student) s).getBooks().stream().limit(limitBook)).collect(Collectors.toList());
    }

    public ArrayList<Integer> getAllBooksEdition() {
        return (ArrayList<Integer>) studentsCollection.stream()
                .flatMap((s) -> ((Student) s).getBooks().stream().distinct()).map((b) -> ((Book) b).getYearEdition())
                .collect(Collectors.toList());
    }

    public void showYearOfBookByName(String name) {
        studentsCollection.stream()
                .flatMap((s) -> ((Student) s).getBooks().stream().distinct()
                        .filter((b) -> ((Book) b).getBookName().toLowerCase() == name.toLowerCase()))
                .map((b) -> ((Book) b).getYearEdition()).distinct().findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("not found"));
    }

    public int getYearOfBookByName(String name) {
        return studentsCollection.stream()
                .flatMap((s) -> ((Student) s).getBooks().stream().distinct()
                        .filter((b) -> ((Book) b).getBookName().toLowerCase() == name.toLowerCase()))
                .map((b) -> ((Book) b).getYearEdition()).distinct().findFirst().orElse(-1);
    }
}
