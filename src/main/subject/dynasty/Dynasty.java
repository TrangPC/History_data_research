package main.subject.dynasty;

import java.util.ArrayList;
import java.util.List;

import main.subject.Subject;
import main.subject.place.Capital;

public class Dynasty extends Subject{
	private String yearStart;
	private String yearEnd;
	private String capital;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	private String countryName;

	public Dynasty(String resource, String name, String yearStart, String yearEnd, String countryName) {
		super(name,resource);
		this.yearStart = yearStart;
		this.yearEnd = yearEnd;
		this.capital = capital;
		this.countryName = countryName;
	}

	public String getYearStart() {
		return yearStart;
	}

	
	public String getYearEnd() {
		return yearEnd;
	}

	

	public String getCapital() {
		return capital;
	}

	
	@Override
	public List<String> getAllKeyNames(){
		List<String>  keyNames = new ArrayList<String>();
		keyNames.add("Tên triều đại");
		keyNames.add("Năm bắt đầu");
		keyNames.add("Năm kết thúc");
		keyNames.add("Thủ đô");
		keyNames.add("Tên nước");
		return keyNames;
	}
	@Override
	public List<String> getAllValues(){
		List<String>  valueNames = new ArrayList<String>();
		valueNames.add(name);
		valueNames.add(yearStart);
		valueNames.add(yearEnd);
		valueNames.add(capital==null?"":capital);
		return valueNames;
	}
}
