package com.example.aplikasikalkulator;

public class ListRiwayat {
    private final String Nilai1;
    private final String Operasi;
    private final String Nilai2;
    private final String Hasil;

    public ListRiwayat(String nilai1, String operasi, String nilai2, String hasil) {
        Nilai1 = nilai1;
        Operasi = operasi;
        Nilai2 = nilai2;
        Hasil = hasil;
    }

    public String getNilai1() {
        return Nilai1;
    }

    public String getOperasi() {
        return Operasi;
    }

    public String getNilai2() {
        return Nilai2;
    }

    public String getHasil() {
        return Hasil;
    }
}
