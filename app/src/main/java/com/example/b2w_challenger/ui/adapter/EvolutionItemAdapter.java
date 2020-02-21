package com.example.b2w_challenger.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EvolutionItemAdapter extends RecyclerView.Adapter<EvolutionItemAdapter.ViewHolder> {
    private List<Pokemon> pokemonList;

    public EvolutionItemAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_evolution_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(pokemonList.get(position).getName());
        Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/" +
                String.valueOf(pokemonList.get(position).getId()) + ".png")
                .placeholder(R.drawable.ic_ball)
                .fit()
                .into(holder.imgPoke);

        if (position != getItemCount() - 1) holder.imgArrow.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        if (pokemonList != null) return pokemonList.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private ImageView imgPoke;
        private ImageView imgArrow;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tv_poke_name);
            imgPoke = itemView.findViewById(R.id.img_poke);
            imgArrow = itemView.findViewById(R.id.img_arrow);
        }
    }
}
