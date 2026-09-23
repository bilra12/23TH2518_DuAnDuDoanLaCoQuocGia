package duan.duandudoanlacoquocgia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnBatDau, btnKyLuc, btnCaiDat, btnHuongDan;

    SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnBatDau = findViewById(R.id.btnBatDau);
        btnKyLuc = findViewById(R.id.btnKyLuc);
        btnCaiDat = findViewById(R.id.btnCaiDat);
        btnHuongDan = findViewById(R.id.btnHuongDan);

        // Khởi tạo âm thanh
        soundManager = new SoundManager(this);
        soundManager.playBackgroundMusic();

        // Bắt đầu game
        btnBatDau.setOnClickListener(v -> {

            soundManager.playClick();

            startActivity(
                    new Intent(
                            MainActivity.this,
                            GameActivity.class
                    )
            );
        });

        // Kỷ lục
        btnKyLuc.setOnClickListener(v -> {

            soundManager.playClick();

            startActivity(
                    new Intent(
                            MainActivity.this,
                            HighScoreActivity.class
                    )
            );
        });

        // Cài đặt
        btnCaiDat.setOnClickListener(v -> {

            soundManager.playClick();

            startActivity(
                    new Intent(
                            MainActivity.this,
                            SettingsActivity.class
                    )
            );
        });

        // Hướng dẫn
        btnHuongDan.setOnClickListener(v -> {

            soundManager.playClick();

            // Sau này mình sẽ làm màn hình hướng dẫn ở đây
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (soundManager != null) {
            soundManager.release();
        }
    }
}