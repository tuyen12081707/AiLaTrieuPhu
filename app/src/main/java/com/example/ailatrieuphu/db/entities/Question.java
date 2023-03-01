package com.example.ailatrieuphu.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {
    @ColumnInfo(name = "_id")
    @PrimaryKey
    @NonNull
    public int id;
    @ColumnInfo(name = "question")
    public String question;
    @ColumnInfo(name = "level")
    public String level;
    @ColumnInfo(name = "casea")
    public String casea;
    @ColumnInfo(name = "caseb")
    public String caseb;
    @ColumnInfo(name = "casec")
    public String casec;
    @ColumnInfo(name = "cased")
    public String cased;
    @ColumnInfo(name = "truecase")
    public String trueCase;

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", id=" + id +
                ", level=" + level +
                ", casea=" + casea +
                ", caseb=" + caseb +
                ", casec=" + casec +
                ", cased=" + cased +
                ", trueCase=" + trueCase +
                '}';
    }


}
