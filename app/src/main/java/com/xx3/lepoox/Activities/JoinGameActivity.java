package com.xx3.lepoox.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.xx3.lepoox.Model.Problem;
import com.xx3.lepoox.Model.Solution;
import com.xx3.lepoox.R;
import com.xx3.lepoox.Utils.Packet;

import java.io.IOException;
import java.util.ArrayList;

public class JoinGameActivity extends AppCompatActivity {

    static Client client;
    static Problem problem;
    private String IP_ADDRESS;
    private final int PORT = 44423;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            IP_ADDRESS = String.valueOf(((EditText)findViewById(R.id.ip_address)).getText());
            initClient();

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Launch GameActivity
     */
    private void launchGameActivity() {

        Intent launchGameActivity = new Intent(this, GameActivity.class);
        startActivity(launchGameActivity);
        GameActivity.singlePlayer = false;
        GameActivity.host = false;

    }

    /**
     * Initialize Client
     */
    private void initClient() {

        client = new Client();

        client.getKryo().register(Packet.newMatchRequest.class);
        client.getKryo().register(Packet.gameResult.class);
        client.getKryo().register(Problem.class);
        client.getKryo().register(Solution.class);
        client.getKryo().register(ArrayList.class);

        client.addListener(new Listener() {

            @Override
            public void connected(Connection connection) {
                super.connected(connection);
                client.sendTCP(new Packet.newMatchRequest());
            }

            @Override
            public void received(Connection connection, Object object) {
                super.received(connection, object);

                if (object instanceof Problem) {

                    Log.i("[CLIENT]", "Problem received!");

                    problem = (Problem) object;
                    launchGameActivity();

                }

            }

        });

        new Thread(client).start();

        try {
            client.connect(5000, IP_ADDRESS, PORT);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Can't connect to server", Toast.LENGTH_SHORT).show();
        }

    }

}
