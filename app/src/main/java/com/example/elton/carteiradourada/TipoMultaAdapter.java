package com.example.elton.carteiradourada;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.elton.carteiradourada.model.TipoMulta;

import java.util.List;

public class TipoMultaAdapter extends ArrayAdapter<TipoMulta> {

    private Context context;
    private List<TipoMulta> tiposMulta;

    public TipoMultaAdapter(@NonNull Context context, int resource,@NonNull List<TipoMulta> objects) {
        super(context, resource, objects);
        this.context = context;
        this.tiposMulta = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_tiposmulta, parent, false);

        TextView txtTipoMultaId = rowView.findViewById(R.id.txtTipoMultaId);
        TextView txtTipoMultaCodigo = rowView.findViewById(R.id.txtTipoMultaCodigo);
        TextView txtTipoMultaDescricao = rowView.findViewById(R.id.txtTipoMultaDescricao);
        TextView txtTipoMultaInfrator = rowView.findViewById(R.id.txtTipoMultaInfrator);
        TextView txtTipoMultaPontos = rowView.findViewById(R.id.txtTipoMultaPontos);

        txtTipoMultaId.setText(String.format("Id: %d", tiposMulta.get(pos).getId())); //%d
        txtTipoMultaCodigo.setText(String.format("Código: %s", tiposMulta.get(pos).getCodigo())); //%d
        txtTipoMultaDescricao.setText(String.format("Descrição: %s", tiposMulta.get(pos).getDescricao())); //%d
        txtTipoMultaInfrator.setText(String.format("Infrator: %s", tiposMulta.get(pos).getInfrator())); //%d
        txtTipoMultaPontos.setText(String.format("Pontos: %d", tiposMulta.get(pos).getPontos())); //%s

        rowView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //inicia Activity TipoMulta form
                Intent intent = new Intent(context, TipoMultaActivity.class);
                intent.putExtra("id", String.valueOf(tiposMulta.get(pos).getId()));
                intent.putExtra("codigo", tiposMulta.get(pos).getCodigo());
                intent.putExtra("descricao", tiposMulta.get(pos).getDescricao());
                intent.putExtra("infrator", tiposMulta.get(pos).getInfrator());
                intent.putExtra("pontos", String.valueOf(tiposMulta.get(pos).getPontos()));
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}
