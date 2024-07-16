package main.subject.place;

import java.util.ArrayList;
import java.util.List;

import main.subject.Subject;

public class Place extends Subject{
	public Place(String name) {
		super(name);
	}
	@Override
	public List<String> getAllKeyNames(){
		List<String>  keyNames = new ArrayList<String>();
		keyNames.add("Tên địa danh");
		return keyNames;
	}
	@Override
	public List<String> getAllValues(){
		List<String>  valueNames = new ArrayList<String>();
		valueNames.add(name);
		return valueNames;
	}
	
}
