package am.ze.wookoo.withtaxi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

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
                String PhoneNum = String.valueOf(PhoneNumEdit.getText());
                ShowDialog swd = new ShowDialog(StartPoint,StopPoint,PhoneNum,RidingPerson,ReadyToMatching.this,ReadyToMatching.this);
                swd.Show();
            }
        });



    }
}
