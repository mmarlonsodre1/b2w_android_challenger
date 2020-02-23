package com.example.b2w_challenger.ui.adapter;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.Ability.AbilityInfo;
import com.example.b2w_challenger.ui.contracts.AbilityContract;

import java.util.List;

public class AbilityAdapter extends RecyclerView.Adapter<AbilityAdapter.ViewHolder> {
    private List<AbilityInfo> abilitiesList;
    private AbilityContract.AbilityClickListener abilityClickListener;

    public AbilityAdapter(List<AbilityInfo> abilitiesList, AbilityContract.AbilityClickListener abilityClickListener) {
        this.abilitiesList = abilitiesList;
        this.abilityClickListener = abilityClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_ability, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpannableString name = new SpannableString(abilitiesList.get(position).getName());
        name.setSpan(new UnderlineSpan(), 0, name.length(), 0);

        holder.tvName.setText(name);
        holder.bind(abilitiesList.get(position));
    }

    @Override
    public int getItemCount() {
        if (abilitiesList != null) return abilitiesList.size();
        else return 0;
    }

    public void setAbilitiesList(List<AbilityInfo> abilitiesList) {
        this.abilitiesList = abilitiesList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_ability_name);
        }

        public void bind(AbilityInfo abilityInfo) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abilityClickListener.onAbilityClick(abilityInfo);
                }
            });
        }
    }
}