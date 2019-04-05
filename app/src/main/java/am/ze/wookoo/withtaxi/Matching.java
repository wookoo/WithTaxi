package am.ze.wookoo.withtaxi;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Matching extends AppCompatActivity {
    private Button StopMatching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        StopMatching = findViewById(R.id.StopMatching);
        StopMatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Matching.this);
                dialog.setTitle("매칭 취소 안내");
                dialog.setMessage("매칭을 취소하시겠습니까?");
// OK 버튼 이벤트
                dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //매칭 취소 작업
                        Toast.makeText(Matching.this,"매칭이 취소되었습니다.",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
// Cancel 버튼 이벤트
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


}
