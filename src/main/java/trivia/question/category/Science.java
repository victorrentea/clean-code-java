package trivia.question.category;

import trivia.question.QuestionCategory;

import java.util.LinkedList;

public class Science implements QuestionCategory {
    private LinkedList<String> questions = new LinkedList<>();

    private static final String NO_MORE_QUESTIONS = "No more Science questions";
    private static final String SCIENCE_QUESTIONS = "Science Question ";

    public Science() {
        for (int i = 0; i < 50; i++) {
            questions.addLast(SCIENCE_QUESTIONS + i);
        }
    }

    public void askQuestion() {
        if (questions.isEmpty()) {
            throw new RuntimeException(NO_MORE_QUESTIONS);
        }
        System.out.println(questions.removeFirst());
    }

}