package com.example.transmobile.Entradas.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.transmobile.Entradas.Modelos.mEntrada;
import com.example.transmobile.Entradas.RealmDB.rCliente;
import com.example.transmobile.Entradas.RealmDB.rColor;
import com.example.transmobile.Entradas.RealmDB.rModelo;
import com.example.transmobile.Global.VarGlobal;
import com.example.transmobile.R;
import java.util.ArrayList;
import java.util.List;

import static com.example.transmobile.Entradas.EntradaActivity.GetCurrentTimeStamp2;

public class AdapterReimprecionEntrada extends RecyclerView.Adapter<AdapterReimprecionEntrada.HolderReimpresionEntrada>{
    private Context context;
    private List<mEntrada>List = new ArrayList<mEntrada>();

    public AdapterReimprecionEntrada(Context context, List<mEntrada> list) {
        this.context = context;
        List = list;

    }

    @NonNull
    @Override
    public HolderReimpresionEntrada onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterReimprecionEntrada.HolderReimpresionEntrada(LayoutInflater.from(context).inflate(R.layout.item_rv_reimpresion_entrada, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderReimpresionEntrada holder, final int position) {
            holder.textView_cliente.setText(rCliente.getClienteById(List.get(position).getId_clinete()).getNombreClinete());
            holder.textView_modelo.setText(rModelo.getModeloById(List.get(position).getId_modelo()).getNombreModelo());
            holder.textView_color.setText(rColor.getcolorByid(List.get(position).getId_color()).getNombreColor());
            holder.textView_chasis.setText(List.get(position).getChasis());
            holder.textView_impreso.setText("Impreso: "+List.get(position).getImpresa());
            holder.CardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  VarGlobal.editText.setText(rCliente.getClienteById(List.get(position).getId_clinete()).getNombreClinete() +
                            "|" + List.get(position).getChasis() +
                            "|" + List.get(position).getChasis().substring(11, 17) +
                            "|" + GetCurrentTimeStamp2());
                    VarGlobal.mPrint.performClick();
                }
            });

    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class HolderReimpresionEntrada extends RecyclerView.ViewHolder {
        private TextView textView_cliente, textView_chasis, textView_modelo, textView_color, textView_impreso;
        private CardView CardView;
        public HolderReimpresionEntrada(@NonNull View itemView) {
            super(itemView);
            textView_cliente = itemView.findViewById(R.id.textView_cliente);
            textView_chasis = itemView.findViewById(R.id.textView_chasis);
            textView_modelo = itemView.findViewById(R.id.textView_modelo);
            textView_color = itemView.findViewById(R.id.textView_color);
            textView_impreso = itemView.findViewById(R.id.textView_impreso);
            CardView = itemView.findViewById(R.id.CardView);
        }
    }
}
