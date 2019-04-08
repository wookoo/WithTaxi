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
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class StartToStation extends AppCompatActivity {
    private String StartPoint;
    private String StopPoint;

    Button StopFront;
    Button StopHyang2;
    Button StopBack;
    Button StopKyung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_to_station);
        Intent intent = getIntent();
        StartPoint = intent.getStringExtra("StartPoint");
        Log.d("인텐트 수신 ",StartPoint);

        StopFront = findViewById(R.id.StopFront);
        StopHyang2 = findViewById(R.id.StopHyang2);
        StopBack = findViewById(R.id.StopBack);
        StopKyung = findViewById(R.id.StopKyung);

        List<Button> ButtonLists = new ArrayList<Button>();
        ButtonLists.add(StopFront);
        ButtonLists.add(StopHyang2);
        ButtonLists.add(StopBack);
        ButtonLists.add(StopKyung);

        AdView mAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        for (final Button buttons : ButtonLists){
            buttons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //다이얼로그 띄우기
                    StopPoint = (String) buttons.getText();
                    Intent intent = new Intent(StartToStation.this,ReadyToMatching.class);
                    intent.putExtra("StartPoint",StartPoint);
                    intent.putExtra("StopPoint",StopPoint);
                    startActivity(intent);

                   // ShowDialog swd = new ShowDialog(StartPoint,StopPoint,StartToStation.this,StartToStation.this);
                   // swd.Show();


                }
            });
        }


    }
}
