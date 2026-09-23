package duan.duandudoanlacoquocgia;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    TextView tvCauSo, tvThoiGian, tvCauHoi, tvDiem;
    TextView tvKetQua, tvGioiThieu;

    ImageView imgQuocGia;

    Button btnDapAnA, btnDapAnB, btnDapAnC, btnDapAnD;
    Button btnCauTiep;

    List<QuocGia> danhSachCauHoi = new ArrayList<>();

    int viTriCauHoi = 0;
    int diem = 0;

    CountDownTimer countDownTimer;
    SoundManager soundManager;
    boolean daTraLoi = false;

    // Firebase
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
        soundManager = new SoundManager(this);
        soundManager.playBackgroundMusic();

        // =====================================================
        // ÁNH XẠ GIAO DIỆN
        // =====================================================

        tvCauSo = findViewById(R.id.tvCauSo);
        tvThoiGian = findViewById(R.id.tvThoiGian);
        tvCauHoi = findViewById(R.id.tvCauHoi);
        tvDiem = findViewById(R.id.tvDiem);

        tvKetQua = findViewById(R.id.tvKetQua);
        tvGioiThieu = findViewById(R.id.tvGioiThieu);

        imgQuocGia = findViewById(R.id.imgQuocGia);

        btnDapAnA = findViewById(R.id.btnDapAnA);
        btnDapAnB = findViewById(R.id.btnDapAnB);
        btnDapAnC = findViewById(R.id.btnDapAnC);
        btnDapAnD = findViewById(R.id.btnDapAnD);

        btnCauTiep = findViewById(R.id.btnCauTiep);


        // =====================================================
        // KẾT NỐI FIREBASE
        // =====================================================

        databaseReference = FirebaseDatabase
                .getInstance()
                .getReference("quocgia");


        // =====================================================
        // LẤY DỮ LIỆU TỪ FIREBASE
        // =====================================================

        layDuLieuFirebase();


        // =====================================================
        // SỰ KIỆN ĐÁP ÁN
        // =====================================================

        btnDapAnA.setOnClickListener(v -> kiemTraDapAn(btnDapAnA));

        btnDapAnB.setOnClickListener(v -> kiemTraDapAn(btnDapAnB));

        btnDapAnC.setOnClickListener(v -> kiemTraDapAn(btnDapAnC));

        btnDapAnD.setOnClickListener(v -> kiemTraDapAn(btnDapAnD));


        // =====================================================
        // CÂU TIẾP THEO
        // =====================================================

        btnCauTiep.setOnClickListener(v -> {

            viTriCauHoi++;

            if (viTriCauHoi < danhSachCauHoi.size()) {

                hienThiCauHoi();

            } else {

                ketThucGame();
            }
        });
    }


    // =====================================================
    // LẤY DỮ LIỆU TỪ FIREBASE
    // =====================================================

    private void layDuLieuFirebase() {

        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        danhSachCauHoi.clear();

                        // Duyệt từng quốc gia
                        for (DataSnapshot data : snapshot.getChildren()) {

                            String tenQuocGia =
                                    data.child("tenQuocGia").getValue(String.class);

                            String cauHoi =
                                    data.child("cauHoi").getValue(String.class);

                            String dapAnA =
                                    data.child("dapAnA").getValue(String.class);

                            String dapAnB =
                                    data.child("dapAnB").getValue(String.class);

                            String dapAnC =
                                    data.child("dapAnC").getValue(String.class);

                            String dapAnD =
                                    data.child("dapAnD").getValue(String.class);

                            String dapAnDung =
                                    data.child("dapAnDung").getValue(String.class);

                            String hinhAnh =
                                    data.child("hinhAnh").getValue(String.class);

                            String gioiThieu =
                                    data.child("gioiThieu").getValue(String.class);


                            // Kiểm tra dữ liệu
                            if (cauHoi == null ||
                                    dapAnA == null ||
                                    dapAnB == null ||
                                    dapAnC == null ||
                                    dapAnD == null ||
                                    dapAnDung == null) {

                                continue;
                            }


                            // Nếu Firebase có hinhAnh thì dùng nó
                            // để tìm ảnh trong drawable
                            if (hinhAnh != null && !hinhAnh.isEmpty()) {

                                tenQuocGia = hinhAnh;
                            }


                            // Tạo đối tượng QuocGia
                            QuocGia q = new QuocGia();

                            q.setTenQuocGia(tenQuocGia);
                            q.setCauHoi(cauHoi);

                            q.setDapAnA(dapAnA);
                            q.setDapAnB(dapAnB);
                            q.setDapAnC(dapAnC);
                            q.setDapAnD(dapAnD);

                            q.setDapAnDung(dapAnDung);

                            q.setGioiThieu(gioiThieu);


                            danhSachCauHoi.add(q);
                        }


                        // =================================================
                        // KIỂM TRA DỮ LIỆU
                        // =================================================

                        if (danhSachCauHoi.isEmpty()) {

                            Toast.makeText(
                                    GameActivity.this,
                                    "Không có dữ liệu câu hỏi!",
                                    Toast.LENGTH_LONG
                            ).show();

                            return;
                        }


                        // =================================================
                        // TRỘN CÂU HỎI
                        // =================================================

                        Collections.shuffle(danhSachCauHoi);


                        // =================================================
                        // HIỂN THỊ CÂU ĐẦU TIÊN
                        // =================================================

                        viTriCauHoi = 0;

                        hienThiCauHoi();
                    }


                    @Override
                    public void onCancelled(DatabaseError error) {

                        Toast.makeText(
                                GameActivity.this,
                                "Lỗi Firebase: " + error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );
    }


    // =====================================================
    // HIỂN THỊ CÂU HỎI
    // =====================================================

    private void hienThiCauHoi() {

        daTraLoi = false;


        // Hiện lại các nút
        btnDapAnA.setEnabled(true);
        btnDapAnB.setEnabled(true);
        btnDapAnC.setEnabled(true);
        btnDapAnD.setEnabled(true);


        btnCauTiep.setVisibility(View.GONE);


        tvKetQua.setText("");

        tvGioiThieu.setText("");


        QuocGia q = danhSachCauHoi.get(viTriCauHoi);


        tvCauSo.setText(
                "Câu " +
                        (viTriCauHoi + 1) +
                        "/" +
                        danhSachCauHoi.size()
        );


        tvDiem.setText("Điểm: " + diem);


        tvCauHoi.setText(q.getCauHoi());


        // =====================================================
        // TRỘN VỊ TRÍ ĐÁP ÁN
        // =====================================================

        String dapAnDung = q.getDapAnDung();

        List<String> danhSachDapAn = new ArrayList<>();

        danhSachDapAn.add(q.getDapAnA());
        danhSachDapAn.add(q.getDapAnB());
        danhSachDapAn.add(q.getDapAnC());
        danhSachDapAn.add(q.getDapAnD());


        Collections.shuffle(danhSachDapAn);


        // Gán lại 4 đáp án sau khi trộn
        btnDapAnA.setText("A. " + danhSachDapAn.get(0));
        btnDapAnB.setText("B. " + danhSachDapAn.get(1));
        btnDapAnC.setText("C. " + danhSachDapAn.get(2));
        btnDapAnD.setText("D. " + danhSachDapAn.get(3));


        // =====================================================
        // HIỂN THỊ HÌNH ẢNH
        // =====================================================

        int idHinhAnh = getResources().getIdentifier(
                q.getTenQuocGia(),
                "drawable",
                getPackageName()
        );


        if (idHinhAnh != 0) {

            imgQuocGia.setImageResource(idHinhAnh);

        } else {

            imgQuocGia.setImageResource(
                    android.R.drawable.ic_menu_gallery
            );
        }


        // =====================================================
        // BẮT ĐẦU ĐẾM GIỜ
        // =====================================================

        batDauDemNguoc();
    }


    // =====================================================
    // ĐẾM NGƯỢC 10 GIÂY
    // =====================================================

    private void batDauDemNguoc() {

        if (countDownTimer != null) {

            countDownTimer.cancel();
        }


        countDownTimer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                long giay = millisUntilFinished / 1000;

                tvThoiGian.setText("⏱ " + giay);
            }


            @Override
            public void onFinish() {

                tvThoiGian.setText("⏱ 0");


                if (!daTraLoi) {

                    hetThoiGian();
                }
            }

        }.start();
    }


    // =====================================================
    // KIỂM TRA ĐÁP ÁN
    // =====================================================

    private void kiemTraDapAn(Button button) {

        if (daTraLoi) {

            return;
        }


        daTraLoi = true;


        if (countDownTimer != null) {

            countDownTimer.cancel();
        }


        // Không cho chọn tiếp
        btnDapAnA.setEnabled(false);
        btnDapAnB.setEnabled(false);
        btnDapAnC.setEnabled(false);
        btnDapAnD.setEnabled(false);


        QuocGia q = danhSachCauHoi.get(viTriCauHoi);


        String dapAnNguoiChon =
                button.getText().toString();


        // Bỏ "A. ", "B. ", "C. ", "D. "
        if (dapAnNguoiChon.length() > 3) {

            dapAnNguoiChon =
                    dapAnNguoiChon.substring(3);
        }


        // =====================================================
        // TRẢ LỜI ĐÚNG
        // =====================================================

        if (dapAnNguoiChon.equals(q.getDapAnDung())) {
            soundManager.playCorrect();
            diem += 10;


            tvKetQua.setText(
                    "✅ Chính xác! +10 điểm"
            );


            tvDiem.setText(
                    "Điểm: " + diem
            );


            tvGioiThieu.setText(
                    "🌎 " + q.getGioiThieu()
            );


            // Đúng thì được chơi tiếp
            btnCauTiep.setVisibility(
                    View.VISIBLE
            );
        }


        // =====================================================
        // TRẢ LỜI SAI
        // =====================================================

        else {
            soundManager.playWrong();
            tvKetQua.setText(
                    "❌ Sai rồi!\nĐáp án đúng: "
                            + q.getDapAnDung()
            );


            tvGioiThieu.setText(
                    "🌎 " + q.getGioiThieu()
            );


            // Sai là kết thúc game
            btnCauTiep.setVisibility(
                    View.GONE
            );


            // Chờ 2 giây
            new Handler().postDelayed(
                    () -> ketThucGame(),
                    2000
            );
        }
    }


    // =====================================================
    // HẾT THỜI GIAN
    // =====================================================

    private void hetThoiGian() {

        daTraLoi = true;
        soundManager.playTimeout();

        btnDapAnA.setEnabled(false);
        btnDapAnB.setEnabled(false);
        btnDapAnC.setEnabled(false);
        btnDapAnD.setEnabled(false);


        QuocGia q =
                danhSachCauHoi.get(viTriCauHoi);


        tvKetQua.setText(
                "⏰ Hết thời gian!\nĐáp án đúng: "
                        + q.getDapAnDung()
        );


        tvGioiThieu.setText(
                "🌎 " + q.getGioiThieu()
        );


        // Hết giờ cũng kết thúc
        btnCauTiep.setVisibility(
                View.GONE
        );


        // Chờ 2 giây
        new Handler().postDelayed(
                () -> ketThucGame(),
                2000
        );
    }


    // =====================================================
    // KẾT THÚC GAME
    // =====================================================

    private void ketThucGame() {

        if (countDownTimer != null) {

            countDownTimer.cancel();
        }


        // =====================================================
        // LƯU ĐIỂM CAO
        // =====================================================

        android.content.SharedPreferences preferences =
                getSharedPreferences(
                        "GAME_DATA",
                        MODE_PRIVATE
                );


        int diemCao =
                preferences.getInt(
                        "DIEM_CAO",
                        0
                );


        if (diem > diemCao) {

            preferences.edit()
                    .putInt("DIEM_CAO", diem)
                    .apply();
        }


        // =====================================================
        // CHUYỂN SANG RESULT
        // =====================================================

        Intent intent =
                new Intent(
                        GameActivity.this,
                        ResultActivity.class
                );

        soundManager.playWin();
        soundManager.stopBackgroundMusic();
        intent.putExtra(
                "DIEM",
                diem
        );


        startActivity(intent);

        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (soundManager != null) {
            soundManager.release();
        }
    }
}