package com.vmware.bhenao;

import com.vmware.bhenao.boggle.Boggle;
import com.vmware.bhenao.exceptions.StopPlayingException;
import com.vmware.bhenao.interfaces.WeeklyProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.vmware.bhenao.constants.Constants.BOGGLE_PROBLEM;
import static com.vmware.bhenao.constants.Constants.BOGGLE_PROBLEM_NUMBER;
import static com.vmware.bhenao.constants.Constants.CHARACTER_FILLING;
import static com.vmware.bhenao.constants.Constants.EXIT;
import static com.vmware.bhenao.constants.Constants.EXIT_NUMBER;
import static com.vmware.bhenao.constants.Constants.LINE_BREAK;

@SpringBootApplication
public class ProblemSolver implements CommandLineRunner {


    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(ProblemSolver.class, args);
        log.info("APPLICATION FINISHED");
    }

    private void printInitialInformation() {
        System.out.println(CHARACTER_FILLING);
        System.out.println("####################################### Problem Solver #######################################");
        System.out.println("##################################### bhenao@vmware.com ######################################");
        System.out.println(CHARACTER_FILLING);
        System.out.println(LINE_BREAK);
    }

    private void printProblemsOptions() {
        System.out.println("Select the number problem you want to solve");
        System.out.println(LINE_BREAK);
        System.out.println(BOGGLE_PROBLEM_NUMBER + " - " + BOGGLE_PROBLEM);
        System.out.println(EXIT_NUMBER + " - " + EXIT);
    }

    private void printFinalInformation() {
        System.out.println(LINE_BREAK);
        System.out.println(CHARACTER_FILLING);
        System.out.println(CHARACTER_FILLING);
        System.out.println(CHARACTER_FILLING);
        System.out.println(CHARACTER_FILLING);
    }

    private static Logger log = LoggerFactory
            .getLogger(ProblemSolver.class);

    @Override
    public void run(String... args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean wantToPlay = true;
        WeeklyProblem weeklyProblem = null;
        int selectedNumber = -1;

        while (wantToPlay) {
            printInitialInformation();
            printProblemsOptions();
            printFinalInformation();

            try {
                selectedNumber = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println(("Invalid input. Please provide a valid number"));
            }

            switch (selectedNumber) {
                case BOGGLE_PROBLEM_NUMBER:
                    weeklyProblem = new Boggle(reader);
                    break;
                case EXIT_NUMBER:
                    System.exit(0);
                    break;
                default:
                    System.out.println(("Wrong number. Please select a problem number to solve"));
            }

            if (weeklyProblem != null) {
                try {
                    weeklyProblem.Start();
                } catch (StopPlayingException e) {
                    wantToPlay = false;
                }
            }
        }
    }
}
