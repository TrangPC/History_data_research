package main.subject.festival;

import java.util.ArrayList;
import java.util.List;

import main.subject.Subject;

public class Festival extends Subject{
	private String content;
	
	public Festival(String resource, String name, String content) {
		super(name,resource);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public List<String> getAllKeyNames(){
		List<String>  keyNames = new ArrayList<String>();
		keyNames.add("Tên lễ hội");
		keyNames.add("Nội dung");
		return keyNames;
	}
	@Override
	public List<String> getAllValues(){
		List<String>  valueNames = new ArrayList<String>();
		valueNames.add(name);
		valueNames.add(content);
		return valueNames;
	}
}
