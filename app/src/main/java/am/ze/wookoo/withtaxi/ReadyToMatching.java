package am.ze.wookoo.withtaxi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ReadyToMatching extends AppCompatActivity {
    private String StartPoint;
    private String StopPoint;
    private Spinner RiderPersonSpinner;
    private Button StartMatching;
    private EditText PhoneNumEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_to_matching);
        Intent intent = getIntent();
        StartPoint = intent.getStringExtra("StartPoint");
        StopPoint = intent.getStringExtra("StopPoint");
        RiderPersonSpinner = findViewById(R.id.spinner);
        PhoneNumEdit = findViewById(R.id.InputPhone);

        AdView mAdView = findViewById(R.id.adView4);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        final ArrayList<String> RidePerson = new ArrayList<String>();
        RidePerson.add("1명");
        RidePerson.add("2명");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                RidePerson);
        RiderPersonSpinner.setAdapter(arrayAdapter);


        StartMatching = findViewById(R.id.StartMatching);
        StartMatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String RidingPerson = RiderPersonSpinner.getSelectedItem().toString();
                String PhoneNum = String.valueOf(PhoneNumEdit.getText()).trim();
                Log.d("연락처 ",PhoneNum);
                if (!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", PhoneNum)){ //정규 표현식을 사용하여 전화번호가 올바르지 않다면

                    Toast.makeText(ReadyToMatching.this,"올바를 번호를 입력해주세요",Toast.LENGTH_SHORT).show(); //오류를 띄운다.
                }
                else{
                    ShowDialog swd = new ShowDialog(StartPoint,StopPoint,PhoneNum,RidingPerson,ReadyToMatching.this,ReadyToMatching.this); //오류가 발생하지 않을 경우 Dialog 를 띄운다.
                    swd.Show();
                }

            }
        });
    }
}
