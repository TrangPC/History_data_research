package main.subject.place;

import java.util.ArrayList;
import java.util.List;

public class Capital extends Place {
	private int startYear;
	private int endYear;

	public Capital(String name) {
		super(name);
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	
	@Override
	public List<String> getAllKeyNames(){
		List<String>  keyNames = new ArrayList<String>();
		keyNames.add("Năm bắt đầu");
		keyNames.add("Năm kết thúc");
		return keyNames;
	}
	@Override
	public List<String> getAllValues(){
		List<String>  valueNames = new ArrayList<String>();
		valueNames.add(name);
		valueNames.add(Integer.toString(startYear));
		valueNames.add(Integer.toString(endYear));
		return valueNames;
	}
}
