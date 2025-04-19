package part2;

public class Demo {
        public static void main(String[] args) {
                Student student1 = new Student("student1");
                Student student2 = new Student("student2");
                Student student3 = new Student("student3");

                student1.addBook("book 1", " author 1", 1988, 547)
                                .addBook("book 2", "author 2", 2005, 1025)
                                .addBook("book 3", "author 3", 2001, 577)
                                .addBook("book 4", "author 4", 1999, 460)
                                .addBook("book 3", "author 3", 2001, 577)
                                .addBook("book 4", "author 4", 1999, 460)
                                .addBook("book 5", "author 5", 2003, 466);

                student2.addBook("book 1", " author 1", 1988, 547)
                                .addBook("book 2", "author 2", 2005, 1025)
                                .addBook("book 10", "author 10", 2000, 477)
                                .addBook("book 3", "author 3", 2001, 577)
                                .addBook("book 4", "author 4", 1999, 460)
                                .addBook("book 4", "author 4", 1999, 460)
                                .addBook("book 15", "author 15", 2000, 466);

                student3.addBook("book 11", " author 11", 1987, 347)
                                .addBook("book 22", "author 2", 2015, 1325)
                                .addBook("book 3", "author 3", 2001, 577)
                                .addBook("book 44", "author 44", 2001, 460)
                                .addBook("book 5", "author 5", 2003, 466);

                StudentCollection studentsApi = new StudentCollection();

                studentsApi.add(student1).add(student2).add(student3);
                System.out.println("All students : ");
                studentsApi.showStudents();
                System.out.println("books of the student2 : \n"
                                + studentsApi.getBooksByStudentName("student2"));
                System.out.println("-----------");
                System.out.println("Sorted books of the student2 : \n"
                                + studentsApi.getSortedBooksByStudentName("student2"));
                System.out.println("-----------");
                System.out.println("Filtred books of the student2 (higer then 2000 year) : \n"
                                + studentsApi.filterStudentBooksByYearEdition("student2", 2000));
                System.out.println("-----------");
                System.out.println("Unique books of the student2 : \n"
                                + studentsApi.getUniqueStudentBook("student2"));
                System.out.println("-----------");
                System.out.println("limit books of the student2 (limit 3): \n"
                                + studentsApi.getLimitStudentBooks("student2", 3));
                System.out.println("-----------");
                System.out.println("All books : \n" + studentsApi.getAllBooks());
                System.out.println("-----------");
                System.out.println(
                                "Show year of the 'book 1' (Optional) if found - 'year' else  'not found' : ");
                studentsApi.showYearOfBookByName("book 1");
                System.out.println(
                                "Get the year of the 'book 1' (Optional) if found 'year' else -1 : \n"
                                                + studentsApi.getYearOfBookByName("book 1"));
                System.out.println("-----------");
                System.out.println("All unique books : \n" + studentsApi.getAllUniqueBooks());
                System.out.println("-----------");
                System.out.println("All year edition of books : \n"
                                + studentsApi.getAllBooksEdition());
                System.out.println("-----------");

        }
}
