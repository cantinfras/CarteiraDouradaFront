package com.example.elton.carteiradourada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.elton.carteiradourada.model.TipoMulta;
import com.example.elton.carteiradourada.services.APIUtils;
import com.example.elton.carteiradourada.services.TipoMultaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnBuscarTiposMulta;
    Button btnAdicionarTiposMulta;
    Button btnAlterarTiposMulta;
    Button btnDeletarTiposMulta;
    ListView listTipoMulta;

    TipoMultaService tipoMultaService;
    List<TipoMulta> lista = new ArrayList<TipoMulta>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdicionarTiposMulta = findViewById(R.id.btnAdicionarTiposMulta);
        btnBuscarTiposMulta = findViewById(R.id.btnBuscarTiposMulta);
        listTipoMulta = findViewById(R.id.listTiposMulta);
        tipoMultaService = APIUtils.getTipoMultaService();

        btnBuscarTiposMulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTiposMulta();
            }
        });

        btnAdicionarTiposMulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TipoMultaActivity.class);
                intent.putExtra("codigo", "");
                intent.putExtra("descricao", "");
                intent.putExtra("infrator", "");
                intent.putExtra("pontos", "");
                startActivity(intent);

            }
        });
    }

    public void getTiposMulta(){
        Call<List<TipoMulta>> call = tipoMultaService.getTiposMulta();
        call.enqueue(new Callback<List<TipoMulta>>() {
            @Override
            public void onResponse(Call<List<TipoMulta>> call, Response<List<TipoMulta>> response) {
                if(response.isSuccessful()){
                    lista = response.body();
                    listTipoMulta.setAdapter(new TipoMultaAdapter(MainActivity.this, R.layout.list_tiposmulta, lista));
                }
            }

            @Override
            public void onFailure(Call<List<TipoMulta>> call, Throwable t) {
                Log.e("ERRO!!! ", t.getMessage());
            }
        });
    }

}
