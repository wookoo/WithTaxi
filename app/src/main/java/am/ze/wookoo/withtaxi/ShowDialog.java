package am.ze.wookoo.withtaxi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class ShowDialog {

    private String StartPoint;
    private String StopPoint;
    private Context context;
    private Activity Activity;
    private String RidingPerson;
    private String PhoneNum;

    public ShowDialog(String StartPoint, String StopPoint,String PhoneNum,String RidingPerson, Context context, Activity Activity){
        this.StartPoint = StartPoint;
        this.StopPoint = StopPoint;
        this.context = context;
        this.Activity = Activity;
        this.RidingPerson = RidingPerson;
        this.PhoneNum = PhoneNum;
    }


    public void Show(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("경로 설정 안내");
        dialog.setMessage("출발지 : " + StartPoint + "\n도착지 : " + StopPoint + "\n탑승인원 : "+RidingPerson+"\n전화번호 : " + PhoneNum +"\n\n입력한 정보가 맞다면 \"예\" 버튼을 눌러주세요.");
// OK 버튼 이벤트
        dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                //배차 시작
                Toast.makeText(context,"매칭을 시도합니다.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,Matching.class);
                Activity.startActivity(intent);
                Activity.finish();


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
}
