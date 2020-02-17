package com.example.b2w_challenger.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.Pokedex;
import com.example.b2w_challenger.ui.contracts.PokedexContract;

import java.util.List;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.ViewHolder> {
    private List<Pokedex.PokemonSimple> pokemonList;
    private final PokedexContract.PokedexClickListener pokedexClickListener;

    public PokedexAdapter(List<Pokedex.PokemonSimple> pokemonList, PokedexContract.PokedexClickListener pokedexClickListener) {
        this.pokemonList = pokemonList;
        this.pokedexClickListener = pokedexClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_pokedex, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(pokemonList.get(position).getName());
        holder.bind(getPokemon(position));
    }

    @Override
    public int getItemCount() {
        if (pokemonList != null) return pokemonList.size();
        else return 0;
    }

    private Pokedex.PokemonSimple getPokemon(int position) {
        if (position > pokemonList.size()) return null;
        return pokemonList.get(position);
    }

    public void setPokemonList(List<Pokedex.PokemonSimple> pokemonList) {
        this.pokemonList = pokemonList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
        }

        public void bind(Pokedex.PokemonSimple pokemon) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pokedexClickListener.onPokemonClick(pokemon, v);
                }
            });
        }
    }
}