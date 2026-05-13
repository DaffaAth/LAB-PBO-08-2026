package Soal1;




public class Gudang {

    private int stok;
    private final int kapasitasMaksimal;

    public Gudang(int kapasitasMaksimal) {
        this.stok = 0;
        this.kapasitasMaksimal = kapasitasMaksimal;
        System.out.println("[GUDANG] Gudang diinisialisasi. Kapasitas Maksimal: " + kapasitasMaksimal);
    }


    public synchronized void tambahStok(int jumlah) throws InterruptedException {
        String threadName = Thread.currentThread().getName();

        while (stok + jumlah > kapasitasMaksimal) {
            System.out.println("[" + threadName + "] Gudang hampir penuh (Stok: " + stok
                    + "/" + kapasitasMaksimal + "). Pemasok menunggu...");
            wait(); 
        }

        stok += jumlah;
        System.out.println("[" + threadName + "] Menambah " + jumlah
                + " barang. Stok sekarang: " + stok + "/" + kapasitasMaksimal);

        notifyAll(); 
    }


    public synchronized void ambilStok(int jumlah) throws InterruptedException {
        String threadName = Thread.currentThread().getName();


        while (stok < jumlah) {
            System.out.println("[" + threadName + "] Stok kurang (Stok: " + stok
                    + ", butuh: " + jumlah + "). Kurir menunggu...");
            wait(); 
        }

        stok -= jumlah;
        System.out.println("[" + threadName + "] Mengambil " + jumlah
                + " barang. Stok sekarang: " + stok + "/" + kapasitasMaksimal);

        notifyAll(); 
    }

    public synchronized int getStok() {
        return stok;
    }

    public int getKapasitasMaksimal() {
        return kapasitasMaksimal;
    }
}