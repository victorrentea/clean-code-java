package trivia.question.category;

import trivia.question.QuestionCategory;

import java.util.LinkedList;

public class Rock implements QuestionCategory {
    private LinkedList<String> questions = new LinkedList<>();

    private static final String NO_MORE_QUESTIONS = "No more Rock questions";
    private static final String ROCK_QUESTIONS = "Rock Question ";

    public Rock() {
        for (int i = 0; i < 50; i++) {
            questions.addLast(ROCK_QUESTIONS + i);
        }
    }

    public void askQuestion() {
        if (questions.isEmpty()) {
            throw new RuntimeException(NO_MORE_QUESTIONS);
        }
        System.out.println(questions.removeFirst());
    }

}