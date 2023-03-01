package com.example.ailatrieuphu;

import com.example.ailatrieuphu.db.entities.Question;

import java.util.List;

public class Storage {
    public static List<Question> listQuestion;

    public void setListQuestion(List<Question> listQuestion) {
        Storage.listQuestion = listQuestion;
    }
}
