package action;

import handler.DaoHandler;

import java.util.Date;

import model.ReciteLog;
import model.Word;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tools.Factory;

import com.opensymphony.xwork2.ActionSupport;


public class AddWordAndLog extends ActionSupport{
	private String word;
	private int level;
	private int method;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getMethod() {
		return method;
	}
	public void setMethod(int method) {
		this.method = method;
	}
	public String excute(){	
		Word w=new Word(this.word,"NONE",1);
		DaoHandler.saveWord(w);
		ReciteLog rl=new ReciteLog();
		rl.setDate(new Date());
		rl.setMethod(this.getMethod());
		rl.setStatus(this.getLevel());
		rl.setUsername("wangxy");
		DaoHandler.saveLog(rl);
		return "SUCCESS";
	}
	public static void main(String[] args) {
		AddWordAndLog d=new AddWordAndLog();
		d.excute();		
	}
	
}
