package day1_13May;



class Event  {
    private String title;
    private int availableTickets;

    public Event(String title, int availableTickets) {
        this.title = title;
        this.availableTickets = availableTickets;
    }

    public boolean isAvailable() {
        return availableTickets > 0;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    @Override
    public String toString() {
        return "Event: " + title + ", Tickets Left: " + availableTickets;
    }
}