package trivia.questions;

import java.util.*;

import static trivia.questions.QuestionCategory.*;

public final class Questions {
    private static final int QUESTIONS_PER_CATEGORY = 50;

    /** Mapping from question type to question. */
    private final Map<QuestionCategory, List<String>> questions;

    public Questions() {
        questions = generateQuestions();
    }

    public Optional<String> pickQuestion(QuestionCategory category) {
        List<String> questionsForCategory = questions.get(category);
        if (questionsForCategory.isEmpty()) {
            // take a q from any other cat
            return Optional.empty();
        }

        return Optional.of(questionsForCategory.remove(0));
    }

    private static Map<QuestionCategory, List<String>> generateQuestions() {
        final Map<QuestionCategory, List<String>> questions = new HashMap<>();
        for (int i = 0; i < QUESTIONS_PER_CATEGORY; i++) {
            addQuestion(questions, POP, "Pop Question " + i);
            addQuestion(questions, SCIENCE, "Science Question " + i);
            addQuestion(questions, SPORTS, "Sports Question " + i);
            addQuestion(questions, ROCK, "Rock Question " + i);
        }
        return questions;
    }

    private static void addQuestion(
            Map<QuestionCategory, List<String>> questions,
            QuestionCategory questionCategory,
            String question) {
        questions.computeIfAbsent(questionCategory, t -> new ArrayList<>()).add(question);
    }
}
