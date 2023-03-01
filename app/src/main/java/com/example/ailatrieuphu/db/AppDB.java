package com.example.ailatrieuphu.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ailatrieuphu.db.dao.QuestionDAO;
import com.example.ailatrieuphu.db.entities.Question;


@Database(entities = {Question.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public  abstract QuestionDAO questionDAO();
}