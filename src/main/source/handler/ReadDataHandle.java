package main.source.handler;

import main.subject.dynasty.Dynasty;
import main.subject.person.*;
import main.subject.place.*;
import main.subject.event.*;
import main.subject.festival.Festival;
import main.source.crawler.Constant;
import main.source.crawler.LoadCache;
import org.apache.jena.base.Sys;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class ReadDataHandle {
	public void loadConf() throws Exception {
		File file = new File("/Users/hh/Documents/CODE/TEST/oops_project/conf/properties.conf");
		Properties properties = new Properties();
		FileInputStream confFile = new FileInputStream(file.getAbsoluteFile());
		properties.load(new InputStreamReader(confFile,"UTF-8"));
		Constant.lyKing = properties.getProperty("Ly_King");
		Constant.lyDynasty = properties.getProperty("Ly_dynasty");
		Constant.fileSaveDir = properties.getProperty("fileSaveDir");
		Constant.location = properties.getProperty("location");
		Constant.split = properties.getProperty("split");
	}
	public void LoadDynastyObject(String filename){
		JSONParser parser = new JSONParser();
		System.out.println(filename);
		try(FileReader reader = new FileReader(filename))
		{
			Object obj = parser.parse(reader);
			JSONArray array = (JSONArray) obj;
			array.forEach(dynastyObject -> parseDynastyObject((JSONObject) dynastyObject));
		}catch (Exception e){

		}
	}
	public void parseDynastyObject(JSONObject dynastyObject){
		String resourceLink = (String) dynastyObject.get("dynasty");
		String name = (String) dynastyObject.get("callret-1");
		String yearStart = (String) dynastyObject.get("callret-2");
		String yearEnd = (String) dynastyObject.get("callret-3");
		String capital = (String) dynastyObject.get("callret-4");
		String countryName = (String) dynastyObject.get("callret-5");
		Dynasty dynasty = new Dynasty(resourceLink,name,yearStart,yearEnd,countryName);
		LoadCache.listDynasty.add(dynasty);
		System.out.println(resourceLink+name+yearEnd+yearStart+capital+countryName);
		System.out.println(LoadCache.listDynasty.size());
	}

	public void LoadKingObject(String filename) throws IOException {
		JSONParser parser = new JSONParser();
		System.out.println(filename);
		try(FileReader reader = new FileReader(filename))
		{
			Object obj = parser.parse(reader);
			JSONArray array = (JSONArray) obj;
			array.forEach(kingObject -> parseKingObject((JSONObject) kingObject));
		}catch (Exception e){

		}
	}
	public void parseKingObject(JSONObject kingObject){
		String resourceLink = (String) kingObject.get("Concept");
		System.out.println(resourceLink);
		Boolean bool = false;
		for(King king : LoadCache.listKing){
			if(king.getResourceLink().equals(resourceLink)) {
				System.out.println("abcd");
				bool = true;
				king.setName((String)kingObject.get("callret-1"));
				king.setBirthDate((String)kingObject.get("callret-2"));
				king.setDeathDate((String)kingObject.get("callret-3"));
				king.setKingName((String)kingObject.get("callret-4"));
				king.setStartYear((String)kingObject.get("callret-5"));
				king.setEndYear((String)kingObject.get("callret-6"));
				for(Dynasty dynasty : LoadCache.listDynasty){
					if(dynasty.getResourceLink().equals((String)kingObject.get("callret-7"))) {
						king.setDynasty(dynasty);
					}
				}
				int kt1=0, kt2=0;
				String predessor = (String)kingObject.get("callret-8");
				String successor = (String)kingObject.get("callret-9");
				for(King king1 : LoadCache.listKing){
					if(king1.getResourceLink().equals(predessor)){
						king.setPredecessor(king1);
						kt1=1;
					}
					if(king1.getResourceLink().equals(successor)){
						king.setSuccessor(king1);
						kt2=1;
					}
				}
				if(kt1==0){
					King king1 = new King(predessor,null,null,null,null,null,null);

					LoadCache.listKing.add(king1);
					king.setPredecessor(king1);
				}
				if(kt2==0){
					King king1 = new King(successor,null,null,null,null,null,null);
					LoadCache.listKing.add(king1);
					king.setSuccessor(king1);
				}
			}

		}
		if(bool.equals(false)){
			King king = new King(resourceLink,(String)kingObject.get("callret-4"),(String)kingObject.get("callret-5"),(String)kingObject.get("callret-6"),(String)kingObject.get("callret-1"),(String)kingObject.get("callret-2"),(String)kingObject.get("callret-3"));
			for(Dynasty dynasty : LoadCache.listDynasty){
				if(dynasty.getResourceLink().equals((String)kingObject.get("callret-7"))) {
					king.setDynasty(dynasty);
				}
			}
			int kt1=0, kt2=0;
			String predessor = (String)kingObject.get("callret-8");
			String successor = (String)kingObject.get("callret-9");
			for(King king1 : LoadCache.listKing){
				if(king1.getResourceLink().equals(predessor)){
					king.setPredecessor(king1);
					kt1=1;
				}
				if(king1.getResourceLink().equals(successor)){
					king.setSuccessor(king1);
					kt2=1;
				}
			}
			if(kt1==0){
				King king1 = new King(predessor,null,null,null,null,null,null);
				LoadCache.listKing.add(king1);
				king.setPredecessor(king1);
			}
			if(kt2==0){
				King king1 = new King(successor,null,null,null,null,null,null);
				LoadCache.listKing.add(king1);
				king.setSuccessor(king1);
			}
			LoadCache.listKing.add(king);
		}

	}
	public static void main(String[] args) throws Exception {
		String configFileDir = "/Users/hh/Documents/CODE/TEST/oops_project/conf/properties.conf";
		Constant.configFileDir = configFileDir;
		CrawlDataHandle crawlDataHandle = new CrawlDataHandle();
		crawlDataHandle.loadConf();
		ReadDataHandle readDataHandle = new ReadDataHandle();
		readDataHandle.LoadDynastyObject(Constant.fileSaveDir+Constant.lyDynastyFileName);
		readDataHandle.LoadDynastyObject(Constant.fileSaveDir+Constant.tranDynastyFileName);
		readDataHandle.LoadDynastyObject(Constant.fileSaveDir+Constant.leDynastyFileName);
		readDataHandle.LoadKingObject(Constant.fileSaveDir+Constant.lyKingFileName);
		System.out.println(LoadCache.listKing.size());
		System.out.println(LoadCache.listDynasty.size());

        /*ing king = LoadCache.listKing.get(0);
        System.out.println(king.getKingName());
        System.out.println(king.getName());*/
	}
}

