package main.subject.person;

import java.util.ArrayList;
import java.util.List;

import main.subject.Subject;

public class People extends Subject{
	protected String birthDate;
	protected String deathDate;

	public People(String resourceLink, String name, String dob, String dod) {
		super(name,resourceLink);
		this.birthDate = dob;
		this.deathDate = dod;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}

	public String getBirthDate() {
		return birthDate;
	}

	

	public String getDeathDate() {
		return deathDate;
	}

	
	
	@Override
	public List<String> getAllKeyNames(){
		List<String>  keyNames = new ArrayList<String>();
		keyNames.add("Tên");
		keyNames.add("Năm sinh");
		keyNames.add("Năm mất");
		return keyNames;
	}
	@Override
	public List<String> getAllValues(){
		List<String>  valueNames = new ArrayList<String>();
		valueNames.add(name);
		valueNames.add(birthDate);
		valueNames.add(deathDate);
		return valueNames;
	}
}
