package am.ze.wookoo.withtaxi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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
                JSONObject stationLists = new JSONObject();
                try {
                    stationLists.put("온양온천역","onyang");
                    stationLists.put("신창역","sinchang");
                    stationLists.put("경희학성","kyunghee");
                    stationLists.put("학교후문","schoolback");
                    stationLists.put("향설 2관","hyang2");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context,"오류가 발생했습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                StartPoint = StartPoint.trim();
                StopPoint = StopPoint.trim();

                try {
                    String TempURL = stationLists.get(StartPoint)+"_to_" + stationLists.get(StopPoint)+"+"+PhoneNum;
                    //배차 시작
                    Toast.makeText(context,"매칭을 시도합니다." + TempURL,Toast.LENGTH_SHORT).show();
                    Log.d("임시 URL",TempURL);

                    Intent intent = new Intent(context,Matching.class);

                    Activity.startActivity(intent);
                    Activity.finish();
                } catch (JSONException e) {
                    Toast.makeText(context,"오류가 발생했습니다" ,Toast.LENGTH_SHORT).show();
                    return;
                }




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
