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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

public class Matching extends AppCompatActivity {
    private Button StopMatching;
    private String server = "withtaxi.wookoo.ze.am";
    private int Port = 5555;
    private String URL = String.format("ws://%s:%d/ws/chat/",server,Port);
    private String StartPoint;
    private String StopPoint;

    private InterstitialAd mInterstitialAd;

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
        StartPoint = intent.getStringExtra("StartPoint");
        StopPoint = intent.getStringExtra("StopPoint");
        //소켓 객체 만든후 핸들러 만들고 핸들러에다가 일 시키게 하고
        //StropMatching 눌렀으면 그 객체 죽이기 > 객체 =NULL;

        connectWebSocket();

        AdView mAdView = findViewById(R.id.adView5);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(Matching.this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4210582747176451/1284205789");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());




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

                        mWebSocketClient = null;

                        //객체 죽여버리기
                        Toast.makeText(Matching.this,"매칭이 취소되었습니다.",Toast.LENGTH_SHORT).show();
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
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

                                dialog.setPositiveButton("바로 문자보내기", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+receiveMessage));
                                        String message = "안녕하세요, "+StartPoint +"에서 "+StopPoint+"까지 같이 합승 할 사람입니다!";
                                        intent.putExtra("sms_body", message);


                                        if (mInterstitialAd.isLoaded()) {
                                            mInterstitialAd.show();
                                        }
                                        startActivity(intent);

                                        finish();

                                    }
                                });
                                dialog.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (mInterstitialAd.isLoaded()) {
                                            mInterstitialAd.show();
                                        }
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
                Toast.makeText(Matching.this,"오류가 발생하였습니다",Toast.LENGTH_SHORT).show();
                mWebSocketClient.close();
                finish();

            }

        };

        mWebSocketClient.connect();

    }


}
