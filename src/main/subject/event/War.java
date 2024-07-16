package main.subject.event;

import java.util.List;

import main.subject.person.People;
import main.subject.place.Place;

public class War extends Event {
	public War(String resource, String name, Place place, String content, int yearStart, int yearEnd) {
		super(resource,name, place, content, yearStart, yearEnd);
	}
	
	private People commander;

	public People getCommander() {
		return commander;
	}

	public void setCommander(People commander) {
		this.commander = commander;
	}

	
	@Override
	public List<String> getAllKeyNames(){
		List<String>  keyNames = super.getAllKeyNames();
		keyNames.add("Chỉ huy");
		return keyNames;
	}
	@Override
	public List<String> getAllValues(){
		List<String>  valueNames = super.getAllValues();
		valueNames.add(commander.getName());
		return valueNames;
	}
}
