package action;

import model.ReciteLog;
import model.Word;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdom2.input.SAXBuilder;

import tools.Factory;

import com.opensymphony.xwork2.ActionSupport;

public class showLog extends ActionSupport{
	private int wordId;

	public int getWordId() {
		return wordId;
	}

	public void setWordId(int wordId) {
		this.wordId = wordId;
	}
	public void sendArray(){
		Factory.beforeclass();
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		Word word=(Word)s.load(Word.class, wordId);
		int time=word.getRemTimes();
		int len=word.getLog().size();
		for(ReciteLog rl:word.getLog()){
			String dt=rl.getDate().toString();
			String st=rl.getStatus();
			
		}
	}
}
