package duan.duandudoanlacoquocgia;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuocGiaAdapter extends ArrayAdapter<QuocGia> {

    private Context context;
    private ArrayList<QuocGia> danhSach;

    public QuocGiaAdapter(
            Context context,
            ArrayList<QuocGia> danhSach
    ) {
        super(context, 0, danhSach);

        this.context = context;
        this.danhSach = danhSach;
    }

    @NonNull
    @Override
    public View getView(
            int position,
            @Nullable View convertView,
            @NonNull ViewGroup parent
    ) {

        if (convertView == null) {

            convertView = LayoutInflater
                    .from(context)
                    .inflate(
                            R.layout.item_quoc_gia,
                            parent,
                            false
                    );
        }

        ImageView imgQuocGia =
                convertView.findViewById(R.id.imgQuocGia);

        TextView txtTenQuocGia =
                convertView.findViewById(R.id.txtTenQuocGia);

        TextView txtThuDo =
                convertView.findViewById(R.id.txtThuDo);

        TextView txtChauLuc =
                convertView.findViewById(R.id.txtChauLuc);

        TextView txtDacDiem =
                convertView.findViewById(R.id.txtDacDiem);

        QuocGia quocGia = danhSach.get(position);

        imgQuocGia.setImageResource(
                quocGia.getHinhAnh()
        );

        txtTenQuocGia.setText(
                quocGia.getTenQuocGia()
        );

        txtThuDo.setText(
                "Thủ đô: " + quocGia.getThuDo()
        );

        txtChauLuc.setText(
                "Châu lục: " + quocGia.getChauLuc()
        );

        txtDacDiem.setText(
                "Đặc điểm: " + quocGia.getDacDiem()
        );

        return convertView;
    }
}