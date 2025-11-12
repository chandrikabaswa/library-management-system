package p1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Book> library = new ArrayList<>();

        // Add at least two initial books
        library.add(new Book("Java Programming", "Alice"));
        library.add(new Book("Python Basics", "Bob"));

        // Allow user to add more books
        String choice;
        do {
            System.out.print("Do you want to add another book? (yes/no): ");
            choice = sc.nextLine().trim().toLowerCase();

            if (choice.equals("yes")) {
                System.out.print("Enter book title: ");
                String title = sc.nextLine();
                System.out.print("Enter author name: ");
                String author = sc.nextLine();
                library.add(new Book(title, author));
                System.out.println("Book added successfully.");
            }
        } while (choice.equals("yes"));

        int option;
        do {
            // Menu
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Issue a book");
            System.out.println("2. Return a book");
            System.out.println("3. Display book details");
            System.out.println("4. View total issued books");
            System.out.println("5. Search book by ID");
            System.out.println("6. List all available books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter Book ID to issue: ");
                    int issueID = sc.nextInt();
                    boolean foundIssue = false;
                    for (Book book : library) {
                        if (book.getBookID() == issueID) {
                            book.issueBook();
                            foundIssue = true;
                            break;
                        }
                    }
                    if (!foundIssue) System.out.println("Book not found.");
                    break;

                case 2:
                    System.out.print("Enter Book ID to return: ");
                    int returnID = sc.nextInt();
                    boolean foundReturn = false;
                    for (Book book : library) {
                        if (book.getBookID() == returnID) {
                            book.returnBook();
                            foundReturn = true;
                            break;
                        }
                    }
                    if (!foundReturn) System.out.println("Book not found.");
                    break;

                case 3:
                    System.out.print("Enter Book ID to display: ");
                    int displayID = sc.nextInt();
                    boolean foundDisplay = false;
                    for (Book book : library) {
                        if (book.getBookID() == displayID) {
                            book.displayBookInfo();
                            foundDisplay = true;
                            break;
                        }
                    }
                    if (!foundDisplay) System.out.println("Book not found.");
                    break;

                case 4:
                    Book.displayTotalIssued();
                    break;

                case 5:
                    System.out.print("Enter Book ID to search: ");
                    int searchID = sc.nextInt();
                    boolean foundSearch = false;
                    for (Book book : library) {
                        if (book.getBookID() == searchID) {
                            System.out.println("Book found!");
                            book.displayBookInfo();
                            foundSearch = true;
                            break;
                        }
                    }
                    if (!foundSearch) System.out.println("Book not found.");
                    break;

                case 6:
                    System.out.println("Available books:");
                    boolean availableFound = false;
                    for (Book book : library) {
                        if (book.isAvailable()) {
                            book.displayBookInfo();
                            availableFound = true;
                        }
                    }
                    if (!availableFound) {
                        System.out.println("No books currently available.");
                    }
                    break;

                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (option != 7);

        sc.close();
    }
}

class Book{

    public static int nextID = 1000;

    int  bookID;
    String title;
    String author;
    boolean isIssued;

    public static int totalIssued = 0;

    Book(String title, String author){
        this.title = title;
        this.author = author;
        this.bookID = nextID++;
        this.isIssued = false;
    }

    void issueBook(){
        if (!isIssued) {
            isIssued = true;
            totalIssued++;
            System.out.println("Book issued: " + title);
            System.out.println("Book issued successfully");
        }
        else {
            System.out.println("Book already issued: " + title);
        }
    }

    void returnBook() {
        if (isIssued) {
            isIssued = false;
            totalIssued--;
            System.out.println("Book returned: " + title);
        }
        else {
            System.out.println("Book was not issued: " + title);
        }
    }

    void displayBookInfo() {
        System.out.println("**** Book Information ****");
        System.out.println("Book ID   : " + bookID);
        System.out.println("Title     : " + title);
        System.out.println("Author    : " + author);
        System.out.println("Status    : " + (isIssued ? "Issued" : "Available"));
        System.out.println("***********************");
    }

    // Displays total number of issued books
    public static void displayTotalIssued() {
        System.out.println("Total books currently issued: " + totalIssued);
    }

    public int getBookID() {
        return bookID;
    }

    public boolean isAvailable() {
        return !isIssued;
    }
}
