package Soal2;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class SearchEngineCore {

    /**
     *  tabel ringkasan hasil indexing semua dokumen.
     *
     * @param daftarDokumen  list nama dokumen
     * @param hasilIndex     map hasil jumlah kata per dokumen
     * @param infoThread     map nama thread yang memproses per dokumen
     * @param infoDurasi     map durasi proses (ms) per dokumen
     */
    public static void tampilkanHasilAkhir(
            List<String> daftarDokumen,
            ConcurrentHashMap<String, Integer> hasilIndex,
            ConcurrentHashMap<String, String> infoThread,
            ConcurrentHashMap<String, Long> infoDurasi) {

        System.out.println("\n========================================================");
        System.out.println("           HASIL AKHIR - KLASEMEN INDEXING");
        System.out.println("========================================================");
        System.out.printf("%-22s | %-20s | %-10s | %-10s%n",
                "Nama Dokumen", "Thread", "Jml Kata", "Durasi(ms)");
        System.out.println("--------------------------------------------------------");

        long totalKata = 0;
        long totalDurasi = 0;
        int jumlahDokumen = 0;

        for (String dokumen : daftarDokumen) {
            Integer kata = hasilIndex.get(dokumen);
            String thread = infoThread.getOrDefault(dokumen, "N/A");
            Long durasi = infoDurasi.getOrDefault(dokumen, 0L);

            if (kata != null) {
                System.out.printf("%-22s | %-20s | %-10d | %-10d%n",
                        dokumen, thread, kata, durasi);
                totalKata += kata;
                totalDurasi += durasi;
                jumlahDokumen++;
            } else {
                System.out.printf("%-22s | %-20s | %-10s | %-10s%n",
                        dokumen, "N/A", "ERROR", "N/A");
            }
        }

        System.out.println("========================================================");

        if (jumlahDokumen > 0) {
            double rataRataDurasi = (double) totalDurasi / jumlahDokumen;
            System.out.printf("Total Kata Keseluruhan  : %d kata%n", totalKata);
            System.out.printf("Rata-rata Waktu Proses  : %.2f ms%n", rataRataDurasi);
            System.out.printf("Total Dokumen Diproses  : %d dokumen%n", jumlahDokumen);
        }

        System.out.println("========================================================\n");
    }
}