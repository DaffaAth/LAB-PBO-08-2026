package Soal2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MainSearchEngine {


    private static final int JUMLAH_THREAD = 4;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("========================================================");
        System.out.println("   HIGH-PERFORMANCE DATA INDEXER - THE SEARCH ENGINE CORE");
        System.out.println("========================================================\n");


        List<String> daftarDokumen = Arrays.asList(
                "Dokumen_A.txt",
                "Dokumen_B.txt",
                "Dokumen_C.txt",
                "Dokumen_D.txt",
                "Dokumen_E.txt",
                "Dokumen_F.txt",
                "Dokumen_G.txt",
                "Dokumen_H.txt",
                "Dokumen_I.txt",
                "Dokumen_J.txt"
        );


        ConcurrentHashMap<String, Integer> hasilIndex = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, String> infoThread   = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Long>   infoDurasi   = new ConcurrentHashMap<>();


        CountDownLatch latch = new CountDownLatch(daftarDokumen.size());


        DataProcessor processor = new DataProcessor();


        ExecutorService executor = Executors.newFixedThreadPool(JUMLAH_THREAD);

        System.out.println("[MAIN] Memulai indexing " + daftarDokumen.size()
                + " dokumen dengan " + JUMLAH_THREAD + " thread...\n");


        for (String dokumen : daftarDokumen) {
            executor.submit(new IndexerWorker(
                    dokumen, processor,
                    hasilIndex, infoThread, infoDurasi,
                    latch
            ));
        }


        executor.shutdown();

        System.out.println("[MAIN] Semua task sudah di-submit. Menunggu semua selesai...\n");

        
        latch.await();

        
        executor.awaitTermination(30, TimeUnit.SECONDS);

        System.out.println("\n[MAIN] Semua dokumen selesai diproses!");


        SearchEngineCore.tampilkanHasilAkhir(daftarDokumen, hasilIndex, infoThread, infoDurasi);
    }
}