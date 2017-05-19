package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Word {
	private String word;
	private String paraphrase;
	private int id;
	private String state;
	private int remTimes;
	private Set<ReciteLog> log=new HashSet<ReciteLog>();
	public Word(){		
	};
	public Word(int time,String word,String paraphrase,String state){
		this.word=word;
		this.paraphrase=paraphrase;
		this.state=state;
		this.remTimes=time;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getRemTimes() {
		return remTimes;
	}
	public void setRemTimes(int remTimes) {
		this.remTimes = remTimes;
		}
	public String getParaphrase() {
		return paraphrase;
	}
	public void setParaphrase(String paraphrase) {
		this.paraphrase = paraphrase;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	@OneToMany
	@JoinColumn(name="log")
	public Set<ReciteLog> getLog() {
		return log;
	}
	public void setLog(Set<ReciteLog> log) {
		this.log = log;
	}

}
