import java.util.Scanner;

/**
 * Lớp Main chứa hàm main() để chạy chương trình.
 * Cung cấp giao diện console qua menu để người dùng tương tác.
 */
public class Main {
    public static void main(String[] args) {
        // Khởi tạo đối tượng Scanner để đọc dữ liệu từ bàn phím
        Scanner scanner = new Scanner(System.in);
        // Khởi tạo đối tượng QuanLySinhVien để sử dụng các chức năng quản lý
        QuanLySinhVien quanLy = new QuanLySinhVien();

        int luaChon = -1; // Biến lưu lựa chọn của người dùng

        // Vòng lặp while để hiển thị menu liên tục cho đến khi chọn 0
        while (luaChon != 0) {
            System.out.println("\n===== CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN =====");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Xóa sinh viên theo ID");
            System.out.println("3. Sửa / cập nhật thông tin sinh viên");
            System.out.println("4. Tìm kiếm sinh viên theo tên");
            System.out.println("5. Sắp xếp danh sách sinh viên");
            System.out.println("6. Hiển thị danh sách sinh viên");
            System.out.println("0. Thoát chương trình");
            System.out.print("Mời bạn chọn chức năng (0-6): ");

            try {
                // Đọc toàn bộ dòng chữ nhập vào và ép sang kiểu số nguyên
                // Tránh lỗi trôi lệnh so với khi dùng scanner.nextInt()
                luaChon = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên!");
                continue; // Bỏ qua phần dưới và lặp lại vòng lặp
            }

            // Sử dụng switch-case để xử lý từng chức năng tương ứng
            switch (luaChon) {
                case 1:
                    System.out.println("\n--- 1. Thêm sinh viên mới ---");
                    String maSV;
                    // Vòng lặp yêu cầu nhập mã sinh viên, bắt buộc không trùng lặp
                    while (true) {
                        System.out.print("Nhập mã sinh viên (ID): ");
                        maSV = scanner.nextLine().trim();

                        if (maSV.isEmpty()) {
                            System.out.println("Mã sinh viên không được để trống. Vui lòng nhập lại!");
                            continue;
                        }

                        // Kiểm tra xem mã đã tồn tại chưa
                        if (quanLy.kiemTraTonTaiMaSinhVien(maSV)) {
                            System.out.println("Lỗi: Mã sinh viên đã tồn tại. Vui lòng nhập mã khác!");
                        } else {
                            break; // Mã hợp lệ, thoát khỏi vòng lặp
                        }
                    }

                    System.out.print("Nhập tên sinh viên: ");
                    String tenSV = scanner.nextLine().trim();

                    double diemTB;
                    // Vòng lặp yêu cầu nhập điểm, bắt buộc trong khoảng 0 - 10
                    while (true) {
                        System.out.print("Nhập điểm trung bình (0 - 10): ");
                        try {
                            diemTB = Double.parseDouble(scanner.nextLine());
                            if (diemTB >= 0 && diemTB <= 10) {
                                break; // Điểm hợp lệ, thoát vòng lặp
                            } else {
                                System.out.println("Lỗi: Điểm phải nằm trong khoảng từ 0 đến 10. Vui lòng nhập lại!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Điểm phải là một số (VD: 8.5). Vui lòng nhập lại!");
                        }
                    }

                    // Tạo đối tượng SinhVien mới và thêm vào quản lý
                    SinhVien svMoi = new SinhVien(maSV, tenSV, diemTB);
                    quanLy.themSinhVien(svMoi);
                    break;

                case 2:
                    System.out.println("\n--- 2. Xóa sinh viên ---");
                    System.out.print("Nhập mã sinh viên cần xóa: ");
                    String maXoa = scanner.nextLine().trim();
                    quanLy.xoaSinhVien(maXoa);
                    break;

                case 3:
                    System.out.println("\n--- 3. Cập nhật thông tin sinh viên ---");
                    System.out.print("Nhập mã sinh viên cần cập nhật: ");
                    String maCapNhat = scanner.nextLine().trim();

                    // Kiểm tra xem mã có tồn tại không trước khi yêu cầu nhập thông tin mới
                    if (!quanLy.kiemTraTonTaiMaSinhVien(maCapNhat)) {
                        System.out.println("Không tìm thấy sinh viên có mã: " + maCapNhat);
                        break;
                    }

                    System.out.print("Nhập tên mới: ");
                    String tenMoi = scanner.nextLine().trim();

                    double diemMoi;
                    // Tương tự, bắt buộc nhập điểm mới hợp lệ
                    while (true) {
                        System.out.print("Nhập điểm trung bình mới (0 - 10): ");
                        try {
                            diemMoi = Double.parseDouble(scanner.nextLine());
                            if (diemMoi >= 0 && diemMoi <= 10) {
                                break; // Thoát nếu điểm đúng
                            } else {
                                System.out.println("Lỗi: Điểm phải nằm trong khoảng từ 0 đến 10. Vui lòng nhập lại!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Lỗi: Điểm phải là một số. Vui lòng nhập lại!");
                        }
                    }

                    quanLy.capNhatSinhVien(maCapNhat, tenMoi, diemMoi);
                    break;

                case 4:
                    System.out.println("\n--- 4. Tìm kiếm sinh viên theo tên ---");
                    System.out.print("Nhập tên hoặc một phần tên sinh viên cần tìm: ");
                    String tenTimKiem = scanner.nextLine().trim();
                    quanLy.timKiemTheoTen(tenTimKiem);
                    break;

                case 5:
                    System.out.println("\n--- 5. Sắp xếp danh sách sinh viên ---");
                    System.out.println("a. Sắp xếp theo điểm trung bình (tăng dần)");
                    System.out.println("b. Sắp xếp theo tên (A-Z)");
                    System.out.print("Mời chọn kiểu sắp xếp (a/b): ");
                    String kieuSapXep = scanner.nextLine().trim().toLowerCase();

                    if (kieuSapXep.equals("a")) {
                        quanLy.sapXepTheoDiem();
                    } else if (kieuSapXep.equals("b")) {
                        quanLy.sapXepTheoTen();
                    } else {
                        System.out.println("Lựa chọn không hợp lệ!");
                    }
                    break;

                case 6:
                    System.out.println("\n--- 6. Hiển thị danh sách ---");
                    quanLy.hienThiDanhSach();
                    break;

                case 0:
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 0 đến 6!");
                    break;
            }
        }

        // Đóng scanner sau khi sử dụng xong để giải phóng tài nguyên
        scanner.close();
    }
}
