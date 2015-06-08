package com.xx3.lepoox.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esotericsoftware.kryonet.Client;
import com.xx3.lepoox.Model.Problem;
import com.xx3.lepoox.Model.Solution;
import com.xx3.lepoox.R;
import com.xx3.lepoox.Model.Match;
import com.xx3.lepoox.Model.Operation;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private int opCount;

    static boolean singlePlayer;

    private Match match;
    private Client client;

    private Problem problem;
    private Solution solution;

    private ArrayList<Operation> myOperations;

    private TextView firstOperand;
    private TextView secondOperand;
    private TextView thirdOperand;
    private TextView fourthOperand;
    private TextView firstOperation;
    private TextView secondOperation;
    private TextView thirdOperation;
    private TextView result;
    private ImageView addition;
    private ImageView subtraction;
    private ImageView multiplication;
    private ImageView reset;
    private ImageView done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();

    }

    /**
     * Initialize game
     */
    private void init() {

        opCount = 0;

        if ( singlePlayer ) {
            match = new Match();
            problem = match.getProblem();
        } else {
            client = HostGameActivity.client;
            problem = HostGameActivity.problem;
        }

        myOperations = new ArrayList<>();

        firstOperand    = (TextView)findViewById(R.id.firstOperand);
        secondOperand   = (TextView)findViewById(R.id.secondOperand);
        thirdOperand    = (TextView)findViewById(R.id.thirdOperand);
        fourthOperand   = (TextView)findViewById(R.id.fourthOperand);
        firstOperation  = (TextView)findViewById(R.id.firstOperation);
        secondOperation = (TextView)findViewById(R.id.secondOperation);
        thirdOperation  = (TextView)findViewById(R.id.thirdOperation);
        result          = (TextView)findViewById(R.id.result);
        addition        = (ImageView)findViewById(R.id.addition);
        subtraction     = (ImageView)findViewById(R.id.subtraction);
        multiplication  = (ImageView)findViewById(R.id.multiplication);
        reset           = (ImageView)findViewById(R.id.game_action_reset);
        done            = (ImageView)findViewById(R.id.game_action_done);

        firstOperand.setText( Integer.toString( problem.getOperands().get(0) ) );
        secondOperand.setText( Integer.toString( problem.getOperands().get(1) ) );
        thirdOperand.setText( Integer.toString( problem.getOperands().get(2) ) );
        fourthOperand.setText( Integer.toString( problem.getOperands().get(3) ) );
        result.setText(Integer.toString(problem.getResult()));

        firstOperation.setVisibility(View.INVISIBLE);
        secondOperation.setVisibility(View.INVISIBLE);
        thirdOperation.setVisibility(View.INVISIBLE);

        done.setVisibility(View.GONE);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (opCount) {
                    case 0:
                        firstOperation.setText(Operation.ADDITION.toString());
                        firstOperation.setVisibility(View.VISIBLE);
                        opCount++;
                        break;
                    case 1:
                        secondOperation.setText(Operation.ADDITION.toString());
                        secondOperation.setVisibility(View.VISIBLE);
                        opCount++;
                        break;
                    case 2:
                        thirdOperation.setText(Operation.ADDITION.toString());
                        thirdOperation.setVisibility(View.VISIBLE);
                        opCount++;
                        break;
                }

                myOperations.add(Operation.ADDITION);

                addition.setVisibility(View.GONE);

                if ( opCount == 3 ) {
                    done.setVisibility(View.VISIBLE);
                }

            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (opCount) {
                    case 0:
                        firstOperation.setText(Operation.SUBTRACTION.toString());
                        firstOperation.setVisibility(View.VISIBLE);
                        opCount++;
                        break;
                    case 1:
                        secondOperation.setText(Operation.SUBTRACTION.toString());
                        secondOperation.setVisibility(View.VISIBLE);
                        opCount++;
                        break;
                    case 2:
                        thirdOperation.setText(Operation.SUBTRACTION.toString());
                        thirdOperation.setVisibility(View.VISIBLE);
                        opCount++;
                        break;
                }

                myOperations.add(Operation.SUBTRACTION);

                subtraction.setVisibility(View.GONE);

                if (opCount == 3) {
                    done.setVisibility(View.VISIBLE);
                }

            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (opCount) {
                    case 0:
                        firstOperation.setText(Operation.MULTIPLICATION.toString());
                        firstOperation.setVisibility(View.VISIBLE);
                        opCount++;
                        break;
                    case 1:
                        secondOperation.setText(Operation.MULTIPLICATION.toString());
                        secondOperation.setVisibility(View.VISIBLE);
                        opCount++;
                        break;
                    case 2:
                        thirdOperation.setText(Operation.MULTIPLICATION.toString());
                        thirdOperation.setVisibility(View.VISIBLE);
                        opCount++;
                        break;
                }

                myOperations.add(Operation.MULTIPLICATION);

                multiplication.setVisibility(View.GONE);

                if ( opCount == 3 ) {
                    done.setVisibility(View.VISIBLE);
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstOperation.setText("");
                secondOperation.setText("");
                thirdOperation.setText("");

                addition.setVisibility(View.VISIBLE);
                subtraction.setVisibility(View.VISIBLE);
                multiplication.setVisibility(View.VISIBLE);

                myOperations.clear();

                firstOperation.setVisibility(View.INVISIBLE);
                secondOperation.setVisibility(View.INVISIBLE);
                thirdOperation.setVisibility(View.INVISIBLE);

                done.setVisibility(View.GONE);

                opCount = 0;

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( opCount == 3 ) {

                    solution = new Solution(myOperations);

                    if ( singlePlayer ) {

                        if (match.validateSolution(solution)) {
                            Toast.makeText(GameActivity.this, "RIGHT! You won!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(GameActivity.this, "WRONG! You lost!", Toast.LENGTH_SHORT).show();
                        }

                    } else {



                    }



                }

            }
        });

    }

}
