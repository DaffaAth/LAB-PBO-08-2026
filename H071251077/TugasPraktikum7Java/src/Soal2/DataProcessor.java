package Soal2;
import java.util.Random;


public class DataProcessor {

    private static final Random random = new Random();

    /**
     * Mensimulasikan proses pembacaan file dan menghitung jumlah kata.
     *
     * @param fileName nama file/dokumen yang diproses
     * @return jumlah kata yang ditemukan (simulasi, acak antara 50-500)
     * @throws InterruptedException jika thread diinterupsi saat sleep
     */
    public int process(String fileName) throws InterruptedException {
        // Simulasi waktu baca file: acak antara 500ms - 2000ms
        int waktuProses = 500 + random.nextInt(1501);
        Thread.sleep(waktuProses);

        // Simulasi jumlah kata yang ditemukan: acak antara 50 - 500
        int jumlahKata = 50 + random.nextInt(451);

        return jumlahKata;
    }
}