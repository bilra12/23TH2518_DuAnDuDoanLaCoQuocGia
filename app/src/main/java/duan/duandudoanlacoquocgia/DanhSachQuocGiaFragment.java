package duan.duandudoanlacoquocgia;



import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class DanhSachQuocGiaFragment extends Fragment {

    private EditText edtTimKiem;
    private ListView lvQuocGia;

    private ArrayList<QuocGia> danhSachGoc;
    private ArrayList<QuocGia> danhSachHienThi;

    private QuocGiaAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        View view = inflater.inflate(
                R.layout.fragment_danh_sach_quoc_gia,
                container,
                false
        );

        edtTimKiem =
                view.findViewById(R.id.edtTimKiem);

        lvQuocGia =
                view.findViewById(R.id.lvQuocGia);

        // Tạo danh sách quốc gia
        taoDanhSachQuocGia();

        // Danh sách hiển thị ban đầu
        danhSachHienThi =
                new ArrayList<>(danhSachGoc);

        adapter = new QuocGiaAdapter(
                requireContext(),
                danhSachHienThi
        );

        lvQuocGia.setAdapter(adapter);

        // Chức năng tìm kiếm
        edtTimKiem.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(
                            CharSequence s,
                            int start,
                            int count,
                            int after
                    ) {
                    }

                    @Override
                    public void onTextChanged(
                            CharSequence s,
                            int start,
                            int before,
                            int count
                    ) {

                        timKiemQuocGia(
                                s.toString()
                        );
                    }

                    @Override
                    public void afterTextChanged(
                            Editable s
                    ) {
                    }
                }
        );

        return view;
    }

    private void taoDanhSachQuocGia() {

        danhSachGoc = new ArrayList<>();

        danhSachGoc.add(
                new QuocGia(
                        "Việt Nam",
                        "Hà Nội",
                        "Châu Á",
                        "Nổi tiếng với nền văn hóa lâu đời, "
                                + "ẩm thực phong phú và nhiều danh lam thắng cảnh.",
                        R.drawable.vietnam
                )
        );

        danhSachGoc.add(
                new QuocGia(
                        "Nhật Bản",
                        "Tokyo",
                        "Châu Á",
                        "Nổi tiếng với hoa anh đào, "
                                + "núi Phú Sĩ và nền công nghệ phát triển.",
                        R.drawable.japan
                )
        );

        danhSachGoc.add(
                new QuocGia(
                        "Hàn Quốc",
                        "Seoul",
                        "Châu Á",
                        "Nổi tiếng với văn hóa K-Pop, "
                                + "ẩm thực và ngành công nghệ hiện đại.",
                        R.drawable.korea
                )
        );

        danhSachGoc.add(
                new QuocGia(
                        "Thái Lan",
                        "Bangkok",
                        "Châu Á",
                        "Nổi tiếng với các ngôi chùa, "
                                + "bãi biển đẹp và nền văn hóa đặc sắc.",
                        R.drawable.thailand
                )
        );

        danhSachGoc.add(
                new QuocGia(
                        "Pháp",
                        "Paris",
                        "Châu Âu",
                        "Nổi tiếng với tháp Eiffel, "
                                + "thời trang, nghệ thuật và ẩm thực.",
                        R.drawable.france
                )
        );

        danhSachGoc.add(
                new QuocGia(
                        "Anh",
                        "London",
                        "Châu Âu",
                        "Nổi tiếng với cung điện Buckingham, "
                                + "tháp Big Ben và lịch sử lâu đời.",
                        R.drawable.uk
                )
        );

        danhSachGoc.add(
                new QuocGia(
                        "Hoa Kỳ",
                        "Washington D.C.",
                        "Bắc Mỹ",
                        "Có nền kinh tế lớn, nhiều thành phố "
                                + "và công trình nổi tiếng trên thế giới.",
                        R.drawable.usa
                )
        );

        danhSachGoc.add(
                new QuocGia(
                        "Canada",
                        "Ottawa",
                        "Bắc Mỹ",
                        "Nổi tiếng với thiên nhiên rộng lớn, "
                                + "rừng, hồ nước và thác Niagara.",
                        R.drawable.canada
                )
        );

        danhSachGoc.add(
                new QuocGia(
                        "Úc",
                        "Canberra",
                        "Châu Đại Dương",
                        "Nổi tiếng với Nhà hát Opera Sydney, "
                                + "chuột túi và hệ sinh thái đa dạng.",
                        R.drawable.australia
                )
        );

        danhSachGoc.add(
                new QuocGia(
                        "Brazil",
                        "Brasília",
                        "Nam Mỹ",
                        "Nổi tiếng với rừng Amazon, "
                                + "bóng đá và lễ hội Carnival.",
                        R.drawable.brazil
                )
        );
    }

    private void timKiemQuocGia(String tuKhoa) {

        danhSachHienThi.clear();

        tuKhoa = tuKhoa.toLowerCase().trim();

        if (tuKhoa.isEmpty()) {

            danhSachHienThi.addAll(
                    danhSachGoc
            );

        } else {

            for (QuocGia quocGia : danhSachGoc) {

                String ten =
                        quocGia.getTenQuocGia()
                                .toLowerCase();

                String thuDo =
                        quocGia.getThuDo()
                                .toLowerCase();

                String chauLuc =
                        quocGia.getChauLuc()
                                .toLowerCase();

                if (ten.contains(tuKhoa)
                        || thuDo.contains(tuKhoa)
                        || chauLuc.contains(tuKhoa)) {

                    danhSachHienThi.add(
                            quocGia
                    );
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
}