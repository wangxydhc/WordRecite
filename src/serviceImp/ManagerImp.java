package serviceImp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.ReciteLogDao;
import dao.WordDao;
import daoImp.ReciteLogDaoImp;
import daoImp.WordDaoImp;
import model.JsonModel;
import model.ReciteLog;
import model.Word;
import tools.ConvertTool;
import tools.Factory;
import tools.WriteJson;

public class ManagerImp {
	private Session session;
	private ReciteLogDaoImp rld;
	private WordDaoImp wd;
	private Transaction ts;

	public void openSession() {
		session = Factory.getSessionFactory().openSession();
		rld = new ReciteLogDaoImp();
		rld.setSession(session);
		wd = new WordDaoImp();
		wd.setSession(session);
		ts = session.beginTransaction();
	}

	public void closeSession() {
		ts.commit();
		session.close();
	}

	public void addLogToWord(ReciteLog rl, int id) {
		this.openSession();
		this.rld.saveLog(rl);
		Word word = this.wd.getWord(id);
		word.getLog().add(rl);
		this.wd.saveWord(word);
		this.closeSession();
	}

	public Word getWord(int id) {
		this.openSession();
		Word res = this.wd.getWord(id);
		this.closeSession();
		return res;

	}

	public void generateJson(int id) {
		this.openSession();
		List<JsonModel> list = new ArrayList<JsonModel>();
		for (int i = 0; i < 100; i++) {
			Word word = this.wd.getWord(id+i);
			if (word != null) {
				List<ReciteLog> set = word.getLog();
				for (ReciteLog rl : set) {
					JsonModel jm = new JsonModel();
					SimpleDateFormat ft = new SimpleDateFormat("MM月dd日HH时");

					jm.setUnit(ft.format(rl.getDate()));
					jm.setValue(rl.getStatus());
					list.add(jm);
				}
			}
		}
		ConvertTool convert = new ConvertTool();
		convert.ArraytoJ(list, "d:/in.json");
		WriteJson.writeBefore("d:/in.json", "d:/outf.json");
		this.closeSession();
	}


	public List<ReciteLog> getLogs(int id) {
		this.openSession();
		List<ReciteLog> res = this.rld.getLogs(id);
		this.closeSession();
		return res;
	}


	public void saveWord(Word word) {
		this.openSession();
		this.wd.saveWord(word);
		this.closeSession();
	}


	public void saveWord(Word[] word) {
		this.openSession();
		for (Word w : word) {
			this.wd.saveWord(w);
		}
		this.closeSession();
	}

	public void saveReciteLog(ReciteLog rl, Word word) {
		this.openSession();
		this.rld.saveLog(rl);
		word.getLog().add(rl);
		this.wd.saveWord(word);
		this.closeSession();
	}

	public void saveReciteLog(ReciteLog rl, String wordContent) {
		this.openSession();
		if (!this.wd.exist(wordContent)) {
			Word w = new Word();
			w.setWord(wordContent);
			this.wd.saveWord(w);
		}
		Word word = this.wd.getByContent(wordContent);
		this.rld.saveLog(rl);
		word.getLog().add(rl);
		this.wd.saveWord(word);
		this.closeSession();

	}

	public List<Word> getWordArrays(int count) {
		this.openSession();
		List<Word> list = new ArrayList<Word>();
		Random rd = new Random();
		int id = rd.nextInt(2000) + 1;
		List<Integer> idList = new LinkedList<Integer>();
		for (int i = 0; i < count; i++) {
			Word w = new Word();
			w = this.wd.getWord(id);
			while (idList.contains(id) || w == null) {
				if (w == null)
					;
				// System.out.println("_______________________________word=null");
				if (idList.contains(id))
					// System.out.println("______________________________dumplicated");
					;
				id = rd.nextInt(2000) + 1;
				w = this.wd.getWord(id);
			}
			idList.add(id);
			list.add(w);
			// System.out.println(w.getId()+" "+w.getWord());
			id = rd.nextInt(2000) + 1;
		}
		return list;
	}

}
