package com.vmware.bhenao.boggle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BoggleTest {

    private char[][] boggleBoard;
    Boggle boggle;

    @BeforeEach
    public void setup() {
        boggleBoard = new char[4][4];

        boggleBoard[0] = new char[]{'R', 'H', 'R', 'E'};
        boggleBoard[1] = new char[]{'Y', 'P', 'C', 'S'};
        boggleBoard[2] = new char[]{'W', 'N', 'S', 'N'};
        boggleBoard[3] = new char[]{'T', 'E', 'G', 'O'};

        boggle = new Boggle(boggleBoard);
    }

    @Test
    void testExistingWords() {
        String[] words = {"SNOG", "RPSO", "RYPNSGONSE"};
        for (String word : words) {
            assertTrue(boggle.wordExists(word));
        }
    }

    @Test
    void testNonExistingWords() {
        String[] words = {"SNOGSS", "RHS"};
        for (String word : words) {
            assertFalse(boggle.wordExists(word));
        }
    }
}