package ru.otus.testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private String answer;
    private boolean isCorrect;
}
