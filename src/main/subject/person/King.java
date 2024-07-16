package main.subject.person;

import java.util.List;

import main.subject.dynasty.Dynasty;
import main.subject.place.Capital;

public class King extends People {
	private String startYear;
	private String endYear;
	private Dynasty dynasty;
	private King predecessor;
	private King successor;

	public String getKingName() {
		return kingName;
	}

	public void setKingName(String kingName) {
		this.kingName = kingName;
	}

	private String kingName;

	public King(String resource,String kingName, String startYear,
				  String endYear,String name, String birthDate, String deathDate) {
		super(resource,name, birthDate, deathDate);
		this.kingName = kingName;
		this.startYear = startYear;
		this.endYear = endYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public void setDynasty(Dynasty capital) {
		this.dynasty = capital;
	}

	public void setPredecessor(King predecessor) {
		this.predecessor = predecessor;
	}

	public void setSuccessor(King successor) {
		this.successor = successor;
	}

	public String getStartYear() {
		return startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public Dynasty getCapital() {
		return dynasty;
	}

	public King getPredecessor() {
		return predecessor;
	}

	public King getSuccessor() {
		return successor;
	}
	
	@Override
	public List<String> getAllKeyNames(){
		List<String>  keyNames = super.getAllKeyNames();
		keyNames.add("Năm lên ngôi");
		keyNames.add("Năm thoái vị");
		keyNames.add("Kinh đô");
		keyNames.add("Tiền nhiệm");
		keyNames.add("Kế vị");
		keyNames.add("Tên tại vị");
		return keyNames;
	}
	@Override
	public List<String> getAllValues(){
		List<String>  valueNames = super.getAllValues();
		valueNames.add(startYear);
		valueNames.add(endYear);
		valueNames.add(dynasty.getName());
		valueNames.add(predecessor.getKingName());
		valueNames.add(successor.getKingName());
		valueNames.add(kingName);
		return valueNames;
	}
}
