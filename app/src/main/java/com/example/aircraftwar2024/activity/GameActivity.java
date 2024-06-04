package com.example.aircraftwar2024.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar2024.game.BaseGame;
import com.example.aircraftwar2024.game.EasyGame;
import com.example.aircraftwar2024.game.HardGame;
import com.example.aircraftwar2024.game.MediumGame;
import com.example.aircraftwar2024.record.Record;


public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";
    private Handler mainThreadHandler;
    private int gameType=0;
    public static int screenWidth,screenHeight;
    public static final int GAME_OVER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == GAME_OVER) {
                    Intent intent = new Intent(GameActivity.this, RankingActivity.class);
                    Record record = (Record)msg.obj;

                    intent.putExtra("record", record);
                    intent.putExtra("gameType", gameType);
                    startActivity(intent);

                }
            }
        };
        getScreenHW();

        if(getIntent() != null){
            gameType = getIntent().getIntExtra("gameType",1);
        }


        BaseGame baseGameView;
        switch (gameType) {
            case 1 -> baseGameView = new EasyGame(this);
            case 2 -> baseGameView = new MediumGame(this);
            case 3 -> baseGameView = new HardGame(this);
            default -> baseGameView = null;
        }
        setContentView(baseGameView);

    }

    public void getScreenHW(){
        //定义DisplayMetrics 对象
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getDisplay().getRealMetrics(dm);

        //窗口的宽度
        screenWidth= dm.widthPixels;
        //窗口高度
        screenHeight = dm.heightPixels;

        Log.i(TAG, "screenWidth : " + screenWidth + " screenHeight : " + screenHeight);
    }

    public Handler getMainThreadHandler(){
        return mainThreadHandler;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}