package ru.otus.testing.dao.impl;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    private final ClassPathResource classPathResource;
    @Override
    public List<Question> findAll() {
        List<Question> questionList = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(classPathResource.getInputStream()));
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                questionList.add(new Question(nextLine[0], findAnswers(nextLine)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return questionList;
    }

    private List<Answer> findAnswers(String[] nextLine) {
        List<Answer> answerList = new ArrayList<>();
        for (int i = 1; i < nextLine.length; i++) {
            if (nextLine[i].contains("correct:")) {
                answerList.add(new Answer(nextLine[i].replaceFirst("correct:", ""), true));
            } else {
                answerList.add(new Answer(nextLine[i], false));
            }
        }
        return answerList;
    }
}
