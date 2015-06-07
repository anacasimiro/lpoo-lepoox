package com.xx3.lepoox.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A class that represents a Match
 */
public class Match {

    private int duration;
    private int result;
    private ArrayList<Integer> operands = new ArrayList<>();
    private ArrayList<Operation> operations = new ArrayList<>();

    /**
     * Constructor
     *
     * @param duration
     */
    public Match(int duration) {

        Random rand = new Random();

        this.duration = duration;

        for (int i = 0; i < 4; i++) {
            this.operands.add( rand.nextInt(8) + 1 );
        }

        for (int i = 0; i < 3; i++) {
            this.operations.add( Operation.values()[i] );
        }

        Collections.shuffle(this.operations);

        this.result = calculateResult(this.operations);

    }

    /**
     * Calculate the result
     *
     * @param operations The operations
     *
     * @return The result
     */
    public int calculateResult(ArrayList<Operation> operations) {

        int result = 0;

        if ( operations.get(0) == Operation.MULTIPLICATION ) {

            result = operations.get(0).apply(this.operands.get(0), this.operands.get(1));
            result = operations.get(1).apply(result, this.operands.get(2));
            result = operations.get(2).apply(result, this.operands.get(3));

        } else if ( operations.get(1) == Operation.MULTIPLICATION ) {

            result = operations.get(1).apply(this.operands.get(1), this.operands.get(2));
            result = operations.get(0).apply(this.operands.get(0), result);
            result = operations.get(2).apply(result, this.operands.get(3));

        } else if ( operations.get(2) == Operation.MULTIPLICATION ) {

            int product = operations.get(2).apply(this.operands.get(2), this.operands.get(3));
            result = operations.get(0).apply(this.operands.get(0), this.operands.get(1));
            result = operations.get(1).apply(result, product);

        }

        return result;

    }

    /**
     * Get the operands
     *
     * @return The operands
     */
    public ArrayList<Integer> getOperands() {
        return operands;
    }

    /**
     * Get the operations
     *
     * @return The operations
     */
    public ArrayList<Operation> getOperations() {
        return operations;
    }

    /**
     * Get the result
     *
     * @return The result
     */
    public int getResult() {
        return result;
    }

}
