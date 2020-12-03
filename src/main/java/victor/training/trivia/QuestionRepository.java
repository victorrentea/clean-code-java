package victor.training.trivia;

import java.util.LinkedList;
import java.util.List;

public class QuestionRepository {
   private List<String> popQuestions = new LinkedList<>();
   private List<String> scienceQuestions = new LinkedList<>();
   private List<String> sportsQuestions = new LinkedList<>();
   private List<String> rockQuestions = new LinkedList<>();

   // TODO  Map<tip, List<String>>
   public QuestionRepository() {
      for (int i = 0; i < 50; i++) {
         popQuestions.add("Pop Question " + i);
         scienceQuestions.add("Science Question " + i);
         sportsQuestions.add("Sports Question " + i);
         rockQuestions.add("Rock Question " + i);
      }
   }


   public QuestionCategory getCurrentCategory(int place) {
//      return QuestionCategory.values()[places[currentPlayer] % 4]; <periculos: te bazezi pe ordinea enumurilor
      switch (place % 4) {
         case 0:
            return QuestionCategory.POP;
         case 1:
            return QuestionCategory.SCIENCE;
         case 2:
            return QuestionCategory.SPORTS;
         case 3:
            return QuestionCategory.ROCK;
         default:
            throw new IllegalStateException("Unexpected value: " + place % 4);
      }
   }

   public String nextQuestion(int place) {
      QuestionCategory category = getCurrentCategory(place);
      if (category == QuestionCategory.POP)
         return popQuestions.remove(0);
      if (category == QuestionCategory.SCIENCE)
         return scienceQuestions.remove(0);
      if (category == QuestionCategory.SPORTS)
         return sportsQuestions.remove(0);
      if (category == QuestionCategory.ROCK)
         return rockQuestions.remove(0);
      throw new IllegalArgumentException(category.name());
   }
}
