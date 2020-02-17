package com.example.b2w_challenger.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b2w_challenger.MyApplication;
import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.AbilityInfo;
import com.example.b2w_challenger.models.Pokedex;
import com.example.b2w_challenger.models.Pokemon;
import com.example.b2w_challenger.ui.contracts.AbilityContract;
import com.example.b2w_challenger.ui.presenter.AbilityPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.b2w_challenger.services.PokemonService.BASE_URL;

public class PokemonFragment extends Fragment implements AbilityContract.AbilitiesRequestListener {
    private AbilityPresenter presenter;
    private List<AbilityInfo> abilityList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new AbilityPresenter(this);
        ((MyApplication) getActivity().getApplication()).setServiceComponent(BASE_URL);
        ((MyApplication) getActivity().getApplication()).getServiceComponent().inject(presenter);

        return inflater.inflate(R.layout.fragment_pokemon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Pokedex.PokemonSimple pokemon = (Pokedex.PokemonSimple) getArguments().getSerializable("POKEMON");
        presenter.getPokemon(pokemon.getName());

        abilityList = new ArrayList<>();
    }

    @Override
    public void onAbilitySucess(AbilityInfo abilityInfo) {
        if (abilityInfo != null) {
            abilityList.add(abilityInfo);
        }
    }

    @Override
    public void onPokemonSucess(Pokemon pokemon) {
        if (pokemon != null) {
            for (int i = 0; i < pokemon.getAbilities().size(); i++) {
                String[] ability = pokemon.getAbilities().get(i).getAbilitySimple().getUrl().split("/");
                presenter.getAbility(Integer.parseInt(ability[ability.length - 1]));
            }
        }
    }

    @Override
    public void onError(Throwable error) {

    }

    @Override
    public void onComplete() {

    }
}
