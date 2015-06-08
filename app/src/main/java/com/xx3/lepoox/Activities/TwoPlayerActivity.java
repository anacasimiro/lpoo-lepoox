package com.xx3.lepoox.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.xx3.lepoox.R;

public class TwoPlayerActivity extends AppCompatActivity {

    private ImageView hostGame;
    private ImageView joinGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);

        hostGame = (ImageView)findViewById(R.id.hostGame);
        joinGame = (ImageView)findViewById(R.id.joinGame);

        hostGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showHostGame = new Intent(TwoPlayerActivity.this, HostGameActivity.class);
                startActivity(showHostGame);
            }
        });

        joinGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showJoinGame = new Intent(TwoPlayerActivity.this, JoinGameActivity.class);
                startActivity(showJoinGame);
            }
        });

    }


}
