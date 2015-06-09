package com.xx3.lepoox.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.xx3.lepoox.Model.Problem;
import com.xx3.lepoox.R;
import com.xx3.lepoox.Utils.Packet;

public class WonActivity extends AppCompatActivity {

    static boolean singlePlayer;
    static boolean host;
    static Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        singlePlayer = GameActivity.singlePlayer;
        host = GameActivity.host;

        if ( !singlePlayer ) {

            if ( host ) {
                client = HostGameActivity.client;
            } else {
                client = JoinGameActivity.client;
            }

            client.addListener(new Listener() {

                @Override
                public void disconnected(Connection connection) {
                    super.disconnected(connection);
                    WonActivity.this.finish();
                }

            });

        }

        findViewById(R.id.won_backround).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (singlePlayer) {

                    Intent showGameActivity = new Intent(WonActivity.this, GameActivity.class);
                    startActivity(showGameActivity);
                    GameActivity.singlePlayer = true;
                    finish();

                } else {

                    client.sendTCP(new Packet.newMatchRequest());
                    setContentView(R.layout.waiting_for_opponent);

                }

            }

        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if ( !singlePlayer ) {

            if ( host ) {
                client.close();
                HostGameActivity.server.close();
            } else {
                client.close();
            }

        }

        Intent showTwoPlayerActivity = new Intent(WonActivity.this, TwoPlayerActivity.class);
        showTwoPlayerActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(showTwoPlayerActivity);

    }

}
