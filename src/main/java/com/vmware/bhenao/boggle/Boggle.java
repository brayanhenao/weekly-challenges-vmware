package com.vmware.bhenao.boggle;

import com.vmware.bhenao.exceptions.StopPlayingException;
import com.vmware.bhenao.interfaces.WeeklyProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Random;
import java.util.StringTokenizer;

import static com.vmware.bhenao.constants.Constants.LINE_BREAK;

public class Boggle implements WeeklyProblem {

    private BufferedReader reader;
    private char[][] boggleBoard;

    public Boggle(BufferedReader reader) {
        this.reader = reader;
        this.boggleBoard = new char[4][4];
    }

    public Boggle(char[][] boggleBoard) {
        this.boggleBoard = boggleBoard;
    }

    @Override
    public void Start() throws IOException, StopPlayingException {
        System.out.println(LINE_BREAK);
        boolean selectedNumberWrong = true;
        int selectedNumber = 0;

        while (selectedNumberWrong) {
            System.out.println("You want to provide a Board or generate one random?  (1 - Provide / 2 - Random)");
            try {
                selectedNumber = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please provide a valid number");
            }
            selectedNumberWrong = false;
        }

        if (selectedNumber == 1) {
            System.out.println(LINE_BREAK);
            System.out.println("Please provide a valid game board. Use uppercase letters separated by comma. The board is a 4x4 matrix");
            System.out.println(LINE_BREAK);
            fillBoard();
            System.out.println(LINE_BREAK);
        } else {
            System.out.println(LINE_BREAK);
            fillRandomBoard();
            System.out.println(LINE_BREAK);
        }


        boolean nextWord = true;
        selectedNumber = 0;
        String word;

        while (nextWord) {
            printBoard();
            System.out.println(LINE_BREAK);
            System.out.println("Enter the word you want to search in the Boggle board");
            word = reader.readLine().toUpperCase(Locale.ROOT);
            boolean exists = wordExists(word);

            if (exists) {
                System.out.println(LINE_BREAK);
                System.out.println("The word: " + word + " is in the Boggle board!");
            } else {
                System.out.println(LINE_BREAK);
                System.out.println("The word: " + word + " is NOT in the Boggle board!");
            }

            System.out.println(LINE_BREAK);

            selectedNumberWrong = true;
            while (selectedNumberWrong) {
                System.out.println("You want to search for more words?  (1 - Yes / 2 - No)");
                try {
                    selectedNumber = Integer.parseInt(reader.readLine());
                    selectedNumberWrong = false;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please provide a valid number");
                }
                if (selectedNumber == 2) {
                    nextWord = false;
                }
            }
        }

        selectedNumberWrong = true;
        selectedNumber = 0;

        while (selectedNumberWrong) {
            System.out.println("You want to keep solving more problems?  (1 - Yes / 2 - No)");
            try {
                selectedNumber = Integer.parseInt(reader.readLine());
                selectedNumberWrong = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please provide a valid number");
            }
            if (selectedNumber == 2) {
                throw new StopPlayingException("Stop");
            }
        }
    }

    private void fillBoard() throws IOException {
        String line;
        StringTokenizer tokenizer;
        for (int i = 0; i < 4; i++) {
            line = reader.readLine();
            tokenizer = new StringTokenizer(line);
            for (int j = 0; j < 4; j++) {
                boggleBoard[i][j] = tokenizer.nextToken().toUpperCase(Locale.ROOT).charAt(0);
            }
        }
    }

    private void fillRandomBoard() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                char randomChar = (char) (random.nextInt(26) + 'A');
                boggleBoard[i][j] = randomChar;
            }
        }
    }

    public void printBoard() {
        String str = "|\t";

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                str += boggleBoard[i][j] + "\t";
            }

            System.out.println(str + "|");
            str = "|\t";
        }
    }

    public boolean wordExists(String word) {
        Queue<Character> wordQueue = new LinkedList<>();
        for (char c : word.toCharArray()) {
            wordQueue.add(c);
        }
        char initialWord = wordQueue.poll();

        boolean[][] visited;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (boggleBoard[i][j] == initialWord) {
                    visited = new boolean[4][4];
                    if (dfs(i, j, new LinkedList<>(wordQueue), visited)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(int x, int y, Queue<Character> wordQueue, boolean[][] visited) {
        if (wordQueue.isEmpty()) {
            return true;
        }
        char actualWord = wordQueue.poll();
        visited[x][y] = true;

        for (int i = x - 1; i <= x + 1 && i < 4; i++) {
            for (int j = y - 1; j <= y + 1 && j < 4; j++) {
                if (i >= 0 && j >= 0 && !visited[i][j] && boggleBoard[i][j] == actualWord) {
                    return dfs(i, j, wordQueue, visited);
                }
            }
        }
        return false;
    }
}