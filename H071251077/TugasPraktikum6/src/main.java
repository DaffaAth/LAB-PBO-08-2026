import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistem Manajemen Perpustakaan ===");
            System.out.println("1. Tambah Item");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Pinjam Item");
            System.out.println("4. Kembalikan Item");
            System.out.println("5. Lihat Status Perpustakaan");
            System.out.println("6. Lihat Log Aktivitas");
            System.out.println("7. Lihat Item yang Dipinjam Anggota");
            System.out.println("8. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Tipe (1. Buku, 2. DVD): ");
                        int type = sc.nextInt(); sc.nextLine();
                        System.out.print("Judul: "); String t = sc.nextLine();
                        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
                        if (type == 1) {
                            System.out.print("Penulis: "); String a = sc.nextLine();
                            System.out.println(lib.addItem(new Book(t, id, a)));
                        } else {
                            System.out.print("Durasi (menit): "); int d = sc.nextInt();
                            System.out.println(lib.addItem(new DVD(t, id, d)));
                        }
                        break;
                    case 2:
                        System.out.print("Nama: "); String name = sc.nextLine();
                        System.out.print("ID Member: "); String mid = sc.nextLine();
                        lib.addMember(new Member(name, mid));
                        System.out.println("Member berhasil ditambahkan.");
                        break;
                    case 3:
                        System.out.print("ID Member: "); String m_id = sc.nextLine();
                        System.out.print("ID Item: "); int i_id = sc.nextInt();
                        System.out.print("Lama Pinjam (hari): "); int days = sc.nextInt();
                        Member m = lib.findMemberById(m_id);
                        LibraryItem item = lib.findItemById(i_id);
                        String res = m.borrow(item, days);
                        lib.getLogger().logActivity(item.getTitle() + " dipinjam oleh " + m.getName());
                        System.out.println(res);
                        break;
                    case 4:
                        System.out.print("ID Member: "); String rm_id = sc.nextLine();
                        System.out.print("ID Item: "); int ri_id = sc.nextInt();
                        System.out.print("Keterlambatan (hari): "); int late = sc.nextInt();
                        Member rm = lib.findMemberById(rm_id);
                        LibraryItem r_item = lib.findItemById(ri_id);
                        String r_res = rm.returnItem(r_item, late);
                        lib.getLogger().logActivity(r_item.getTitle() + " dikembalikan oleh " + rm.getName());
                        System.out.println(r_res);
                        break;
                    case 5:
                        System.out.println(lib.getLibraryStatus());
                        break;
                    case 6:
                        System.out.println(lib.getAllLogs());
                        break;
                    case 7:
                        System.out.print("ID Member: "); String gm_id = sc.nextLine();
                        lib.findMemberById(gm_id).getBorrowedItems();
                        break;
                    case 8:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}