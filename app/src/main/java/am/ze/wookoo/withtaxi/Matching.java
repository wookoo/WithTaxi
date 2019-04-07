package am.ze.wookoo.withtaxi;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

public class Matching extends AppCompatActivity {
    private Button StopMatching;
    private String server = "192.168.35.187";
    private int Port = 8080;
    private String URL = String.format("ws://%s:%d/ws/chat/",server,Port);

    private WebSocketClient mWebSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        StopMatching = findViewById(R.id.StopMatching);
        Intent intent = getIntent();
        URL += intent.getStringExtra("URL");
        Log.d("받은 URL",URL);
        URL = URL.trim();
        //소켓 객체 만든후 핸들러 만들고 핸들러에다가 일 시키게 하고
        //StropMatching 눌렀으면 그 객체 죽이기 > 객체 =NULL;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                connectWebSocket();
            //여기에 소켓 객체 만든 후 작업 하기
            }
        },1000);




        StopMatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Matching.this);
                dialog.setTitle("매칭 취소 안내");
                dialog.setMessage("매칭을 취소하시겠습니까?");
                dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //매칭 취소 작업
                        mWebSocketClient.close();

                        //객체 죽여버리기
                        Toast.makeText(Matching.this,"매칭이 취소되었습니다.",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                dialog.setNegativeButton("아니오",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
            }
        });

        //작업 추가
    }
    @Override public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(Matching.this,"뒤로 가실려면 매칭 취소 버튼을 눌러주세요.",Toast.LENGTH_SHORT).show();
    }


    private void connectWebSocket() {
        URI uri;
        Log.d("URL링크",URL);
        try {
            uri = new URI(URL);
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

                        try {
                            JSONObject json = new JSONObject(message);
                            final String receiveMessage = String.valueOf(json.get("message"));
                            Log.d("Json메세지", receiveMessage);
                            if(!receiveMessage.equals("")){

                                mWebSocketClient.close();



                                AlertDialog.Builder dialog = new AlertDialog.Builder(Matching.this);
                                dialog.setTitle("매칭 안내");
                                dialog.setMessage("매칭에 성공하였습니다.\n" + receiveMessage +"로 상대방에게 문자를 보내보세요.");

                                dialog.setPositiveButton("바로 전화걸기", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:"+receiveMessage));
                                        startActivity(intent);
                                        finish();

                                    }
                                });
                                dialog.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                                dialog.setCancelable(false);
                                dialog.show();


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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


}
