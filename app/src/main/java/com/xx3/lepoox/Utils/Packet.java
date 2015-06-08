package com.xx3.lepoox.Utils;

import com.xx3.lepoox.Model.Problem;
import com.xx3.lepoox.Model.Solution;

public class Packet {

    public static class newMatchRequest { }

    public static class gameResult {

        public boolean victory;

        /**
         * Constructor
         */
        public gameResult() {

        }

        /**
         * Constructor
         *
         * @param victory The gameResult
         */
        public gameResult(boolean victory) {
            this.victory = victory;
        }

    }

    public static class problemPacket {

        public Problem problem;

        /**
         * Constructor
         */
        public problemPacket() {

        }

        /**
         * Constructor
         *
         * @param problem The problem
         */
        public problemPacket(Problem problem) {
            this.problem = problem;
        }

    }

    public static class solutionPacket {

        public Solution solution;

        /**
         * Constructor
         */
        public solutionPacket() {

        }

        /**
         * Constructor
         *
         * @param solution The solution
         */
        public solutionPacket(Solution solution) {
            this.solution = solution;
        }

    }

}
