package part2;

import java.util.Objects;

public class Book implements Comparable<Book> {
    private String bookName;
    private int yearEdition;
    private String author;
    private int amountPages;

    Book(String bookName, String author, int yearEdition, int amountPages) {
        this.amountPages = amountPages;
        this.bookName = bookName;
        this.author = author;
        this.yearEdition = yearEdition;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearEdition() {
        return yearEdition;
    }

    public int getAmountPages() {
        return amountPages;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Book other = (Book) obj;
        return Objects.equals(getBookName(), other.getBookName()) && Objects.equals(getAuthor(), other.getAuthor())
                && Objects.equals(getAmountPages(), other.getAmountPages())
                && Objects.equals(getYearEdition(), other.getYearEdition());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getBookName().hashCode()) + (getAuthor().hashCode());
        return result;
    }

    @Override
    public int compareTo(Book book) {
        return this.getAmountPages() - book.getAmountPages();
    }

    @Override
    public String toString() {
        return "{ bookName : " + bookName + ", author : " + author + ", amountPages : " + amountPages
                + ", yearEdition : " + yearEdition + "}\n";
    }
}
