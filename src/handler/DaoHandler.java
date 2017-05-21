package handler;

import model.ReciteLog;
import model.Word;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tools.Factory;

public class DaoHandler {
	public static boolean saveWord(Word w){
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.save(w);
		tx.commit();
		s.close();
		return true;
	}
	public static boolean saveLog(ReciteLog l){
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.save(l);
		tx.commit();
		s.close();
		return true;
	}
	public static Word getWord(int id){
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		Word w=(Word)s.get(Word.class, id);
		tx.commit();
		s.close();
		return w;		
	}
	public static void link(int id,ReciteLog rl){
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		Word w=(Word)s.load(Word.class, id);
		s.save(rl);
		w.getLog().add(rl);
		s.save(w);		
		tx.commit();
		s.close();
	}
}
