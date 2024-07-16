package main.subject.person;

import java.util.ArrayList;
import java.util.List;

import main.subject.dynasty.Dynasty;
import main.subject.event.Event;

public class FamousPeople extends People {
	private Event event;
	private Dynasty dynasty;


	public FamousPeople(String resource, String name, String dob, String dod) {
		super(resource,name, dob, dod);
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Dynasty getDynasty() {
		return dynasty;
	}

	public void setDynasty(Dynasty dynasty) {
		this.dynasty = dynasty;
	}
	
	@Override
	public List<String> getAllKeyNames(){
		List<String>  keyNames = super.getAllKeyNames();
		keyNames.add("Sự kiện liên quan");
		keyNames.add("Triều đại sống");
		return keyNames;
	}
	@Override
	public List<String> getAllValues(){
		List<String>  valueNames = super.getAllValues();
		valueNames.add(event.getName());
		valueNames.add(dynasty.getName());
		return valueNames;
	}
}
