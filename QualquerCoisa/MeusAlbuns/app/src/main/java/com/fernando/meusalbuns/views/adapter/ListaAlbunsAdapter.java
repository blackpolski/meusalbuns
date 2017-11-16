package com.fernando.meusalbuns.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fernando.meusalbuns.R;
import com.fernando.meusalbuns.model.bean.Albuns;
import com.fernando.meusalbuns.model.dao.AlbunsDAO;

import java.util.List;

/**
 * Created by 01796085090 on 14/11/2017.
 */

public class ListaAlbunsAdapter extends BaseAdapter {

    private List<Albuns> lista;
    private LayoutInflater layout;

    public ListaAlbunsAdapter(Context context){

        lista = AlbunsDAO.manager.all();
        layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return (Albuns) lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Albuns c = lista.get(position);

        // Verifica a View
        if(convertView == null){
            convertView = layout.inflate(R.layout.item_list, null, true);

        }
        //pega os campos
        TextView banda = (TextView) convertView.findViewById(R.id.lBanda);
        TextView album = (TextView) convertView.findViewById(R.id.lAlbum);
        TextView genero = (TextView) convertView.findViewById(R.id.lGenero);
        TextView lancamento = (TextView) convertView.findViewById(R.id.lLancamento);

        banda.setText(c.getBanda());
        album.setText(c.getAlbum());
        genero.setText(c.getGenero());
        lancamento.setText(c.getLancamento());


        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
