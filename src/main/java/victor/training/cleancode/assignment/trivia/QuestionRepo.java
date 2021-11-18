package victor.training.cleancode.assignment.trivia;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static victor.training.cleancode.assignment.trivia.QuestionCategory.*;

public class QuestionRepo {
   final Map<QuestionCategory, List<Question>> questionsMap = createQuestionsMap();

   public Map<QuestionCategory, List<Question>> createQuestionsMap() {
      return Map.of(
          POP, createQuestions("Pop Question "),
          SCIENCE, createQuestions("Science Question "),
          SPORTS, createQuestions("Sports Question "),
          ROCK, createQuestions("Rock Question ")
      );
   }

   private List<Question> createQuestions(String label) {
      return IntStream.range(0, 50).mapToObj(i -> new Question(label + i)).collect(toList());
   }

   public QuestionCategory getCurrentCategory(int playerPlace) {
      return switch (playerPlace % 4) {
         case 0 -> POP;
         case 1 -> SCIENCE;
         case 2 -> SPORTS;
         case 3 -> ROCK;
         default -> throw new IllegalStateException("Unexpected value: " + playerPlace % 4);
      };
   }

   public void askQuestion(QuestionCategory currentCategory) {
      final Question question = questionsMap.get(currentCategory).remove(0);
      System.out.println(question.getSentence());
   }
}