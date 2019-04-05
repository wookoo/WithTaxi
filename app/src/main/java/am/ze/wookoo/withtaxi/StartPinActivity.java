package am.ze.wookoo.withtaxi;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
