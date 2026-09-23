package duan.duandudoanlacoquocgia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView tvKetQua, tvDiem, tvKyLuc;
    Button btnChoiLai, btnTrangChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        tvKetQua = findViewById(R.id.tvKetQua);
        tvDiem = findViewById(R.id.tvDiem);
        tvKyLuc = findViewById(R.id.tvKyLuc);

        btnChoiLai = findViewById(R.id.btnChoiLai);
        btnTrangChu = findViewById(R.id.btnTrangChu);

        // Nhận điểm từ GameActivity
        int diem = getIntent().getIntExtra("DIEM", 0);

        // Lấy kỷ lục
        SharedPreferences preferences =
                getSharedPreferences("GAME_DATA", MODE_PRIVATE);

        int kyLuc = preferences.getInt("DIEM_CAO", 0);

        tvDiem.setText("Điểm của bạn: " + diem);
        tvKyLuc.setText("Kỷ lục: " + kyLuc);

        // Chơi lại
        btnChoiLai.setOnClickListener(v -> {

            Intent intent = new Intent(ResultActivity.this, GameActivity.class);

            startActivity(intent);

            finish();
        });

        // Về trang chủ
        btnTrangChu.setOnClickListener(v -> {

            Intent intent = new Intent(ResultActivity.this, MainActivity.class);

            intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
            );

            startActivity(intent);

            finish();
        });
    }
}