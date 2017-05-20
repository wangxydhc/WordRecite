package action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import model.JsonModel;
import model.ReciteLog;
import model.Word;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tools.ConvertTool;
import tools.Factory;

import com.opensymphony.xwork2.ActionSupport;


public class DisLog extends ActionSupport{
	private int wordId;

	public int getWordId() {
		return wordId;
	}
	public void setWordId(int wordId) {
		this.wordId = wordId;		
	}
	public String open(){
		System.out.println("&&&&&"+wordId);		
		Factory.beforeclass();
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		List<JsonModel>list=new ArrayList<JsonModel>();
		for(int i=0;i<100;i++){		
		Word word=(Word)s.get(Word.class, wordId+i);		
		Set<ReciteLog> set=word.getLog();
		for(ReciteLog rl:set){
			JsonModel jm=new JsonModel();
			SimpleDateFormat ft=new SimpleDateFormat("MM月dd日HH时");
			
			jm.setUnit(ft.format(rl.getDate()));
			jm.setValue(rl.getStatus());
			list.add(jm);
		}		
		}
		tx.commit();
		s.close();
		ConvertTool convert=new ConvertTool();
		
		convert.ArraytoJ(list, "d:/in.json");
		return "SUCCESS";
	}
	public static void main(String[] args) {
		DisLog sl=new DisLog();
		sl.setWordId(20);
		sl.open();
	}
	
}
