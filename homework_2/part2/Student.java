package part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student implements Comparable<Student> {
    private String name;
    private List<Book> books = new ArrayList<>();

    Student(String name) {
        this.name = name;
    }

    public Student addBook(String bookName, String author, int yearEdition, int amountPages) {
        books.add(new Book(bookName, author, yearEdition, amountPages));
        return this;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "{" + " name : " + name + " }";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getName().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Student student = (Student) obj;
        return Objects.equals(getName(), student.getName());
    }

    @Override
    public int compareTo(Student student) {
        return name.compareToIgnoreCase(student.getName());
    }
}
