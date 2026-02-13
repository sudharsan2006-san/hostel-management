import java.util.Scanner;

abstract class Person {
    protected String name;
    protected int id;

    public Person(String name, int id) {  
        this.name = name;
        this.id = id;
    }

    public abstract void showInfo();  
}

class Student extends Person {

    public Student(String name, int id) {
        super(name, id);
    }

    @Override
    public void showInfo() {   
        System.out.println("Student Name: " + name + ", ID: " + id);
    }
}

class Room {
    private int roomNumber;
    private boolean occupied;
    private Student student;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.occupied = false;
    }

    public boolean allocate(Student s) {
        if (!occupied) {
            student = s;
            occupied = true;
            return true;
        }
        return false;
    }

    public void showRoom() {
        if (occupied) {
            System.out.print("Room " + roomNumber + " /");
            student.showInfo();   // Polymorphism in action
        } else {
            System.out.println("Room " + roomNumber + " is EMPTY");
        }
    }
}

class Hostel {
    private Room[] rooms = {
        new Room(101),
        new Room(102),
        new Room(103)
    };

    public void allocateRoom(Student s) {
        for (Room r : rooms) {
            if (r.allocate(s)) {
                System.out.println("Room allocated successfully!");
                return;
            }
        }
        System.out.println("No rooms available!");
    }

    public void showAllRooms() {
        for (Room r : rooms) {
            r.showRoom();
        }
    }
}

public class HostelApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Hostel hostel = new Hostel();

        while (true) {
            System.out.println("\n===== HOSTEL MANAGEMENT MENU =====");
            System.out.println("1. Allocate Room");
            System.out.println("2. Show All Rooms");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();

                    Student s = new Student(name, id);
                    hostel.allocateRoom(s);
                    break;

                case 2:
                    hostel.showAllRooms();
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

}
