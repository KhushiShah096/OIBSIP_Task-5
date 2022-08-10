// Khushi Shah
// Task 5: Digital library management system 

import java.util.ArrayList;
import java.util.Scanner;

class Library {
    ArrayList<Book> books; // ARRAYLIST OF "Book" TYPE PRIVATE TO "LIBRARY" CLASS. HERE "book" IS OBJECT OF
                           // "BooK" TYPE ARRAYLIST.
    int ID = 12345, password = 12345;

    void setIDPassword(int ID, int pass) {
        this.ID = ID;
        this.password = pass;
    }

    int getId() {
        return ID;
    }

    int getPassword() {
        return password;
    }

    Library(ArrayList<Book> books) {
        this.books = books;
    }

    void addBook(Book book) {
        this.books.add(book);
        book.bookAvailableStatus = true;
        System.out.println(book.book_name + " is added to library.." + "\nBook no.: " + book.book_no);
    }

    void issueBook(Book book, String issuedTo, String issuedDate) {
        this.books.remove(book);
        book.setIssuedTo(issuedTo);
        book.setIssuedDate(issuedDate);
        book.bookAvailableStatus = false;
        System.out.println(book.book_name + "is issued to " + book.issued_to + "\nBook no.: " + book.book_no);
    }

    void returnBook(Book book, String returnedDate) {
        this.books.add(book);
        book.setReturnedDate(returnedDate);
        book.bookAvailableStatus = true;
        System.out.println(book.book_name + "is returned by " + book.issued_to + "\nBook no.: " + book.book_no);
    }

}

class Book {
    String book_name, author_name, issued_to = "Not issued", issued_date = "Not issued", returned_date = "Not Returned";
    int book_no;
    public Boolean bookAvailableStatus = true;

    Book(String bookName, String authorName, int bookNo) {
        this.book_name = bookName;
        this.author_name = authorName;
        this.book_no = bookNo;
        bookAvailableStatus = true;
    }

    public void setIssuedDate(String issuedDate) {
        this.issued_date = issuedDate;
    }

    public void setIssuedTo(String issuedTo) {
        this.issued_to = issuedTo;
    }

    public void setReturnedDate(String returnedDate) {
        if (this.issued_to == "Not issued") {
            this.returned_date = "Not issued";
        } else if (returnedDate == "\0") {
            this.returned_date = "Not Returned";
        } else {
            this.returned_date = returnedDate;
        }
    }

    public Boolean getBookAvailableStatus() {
        return this.bookAvailableStatus;
    }

    @Override
    public String toString() {
        return "Book:\nBook name: " + this.book_name + "\nAuthor name: " + this.author_name + "\nBook no.: "
                + this.book_no + "\nIssued to: " + this.issued_to + "\nIssued date: " + this.issued_date
                + "\nReturned date: " + this.returned_date + "\n\n";

    }

}

public class DigitalLibraryManagementSystem {
    public static void main(String[] args) {
        // CREATING BOOKS
        Book book1 = new Book("R.S. Agrawal", "S. Chand", 10 );
        Book book2 = new Book("R.S. Agrawal", "S. Chand", 11 );
        Book book3 = new Book("Fundamentals of C Programming", "E. Balagurusamy", 12 );
        Book book4 = new Book("Python Programming", "Dr. R. Nageshwara Rao", 13 );

        // CREATING ARRAY LIST OF "Book" TYPE i.e. THIS ARRAYLIST CONTAINS ALL BOOKS OF "Book"(CUSTOM CREATED CLASS) TYPE

        ArrayList<Book> bk = new ArrayList<>();
        
        // ADD ALL BOOKS TO ARRAYLIST BK
        bk.add(book1);
        bk.add(book2);
        bk.add(book3);
        bk.add(book4);

        Library l =new Library(bk);

        Boolean status = true;
        String book_name, author_name;
        int book_no, choice;

        Scanner sc = new Scanner(System.in);

        while(status){
            System.out.println("For author press 1 \nFor user press 2\nFor exit press 0");
            choice = sc.nextInt();
            if (choice == 1){
                System.out.println("Enter ID and password..");
                int ID = sc.nextInt();
                int password = sc.nextInt();
                
                if (ID == l.getId() && password == l.getPassword()){
                    while(status){
                        System.out.println("Do you want to enter new book? press 1 for yes");
                        choice = sc.nextInt();
                        if (choice == 1){
                            status = true;
                            System.out.println("Enter book name, author name, book no.");
                            book_name = sc.next();
                            author_name = sc.next();
                            book_no = sc.nextInt();
                            l.addBook(new Book(book_name, author_name, book_no));
                        }
                        else{
                            status = false;
                            continue;
                        }

                        choice = 10;
                        System.out.println("Do you want to see status of library? Press 1 for yes and 0 for no");
                        choice = sc.nextInt();
                        if (choice == 1){
                            System.out.println(l.books+"\n");
                        }
                        else{
                            continue;
                        }
                    }
                }
                status = true;
            }
            else if (choice == 2){
                
                while(status){
                    System.out.println("press 1 to add book, press 2 to issue book, press 3 to return book");
                    int choice_ = sc.nextInt();
                    status = true;

                
                    if (choice_ == 1){
                            status = true;
                            System.out.println("Enter book name, author name, book no.");
                            book_name = sc.next();
                            author_name = sc.next();
                            book_no = sc.nextInt();
                            l.addBook(new Book(book_name, author_name, book_no));
                    }

                    else if (choice_ == 2){
                        System.out.println("Library status\n"+l.books+"\n");
                        l.issueBook(book1, "Jayana", "12/5/22");
                    }
                    else if (choice_ == 3){
                        l.returnBook(book1, "22/6/22");
                        System.out.println("Library status\n"+l.books+"\n");
                    }
            }
        }
        else {
            System.exit(1);
        }

        }
    }
}
