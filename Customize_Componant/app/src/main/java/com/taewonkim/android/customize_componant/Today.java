package com.taewonkim.android.customize_componant;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 태원 on 2016-10-20.
 */

public class Today extends TextView {

    public String delimiter;

    // 예제 1
    public Today(Context context) {
        super(context);
    }

    // 예제 2 - 여기서 주 작업이 이루어 짐
    public Today(Context context, AttributeSet attrs) {
        super(context, attrs);

        // res/values/attrs.xml 에 정의된 어트리뷰트를 가져온다
        // 이름이 <declare-styleable name="Today"> 로 정의 된 속성 리스트를 가져오고
        TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.Today);
        int size = typed.getIndexCount();

        // 'Today 라는 속성 리스트' 내에 있는 구체적인 속성 값들을 순차적으로 가져 온다.
        // 커스텀으로 만들어 준 구체적인 속성 중 하나가 'Today_delimiter' 이다.
        for (int i = 0; i < size; i++) {
            int attr = typed.getIndex(i);
            switch(attr){
                case R.styleable.Today_delimiter :
                    delimiter = typed.getString(attr);
                    setDate();
                    break;

                default:
                    break;

            }
        }
    }

    private void setDate(){

        // 오늘 날짜를 가져온 뒤
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY"+delimiter+"MM"+delimiter+"DD");
        // 기존에 존재하는 방법으로 set Text(값 입력)을 해 준다.
        setText(sdf.format(today));
    }


    // 예제 3
    public Today(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
