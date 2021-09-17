package trivia.question.category;

import trivia.question.QuestionCategory;

import java.util.LinkedList;

public class Pop implements QuestionCategory {
    private LinkedList<String> questions = new LinkedList<>();

    private static final String NO_MORE_QUESTIONS = "No more Pop questions";
    private static final String POP_QUESTIONS = "Pop Question ";


    public Pop() {
        for (int i = 0; i < 50; i++) {
            questions.addLast(POP_QUESTIONS + i);
        }
    }

    public void askQuestion() {
        if (questions.isEmpty()) {
            throw new RuntimeException(NO_MORE_QUESTIONS);
        }
        System.out.println(questions.removeFirst());
    }

}