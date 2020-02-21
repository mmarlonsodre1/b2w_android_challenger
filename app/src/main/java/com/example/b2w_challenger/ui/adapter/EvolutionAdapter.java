package com.example.b2w_challenger.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.Pokemon;

import java.util.List;

public class EvolutionAdapter extends RecyclerView.Adapter<EvolutionAdapter.ViewHolder> {
    private List<Pokemon>[] pokemonList;
    private Context context;
    private EvolutionItemAdapter evolutionItemAdapter;

    public EvolutionAdapter(List<Pokemon>[] pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_evolution, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        evolutionItemAdapter = new EvolutionItemAdapter(pokemonList[position]);
        holder.rvEvolutionItem.setLayoutManager(new LinearLayoutManager
                (context, LinearLayoutManager.HORIZONTAL, false));
        holder.rvEvolutionItem.setAdapter(evolutionItemAdapter);
    }

    @Override
    public int getItemCount() {
        if (pokemonList != null) return pokemonList.length;
        else return 0;
    }

    public void setPokemonList(List<Pokemon>[] pokemonList) {
        this.pokemonList = pokemonList;
        evolutionItemAdapter.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvEvolutionItem;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvEvolutionItem = itemView.findViewById(R.id.rv_evolution_item);
        }
    }
}
