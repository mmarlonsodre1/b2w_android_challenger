package com.example.b2w_challenger.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b2w_challenger.MyApplication;
import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.AbilityInfo;
import com.example.b2w_challenger.models.Evolution;
import com.example.b2w_challenger.models.Pokedex;
import com.example.b2w_challenger.models.Pokemon;
import com.example.b2w_challenger.models.Specie;
import com.example.b2w_challenger.models.Stats;
import com.example.b2w_challenger.models.Types;
import com.example.b2w_challenger.ui.adapter.EvolutionAdapter;
import com.example.b2w_challenger.ui.adapter.EvolutionItemAdapter;
import com.example.b2w_challenger.ui.contracts.AbilityContract;
import com.example.b2w_challenger.ui.presenter.AbilityPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.b2w_challenger.services.PokemonService.BASE_URL;

public class PokemonFragment extends Fragment
        implements AbilityContract.AbilitiesRequestListener {
    private AbilityPresenter presenter;
    private EvolutionAdapter evolutionItemAdapter;
    private List<AbilityInfo> abilityList;
    private List<Pokemon>[] pokemonsVector;
    private Pokemon pokemon;

    private RecyclerView rvEvolution;
    private ImageView imgPoke;
    private ImageView imgBug;
    private ImageView imgDark;
    private ImageView imgDragon;
    private ImageView imgElectric;
    private ImageView imgfairy;
    private ImageView imgFighting;
    private ImageView imgFire;
    private ImageView imgFlying;
    private ImageView imgGhost;
    private ImageView imgGrass;
    private ImageView imgGround;
    private ImageView imgIce;
    private ImageView imgNormal;
    private ImageView imgPoison;
    private ImageView imgPsychic;
    private ImageView imgRock;
    private ImageView imgSteel;
    private ImageView imgWater;

    private TextView tvHP;
    private TextView tvAttack;
    private TextView tvDefense;
    private TextView tvSpecialAttack;
    private TextView tvSpecialDefense;
    private TextView tvSpeed;

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
        setupView(view);

        Pokedex.PokemonSimple pokemon = (Pokedex.PokemonSimple) getArguments().getSerializable("POKEMON");
        presenter.getPokemon(pokemon.getName());

        abilityList = new ArrayList<>();
        pokemonsVector = new List[10];
    }

    private void setupView(View view) {
        rvEvolution = view.findViewById(R.id.rv_evolution);
        imgPoke = view.findViewById(R.id.img_poke);
        imgBug = view.findViewById(R.id.img_bug);
        imgDark = view.findViewById(R.id.img_dark);
        imgDragon = view.findViewById(R.id.img_dragon);
        imgElectric = view.findViewById(R.id.img_eletric);
        imgfairy = view.findViewById(R.id.img_fairy);
        imgFighting = view.findViewById(R.id.img_fighting);
        imgFire = view.findViewById(R.id.img_fire);
        imgFlying = view.findViewById(R.id.img_flying);
        imgGhost = view.findViewById(R.id.img_ghost);
        imgGrass = view.findViewById(R.id.img_grass);
        imgGround = view.findViewById(R.id.img_ground);
        imgIce = view.findViewById(R.id.img_ice);
        imgNormal = view.findViewById(R.id.img_normal);
        imgPoison = view.findViewById(R.id.img_poison);
        imgPsychic = view.findViewById(R.id.img_psyshic);
        imgRock = view.findViewById(R.id.img_rock);
        imgSteel = view.findViewById(R.id.img_steel);
        imgWater = view.findViewById(R.id.img_water);

        tvHP = view.findViewById(R.id.tv_hp);
        tvAttack = view.findViewById(R.id.tv_attack);
        tvDefense = view.findViewById(R.id.tv_defense);
        tvSpecialAttack = view.findViewById(R.id.tv_special_attack);
        tvSpecialDefense = view.findViewById(R.id.tv_special_defense);
        tvSpeed = view.findViewById(R.id.tv_speed);
    }

    private void forPokemonTypes(List<Types> types) {
        for (int i = 0; i < types.size(); i++) {

            switch (types.get(i).getType().getName()) {
                case "bug":
                    imgBug.setVisibility(View.VISIBLE);
                    break;

                case "dark":
                    imgDark.setVisibility(View.VISIBLE);
                    break;

                case "dragon":
                    imgDragon.setVisibility(View.VISIBLE);
                    break;

                case "eletric":
                    imgElectric.setVisibility(View.VISIBLE);
                    break;

                case "fairy":
                    imgfairy.setVisibility(View.VISIBLE);
                    break;

                case "fighting":
                    imgFighting.setVisibility(View.VISIBLE);
                    break;

                case "fire":
                    imgFire.setVisibility(View.VISIBLE);
                    break;

                case "flying":
                    imgFlying.setVisibility(View.VISIBLE);
                    break;

                case "ghost":
                    imgGhost.setVisibility(View.VISIBLE);
                    break;

                case "grass":
                    imgGrass.setVisibility(View.VISIBLE);
                    break;

                case "ground":
                    imgGround.setVisibility(View.VISIBLE);
                    break;

                case "ice":
                    imgIce.setVisibility(View.VISIBLE);
                    break;

                case "normal":
                    imgNormal.setVisibility(View.VISIBLE);
                    break;

                case "poison":
                    imgPoison.setVisibility(View.VISIBLE);
                    break;

                case "psychic":
                    imgPsychic.setVisibility(View.VISIBLE);
                    break;

                case "rock":
                    imgRock.setVisibility(View.VISIBLE);
                    break;

                case "steel":
                    imgSteel.setVisibility(View.VISIBLE);
                    break;

                case "water":
                    imgWater.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    private void forStats(List<Stats> stats) {
        for (int i = 0; i < stats.size(); i++) {
            switch (i) {
                case 0:
                    tvSpeed.setText(stats.get(0).getBase_stat());
                    break;
                case 1:
                    tvSpecialDefense.setText(stats.get(1).getBase_stat());
                    break;
                case 2:
                    tvSpecialAttack.setText(stats.get(2).getBase_stat());
                    break;
                case 3:
                    tvDefense.setText(stats.get(3).getBase_stat());
                    break;
                case 4:
                    tvAttack.setText(stats.get(4).getBase_stat());
                    break;
                case 5:
                    tvHP.setText(stats.get(5).getBase_stat());
                    break;
            }
        }
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
            this.pokemon = pokemon;
            Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/" + pokemon.getId() + ".png")
                    .placeholder(R.drawable.ball)
                    .fit()
                    .into(imgPoke);
            forPokemonTypes(pokemon.getTypes());
            forStats(pokemon.getStats());
            presenter.getPokemonSpecie(pokemon.getName());
        }
    }

    @Override
    public void onPokemonSucess(Pokemon pokemon, int index) {
        if (pokemon != null) {
            if (!existPokemon(pokemon.getName(), index)) {
                pokemonsVector[index].add(pokemon);
                Collections.sort(pokemonsVector[index], (Pokemon pokemon1, Pokemon pokemon2) -> {
                    return pokemon1.getId() - pokemon2.getId();
                });
                evolutionItemAdapter.setPokemonList(pokemonsVector);
                evolutionItemAdapter.notifyDataSetChanged();
            }
        }
    }

    private boolean existPokemon(String namePokemon, int index) {
        if (pokemonsVector[index] != null) {
            for (int i = 0; i < pokemonsVector[index].size(); i++) {
                if (pokemonsVector[index].get(i).getName().equals(namePokemon)) return true;
            }
        } else {
            pokemonsVector[index] = new ArrayList<>();
        }
        return false;
    }

    @Override
    public void onPokemonSpecieSucess(Specie specie) {
        if (specie != null) {
            String[] evolutionUrl = specie.getEvolutionSpecie().getUrl().split("/");
            presenter.getPokemonEvolution(Integer.parseInt(evolutionUrl[evolutionUrl.length - 1]));
        }
    }

    @Override
    public void onPokemonEvolutionSucess(Evolution evolution) {
        for (int i = 0; i < pokemon.getAbilities().size(); i++) {
            String[] ability = pokemon.getAbilities().get(i).getAbilitySimple().getUrl().split("/");
            presenter.getAbility(Integer.parseInt(ability[ability.length - 1]));
        }

        for (int i = 0; i < evolution.getEvolves_to().getEvolves_to().size() ; i++) {
            boolean isNull = false;
            Evolution.EvolvesTo evolves_to = evolution.getEvolves_to();

            while (!isNull) {
                presenter.getPokemonVector(evolves_to.getEvolutionInfo().getName(), i);
                if (evolves_to != null && evolves_to.getEvolves_to().size() > 0)
                    evolves_to = evolves_to.getEvolves_to().get(i);
                else isNull = true;
            }
        }

        evolutionItemAdapter = new EvolutionAdapter(pokemonsVector, getContext());
        rvEvolution.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvEvolution.setAdapter(evolutionItemAdapter);
    }

    @Override
    public void onError(Throwable error) {

    }

    @Override
    public void onComplete() {

    }
}
