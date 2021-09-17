package trivia.question.category;

import trivia.question.QuestionCategory;

import java.util.LinkedList;

public class Sports implements QuestionCategory {
    private LinkedList<String> questions = new LinkedList<>();

    private static final String NO_MORE_QUESTIONS = "No more Sports questions";
    private static final String SPORTS_QUESTIONS = "Sports Question ";

    public Sports() {
        for (int i = 0; i < 50; i++) {
            questions.addLast(SPORTS_QUESTIONS + i);
        }
    }

    public void askQuestion() {
        if (questions.isEmpty()) {
            throw new RuntimeException(NO_MORE_QUESTIONS);
        }
        System.out.println(questions.removeFirst());
    }

}
