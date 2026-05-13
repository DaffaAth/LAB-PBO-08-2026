package Soal1;


public class Monitoring implements Runnable {

    private final Gudang gudang;
    private static final int BAR_LENGTH = 20; 

    public Monitoring(Gudang gudang) {
        this.gudang = gudang;
    }

    @Override
    public void run() {
        System.out.println("[MONITORING] Thread monitoring mulai berjalan.");

        try {
            while (!Thread.currentThread().isInterrupted()) {
                tampilkanStatus();
                Thread.sleep(1000); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[MONITORING] Thread monitoring dihentikan.");
        }


        tampilkanStatus();
    }


    private void tampilkanStatus() {
        int stok = gudang.getStok();
        int kapasitas = gudang.getKapasitasMaksimal();

        double persentase = (kapasitas == 0) ? 0 : (double) stok / kapasitas * 100;
        int jumlahHash = (int) (persentase / 100 * BAR_LENGTH);
        int jumlahDash = BAR_LENGTH - jumlahHash;

        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < jumlahHash; i++) bar.append("#");
        for (int i = 0; i < jumlahDash; i++) bar.append("-");
        bar.append("]");

        System.out.printf("[MONITORING] Status Gudang: %s %.0f%% (Stok: %d/%d)%n",
                bar, persentase, stok, kapasitas);
    }
}