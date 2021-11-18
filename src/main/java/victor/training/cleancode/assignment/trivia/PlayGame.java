package victor.training.cleancode.assignment.trivia;

import java.util.Random;
import java.util.Scanner;

// DON'T TOUCH THIS CLASS. ONLY RUN IT TO MANUALLY PLAY THE GAME YOURSELF TO UNDERSTAND THE PROBLEM

public class PlayGame {

    private static final Scanner scanner = new Scanner(System.in);
    //Todo: externalize configuration -> second change
    private static boolean enableSecondChance = true;

    public static void main(String[] args) {

        System.out.println("*** Welcome to Trivia Game ***\n");
        System.out.println("Enter number of players: 2-6");
        int playerCount = Integer.parseInt(scanner.nextLine());
        // TODO: Hmm.. I don't really like this, maybe I should add some handling, the user should be allowed to do mistakes (I think so)
        if (playerCount < 2 || playerCount > 6) throw new IllegalArgumentException("No player 2..6");
        //new check could have been added for 2 numbers, but it doesn't make any sense, let's start with min 2 players
        System.out.println("Reading names for " + playerCount + " players:");

//        IGame aGame = new Game();
        IGame aGame = new GameBetter();

        for (int i = 1; i <= playerCount; i++) {
            System.out.print("Player " + i + " name: ");
            String playerName = scanner.nextLine();
            if (playerName == null || playerName.trim().isEmpty()) {
                System.out.print("Please add a player name: ");
                playerName = scanner.nextLine();
            }
            boolean added = aGame.add(playerName);
            while (!added) {
                System.out.print("Username already taken:" + playerName + ". Please try a different one -> player " + i + " name: ");
                playerName = scanner.nextLine();
                added = aGame.add(playerName);
            }
        }

        System.out.println("\n\n--Starting game--");


        boolean notAWinner;
        do {
            int roll = readRoll();
            aGame.roll(roll);

            System.out.print(">> Was the answer correct? [y/n] ");
            boolean correct = readYesNo();
            if (correct) {
                notAWinner = aGame.wasCorrectlyAnswered();
            } else {
                if (enableSecondChance) {
                    // don't want to update the whole code, secondChance is available only for BetterGame
                    System.out.print("Got a second chance:");
                    ((GameBetter) aGame).prepareQuestion();
                    System.out.print(">> Was the answer correct? [y/n] ");
                    notAWinner = readYesNo() ? aGame.wasCorrectlyAnswered() : aGame.wrongAnswer();
                } else {
                    notAWinner = aGame.wrongAnswer();
                }
            }

        } while (notAWinner);
        System.out.println(">> Game won!");
    }

    private static boolean readYesNo() {
        String yn = scanner.nextLine().trim().toUpperCase();
        if (!yn.matches("[YN]")) {
            System.err.println("y or n please");
            return readYesNo();
        }
        return yn.equalsIgnoreCase("Y");
    }

    private static int readRoll() {
        System.out.print(">> Throw a die and input roll, or [ENTER] to generate a random roll: ");
        String rollStr = scanner.nextLine().trim();
        if (rollStr.isEmpty()) {
            int roll = new Random().nextInt(6) + 1;
            System.out.println(">> Random roll: " + roll);
            return roll;
        }
        if (!rollStr.matches("\\d+")) {
            System.err.println("Not a number: '" + rollStr + "'");
            return readRoll();
        }
        int roll = Integer.parseInt(rollStr);
        if (roll < 1 || roll > 6) {
            System.err.println("Invalid roll");
            return readRoll();
        }
        return roll;
    }
}
