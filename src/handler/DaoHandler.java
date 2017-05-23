package handler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.ReciteLog;
import model.Word;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tools.Factory;

public class DaoHandler {
	public static boolean saveWord(Word w) {
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.save(w);
		tx.commit();
		s.close();
		return true;
	}

	public static List<Word> getWordArrays(int count) {
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		List<Word> list = new ArrayList<Word>();
		Random rd = new Random();
		int id = rd.nextInt(2000) + 1;
		List<Integer> idList = new LinkedList<Integer>();
		for (int i = 0; i < count; i++) {
			Word w = new Word();
			w = (Word) s.get(Word.class, id);
			if(idList.contains(id)){
				System.out.println("dumplicated");
			}
			if (w != null&&!idList.contains(id)) {
				idList.add(id);
				list.add(w);				
			}
			else
				i--;			
			id = rd.nextInt(2000) + 1;
		}
		tx.commit();
		s.close();
		return list;
	}

	public static boolean saveLog(ReciteLog l) {
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		s.save(l);
		tx.commit();
		s.close();
		return true;
	}

	public static Word getWord(int id) {
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		Word w = (Word) s.get(Word.class, id);
		tx.commit();
		s.close();
		return w;
	}

	public static void link(int id, ReciteLog rl) {
		Session s = Factory.getSessionFactory().openSession();
		Transaction tx = s.beginTransaction();
		Word w = (Word) s.load(Word.class, id);
		s.save(rl);
		w.getLog().add(rl);
		s.save(w);
		tx.commit();
		s.close();
	}
}
