package sprint;

import java.util.Iterator;
import java.util.List;

public class Admin extends User{
	public Admin(String id, String name) {
		super(id,name);
	}
	
	@Override
	public void showProfile() {
		System.out.println("ID: "+id+ ", name: "+name);
	}
	
	public void remoteEvent(List<Event>events,String title) {
		Iterator<Event> iterator=events.iterator();
		
		while(iterator.hasNext()) {
			if(iterator.next().getTitle().equalsIgnoreCase(title)) {
				iterator.remove();
				return;
			}
		}
		System.out.println("Event not found!");
	}
}
