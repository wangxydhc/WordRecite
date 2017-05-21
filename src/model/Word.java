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
	private int type;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	private String word;
	private String paraphrase;
	private int id;

	private Set<ReciteLog> log=new HashSet<ReciteLog>();
	public Word(){		
	};
	public Word(String word,String paraphrase){
		this.word=word;
		this.paraphrase=paraphrase;
		this.type=0;
	}
	public Word(String word,String paraphrase,int type){
		this.word=word;
		this.paraphrase=paraphrase;
		this.type=type;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
