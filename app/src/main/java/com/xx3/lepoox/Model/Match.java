package com.xx3.lepoox.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A class that represents a Match
 */
public class Match {

    private Problem problem;
    private Solution solution;

    /**
     * Constructor
     */
    public Match() {

        Random rand = new Random();
        ArrayList<Integer> operands = new ArrayList<>();
        ArrayList<Operation> operations = new ArrayList<>();


        for (int i = 0; i < 4; i++) {
            operands.add( rand.nextInt(8) + 1 );
        }

        for (int i = 0; i < 3; i++) {
            operations.add( Operation.values()[i] );
        }

        Collections.shuffle(operations);

        this.problem = new Problem(operands, calculateResult(operands, operations));
        this.solution = new Solution(operations);

    }

    /**
     * Calculate the result
     *
     * @param operands The operands
     * @param operations The operations
     *
     * @return The result
     */
    public int calculateResult(ArrayList<Integer> operands, ArrayList<Operation> operations) {

        int result = 0;

        if ( operations.get(0) == Operation.MULTIPLICATION ) {

            result = operations.get(0).apply(operands.get(0), operands.get(1));
            result = operations.get(1).apply(result, operands.get(2));
            result = operations.get(2).apply(result, operands.get(3));

        } else if ( operations.get(1) == Operation.MULTIPLICATION ) {

            result = operations.get(1).apply(operands.get(1), operands.get(2));
            result = operations.get(0).apply(operands.get(0), result);
            result = operations.get(2).apply(result, operands.get(3));

        } else if ( operations.get(2) == Operation.MULTIPLICATION ) {

            int product = operations.get(2).apply(operands.get(2), operands.get(3));
            result = operations.get(0).apply(operands.get(0), operands.get(1));
            result = operations.get(1).apply(result, product);

        }

        return result;

    }

    /**
     * Validate solution
     *
     * @param solution The solution
     *
     * @return True if valid solution, false otherwise
     */
    public boolean validateSolution(Solution solution) {
        return calculateResult(problem.getOperands(), solution.getOperations()) == problem.getResult();
    }

    /**
     * Get the Problem
     *
     * @return The Problem
     */
    public Problem getProblem() {
        return problem;
    }

    /**
     * Get the Solution
     *
     * @return The Solution
     */
    public Solution getSolution() {
        return solution;
    }

}
