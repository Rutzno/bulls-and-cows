package bullscows;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Mack_TB
 */

public class Main {
    static Scanner sc;
    static StringBuilder secretCode;
    static int numBulls, numCows;
    static String error = null;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String input1 = sc.nextLine();
        String input2 = null;
        try {
            int size = Integer.parseInt(input1);

            System.out.println("Input the number of possible symbols in the code:");
            input2 = sc.nextLine();
            int numSymbols = Integer.parseInt(input2);

            if (!secretCodeGeneratedV2(size, numSymbols)){
                return;
            }// The secret code is prepared: "****"
        } catch (NumberFormatException ex) {
            error = input2 == null ?
                    String.format("Error: \"%s\" isn't a valid number.", input1) :
                    String.format("Error: \"%s\" isn't a valid number.", input2);
            System.out.println(error);
            return;
        }

        playGame();
    }


    private static void playGame() {
        String grade;
        int nbrRound = 0;
        System.out.println("Okay, let's start a game!");
        String answer = "";
        while (!answer.equals(secretCode.toString())) {
            numBulls = 0;
            numCows = 0;
            System.out.println("Turn " +(++nbrRound)+ ":");
            answer = sc.next();
            if (answer.length() != secretCode.length()) {
                System.out.println("The length of your answer should be equal to secret code\n");
                continue;
            }
            computeNumBullsnCows(secretCode.toString(), answer);
            grade = getGrade();
            System.out.printf("Grade: %s%n%n", grade);
            if (numBulls == 4) {
                System.out.println("Congratulations! You guessed the secret code.");
            }
        }
    }

    private static boolean secretCodeGeneratedV2(int size, int numberSymbols) {
        Random random = new Random();

        if (size == 0) {
            error = "Error: Secret code's length shouldn't be 0";
        }
        if (size > 36 || numberSymbols > 36) {
            error = "Error: maximum number of possible symbols in the code " +
                    "is 36 (0-9, a-z).";
        }
        if (size > numberSymbols) {
            error = String.format("Error: it's not possible to generate a code with a length" +
                    " of %d with %d unique symbols.", size, numberSymbols);
        }

        if (error != null){
            System.out.println(error);
            return false;
        }

        secretCode = new StringBuilder();
        do {
            int digit = random.nextInt(numberSymbols);
            char code = Character.forDigit(digit, numberSymbols);

            if (!secretCode.toString().contains(String.valueOf(code))) {
                secretCode.append(code);
            }

        } while (secretCode.length() != size);

        System.out.printf("The secret is prepared: %s %s.%n", "*".repeat(size), findUsedSymbols(numberSymbols));
//        System.out.printf("The random secret number is %s.%n", secretCode.toString());
        return true;
    }

    private static String findUsedSymbols(int range) {
        String alphaNumeric = "0123456789abcdedfghijklmnopqrstuvwxyz";
        String numeric;
        String alpha;
        if (range <= 10){
            numeric = "0-"+(--range);
            return "("+numeric+")";
        } else {
            numeric = "0-9";
            alpha  = "a-"+(alphaNumeric.charAt(range));
            return "("+numeric+", "+alpha+")";
        }
    }

    private static boolean secretCodeGenerated() {
        System.out.println("Please, enter the secret code's length:");
        int size = sc.nextInt();
        if (size > 10) {
            System.out.printf("Error: can't generate a secret number with a length of %d " +
                    "because there aren't enough unique digits.", size);
            return false;
        }

        do {
            long pseudoRandomNumber = System.nanoTime();
            StringBuilder sbPseudoRnd = new StringBuilder("" + pseudoRandomNumber);
            secretCode = new StringBuilder();
            sbPseudoRnd.reverse();

            // generate secret code for a given length
            for (int i = 0; i < sbPseudoRnd.length(); i++) {
                if (Character.getNumericValue(sbPseudoRnd.charAt(0)) == 0) { // remove the 0s to the start
                    sbPseudoRnd.deleteCharAt(0);
                    i = 0;
                    continue;
                }
                int digit = Character.getNumericValue(sbPseudoRnd.charAt(i));
                if (!secretCode.toString().contains(String.valueOf(digit))) {
                    secretCode.append(digit);
                }
                if (secretCode.length() == size) {
                    break;
                }
            }
        } while (secretCode.length() != size);
        //System.out.printf("The random secret number is %s.%n", secretCode.toString());
        return true;
    }

    // Count number of bulls & cows
    private static void computeNumBullsnCows(String secretCode, String answer) {
        for (int i = 0; i < answer.length(); i++) {
            char symbol = answer.charAt(i);
            if (secretCode.contains(String.valueOf(symbol))) {
                if (answer.charAt(i) == secretCode.charAt(i)) {
                    numBulls++;
                } else {
                    numCows++;
                }
            }
        }
    }

    private static String getGrade() {
        String bulls = numBulls == 1 ? "1 bull" : String.format("%d bulls", numBulls);
        String cows  = numCows == 1 ? "1 cow" : String.format("%d cows", numCows);
        if (numCows >= 1 && numBulls == 0) {
            return cows;
        } else if (numBulls >= 1 && numCows == 0) {
            return bulls;
        } else if (numCows != 0 && numBulls != 0) {
            return  bulls + " and " + cows;
        } else {
            return "None";
        }
    }
}
