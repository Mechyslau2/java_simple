package part2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentCollection {
    private ArrayList<Student> studentsCollection = new ArrayList<>();

    public StudentCollection add(Student student) {
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

    public List<Book> getBooksByStudentName(String name) {
        return studentsCollection.stream().filter((student) -> student.getName().equals(name))
                .flatMap((student) -> ((Student) student).getBooks().stream())
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return studentsCollection.stream().flatMap((student) -> student.getBooks().stream())
                .collect(Collectors.toList());
    }

    public List<Book> getUniqueStudentBook(String stundetName) {
        return studentsCollection.stream()
                .filter((student) -> student.getName().equals(stundetName))
                .flatMap((student) -> ((Student) student).getBooks().stream().distinct())
                .collect(Collectors.toList());
    }

    public List<Book> getAllUniqueBooks() {
        return studentsCollection.stream()
                .flatMap((student) -> student.getBooks().stream().distinct())
                .collect(Collectors.toList());
    }

    public List<Book> getSortedBooksByStudentName(String name) {
        return studentsCollection.stream().filter((student) -> student.getName().equals(name))
                .flatMap((student) -> student.getBooks().stream().sorted())
                .collect(Collectors.toList());
    }

    public List<Book> filterStudentBooksByYearEdition(String studentName, int higerThenYear) {
        return studentsCollection.stream()
                .filter((student) -> student.getName().equals(studentName))
                .flatMap((student) -> student.getBooks().stream()
                        .filter((book) -> book.getYearEdition() > higerThenYear))
                .collect(Collectors.toList());
    }

    public List<Book> getLimitStudentBooks(String studentName, int limitBook) {
        return studentsCollection.stream()
                .filter((student) -> student.getName().equals(studentName))
                .flatMap((student) -> student.getBooks().stream().limit(limitBook))
                .collect(Collectors.toList());
    }

    public List<Integer> getAllBooksEdition() {
        return studentsCollection.stream()
                .flatMap((student) -> student.getBooks().stream().distinct())
                .map((book) -> book.getYearEdition()).collect(Collectors.toList());
    }

    public void showYearOfBookByName(String bookName) {
        studentsCollection.stream()
                .flatMap((student) -> student.getBooks().stream().distinct()
                        .filter((book) -> book.getBookName().equals(bookName)))
                .map((book) -> ((Book) book).getYearEdition()).distinct().findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println("not found"));
    }

    public int getYearOfBookByName(String name) {
        return studentsCollection.stream()
                .flatMap((student) -> student.getBooks().stream().distinct()
                        .filter((book) -> book.getBookName().equals(name)))
                .map((book) -> book.getYearEdition()).distinct().findFirst().orElse(-1);
    }
}
