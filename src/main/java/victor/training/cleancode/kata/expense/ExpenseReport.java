package victor.training.cleancode.kata.expense;

import java.util.List;

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;
        boolean mealOver = false;

        System.out.println("Expenses Report");

        for (Expense expense : expenses) {
          if ("DINNER".equals(expense.type) || "BREAKFAST".equals(expense.type)) {
                mealExpenses += expense.amount;
            }

            String en = null;
            switch (expense.type) {
            case "DINNER":
                en = "Dinner";
                break;
            case "BREAKFAST":
                en = "Breakfast";
                break;
            case "CAR_RENTAL":
                en = "Car Rental";
                break;
            }

            // meal over expenses
          String m = "DINNER".equals(expense.type) && expense.amount > 5000 || "BREAKFAST".equals(expense.type) && expense.amount > 1000 ? "X" : " ";
          mealOver |= "X".equals(m);

            System.out.println(en + "\t" + expense.amount + "\t" + m);

            total += expense.amount;
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
        if (mealOver) {
            System.out.println("Meal expenses exceed limit");
        }
    }
}