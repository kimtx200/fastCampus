package com.taewonkim.android.broadcastreciever01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    static final String BROADCAST_ACTION = "com.taewonkim.android.MESSAGE";

    public MyReceiver() {
    }

    // context 사용 이유
    // 브로드캐스팅 리시버는 context 를 가지고 있지 않아
    // 시스템 상으로 Context 를 Wrapping 해줌으로써 접근 가능하게 됨.
    @Override
    public void onReceive(Context context, Intent intent) {
        // 전달 받은 인텐트로 부터 내가 받을 BR 인지를 판단
        // 이 때는 getIntent() 가 아닌 getAction() 을 사용한다.

        if (intent.getAction().equals(BROADCAST_ACTION)) {
            Intent temp = new Intent(context, CheckActivity.class);

            // FLAG_ACTIVITY_NEW_TASK   : 새로운 액티비티를 생성
            // FLAG_ACTIVITY_CLEAR_TOP  : 이전의 모든 것을 지우고 temp 구성요소를 Top 으로 올린다.
            temp.addFlags(temp.FLAG_ACTIVITY_NEW_TASK | temp.FLAG_ACTIVITY_CLEAR_TOP );
            context.startActivity(temp);
        }
    }
}
