package com.fernando.meusalbuns.views;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.fernando.meusalbuns.R;
import com.fernando.meusalbuns.model.bean.Albuns;
import com.fernando.meusalbuns.model.dao.AlbunsDAO;
import com.fernando.meusalbuns.model.dao.DaoException;
import com.fernando.meusalbuns.utils.UtilsHelper;

/**
 * Created by 01796085090 on 14/11/2017.
 */

public class AddAlbumActivity extends AppCompatActivity {

    private EditText idBanda;
    private EditText idAlbum;
    private EditText idGenero;
    private EditText idLancamento;
    private long idDisco = 0;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_album);
        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        //pega o ID passado
        idDisco = getIntent().getLongExtra("idDisco", 0);

        //pega os campos da tela
        idBanda = (EditText) findViewById(R.id.idBanda);
        idAlbum = (EditText) findViewById(R.id.idAlbum);
        idGenero = (EditText) findViewById(R.id.idGenero);
        idLancamento = (EditText) findViewById(R.id.idLancamento);

        if(idDisco > 0) {

            try {
                //pega o contato selecionado na Lista de Contatos
                Albuns oldAlbuns = AlbunsDAO.manager.get(idDisco);

                //seta os dados do contato nos campos
                idBanda.setText(oldAlbuns.getBanda());
                idAlbum.setText(oldAlbuns.getAlbum());
                idGenero.setText(oldAlbuns.getGenero());
                idLancamento.setText(oldAlbuns.getLancamento());


            } catch (DaoException e) {
                UtilsHelper.msg(getBaseContext(), e.getMessage());
                idDisco = 0;
            }
        }

    }

    public void onSalvar(View view) {
        String nmBanda      = idBanda.getEditableText().toString();
        String nmAlbum      = idAlbum.getEditableText().toString();
        String nmGenero     = idGenero.getEditableText().toString();
        String nmLancamento = idLancamento.getEditableText().toString();

        Albuns albuns = new Albuns();
        albuns.setId(idDisco);
        albuns.setBanda(nmBanda);
        albuns.setAlbum(nmAlbum);
        albuns.setGenero(nmGenero);
        albuns.setLancamento(nmLancamento);

        try {
            if (idDisco == 0){AlbunsDAO.manager.add(albuns);}
            else               {AlbunsDAO.manager.put(albuns);}

            //mostra a mensagem de sucesso
            UtilsHelper.msg(getBaseContext(), "Contato salvo com sucesso!");

            setResult(Activity.RESULT_OK);

        } catch (DaoException e) {
            UtilsHelper.msg(getBaseContext(),e.getMessage());
            setResult(Activity.RESULT_CANCELED, null);
        }


        finish();

           /*     String txt = "Nome: " + nmNome + "\n";
                txt += "Email: " + nmEmail + "\n";
                txt += "Telefone: " + nmTelefone + "\n";

              Toast.makeText(getBaseContext(), txt, Toast.LENGTH_LONG).show();
*/            }

}