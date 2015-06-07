package com.xx3.lepoox.model;

/**
 * An Enum to represent the arithmetic operations
 */
public enum Operation {

    ADDITION {
        public int apply(int a, int b) { return a + b; }
    },
    SUBTRACTION {
        public int apply(int a, int b) { return a - b; }
    },
    MULTIPLICATION {
        public int apply(int a, int b) { return a * b; }
    };

    @Override
    public String toString() {

        switch (this) {
            case ADDITION: return "+";
            case SUBTRACTION: return "-";
            case MULTIPLICATION: return "x";
            default: throw new IllegalArgumentException();
        }

    }

    public abstract int apply(int a, int b);

}
