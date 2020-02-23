package com.example.b2w_challenger.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b2w_challenger.MyApplication;
import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.Ability.AbilityInfo;
import com.example.b2w_challenger.models.Evolution.Evolution;
import com.example.b2w_challenger.models.Evolution.EvolvesTo;
import com.example.b2w_challenger.models.Pokedex.PokemonSimple;
import com.example.b2w_challenger.models.Pokemon.Pokemon;
import com.example.b2w_challenger.models.Specie.Specie;
import com.example.b2w_challenger.models.Pokemon.Stats;
import com.example.b2w_challenger.models.Pokemon.Types;
import com.example.b2w_challenger.ui.adapter.EvolutionAdapter;
import com.example.b2w_challenger.ui.adapter.ImagePokemonAdapter;
import com.example.b2w_challenger.ui.contracts.AbilityContract;
import com.example.b2w_challenger.ui.presenter.AbilityPresenter;
import com.example.b2w_challenger.util.Preferences;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.b2w_challenger.services.PokemonService.BASE_API_URL;
import static com.example.b2w_challenger.services.PokemonService.BASE_IMAGE_URL;

public class PokemonFragment extends Fragment
        implements AbilityContract.AbilitiesRequestListener {
    private AbilityPresenter presenter;
    private List<AbilityInfo> abilityList;
    private List<Pokemon>[] pokemonsVector;
    private Pokemon pokemon;

    private EvolutionAdapter evolutionAdapter;
    private ImagePokemonAdapter imagePokemonAdapter;

    private RecyclerView rvEvolution;
    private ViewPager vpImagePokemon;

    private TabLayout tbDots;

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

    private TextView tvPokeName;
    private TextView tvHP;
    private TextView tvAttack;
    private TextView tvDefense;
    private TextView tvSpecialAttack;
    private TextView tvSpecialDefense;
    private TextView tvSpeed;

    @Override
    public void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move).setDuration(350));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new AbilityPresenter(this);
        ((MyApplication) getActivity().getApplication()).setServiceComponent(BASE_API_URL);
        ((MyApplication) getActivity().getApplication()).getServiceComponent().inject(presenter);

        return inflater.inflate(R.layout.fragment_pokemon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);

        abilityList = new ArrayList<>();
        pokemonsVector = new List[10];

        PokemonSimple pokemon = (PokemonSimple) getArguments().getSerializable("POKEMON");
        if (pokemon != null) {
            Pokemon pokemonSave = Preferences.getPokemon(getContext(), pokemon.getName());
            if (pokemonSave == null) presenter.getPokemon(pokemon.getName());
            else onPokemonSucess(pokemonSave);
        }

        vpImagePokemon.setTransitionName("transition" + pokemon.getName());
    }

    private void setupView(View view) {
        rvEvolution = view.findViewById(R.id.rv_evolution);
        vpImagePokemon = view.findViewById(R.id.vp_image_pokemon);
        tbDots = view.findViewById(R.id.tb_dot);

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

        tvPokeName = view.findViewById(R.id.tv_poke_name);
        tvHP = view.findViewById(R.id.tv_hp);
        tvAttack = view.findViewById(R.id.tv_attack);
        tvDefense = view.findViewById(R.id.tv_defense);
        tvSpecialAttack = view.findViewById(R.id.tv_special_attack);
        tvSpecialDefense = view.findViewById(R.id.tv_special_defense);
        tvSpeed = view.findViewById(R.id.tv_speed);
    }

    private void setupCarousel() {
        String urlImageDefault = BASE_IMAGE_URL + pokemon.getId() + ".png";
        imagePokemonAdapter = new ImagePokemonAdapter(getContext(), pokemon.getSprites(), urlImageDefault);
        vpImagePokemon.setAdapter(imagePokemonAdapter);

        tbDots.setupWithViewPager(vpImagePokemon, true);
        tbDots.setVisibility(View.VISIBLE);

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

                case "electric":
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

            setupCarousel();

            tvPokeName.setText(pokemon.getName());
            forPokemonTypes(pokemon.getTypes());
            forStats(pokemon.getStats());
            evolutionAdapter = new EvolutionAdapter(pokemonsVector, getContext());

            Specie specie = Preferences.getSpecie(getContext(), pokemon.getName());
            if (specie == null) presenter.getPokemonSpecie(pokemon.getName());
            else onPokemonSpecieSucess(specie);
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
                evolutionAdapter.setPokemonList(pokemonsVector);
                evolutionAdapter.notifyDataSetChanged();
                Preferences.savePokemon(getContext(), pokemon);
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
            if (Preferences.getSpecie(getContext(), specie.getName()) == null)
                Preferences.saveSpecie(getContext(), specie);

            String[] evolutionUrl = specie.getEvolutionSpecie().getUrl().split("/");

            Evolution evolution = Preferences.getEvolution(getContext(),
                    Integer.parseInt(evolutionUrl[evolutionUrl.length - 1]));

            if (evolution == null)
                presenter.getPokemonEvolution(Integer.parseInt(
                        evolutionUrl[evolutionUrl.length - 1]));
            else onPokemonEvolutionSucess(evolution);
        }
    }

    @Override
    public void onPokemonEvolutionSucess(Evolution evolution) {
        if (evolution != null) {
            if (Preferences.getEvolution(getContext(), evolution.getId()) == null)
                Preferences.saveEvolution(getContext(), evolution);

            for (int i = 0; i < pokemon.getAbilities().size(); i++) {
                String[] ability = pokemon.getAbilities().get(i).getAbilitySimple().getUrl().split("/");
                presenter.getAbility(Integer.parseInt(ability[ability.length - 1]));
            }

            for (int i = 0; i < evolution.getEvolves_to().getEvolves_to().size(); i++) {
                boolean isNull = false;
                EvolvesTo evolves_to = evolution.getEvolves_to();

                while (!isNull) {
                    Pokemon pokemonSave = Preferences.getPokemon(getContext(),
                            evolves_to.getEvolutionInfo().getName());

                    if (pokemonSave == null) presenter.getPokemonVector(
                            evolves_to.getEvolutionInfo().getName(), i);
                    else onPokemonSucess(pokemonSave, i);

                    if (evolves_to != null && evolves_to.getEvolves_to().size() > 0)
                        evolves_to = evolves_to.getEvolves_to().get(i);
                    else isNull = true;
                }
            }

            rvEvolution.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rvEvolution.setAdapter(evolutionAdapter);
        }
    }

    @Override
    public void onError(Throwable error) {

    }

    @Override
    public void onComplete() {

    }
}
