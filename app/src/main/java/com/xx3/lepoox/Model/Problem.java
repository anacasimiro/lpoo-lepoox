package com.xx3.lepoox.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Problem implements Serializable {

    private ArrayList<Integer> operands;
    private int result;

    /**
     * Constructor
     */
    public Problem() {

    }

    /**
     * Constructor
     *
     * @param operands The operands
     * @param result The result
     */
    public Problem(ArrayList<Integer> operands, int result) {
        this.operands = operands;
        this.result = result;
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
     * Get the result
     *
     * @return The result
     */
    public int getResult() {
        return result;
    }

}
