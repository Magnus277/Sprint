package sprint;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EventBookingSystem {
    List<Attendee> attendees = new ArrayList<>();
    List<Organizer> organizers = new ArrayList<>();
    List<Event> events = new ArrayList<>();
    List<Ticket> tickets = new ArrayList<>();

    public void registerUser(User user) {
        if (user instanceof Attendee) {
            attendees.add((Attendee) user);
        } else if (user instanceof Organizer) {
            organizers.add((Organizer) user);
        }
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void bookTicket(Attendee attendee, String eventTitle) throws invalidBookingException {
        for (Event event : events) {
            if (event.getTitle().equalsIgnoreCase(eventTitle) && event.isAvailable()) {
                event.setAvailableTickets(event.getAvailableTickets() - 1);
                Ticket ticket = new Ticket(attendee, event);
                tickets.add(ticket);
                System.out.println("Ticket booked successfully.");
                return;
            }
        }
        throw new invalidBookingException("Booking failed. Event not found or no tickets available.");
    }

    public void showEvents() {
        for (Event event : events) {
            System.out.println(event);
        }
    }

    public void saveEvents() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("events.ser"))) {
            out.writeObject(events);
            System.out.println("Events saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadEvents() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("events.ser"))) {
            events = (List<Event>) in.readObject();
            System.out.println("Events loaded.");
        } catch (Exception e) {
            System.out.println("No saved events found.");
        }
    }
}

