package com.fernando.meusalbuns.model.bean;

/**
 * Created by 01796085090 on 16/11/2017.
 */

public class Albuns {
    private long id;
    private String banda;
    private String album;
    private String genero;
    private String lancamento;
    private boolean del;

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getLancamento() { return lancamento; }

    public void setLancamento(String lancamento) { this.lancamento = lancamento; }

    public String getBanda() {
        return banda;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}