package Soal1;

import java.util.Random;


public class Kurir implements Runnable {

    private final Gudang gudang;
    private final Random random;
    private final String nama;

    public Kurir(Gudang gudang, String nama) {
        this.gudang = gudang;
        this.random = new Random();
        this.nama = nama;
    }

    @Override
    public void run() {
        System.out.println("[" + nama + "] Kurir mulai bekerja.");

        try {
            while (!Thread.currentThread().isInterrupted()) {

                int jumlahAmbil = random.nextInt(3) + 1;

                gudang.ambilStok(jumlahAmbil);


                int waktuTunggu = 2000 + random.nextInt(1001);
                Thread.sleep(waktuTunggu);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[" + nama + "] Kurir dihentikan.");
        }
    }
}