package com.example.ailatrieuphu.db.dao;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.ailatrieuphu.db.entities.Question;

import java.util.List;

@Dao
public interface QuestionDAO {
    String queryGetQuestion = "SELECT * FROM (SELECT * FROM Question Where level =1 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =2 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =3 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =4 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =5 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =6 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =7 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =8 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =9 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =10 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =11 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =12 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =13 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =14 Order By RANDOM()LIMIT 1)\n" +
            "UNION SELECT * FROM (SELECT * FROM Question Where level =15 Order By RANDOM()LIMIT 1)\n";

    @Query(queryGetQuestion)
    List<Question> getAllQuestion();
    @Query("Select * FROM Question Where level =:level Order By RANDOM() LIMIT 1")
    public  Question getAQuestion(String level);

}
