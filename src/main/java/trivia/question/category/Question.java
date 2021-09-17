package trivia.question.category;

import java.util.LinkedList;

public class Question {
    private final String questionName;
    private LinkedList<String> questions = new LinkedList<>();

    public Question(final String questionName) {
        this.questionName = questionName;
        for (int i = 0; i < 50; i++) {
            questions.addLast(questionName + " Question " + i);
        }
    }

    public void askQuestion() {
        if (questions.isEmpty()) {
            throw new RuntimeException("No more "+questionName+" questions");
        }
        System.out.println(questions.removeFirst());
    }

    public String getName() {
        return questionName;
    }

}
