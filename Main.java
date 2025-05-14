package sprint;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EventBookingSystem system = new EventBookingSystem();
        system.loadEvents();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register Attendee\n2. Add Event\n3. Book Ticket\n4. Show Events\n5. Save & Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        String id = sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        system.registerUser(new Attendee(id, name));
                        break;

                    case 2:
                        System.out.print("Enter Event Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Available Tickets: ");
                        int tickets = sc.nextInt();
                        system.addEvent(new Event(title, tickets));
                        break;

                    case 3:
                        System.out.print("Enter Attendee ID: ");
                        String aid = sc.nextLine();
                        Attendee attendee = system.attendees.stream()
                            .filter(a -> a.id.equals(aid)).findFirst().orElse(null);

                        if (attendee == null) {
                            System.out.println("Attendee not found.");
                            break;
                        }

                        System.out.print("Enter Event Title: ");
                        String eventName = sc.nextLine();
                        system.bookTicket(attendee, eventName);
                        break;

                    case 4:
                        system.showEvents();
                        break;

                    case 5:
                        system.saveEvents();
                        System.exit(0);

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (invalidBookingException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
