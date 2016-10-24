package com.taewonkim.android.tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import static android.R.attr.start;

public class MainActivity extends AppCompatActivity {

    FrameLayout ground;
    FrameLayout nextBlock;
    FrameLayout nextBlockList;

    Button rotate;
    Button left;
    Button right;
    Button down;
    Button start;

    int stageLevel = 1;

    Stage stage = new Stage();
    Block block = new Block();

    MainStage mainStage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ground = (FrameLayout) findViewById(R.id.ground);

        rotate = (Button) findViewById(R.id.btnRotate);
        down = (Button) findViewById(R.id.btnDown);
        left = (Button) findViewById(R.id.btnLeft);
        right = (Button) findViewById(R.id.btnRight);
        start = (Button) findViewById(R.id.btnStart);

        setStage(stageLevel);
        mainStage = new MainStage(this, stage, block);
        ground.addView(mainStage);

    }

    public void setStage(int stageLevel) {
        stage.setStageMap(stageLevel);
    }
}

