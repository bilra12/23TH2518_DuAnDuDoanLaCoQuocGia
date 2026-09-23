package duan.duandudoanlacoquocgia;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Switch switchNhacNen, switchHieuUng;
    SeekBar seekBarNhacNen, seekBarHieuUng;
    Button btnLuuCaiDat, btnTrangChu;
    SharedPreferences preferences;
    SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchNhacNen = findViewById(R.id.switchNhacNen);
        switchHieuUng = findViewById(R.id.switchHieuUng);

        seekBarNhacNen = findViewById(R.id.seekBarNhacNen);
        seekBarHieuUng = findViewById(R.id.seekBarHieuUng);

        btnLuuCaiDat = findViewById(R.id.btnLuuCaiDat);

        preferences = getSharedPreferences("GAME_SETTINGS", MODE_PRIVATE);
        soundManager = new SoundManager(this);
        soundManager.playBackgroundMusic();
        // Lấy cài đặt đã lưu
        boolean nhacNen = preferences.getBoolean("NHAC_NEN", true);
        boolean hieuUng = preferences.getBoolean("HIEU_UNG", true);

        int amLuongNhac =
                preferences.getInt("AM_LUONG_NHAC", 50);

        int amLuongHieuUng =
                preferences.getInt("AM_LUONG_HIEU_UNG", 100);

        switchNhacNen.setChecked(nhacNen);
        switchHieuUng.setChecked(hieuUng);

        seekBarNhacNen.setProgress(amLuongNhac);
        seekBarHieuUng.setProgress(amLuongHieuUng);
        btnTrangChu = findViewById(R.id.btnTrangChu);
        // Lưu cài đặt
        btnLuuCaiDat.setOnClickListener(v -> {

            soundManager.playClick();

            preferences.edit()
                    .putBoolean("NHAC_NEN", switchNhacNen.isChecked())
                    .putBoolean("HIEU_UNG", switchHieuUng.isChecked())
                    .putInt("AM_LUONG_NHAC", seekBarNhacNen.getProgress())
                    .putInt("AM_LUONG_HIEU_UNG", seekBarHieuUng.getProgress())
                    .apply();

            Toast.makeText(
                    SettingsActivity.this,
                    "Đã lưu cài đặt!",
                    Toast.LENGTH_SHORT
            ).show();

            Intent intent = new Intent(
                    SettingsActivity.this,
                    MainActivity.class
            );

            intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
            );

            startActivity(intent);
            finish();
        });
        btnTrangChu.setOnClickListener(v -> {

            soundManager.playClick();

            Intent intent = new Intent(
                    SettingsActivity.this,
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
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (soundManager != null) {
            soundManager.release();
        }
    }
}