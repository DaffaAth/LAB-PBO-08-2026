package Soal2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;


public class IndexerWorker implements Runnable {

    private final String namaFile;
    private final DataProcessor processor;
    private final ConcurrentHashMap<String, Integer> hasilIndex;

    private final ConcurrentHashMap<String, String> infoThread;
    private final ConcurrentHashMap<String, Long> infoDurasi;

    private final CountDownLatch latch;

    public IndexerWorker(
            String namaFile,
            DataProcessor processor,
            ConcurrentHashMap<String, Integer> hasilIndex,
            ConcurrentHashMap<String, String> infoThread,
            ConcurrentHashMap<String, Long> infoDurasi,
            CountDownLatch latch) {
        this.namaFile = namaFile;
        this.processor = processor;
        this.hasilIndex = hasilIndex;
        this.infoThread = infoThread;
        this.infoDurasi = infoDurasi;
        this.latch = latch;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        long waktuMulai = System.currentTimeMillis();

        try {
            System.out.println("[" + threadName + "] Mulai memproses: " + namaFile + "...");


            int jumlahKata = processor.process(namaFile);

            long durasi = System.currentTimeMillis() - waktuMulai;


            hasilIndex.put(namaFile, jumlahKata);
            infoThread.put(namaFile, threadName);
            infoDurasi.put(namaFile, durasi);


            System.out.printf("[%s] Selesai memproses %s (%d kata) dalam %dms%n",
                    threadName, namaFile, jumlahKata, durasi);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[" + threadName + "] Diinterupsi saat memproses: " + namaFile);
        } finally {

            latch.countDown();
        }
    }
}