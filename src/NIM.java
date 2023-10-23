/*
 * CSCI 1913
 * Lab 06
 * Author: Daurie Basham
 * Notes: remove package name student to build project if necessary
 * */
package student;

public class NIM {

    /**
     * create a game state array
     *
     * @param size     -- the number of rows
     * @param stickMax -- the largest value
     * @return an array representing a token array. The array in the first position will be one, each following will be
     * one larger up to the max.
     */
    public static int[] createGameState(int size, int stickMax) {
        int[] gameState = new int[size];
        if (size == 0) {
            return gameState;
        }
        int i = 0;
        int stickEnd = size - stickMax + 1;
        while (i < size) {
            int val = i + 1;
            gameState[i] = val;
            i += 1;
        }
        if (size > stickMax) {
            for (int j = size - 1; j >= (size - stickEnd); j--) {
                gameState[j] = stickMax;
            }
        }

        return gameState;
    }


    /**
     * This provided function operators similarly to the python isDigit method. You give it a string and it will tell
     * you if the string contains only digits. Give it a read -- the actual design isn't hard, basically a linear search.
     *
     * @param str any string
     * @return true if all letters in the string are digits.
     */
    private static boolean isDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a given move (as represented by two input strings) is valid. This should check if the inputs are numbers
     * if those numbers are in the valid range.
     *
     * @param gameState an array representing the number of tokens in each row.
     * @param row       a string representing which row the user wants to take from. 1-indexed.
     * @param takes     a string representing how many tokens the user wants to take.
     * @return true if and only if the move would be valid
     */
    public static boolean isValidMove(int[] gameState, String row, String takes) {

        boolean isRowValid = isDigit(row);
        boolean isTakesValid = isDigit(takes);

        if (isRowValid && isTakesValid) {

            int rowNum = Integer.parseInt(row);
            if (rowNum < 1 || rowNum > gameState.length) {
                return false;
            }
            int idxRow = rowNum - 1;
            int takesNum = Integer.parseInt(takes);
            if (takesNum < 0 || takesNum > 3) {
                return false;
            }
            if (gameState[idxRow] >= takesNum) {
                return true;
            }
        }
        return false;
    }


    /**
     * User System.out.println to represent a token grid to the program user.
     *
     * @param gameState an array representing the number of tokens in each row.
     */
    public static void drawGameState(int[] gameState) {

        System.out.println("====================");
        for (int i = 0; i < gameState.length; i++) {
            int numChars = gameState[i];
            int rowNum = i + 1;
            String row = rowNum + " ";
            for (int j = 1; j <= numChars; j++) {
                row = row + "#";
            }
            System.out.println(row);
        }
        System.out.println("====================");
    }


    /**
     * Create an updated version of the game state. You can assume the row and takes are valid for the gameState array provided.
     *
     * @param gameState an array representing the number of tokens in each row.
     * @param row       the row the user wants to take from (0-indexed)
     * @param takes     the number of tokens the user wants to take
     * @return a new array representing the state number of tokens in each row after the given numbers were removed.
     */
    public static int[] update(int[] gameState, int row, int takes) {
        int[] newGameState = gameState.clone();
        newGameState[row] = newGameState[row] - takes;
        return newGameState;

    }

    /**
     * @param gameState an array representing the number of tokens in each row.
     * @return true if and only if every element of gameState is false.
     */
    public static boolean isOver(int[] gameState) {
        boolean gameOver = true;
        int i = 0;
        while (i < gameState.length) {
            if (gameState[i] > 0) {
                gameOver = false;
                return gameOver;
            } else {
                i += 1;
            }
        }
        return gameOver;
    }
}