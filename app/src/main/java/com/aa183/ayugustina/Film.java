package com.aa183.ayugustina;

public class Film {

    private int idFilm;
    private String judul;
    private String gambar;
    private String genre;
    private String durasi;
    private String tahun;
    private String sinopsisFilm;
    private String pemeranFilm;

    public Film(int idFilm, String judul, String gambar, String genre, String durasi, String tahun, String sinopsisFilm, String pemeranFilm) {
        this.idFilm = idFilm;
        this.judul = judul;
        this.gambar = gambar;
        this.genre = genre;
        this.durasi = durasi;
        this.tahun = tahun;
        this.sinopsisFilm = sinopsisFilm;
        this.pemeranFilm = pemeranFilm;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String genre) {
        this.gambar = gambar;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getSinopsisFilm() {
        return sinopsisFilm;
    }

    public void setSinopsisFilm(String sinopsisFilm) {
        this.sinopsisFilm = sinopsisFilm;
    }

    public String getPemeranFilm() {
        return pemeranFilm;
    }

    public void setPemeranFilm(String pemeranFilm) {
        this.pemeranFilm = pemeranFilm;
    }
}
