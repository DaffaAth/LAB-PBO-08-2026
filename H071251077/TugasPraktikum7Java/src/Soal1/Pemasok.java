package Soal1;

import java.util.Random;


public class Pemasok implements Runnable {

    private final Gudang gudang;
    private final Random random;
    private final String nama;

    public Pemasok(Gudang gudang, String nama) {
        this.gudang = gudang;
        this.random = new Random();
        this.nama = nama;
    }

    @Override
    public void run() {
        System.out.println("[" + nama + "] Pemasok mulai bekerja.");

        try {
            while (!Thread.currentThread().isInterrupted()) {
                int jumlahBarang = random.nextInt(5) + 1;

                gudang.tambahStok(jumlahBarang);


                int waktuTunggu = 1000 + random.nextInt(1001);
                Thread.sleep(waktuTunggu);
            }
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            System.out.println("[" + nama + "] Pemasok dihentikan.");
        }
    }
}