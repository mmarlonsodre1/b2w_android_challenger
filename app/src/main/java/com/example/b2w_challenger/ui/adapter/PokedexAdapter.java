package com.example.b2w_challenger.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.Pokemon;

import java.util.List;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.ViewHolder> {
    private final List<Pokemon> pokemonList;

    public PokedexAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokedex_adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(pokemonList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (pokemonList != null) return pokemonList.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}