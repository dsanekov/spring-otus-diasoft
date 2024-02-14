package ru.otus.testing.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.testing.dao.QuestionDao;
import ru.otus.testing.model.Answer;
import ru.otus.testing.model.Question;
import ru.otus.testing.service.PrintService;
import ru.otus.testing.service.TestingService;

@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {
    private final QuestionDao questionDao;
    private final PrintService printService;
    @Override
    public void printTest() {
        for (Question question : questionDao.findAll()) {
            printService.print("\n" + "Question: " + question.getQuestion());
            printService.print("Answers:");
            for (Answer answer : question.getAnswerList()) {
                printService.print("* " + answer.getAnswer());
            }
        }
    }
}
