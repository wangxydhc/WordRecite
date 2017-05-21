package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ReciteLog;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ConvertTool {
	public void JtoO(ReciteLog rl,String jsonUrl) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(jsonUrl))) {  
//			  while(reader.ready())
//				  System.out.println(reader.readLine());
	           rl = (ReciteLog) JSONObject.toBean(JSONObject.fromObject(reader), ReciteLog.class);  	          		
		}catch(IOException ex) {    
		
	}
	
	}

	
	public void ArraytoJ(List o,String jsonUrl) {

		JSONArray json;

		try (FileWriter writer = new FileWriter(jsonUrl)) {
			json = JSONArray.fromObject(o);
			json.write(writer);
		} catch (IOException e) {

		}
	}
	public void OtoJ(Object o,String jsonUrl) {

		JSONObject json;

		try (FileWriter writer = new FileWriter(jsonUrl)) {
			json = JSONObject.fromObject(o);
			json.write(writer);
		} catch (IOException e) {

		}
	}

	public static void main(String[] args) {
		ConvertTool t=new ConvertTool();
		List<ReciteLog>list=new ArrayList<>();
		for(int i=4;i>0;i--){
		ReciteLog rl=new ReciteLog();
		rl.setDate(new Date());
		rl.setId(4);
		rl.setTimes(45);	
		list.add(rl);}
		t.ArraytoJ(list, "d://o.json");		
//		ReciteLog rs=new ReciteLog();
//		t.JtoO(rs,"d:/o.json");
//		System.out.println(rs.getId()+" "+rs.getTimes());
	}
}
