package action;

import handler.DaoHandler;

import java.util.Date;

import model.ReciteLog;
import model.Word;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tools.Factory;

import com.opensymphony.xwork2.ActionSupport;


public class ConfirmWord extends ActionSupport{
	private int handle;
	public int getHandle() {
		return handle;
	}
	public void setHandle(int handle) {
		this.handle = handle;
	}
	private int wordId;
	public int getWordId() {
		return wordId;
	}
	public void setWordId(int wordId) {
		this.wordId = wordId;		
	}
	public String excute(){	
		ReciteLog rlg=new ReciteLog();
		rlg.setDate(new Date());
		rlg.setStatus(handle);
		rlg.setTimes(123);				
		DaoHandler.link(wordId, rlg);	
		System.out.println(this.getWordId()+" "+this.getHandle());
		return "SUCCESS";
	}
	
	public static void main(String[] args) {
		ConfirmWord d=new ConfirmWord();
		d.setHandle(2);
		d.setWordId(45);
		d.excute();
	}
}
