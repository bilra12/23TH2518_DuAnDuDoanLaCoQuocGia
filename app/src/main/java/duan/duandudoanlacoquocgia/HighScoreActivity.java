package duan.duandudoanlacoquocgia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HighScoreActivity extends AppCompatActivity {

    TextView tvKyLuc;
    Button btnXoaKyLuc, btnTrangChu;
    SoundManager soundManager;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        tvKyLuc = findViewById(R.id.tvKyLuc);
        btnXoaKyLuc = findViewById(R.id.btnXoaKyLuc);
        btnTrangChu = findViewById(R.id.btnTrangChu);

        preferences = getSharedPreferences(
                "GAME_DATA",
                MODE_PRIVATE
        );
        soundManager = new SoundManager(this);
        soundManager.playBackgroundMusic();
        hienThiKyLuc();

        // Xóa kỷ lục
        btnXoaKyLuc.setOnClickListener(v -> {

            soundManager.playClick();

            preferences.edit()
                    .putInt("DIEM_CAO", 0)
                    .apply();

            hienThiKyLuc();

            Toast.makeText(
                    HighScoreActivity.this,
                    "Đã xóa kỷ lục!",
                    Toast.LENGTH_SHORT
            ).show();
        });
        // Về trang chủ
        btnTrangChu.setOnClickListener(v -> {

            soundManager.playClick();

            Intent intent = new Intent(
                    HighScoreActivity.this,
                    MainActivity.class
            );

            intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
            );

            startActivity(intent);
            finish();
        });

    }

    private void hienThiKyLuc() {

        int kyLuc = preferences.getInt(
                "DIEM_CAO",
                0
        );

        tvKyLuc.setText(
                "🏆 KỶ LỤC CỦA BẠN\n\n" +
                        kyLuc + " điểm"
        );
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (soundManager != null) {
            soundManager.release();
        }
    }
}