package am.ze.wookoo.withtaxi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    //private  WebSocketClient mWebSocketClient; //소켓 디버깅용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connectWebSocket();
        Log.d("Tag","시작됨");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //mWebSocketClient.close();
                startActivity(new Intent(MainActivity.this,StartPinActivity.class));
                finish();
            }
        },0);

    }


    /*
    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://192.168.35.187:8080/ws/chat/onyang_to_kyunghee+1234/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {

            @Override

            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");

            }
            @Override
            public void onMessage(String s) {
                final String message = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("받은 메세지" ,message);
                    }

                });

            }



            @Override

            public void onClose(int i, String s, boolean b) {

                Log.i("Websocket", "Closed " + s);

            }



            @Override

            public void onError(Exception e) {

                Log.i("Websocket", "Error " + e.getMessage());

            }

        };

        mWebSocketClient.connect();

    }
    */


}
