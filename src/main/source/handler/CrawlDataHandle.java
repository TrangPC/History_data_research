package main.source.handler;

import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;

import java.io.*;

import main.source.crawler.Constant;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

public class CrawlDataHandle {
	public void loadConf() throws Exception {
		File file = new File(Constant.configFileDir);
		Properties properties = new Properties();
		FileInputStream confFile = new FileInputStream(file.getAbsoluteFile());
		properties.load(new InputStreamReader(confFile,"UTF-8"));
		Constant.lyKing = properties.getProperty("Ly_King");
		Constant.tranKing = properties.getProperty("Tran_King");
		Constant.leKing = properties.getProperty("Le_King");
		Constant.lyDynasty = properties.getProperty("Ly_dynasty");
		Constant.tranDynasty = properties.getProperty("Tran_dynasty");
		Constant.leDynasty = properties.getProperty("Le_dynasty");
		Constant.fileSaveDir = properties.getProperty("fileSaveDir");
		System.out.println(Constant.fileSaveDir);
		Constant.location = properties.getProperty("location");
		Constant.split = properties.getProperty("split");
	}

	public ParameterizedSparqlString createQuery(String query){
		ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
		queryStr.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		queryStr.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		queryStr.setNsPrefix("dbo", "http://dbpedia.org/ontology/");
		queryStr.setNsPrefix("dbr", "http://dbpedia.org/resource/");
		queryStr.setNsPrefix("dbc", "http://dbpedia.org/resource/Category:");
		queryStr.setNsPrefix("dbd", "http://dbpedia.org/datatype/");
		queryStr.setNsPrefix("dbp", "http://dbpedia.org/property/");
		queryStr.setNsPrefix("dbt", "http://dbpedia.org/resource/Template:");
		queryStr.setNsPrefix("dct", "http://purl.org/dc/terms/");
		queryStr.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
		queryStr.setNsPrefix("yago", "http://dbpedia.org/class/yago/");
		queryStr.setNsPrefix("foaf", "http://xmlns.com/foaf/0.1/");
		queryStr.setNsPrefix("xsd","http://www.w3.org/2001/XMLSchema#");
		queryStr.append(query);
		return queryStr;
	}
	public JSONArray ExportToString(ResultSet result){
		//Vector<String> list = new Vector<>();
		List<String> column = result.getResultVars();
		JSONArray jsonArray = new JSONArray();
		//list.add("[\n");
		try {
			while (result.hasNext()){
				JSONObject jsonObject = new JSONObject();
				//StringBuilder stringBuilder = new StringBuilder();
				QuerySolution querySolution = result.nextSolution();
				//stringBuilder.append("{\n\t");
				for(int i=0;i<column.size();i++) {
					//JSONObject jsonObject = new JSONObject();
					String columnName = column.get(i);
					RDFNode node = querySolution.get(columnName);
					jsonObject.put(columnName,node.toString());
					//stringBuilder.append("\""+columnName+"\"").append(": \""+node.toString()+"\"");
					//Resource resource = node.asResource();
					//System.out.println(node.toString()+ "---" +resource.getLocalName() + "---" + resource.getNameSpace() + "---" + resource.getURI() );
                    /*if (i == column.size() - 1)
                        stringBuilder.append("\n}");
                    else
                        stringBuilder.append(",\n\t");*/
				}
				jsonArray.add(jsonObject);
				//stringBuilder.append(", ");
				//list.add(stringBuilder.toString());
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return jsonArray;
	}
	public void ExportFileTxt(JSONArray jsonArray, String filename){
		try{
			File file = new File(filename);
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            /*int dem = 0;
            Enumeration<String> enumeration = list.elements();
            while(enumeration.hasMoreElements()){
                dem ++;
                String str = enumeration.nextElement();
                if(dem == list.size())
                    str = str.substring(0,str.length()-2) + "\n]";
                System.out.println(str);
                bufferedWriter.write(str);
            }*/
			bufferedWriter.write(jsonArray.toJSONString());
			bufferedWriter.flush();
			bufferedWriter.close();
			fileWriter.close();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void CrawlData(String SPQRQLquery,String name){
		ParameterizedSparqlString queryStr = createQuery(SPQRQLquery);
		System.out.println(queryStr);
		Query query = queryStr.asQuery();
		QueryExecution execution = QueryExecutionFactory.sparqlService(Constant.location, query);
		((QueryEngineHTTP)execution).addParam("timeout","10000");
		ResultSet results = execution.execSelect();
		//System.out.println(results.getResultVars().toString());
		JSONArray jsonArray = ExportToString(results);
		//System.out.println(list.toString());
		ExportFileTxt(jsonArray,Constant.fileSaveDir+name);
		execution.close();
		queryStr.clearParams();
	}

	public static void main(String[] args) throws Exception {
		String configFileDir = "/Users/hh/Documents/CODE/TEST/oops_project/conf/properties.conf";
		Constant.configFileDir = configFileDir;
		CrawlDataHandle crawlDataHandle = new CrawlDataHandle();
		crawlDataHandle.loadConf();
		crawlDataHandle.CrawlData(Constant.lyKing,Constant.lyKingFileName);
		System.out.println(Constant.lyKingFileName);
		crawlDataHandle.CrawlData(Constant.tranKing,Constant.tranKingFileName);
		crawlDataHandle.CrawlData(Constant.leKing,Constant.leKingFileName);
		crawlDataHandle.CrawlData(Constant.lyDynasty,Constant.lyDynastyFileName);
		crawlDataHandle.CrawlData(Constant.tranDynasty,Constant.tranDynastyFileName);
		crawlDataHandle.CrawlData(Constant.leDynasty,Constant.leDynastyFileName);
	}
}
