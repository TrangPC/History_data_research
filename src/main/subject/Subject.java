package main.subject;

import java.util.List;

import main.subject.Subject;

public class Subject {
	protected String name;
	protected String resourceLink;

	public String getResourceLink() {
		return resourceLink;
	}

	public void setResourceLink(String resourceLink) {
		this.resourceLink = resourceLink;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	


	public Subject(String resourceLink) {
		this.resourceLink = resourceLink;
	}

	public Subject(String name, String resourceLink) {
		this.name = name;
		this.resourceLink = resourceLink;
	}

	@Override
	public String toString() {
        return this.name;
    }

	public List<String> getAllKeyNames() {
		return null;
	}
	public List<String> getAllValues(){
		return null;
	}
}
