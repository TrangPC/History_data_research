package main.subject.event;

import java.util.ArrayList;
import java.util.List;

import main.subject.Subject;
import main.subject.place.Place;

public class Event extends Subject{
	protected Place place;
	protected String content;
	protected int yearStart;
	protected int yearEnd;
	
	public Event(String resource, String name, Place place, String content, int yearStart, int yearEnd) {
		super(name,resource);
		this.place = place;
		this.content = content;
		this.yearStart = yearStart;
		this.yearEnd = yearEnd;
	}
	
	@Override
	public List<String> getAllKeyNames(){
		List<String>  keyNames = new ArrayList<String>();
		keyNames.add("Tên sự kiện");
		keyNames.add("Địa điểm");
		keyNames.add("Năm bắt đầu");
		keyNames.add("Năm kết thúc");
		keyNames.add("Nội dung");
		return keyNames;
	}
	@Override
	public List<String> getAllValues(){
		List<String>  valueNames = new ArrayList<String>();
		valueNames.add(name);
		valueNames.add(place.getName());
		valueNames.add(Integer.toString(yearStart));
		valueNames.add(Integer.toString(yearEnd));
		valueNames.add(content==null?"":content);
		return valueNames;
	}
}
