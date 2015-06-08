package com.xx3.lepoox.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xx3.lepoox.R;

public class TwoPlayerActivity extends ActionBarActivity {

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
                Toast.makeText(TwoPlayerActivity.this, "Coming soon...", Toast.LENGTH_SHORT).show();
            }
        });

        joinGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TwoPlayerActivity.this, "Coming soon...", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
