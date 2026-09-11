/**
 * Lớp SinhVien đại diện cho một sinh viên trong hệ thống.
 * Áp dụng tính Đóng gói (Encapsulation) bằng cách để các thuộc tính là private
 * và cung cấp các phương thức public (getter, setter) để truy cập và sửa đổi.
 */
public class SinhVien {
    // Các thuộc tính private để đảm bảo tính đóng gói
    private String maSinhVien;
    private String tenSinhVien;
    private double diemTrungBinh;

    /**
     * Constructor (Hàm khởi tạo) dùng để tạo ra một đối tượng SinhVien mới
     * với các thông tin ban đầu.
     */
    public SinhVien(String maSinhVien, String tenSinhVien, double diemTrungBinh) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.diemTrungBinh = diemTrungBinh;
    }

    // Các phương thức getter (để lấy giá trị) và setter (để thay đổi giá trị)

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    /**
     * Phương thức hiển thị thông tin của sinh viên ra màn hình.
     * Sử dụng System.out.printf để định dạng đầu ra cho đẹp mắt.
     */
    public void hienThiThongTin() {
        System.out.printf("Mã SV: %-10s | Tên SV: %-20s | Điểm TB: %.2f\n",
                          maSinhVien, tenSinhVien, diemTrungBinh);
    }
}
