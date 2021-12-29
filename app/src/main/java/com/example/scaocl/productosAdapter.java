package com.example.scaocl;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class productosAdapter extends RecyclerView.Adapter<productosAdapter.productosHolder> {
    List<producto> listaProducto;
    @Override
    public productosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull productosHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class productosHolder extends RecyclerView.ViewHolder {
        public productosHolder(View itemView){
            super(itemView);
        }
    }
}
