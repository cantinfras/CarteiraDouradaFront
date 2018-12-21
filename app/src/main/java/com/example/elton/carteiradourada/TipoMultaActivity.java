package com.example.elton.carteiradourada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elton.carteiradourada.model.TipoMulta;
import com.example.elton.carteiradourada.services.APIUtils;
import com.example.elton.carteiradourada.services.TipoMultaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipoMultaActivity extends AppCompatActivity {

    TipoMultaService tipoMultaService;
    EditText edTipoMultaId;
    EditText edTipoMultaCodigo;
    EditText edTipoMultaDescricao;
    EditText edTipoMultaInfrator;
    EditText edTipoMultaPontos;
    Button btnVoltar;
    Button btnSalvarTipoMulta;
    Button btnDeletarTipoMulta;
    TextView txTipoMultaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_multa);

        txTipoMultaId = findViewById(R.id.txtTipoMultaId);
        edTipoMultaCodigo = findViewById(R.id.edTipoMultaCodigo);
        edTipoMultaDescricao = findViewById(R.id.edTipoMultaDescricao);
        edTipoMultaInfrator = findViewById(R.id.edTipoMultaInfrator);
        edTipoMultaPontos = findViewById(R.id.edTipoMultaPontos);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnDeletarTipoMulta = findViewById(R.id.btnDeletarTipoMulta);
        btnDeletarTipoMulta = findViewById(R.id.btnDeletarTipoMulta);

        tipoMultaService = APIUtils.getTipoMultaService();

        Bundle extras = getIntent().getExtras();
        final String tipoMultaId = extras.getString("id");
        String tipoMultaCodigo = extras.getString("codigo");
        String tipoMultaDescricao = extras.getString("descricao");
        String tipoMultaInfrator = extras.getString("infrator");
        final String tipoMultaPontos = extras.getString("pontos");

        edTipoMultaId.setText(tipoMultaId);
        edTipoMultaCodigo.setText(tipoMultaCodigo);
        edTipoMultaDescricao.setText(tipoMultaDescricao);
        edTipoMultaInfrator.setText(tipoMultaInfrator);
        edTipoMultaPontos.setText(tipoMultaPontos);

        if(tipoMultaId != null && tipoMultaId.trim().length() > 0){
            edTipoMultaId.setFocusable(false);
        } else {
            txTipoMultaId.setVisibility(View.INVISIBLE);
            edTipoMultaId.setVisibility(View.INVISIBLE);
            btnDeletarTipoMulta.setVisibility(View.INVISIBLE);
        }

        btnSalvarTipoMulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipoMulta tm = new TipoMulta();
                tm.setCodigo(edTipoMultaCodigo.getText().toString());
                tm.setDescricao(edTipoMultaDescricao.getText().toString());
                tm.setInfrator(edTipoMultaInfrator.getText().toString());
                tm.setPontos(edTipoMultaPontos.getText().toString());

                 if(tipoMultaId != null && tipoMultaId.trim().length() > 0){
                    updateTipoMulta(Integer.parseInt(tipoMultaId), tm);
                 } else {
                    addTipoMulta(tm);
                 }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TipoMultaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDeletarTipoMulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTipoMulta(Integer.parseInt(tipoMultaId));

                Intent intent = new Intent(TipoMultaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addTipoMulta(TipoMulta tm){
        Call<TipoMulta> call = tipoMultaService.addTipoMulta(tm);
        call.enqueue(new Callback<TipoMulta>() {
            @Override
            public void onResponse(Call<TipoMulta> call, Response<TipoMulta> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TipoMultaActivity.this, "Tipo de multa criado com sucesso!", Toast.LENGTH_SHORT).show());
                }
            }

            @Override
            public void onFailure(Call<TipoMulta> call, Throwable t) {
                Log.e("ERRO: ", t.getMessage())
            }
        });
    }

    public void updateTipoMulta(int id, TipoMulta tm){
        Call<TipoMulta> call = tipoMultaService.updateTipoMulta(id, tm);
        call.enqueue(new Callback<TipoMulta>() {
            @Override
            public void onResponse(Call<TipoMulta> call, Response<TipoMulta> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TipoMultaActivity.this, "Tipo de multa alterado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TipoMulta> call, Throwable t) {
                Log.e("ERRO: ", t.getMessage());
            }
        });
    }

    public void deleteTipoMulta(int id){
        Call<TipoMulta> call = tipoMultaService.deleteTipoMulta(id);
        call.enqueue(new Callback<TipoMulta>() {
            @Override
            public void onResponse(Call<TipoMulta> call, Response<TipoMulta> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TipoMultaActivity.this, "Tipo de multa apagado com sucesso!", Toast.LENGTH_SHORT).show());
                }
            }

            @Override
            public void onFailure(Call<TipoMulta> call, Throwable t) {
                Log.e("ERRO: ", t.getMessage())
            }
        });
    }

}
