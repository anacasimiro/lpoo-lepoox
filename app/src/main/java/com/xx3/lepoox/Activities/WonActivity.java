package com.xx3.lepoox.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
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

        ((ImageView) findViewById(R.id.won_backround)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            if (singlePlayer) {

                Intent showGameActivity = new Intent(WonActivity.this, GameActivity.class);
                startActivity(showGameActivity);
                GameActivity.singlePlayer = true;
                finish();

            } else {

                if (host) {
                    client.sendTCP(new Packet.newMatchRequest());
                } else {
                    client.sendTCP(new Packet.newMatchRequest());
                }

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
    public void onBackPressed() {
        super.onBackPressed();

        if ( !singlePlayer ) {

            if ( host ) {
                client.close();
                HostGameActivity.server.close();
            } else {
                client.close();
            }

        }

    }

}
