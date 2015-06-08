package com.xx3.lepoox.Activities;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.xx3.lepoox.Model.Match;
import com.xx3.lepoox.Model.Problem;
import com.xx3.lepoox.Model.Solution;
import com.xx3.lepoox.R;
import com.xx3.lepoox.Utils.Packet;

import java.io.IOException;
import java.util.ArrayList;

public class HostGameActivity extends AppCompatActivity {

    static Client client;
    static Server server;
    static Problem problem;
    private Match match;
    private final int PORT = 44423;
    private int clientsReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_game);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        initServer();
        initClient();

        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));

        ((TextView)findViewById(R.id.ip_address)).setText(ip);

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
        GameActivity.host = true;

    }

    /**
     * Initialize Server
     */
    private void initServer() {

        clientsReady = 0;
        server = new Server();

        server.getKryo().register(Packet.newMatchRequest.class);
        server.getKryo().register(Packet.gameResult.class);
        server.getKryo().register(Problem.class);
        server.getKryo().register(Solution.class);
        server.getKryo().register(ArrayList.class);

        server.addListener(new Listener() {

            @Override
            public void connected(Connection connection) {
                super.connected(connection);

                if ( server.getConnections().length > 2 ) {
                    connection.close();
                }

            }

            @Override
            public void received(Connection connection, Object object) {
                super.received(connection, object);

                if ( object instanceof Packet.newMatchRequest ) {

                    clientsReady++;

                    if ( clientsReady == 2 ) {
                        match = new Match();
                        server.sendToAllTCP(match.getProblem());
                        clientsReady = 0;
                    }

                } else if ( object instanceof Solution ) {

                    Solution solution = (Solution) object;

                    if ( match.validateSolution(solution) ) {
                        server.sendToTCP(connection.getID(), new Packet.gameResult(true));
                        server.sendToAllExceptTCP(connection.getID(), new Packet.gameResult(false));
                    } else {
                        server.sendToTCP(connection.getID(), new Packet.gameResult(false));
                        server.sendToAllExceptTCP(connection.getID(), new Packet.gameResult(true));
                    }

                }

            }

        });

        new Thread(server).start();

        try {
            server.bind(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            client.connect(5000, "localhost", PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
