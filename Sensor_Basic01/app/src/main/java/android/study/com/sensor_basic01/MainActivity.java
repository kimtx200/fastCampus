package android.study.com.sensor_basic01;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/*
    - 센서 동작 기본 흐름

    1. SensorManager 생성
    2. Sensor 객체 생성 (사용할 Sensor Type 선택)
    3. Sensor Listener 작성
    4. Listener 등록 및 Sensor 값 받기
    5. Listener 해제

    - 동작 속도
    FASTEST, GAME, UI, NORMAL ( 용도에 따라 자원 성능을 조절 할 수 있다)

    - 정확도
    HIGH, MEDIUM, LOW

 */

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager manager;

    Sensor acc;
    Sensor light;

    TextView tvLight;
    TextView tvAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        acc = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        light = manager.getDefaultSensor(Sensor.TYPE_LIGHT);

        tvLight = (TextView) findViewById(R.id.lightValue);
        tvAcc = (TextView) findViewById(R.id.accValue);

    }

    // 여기서 센서 값이 변경 됨
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        synchronized (this){
            // 센서에서 념겨주는 배열의 값들을 가져 옴
            // 보통은 x,y,z 값

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            switch(sensorEvent.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    tvAcc.setText("x="+x+"y="+y+"z="+z);
                    break;

                case Sensor.TYPE_LIGHT:
                    tvLight.setText("x="+x+"y="+y+"z="+z);
                    break;
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    // 센서를 켜는 시점
    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this,acc,SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,light,SensorManager.SENSOR_DELAY_NORMAL);
    }


    // 센서 동작을 멈추는 시점
    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}
