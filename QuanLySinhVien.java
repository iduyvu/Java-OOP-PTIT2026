import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Lớp QuanLySinhVien chịu trách nhiệm quản lý danh sách các sinh viên.
 * Chứa các phương thức thêm, xóa, sửa, tìm kiếm và sắp xếp.
 */
public class QuanLySinhVien {
    // Sử dụng ArrayList để lưu trữ danh sách sinh viên
    private ArrayList<SinhVien> danhSachSinhVien;

    // Hàm khởi tạo sẽ tạo một danh sách rỗng khi QuanLySinhVien được tạo
    public QuanLySinhVien() {
        this.danhSachSinhVien = new ArrayList<>();
    }

    /**
     * Phương thức kiểm tra xem mã sinh viên đã tồn tại trong danh sách chưa.
     * @param maSinhVien Mã sinh viên cần kiểm tra
     * @return true nếu đã tồn tại, false nếu chưa tồn tại
     */
    public boolean kiemTraTonTaiMaSinhVien(String maSinhVien) {
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            SinhVien sv = danhSachSinhVien.get(i);
            if (sv.getMaSinhVien().equals(maSinhVien)) {
                return true; // Tìm thấy mã sinh viên giống nhau
            }
        }
        return false; // Không tìm thấy
    }

    /**
     * Phương thức thêm một sinh viên mới vào danh sách.
     * @param sv Đối tượng SinhVien mới
     */
    public void themSinhVien(SinhVien sv) {
        danhSachSinhVien.add(sv);
        System.out.println("Đã thêm sinh viên thành công!");
    }

    /**
     * Phương thức xóa sinh viên theo mã.
     * @param maSinhVien Mã sinh viên cần xóa
     */
    public void xoaSinhVien(String maSinhVien) {
        boolean daXoa = false;
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            if (danhSachSinhVien.get(i).getMaSinhVien().equals(maSinhVien)) {
                danhSachSinhVien.remove(i);
                System.out.println("Đã xóa sinh viên có mã: " + maSinhVien);
                daXoa = true;
                break; // Xóa xong thì thoát vòng lặp
            }
        }
        if (!daXoa) {
            System.out.println("Không tìm thấy sinh viên có mã: " + maSinhVien);
        }
    }

    /**
     * Phương thức cập nhật thông tin sinh viên theo mã.
     * @param maSinhVien Mã sinh viên cần cập nhật
     * @param tenMoi Tên sinh viên mới
     * @param diemMoi Điểm sinh viên mới
     */
    public void capNhatSinhVien(String maSinhVien, String tenMoi, double diemMoi) {
        boolean daCapNhat = false;
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            SinhVien sv = danhSachSinhVien.get(i);
            if (sv.getMaSinhVien().equals(maSinhVien)) {
                // Sử dụng setter để cập nhật thông tin
                sv.setTenSinhVien(tenMoi);
                sv.setDiemTrungBinh(diemMoi);
                System.out.println("Đã cập nhật thông tin sinh viên!");
                daCapNhat = true;
                break;
            }
        }
        if (!daCapNhat) {
            System.out.println("Không tìm thấy sinh viên có mã: " + maSinhVien);
        }
    }

    /**
     * Phương thức tìm kiếm sinh viên theo tên (tìm kiếm gần đúng).
     * Bỏ qua phân biệt hoa thường khi tìm kiếm.
     * @param tenCanTim Tên sinh viên cần tìm
     */
    public void timKiemTheoTen(String tenCanTim) {
        System.out.println("--- Kết quả tìm kiếm ---");
        boolean timThay = false;
        // Chuyển tên cần tìm về chữ thường để dễ so sánh
        String tenTimKiemThuong = tenCanTim.toLowerCase();

        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            SinhVien sv = danhSachSinhVien.get(i);
            // Chuyển tên sinh viên về chữ thường và kiểm tra xem có chứa tên cần tìm không
            if (sv.getTenSinhVien().toLowerCase().contains(tenTimKiemThuong)) {
                sv.hienThiThongTin();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Không tìm thấy sinh viên nào phù hợp!");
        }
    }

    /**
     * Phương thức sắp xếp danh sách sinh viên theo điểm trung bình.
     * Sắp xếp từ thấp đến cao.
     */
    public void sapXepTheoDiem() {
        Collections.sort(danhSachSinhVien, new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien sv1, SinhVien sv2) {
                // So sánh điểm của 2 sinh viên
                if (sv1.getDiemTrungBinh() > sv2.getDiemTrungBinh()) {
                    return 1;
                } else if (sv1.getDiemTrungBinh() < sv2.getDiemTrungBinh()) {
                    return -1;
                }
                return 0;
            }
        });
        System.out.println("Đã sắp xếp danh sách theo điểm trung bình (tăng dần).");
    }

    /**
     * Phương thức sắp xếp danh sách sinh viên theo tên.
     * Sắp xếp theo thứ tự bảng chữ cái.
     */
    public void sapXepTheoTen() {
        Collections.sort(danhSachSinhVien, new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien sv1, SinhVien sv2) {
                // Sử dụng phương thức compareTo của String để so sánh tên
                return sv1.getTenSinhVien().compareToIgnoreCase(sv2.getTenSinhVien());
            }
        });
        System.out.println("Đã sắp xếp danh sách theo tên (A-Z).");
    }

    /**
     * Phương thức hiển thị toàn bộ danh sách sinh viên ra màn hình.
     */
    public void hienThiDanhSach() {
        if (danhSachSinhVien.isEmpty()) {
            System.out.println("Danh sách sinh viên hiện đang trống!");
            return;
        }

        System.out.println("--- Danh sách sinh viên ---");
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            danhSachSinhVien.get(i).hienThiThongTin();
        }
        System.out.println("---------------------------");
    }
}
