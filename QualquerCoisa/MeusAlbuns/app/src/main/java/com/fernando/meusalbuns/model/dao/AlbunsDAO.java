package com.fernando.meusalbuns.model.dao;

import android.os.Build;

import com.fernando.meusalbuns.model.bean.Albuns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 01796085090 on 16/11/2017.
 */

public class AlbunsDAO {
    public static AlbunsDAO manager = new AlbunsDAO();
    private List<Albuns> lista;
    private long id = 0;

    //Contructor
    private AlbunsDAO() {
        lista = new ArrayList<>();
    }
    public int size(){return lista.size();}

    public void add(Albuns obj) throws DaoException {
        Albuns pesquisa = null;
        for(Albuns item:lista){
            if(item.getAlbum().equals(obj.getAlbum())){
                pesquisa = item;
                break;
            }
        }

        if(pesquisa != null){
            throw new DaoException("Álbum duplicado para a banda: " + obj.getAlbum());
        }
        // incrementa o ID
        id++;

        obj.setId(id);
        lista.add(obj);
    }
    public void del(long id) throws DaoException {
        Albuns pesquisa = get(id);
        lista.remove(pesquisa);


    }
    public Albuns get(long id) throws DaoException {
        Albuns pesquisa = null;
        for (Albuns item : lista) {
            if (item.getId() == id) {
                pesquisa = item;
                break;
            }
        }
        if (pesquisa == null) {
            throw new DaoException("Álbum não encontrado para o id: " + id);
        }
        return pesquisa;
    }
    public void put (Albuns obj) throws DaoException {
        Albuns pesquisa = get(obj.getId());
        pesquisa.setBanda(obj.getBanda());
        pesquisa.setAlbum(obj.getAlbum());
        pesquisa.setGenero(obj.getGenero());
        pesquisa.setLancamento(obj.getLancamento());
        pesquisa.setDel(obj.isDel());
    }
    //Método que retorna a lista de contatos
    public List<Albuns> all() {return lista;}

    public void ordenar() throws DaoException{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //Collections.sort(lista, Comparator.comparing(Contatos::getNome));
            Collections.sort(lista, new Comparator<Albuns>() {
                @Override
                public int compare(Albuns o1, Albuns o2) {

                    return o1.getBanda().compareToIgnoreCase(o2.getBanda());
                }
            });
        }
        else{
            throw new DaoException("Erro ao ordenar a lista!");
        }

    }
}
