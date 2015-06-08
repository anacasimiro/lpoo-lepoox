package com.xx3.lepoox.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xx3.lepoox.R;

public class GameModeActivity extends ActionBarActivity {

    private ImageView onePlayerMode;
    private ImageView twoPlayerMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        onePlayerMode = (ImageView)findViewById(R.id.onePlayerMode);
        twoPlayerMode = (ImageView)findViewById(R.id.twoPlayerMode);

        onePlayerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showGame = new Intent(GameModeActivity.this, GameActivity.class);
                startActivity(showGame);
            }
        });

        twoPlayerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showTwoPlayerGame = new Intent(GameModeActivity.this, TwoPlayerActivity.class);
                startActivity(showTwoPlayerGame);
            }
        });

    }



}
