package com.colewe.ws1819.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Entry implements Serializable{
	String id;
	String abbr;
	String chinese;
	String englishLiteral;
	String englishFigurative;
	String pinyin;
	String example;
	String exampleTranslation;
	String origin;
	String originTranslation;
	String frequency;
	ArrayList<String> tags;
	
	public Entry() {
		this.id = null;
		this.abbr = null;
		this.chinese = null;
		this.englishLiteral = null;
		this.englishFigurative = null;
		this.pinyin = null;
		this.example = null;
		this.exampleTranslation = null;
		this.origin = null;
		this.originTranslation = null;
		this.frequency = null;
		this.tags = new ArrayList<String>();
	}
	
	public Entry(String id, String abbr, String chinese, String englishLiteral,
			String englishFigurative, String pinyin, String example, String exampleTranslation,
			String origin, String originTranslation, String frequency) {
		this.id = id;
		this.abbr = abbr;
		this.chinese = chinese;
		this.englishLiteral = englishLiteral;
		this.englishFigurative = englishFigurative;
		this.pinyin = pinyin;
		this.example = example;
		this.exampleTranslation = exampleTranslation;
		this.origin = origin;
		this.originTranslation = originTranslation;
		this.frequency = frequency;
		this.tags = new ArrayList<String>();
	}
	
	public void addTag(String tag) {
		if (this.tags.contains(tag)) {
			return;
		}
		
		this.tags.add(tag);
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getAbbr() {
		return this.abbr;
	}
	
	public String getChinese() {
		return this.chinese;
	}
	public String getEnglishLiteral() {
		return this.englishLiteral;
	}
	public String getEnglishFigurative() {
		return this.englishFigurative;
	}
	public String getPinyin() {
		return this.pinyin;
	}
	public String getExample() {
		return this.example;
	}
	public String getExampleTranslation() {
		return this.exampleTranslation;
	}
	public String getOrigin() {
		return this.origin;
	}
	public String getOrignTranslation() {
		return this.originTranslation;
	}
	public String getFrequency() {
		return this.frequency;
	}
	public ArrayList<String> getTags() {
		return this.tags;
	}
	public boolean hasTags() {
		if(this.tags.size() == 0) {
			return false;
		}
		
		return true;
	}
	
	public String toString() {
		String output = "";
		
		output += this.id + "\t";
		output += this.abbr + "\t";
		output += this.chinese + "\t";
		output += this.tags.size() + "\t";
		
		for(String tag: this.tags) {
			output += tag + "\t";
		}
		
		return output;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(this.getClass() == obj.getClass())) {
            return false;
        }

        final Entry other = (Entry)obj;

        if (this.id != other.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
	
}
