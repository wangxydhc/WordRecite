package action;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.Word;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tools.Factory;

import com.opensymphony.xwork2.ActionSupport;


public class ShowWords extends ActionSupport{	

	private int nums;
	private List<Word>res=new LinkedList<Word>();
	public List<Word> getRes() {
		return res;
	}
	public void setRes(List<Word> res) {
		this.res = res;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public String start() {
		Factory.beforeclass();
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		Random rd = new Random();
		int id = rd.nextInt(2000)+1;
		Word w=new Word();
		while (nums-- > 0) {		
			 w= (Word) s.get(Word.class, id);
			if (w!=null)
			res.add(w);
			id = rd.nextInt(2000);
		}		
		tx.commit();
		s.close();
		return "SUCCESS";
	}

//	public static void main(String[] args) {
//		ShowWords sw = new ShowWords();
//		sw.setNums(500);
//		sw.start();
//	}
}
