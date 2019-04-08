package am.ze.wookoo.withtaxi;


import android.content.Intent;
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

public class StartPinActivity extends AppCompatActivity {
    Button StartSinchang;
    Button StartKyung;
    Button StartBack;
    Button StartHyang2;
    Button StartFront;
    Button StartOnyang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_pin);
        StartSinchang = findViewById(R.id.StartSinchang);
        StartKyung = findViewById(R.id.StartKyung);
        StartBack = findViewById(R.id.StartBack);
        StartHyang2 = findViewById(R.id.StartHyang2);
        StartFront = findViewById(R.id.StartFront);
        StartOnyang = findViewById(R.id.StartOnyang);

        //MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713"); //앱 단위 입력
        //MobileAds.initialize(this,"ca-app-pub-4210582747176451~9351818945");
        //AdView adView = new AdView(this);
        //adView.setAdSize(AdSize.BANNER);
        //adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        //adView.setAdUnitId("ca-app-pub-4210582747176451/7919672999");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        List<Button> StationButtons = new ArrayList<Button>();
        StationButtons.add(StartOnyang);
        StationButtons.add(StartSinchang);
        for(final Button StationBut : StationButtons){
            StationBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goStartSinchang = new Intent(StartPinActivity.this, StartToStation.class);
                    goStartSinchang.putExtra("StartPoint",(String) StationBut.getText());

                    Log.d("시작버튼 눌림 ", (String) StationBut.getText());
                    startActivity(goStartSinchang);

                }
            });
        }


        List<Button> ButtonLists = new ArrayList<Button>();
        ButtonLists.add(StartKyung);
        ButtonLists.add(StartBack);
        ButtonLists.add(StartHyang2);
        ButtonLists.add(StartFront);
        for(final Button bu : ButtonLists){
            bu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent Starting = new Intent(StartPinActivity.this, StartToElse.class);
                    Starting.putExtra("StartPoint",(String) bu.getText());
                    Log.d("시작버튼 눌림 ", (String) bu.getText());
                    startActivity(Starting);
                }
            });
        }
    }
}
