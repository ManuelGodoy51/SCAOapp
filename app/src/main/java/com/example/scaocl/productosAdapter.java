package com.example.scaocl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class productosAdapter extends RecyclerView.Adapter<productosAdapter.productosHolder> {
    List<producto> listaProductos;

    public productosAdapter (List<producto> listaProductos){
        this.listaProductos= listaProductos;
    }

    @Override
    public productosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_list,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new productosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull productosHolder holder, int position) {
        holder.TVcodigoBarra.setText(listaProductos.get(position).getCodigo_barra().toString());
        holder.TVnombre.setText(listaProductos.get(position).getNombre().toString());
        holder.TVmarca.setText(listaProductos.get(position).getMarca().toString());
        holder.TVstock.setText(listaProductos.get(position).getStock().toString());
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class productosHolder extends RecyclerView.ViewHolder {

        TextView TVcodigoBarra, TVnombre, TVmarca,TVstock;

        public productosHolder(View itemView){
            super(itemView);
            TVcodigoBarra = itemView.findViewById(R.id.TVcodigoBarra);
            TVnombre = itemView.findViewById(R.id.TVnombre);
            TVmarca = itemView.findViewById(R.id.TVmarca);
            TVstock = itemView.findViewById(R.id.TVstock);
        }
    }
}
