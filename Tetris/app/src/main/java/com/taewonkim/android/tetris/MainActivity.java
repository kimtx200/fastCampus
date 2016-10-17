package com.taewonkim.android.tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout ground;
    FrameLayout nextBlock;
    FrameLayout nextBlockList;

    Button up;
    Button left;
    Button right;
    Button down;
    Button rotate;

    int stageLevel = 1;

    Stage stage = new Stage();
    Block block = new Block();

    MainStage mainStage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ground = (FrameLayout) findViewById(R.id.ground);

        up = (Button) findViewById(R.id.btnUp);
        down = (Button) findViewById(R.id.btnDown);
        left = (Button) findViewById(R.id.btnLeft);
        right = (Button) findViewById(R.id.btnRight);
        rotate = (Button) findViewById(R.id.btnRotate);

        mainStage = new MainStage(this, stage, block);
        setStage(1);

    }

    public void setStage(int stageLevel) {
        stage.setStageMap(stageLevel);
        stage.getCurrentStage();

        block.setBlock();
        block.getCurrentBlock();
    }

}

