package action;

import java.util.Date;

import model.ReciteLog;
import model.Word;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tools.Factory;

import com.opensymphony.xwork2.ActionSupport;


public class ConfirmWord extends ActionSupport{
	private String handle;
	private int wordId;



	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public int getWordId() {
		return wordId;
	}
	public void setWordId(int wordId) {
		this.wordId = wordId;		
	}
	public String excute(){
		System.out.println(handle+"&&&&&"+wordId);
		Factory.beforeclass();
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		ReciteLog rlg=new ReciteLog();
		rlg.setDate(new Date());
		rlg.setStatus(handle);
		rlg.setWord("no useful");
		Word word=(Word)s.get(Word.class, wordId);
		int times=word.getRemTimes()+1;
		rlg.setTimes(times);
		word.setRemTimes(times);
		word.setState(handle);
		word.getLog().add(rlg);
		s.save(rlg);
		s.saveOrUpdate(word);
		tx.commit();
		s.close();
		System.out.println(this.getWordId()+" "+this.getHandle());
		return "SUCCESS";
	}
	
}
