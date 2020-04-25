package com.example.viewpager;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int wordId;
    private String worderName;
    private String worderContents;
    private String wordTag;

    @Ignore
    public Word(){

    }

    public Word(int wordId, String worderName, String worderContents, String wordTag) {
        this.wordId = wordId;
        this.worderName = worderName;
        this.worderContents = worderContents;
        this.wordTag = wordTag;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getWorderName() {
        return worderName;
    }

    public void setWorderName(String worderName) {
        this.worderName = worderName;
    }

    public String getWorderContents() {
        return worderContents;
    }

    public void setWorderContents(String worderContents) {
        this.worderContents = worderContents;
    }

    public String getWordTag() {
        return wordTag;
    }

    public void setWordTag(String wordTag) {
        this.wordTag = wordTag;
    }
}
