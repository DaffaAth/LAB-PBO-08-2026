package soal2;

public class SmartCCTV extends PerangkatElektronik implements IInteraksiInternet {

    public SmartCCTV(String merk, int dayaListrik) {
        super(merk, dayaListrik);
    }

    @Override
    public void cekFungsi() {
        System.out.println("[SmartCCTV] Fungsi: Pemantauan keamanan 24 jam.");
    }

    
    public void hubungkanWiFi() {
        System.out.println("[SmartCCTV] Mengirim data ke server...");
    }
}