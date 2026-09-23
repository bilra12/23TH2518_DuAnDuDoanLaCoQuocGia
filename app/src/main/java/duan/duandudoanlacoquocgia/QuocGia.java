package duan.duandudoanlacoquocgia;

public class QuocGia {

    // ===== DỮ LIỆU CŨ - DÙNG CHO DANH SÁCH QUỐC GIA =====
    private String tenQuocGia;
    private String thuDo;
    private String chauLuc;
    private String moTa;
    private String dacDiem;
    private int hinhAnh;

    // ===== DỮ LIỆU GAME =====
    private String cauHoi;
    private String dapAnA;
    private String dapAnB;
    private String dapAnC;
    private String dapAnD;
    private String dapAnDung;
    private String gioiThieu;

    // Constructor rỗng - cần cho Firebase
    public QuocGia() {
    }

    // Constructor cũ
    // Dùng cho DanhSachQuocGiaFragment
    public QuocGia(String tenQuocGia,
                   String thuDo,
                   String chauLuc,
                   String moTa,
                   int hinhAnh) {

        this.tenQuocGia = tenQuocGia;
        this.thuDo = thuDo;
        this.chauLuc = chauLuc;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    // Constructor dùng cho game
    public QuocGia(String tenQuocGia,
                   String cauHoi,
                   String dapAnA,
                   String dapAnB,
                   String dapAnC,
                   String dapAnD,
                   String dapAnDung,
                   String gioiThieu,
                   int hinhAnh) {

        this.tenQuocGia = tenQuocGia;
        this.cauHoi = cauHoi;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnD = dapAnD;
        this.dapAnDung = dapAnDung;
        this.gioiThieu = gioiThieu;
        this.hinhAnh = hinhAnh;
    }

    // =========================
    // GETTER / SETTER DANH SÁCH
    // =========================

    public String getTenQuocGia() {
        return tenQuocGia;
    }

    public void setTenQuocGia(String tenQuocGia) {
        this.tenQuocGia = tenQuocGia;
    }

    public String getThuDo() {
        return thuDo;
    }

    public void setThuDo(String thuDo) {
        this.thuDo = thuDo;
    }

    public String getChauLuc() {
        return chauLuc;
    }

    public void setChauLuc(String chauLuc) {
        this.chauLuc = chauLuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    // =========================
    // GETTER / SETTER GAME
    // =========================

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getDapAnA() {
        return dapAnA;
    }

    public void setDapAnA(String dapAnA) {
        this.dapAnA = dapAnA;
    }

    public String getDapAnB() {
        return dapAnB;
    }

    public void setDapAnB(String dapAnB) {
        this.dapAnB = dapAnB;
    }

    public String getDapAnC() {
        return dapAnC;
    }

    public void setDapAnC(String dapAnC) {
        this.dapAnC = dapAnC;
    }

    public String getDapAnD() {
        return dapAnD;
    }

    public void setDapAnD(String dapAnD) {
        this.dapAnD = dapAnD;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }
    public String getDacDiem() {
        return dacDiem;
    }

    public void setDacDiem(String dacDiem) {
        this.dacDiem = dacDiem;
    }
}