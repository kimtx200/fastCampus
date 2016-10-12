package com.taewonkim.android.basicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // 라디오 그룹 컨트롤
    RadioGroup rg;
    // 텍스트 뷰 결과 값 도출
    TextView tv;
    // 스위치 변수 선언
    Switch sw;
    // Toggle Button 선언
    ToggleButton tg;
    // progress bar 선언
    ProgressBar pb;
    // Seek Bar 선언
    SeekBar sb;
    TextView sb_tv;
    // Rationg Bar 선언
    RatingBar rb;
    TextView rb_tv;

    CheckBox cbDog, cbChicken, cbPig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb = (RatingBar)findViewById(R.id.ratingBar);
        rb_tv = (TextView)findViewById(R.id.rb_tv);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rb_tv.setText(rating+" / 5");
               // tv.setText(rating+" / 5");
            }
        });

        sb = (SeekBar)findViewById(R.id.seekBar);
        sb_tv = (TextView)findViewById(R.id.sb_tv);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sb_tv.setText(progress+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, seekBar.getProgress()+" 위치에서 터치가 시작됨", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, seekBar.getProgress()+" 위치에서 터치가 종료됨", Toast.LENGTH_SHORT).show();
            }
        });

        tv = (TextView) findViewById(R.id.textViewResult);
        pb = (ProgressBar)findViewById(R.id.progressBar);

        sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sw.isChecked()==true) {
                    tv.setText("Switch ON !");
                    pb.setVisibility(pb.VISIBLE);
                }
                else if(sw.isChecked()==false) {
                    tv.setText("Switch OFF !");
                    pb.setVisibility(pb.GONE);
                }
            }
        });

        // 여러개의 체크 박스가 존재
        // 이럴땐 외부에 그룹핑을 할 메소드를 새로이 생성 해줄것
        cbDog = (CheckBox) findViewById(R.id.checkBoxDog);
        cbChicken = (CheckBox) findViewById(R.id.checkBoxChicken);
        cbPig = (CheckBox) findViewById(R.id.checkBoxPig);

        // 각각의 체크 박스는 compound 변수를 불러 와 주어야 한다
        cbDog.setOnCheckedChangeListener(checkedChangeListener);
        cbPig.setOnCheckedChangeListener(checkedChangeListener);
        cbChicken.setOnCheckedChangeListener(checkedChangeListener);

        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 현재 체크된 라디오 버튼의 아이디를 가져온다.
                // int checked = rg.getCheckedRadioButtonId();

                // 라디오 버튼의 개수가 두개 이상일때는
                // Switch~Case를 쓴다.

                switch (checkedId) {
                    case R.id.radioApple:
                        tv.setText("APPLE :)");
                        break;
                    case R.id.radioOrange:
                        tv.setText("ORANGE ;)");
                        break;
                    case R.id.radioBanana:
                        tv.setText("BANANA :D");
                        break;
                }
            }
        });

        tg = (ToggleButton)findViewById(R.id.toggleButton);
        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(tg.isChecked())
                    tv.setText("Toggle ON !");
                else
                    tv.setText("Toggle OFF !!");
            }
        });
    }

    // 체크박스를 그룹핑 하기 위한 변수
    // 컴파운드 계열(체크박스, 토글 스위치 등 ...) 버튼에서 사용되는 체크변화를 감지하는 리스너
    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            // 중복 체크가 되면 이를 합쳐서 보여주기 위한
            // String Builder 선언
            StringBuilder sb = new StringBuilder();

            if (cbDog.isChecked())
                sb.append("DOG");

            if (cbPig.isChecked())
                sb.append("PIG");

            if (cbChicken.isChecked())
                sb.append("Chicken");

            tv.setText(sb);
        }
    };

}
