package com.xx3.lepoox.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {

    private ArrayList<Operation> operations;

    /**
     * Constructor
     */
    public Solution() {

    }

    /**
     * Constructor
     *
     * @param operations The operations
     */
    public Solution(ArrayList<Operation> operations) {
        this.operations = operations;
    }

    /**
     * Get the operations
     *
     * @return The operations
     */
    public ArrayList<Operation> getOperations() {
        return operations;
    }

}
