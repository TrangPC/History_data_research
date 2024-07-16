package main.subject.place;

import java.util.ArrayList;
import java.util.List;

public class River extends Place {
	public River(String name) {
		super(name);
	}
	
	@Override
	public List<String> getAllKeyNames(){
		List<String>  keyNames = new ArrayList<String>();
		keyNames.add("Tên sông");
		return keyNames;
	}
	@Override
	public List<String> getAllValues(){
		List<String>  valueNames = new ArrayList<String>();
		valueNames.add(name);
		return valueNames;
	}
}
