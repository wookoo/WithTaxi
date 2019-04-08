package am.ze.wookoo.withtaxi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class StartToElse extends AppCompatActivity {

    private String StartPoint;
    private String StopPoint;
    Button StopSinchang;
    Button StopOnyang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_to_else);
        Intent intent = getIntent();
        StartPoint = intent.getStringExtra("StartPoint");
        Log.d("인텐트 수신 ",StartPoint);

        StopOnyang = findViewById(R.id.StopOnyang);
        StopSinchang = findViewById(R.id.StopSinchang);
        List<Button> ButtonsLists = new ArrayList<Button>();
        ButtonsLists.add(StopOnyang);
        ButtonsLists.add(StopSinchang);

        //MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713"); //앱 단위 입력
        //MobileAds.initialize(this,"ca-app-pub-4210582747176451~9351818945");
        //AdView adView = new AdView(this);
        //adView.setAdSize(AdSize.BANNER);
        //adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        //adView.setAdUnitId("ca-app-pub-4210582747176451/7919672999");

        AdView mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        for(final Button buttons : ButtonsLists){
            buttons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //다이얼로그 띄우기
                    StopPoint = (String) buttons.getText();
                    Intent intent = new Intent(StartToElse.this,ReadyToMatching.class);
                    intent.putExtra("StartPoint",StartPoint);
                    intent.putExtra("StopPoint",StopPoint);
                    startActivity(intent);

                    //ShowDialog swd = new ShowDialog(StartPoint,StopPoint,StartToElse.this,StartToElse.this);
                    //swd.Show();



                }
            });
        }



    }
}
