package com.fernando.meusalbuns.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fernando.meusalbuns.R;
import com.fernando.meusalbuns.model.dao.AlbunsDAO;
import com.fernando.meusalbuns.model.dao.DaoException;
import com.fernando.meusalbuns.views.adapter.ListaAlbunsAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;
    private ListaAlbunsAdapter adapter;


    // cria o menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // cria as funções do menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.novo_album:
                goContatos(0);
                break;
            case R.id.ordenar:
                ordenar();
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //carrega o layout padrão
        setContentView(R.layout.activity_main);

        adapter = new ListaAlbunsAdapter(getBaseContext());

        //pega o ListView
        listView = (ListView) findViewById(R.id.listaAlbum);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }


    //Método executado ao pressionar o botão Contatos
    private void goContatos(long id) {
        Intent it = new Intent(getBaseContext(), AddAlbumActivity.class);
        it.putExtra("idContato", id);
        startActivityForResult(it, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        goContatos(id);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        dialogo.setTitle(R.string.excluir_album);
        dialogo.setMessage(R.string.confirma_exclusao)
                .setPositiveButton(R.string.excluir, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {

                            AlbunsDAO.manager.del(id);

                            adapter.notifyDataSetChanged();

                            Toast.makeText(getBaseContext(), "Álbum excluído", Toast.LENGTH_LONG).show();
                        } catch (DaoException e) {
                        }
                    }
                });
        dialogo.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogo.create();
        dialogo.show();


        return true;
    }
    private void ordenar(){
        try {
            AlbunsDAO.manager.ordenar();
            adapter.notifyDataSetChanged();
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }
}
