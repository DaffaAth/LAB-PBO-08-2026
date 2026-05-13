package Soal1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MainWarehouse {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("========================================");
        System.out.println("  SISTEM LOGISTIK - THE CENTRAL WAREHOUSE");
        System.out.println("========================================\n");


        Gudang gudang = new Gudang(15);


        ExecutorService pemasokPool = Executors.newFixedThreadPool(2);
        pemasokPool.submit(new Pemasok(gudang, "Pemasok-1"));
        pemasokPool.submit(new Pemasok(gudang, "Pemasok-2"));


        ExecutorService kurirPool = Executors.newFixedThreadPool(3);
        kurirPool.submit(new Kurir(gudang, "Kurir-1"));
        kurirPool.submit(new Kurir(gudang, "Kurir-2"));
        kurirPool.submit(new Kurir(gudang, "Kurir-3"));


        Thread monitoringThread = new Thread(new Monitoring(gudang), "Thread-Monitoring");
        monitoringThread.setDaemon(false); 
        monitoringThread.start();

        System.out.println("\n[MAIN] Sistem berjalan selama 15 detik...\n");


        Thread.sleep(15_000);

        System.out.println("\n[MAIN] Waktu habis. Menghentikan semua thread secara aman...");

        pemasokPool.shutdownNow();
        kurirPool.shutdownNow();

    
        if (!pemasokPool.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("[MAIN] Pemasok pool tidak selesai dalam batas waktu.");
        } else {
            System.out.println("[MAIN] Semua Pemasok telah dihentikan.");
        } 

        if (!kurirPool.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("[MAIN] Kurir pool tidak selesai dalam batas waktu.");
        } else {
            System.out.println("[MAIN] Semua Kurir telah dihentikan.");
        }


        monitoringThread.interrupt();
        monitoringThread.join(2000); 

        System.out.println("\n========================================");
        System.out.println("  SISTEM SELESAI. Stok akhir: " + gudang.getStok()
                + "/" + gudang.getKapasitasMaksimal());
        System.out.println("========================================");
    }
}