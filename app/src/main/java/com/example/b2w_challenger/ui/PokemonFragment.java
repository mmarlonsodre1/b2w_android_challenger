package com.example.b2w_challenger.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.b2w_challenger.ui.adapter.AbilityAdapter;
import com.example.b2w_challenger.ui.adapter.EvolutionAdapter;
import com.example.b2w_challenger.ui.adapter.ImagePokemonAdapter;
import com.example.b2w_challenger.ui.contracts.PokemonContract;
import com.example.b2w_challenger.ui.presenter.PokemonPresenter;
import com.example.b2w_challenger.util.Preferences;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.b2w_challenger.services.PokemonService.BASE_API_URL;
import static com.example.b2w_challenger.services.PokemonService.BASE_IMAGE_URL;

public class PokemonFragment extends Fragment
        implements PokemonContract.PokemonRequestListener, PokemonContract.AbilityClickListener {
    private PokemonPresenter presenter;
    private List<AbilityInfo> abilityList;
    private List<Pokemon>[] pokemonsVector;
    private Pokemon pokemon;
    private Context context;

    private EvolutionAdapter evolutionAdapter;
    private AbilityAdapter abilityAdapter;

    private RecyclerView rvEvolution;
    private ViewPager vpImagePokemon;

    private TabLayout tbDots;
    private ProgressBar progressBar;

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

    private TextView tvPokeId;
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
        context = getContext();
        setSharedElementEnterTransition(TransitionInflater.from(context)
                .inflateTransition(android.R.transition.move).setDuration(350));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new PokemonPresenter(this);
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
            Pokemon pokemonSave = Preferences.getPokemon(context, pokemon.getName());
            if (pokemonSave == null) presenter.getPokemon(pokemon.getName());
            else onPokemonSucess(pokemonSave);
        }

        vpImagePokemon.setTransitionName("transition_" + pokemon.getName()); // shared element transition
    }

    private void setupView(View view) {
        rvEvolution = view.findViewById(R.id.rv_evolution);
        RecyclerView rvAbilities = view.findViewById(R.id.rv_abilities);
        vpImagePokemon = view.findViewById(R.id.vp_image_pokemon);
        tbDots = view.findViewById(R.id.tb_dot);
        progressBar = view.findViewById(R.id.progress_bar);

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

        tvPokeId = view.findViewById(R.id.tv_poke_id);
        tvPokeName = view.findViewById(R.id.tv_poke_name);
        tvHP = view.findViewById(R.id.tv_hp);
        tvAttack = view.findViewById(R.id.tv_attack);
        tvDefense = view.findViewById(R.id.tv_defense);
        tvSpecialAttack = view.findViewById(R.id.tv_special_attack);
        tvSpecialDefense = view.findViewById(R.id.tv_special_defense);
        tvSpeed = view.findViewById(R.id.tv_speed);

        abilityAdapter = new AbilityAdapter(abilityList, this);
        rvAbilities.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvAbilities.setAdapter(abilityAdapter);
    }

    private void setupCarousel() {
        String urlImageDefault = BASE_IMAGE_URL + pokemon.getId() + ".png";
        ImagePokemonAdapter imagePokemonAdapter = new ImagePokemonAdapter(context, pokemon.getSprites(), urlImageDefault);
        vpImagePokemon.setAdapter(imagePokemonAdapter);

        tbDots.setupWithViewPager(vpImagePokemon, true);
        tbDots.setVisibility(View.VISIBLE);
    }

    private void forPokemonTypes(List<Types> types) {
        for (int i = 0; i < types.size(); i++) {
            switch (types.get(i).getType().getName()) { //Show type icons
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

    private void forStats(List<Stats> stats) { //Pokémon stats values
        for (int i = 0; i < stats.size(); i++) {
            switch (i) {
                case 0:
                    tvSpeed.setText(stats.get(0).getBaseStat());
                    break;
                case 1:
                    tvSpecialDefense.setText(stats.get(1).getBaseStat());
                    break;
                case 2:
                    tvSpecialAttack.setText(stats.get(2).getBaseStat());
                    break;
                case 3:
                    tvDefense.setText(stats.get(3).getBaseStat());
                    break;
                case 4:
                    tvAttack.setText(stats.get(4).getBaseStat());
                    break;
                case 5:
                    tvHP.setText(stats.get(5).getBaseStat());
                    break;
            }
        }
    }

    @Override
    public void onAbilitySucess(AbilityInfo abilityInfo) {
        if (abilityInfo != null) {
            if (Preferences.getAbility(context, abilityInfo.getId()) == null) {
                Preferences.saveAbility(context, abilityInfo);
            }

            abilityList.add(abilityInfo);
            abilityAdapter.setAbilitiesList(abilityList);
            abilityAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPokemonSucess(Pokemon pokemon) {
        if (pokemon != null) {
            this.pokemon = pokemon;

            setupCarousel();

            tvPokeId.setText("#" + String.valueOf(pokemon.getId()));
            tvPokeName.setText(pokemon.getName());
            forPokemonTypes(pokemon.getTypes());
            forStats(pokemon.getStats());
            evolutionAdapter = new EvolutionAdapter(pokemonsVector, context);

            Specie specie = Preferences.getSpecie(context, pokemon.getName());
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
                Preferences.savePokemon(context, pokemon);
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
            if (Preferences.getSpecie(context, specie.getName()) == null)
                Preferences.saveSpecie(context, specie);

            String[] evolutionUrl = specie.getEvolutionSpecie().getUrl().split("/"); //Parse evolution url

            Evolution evolution = Preferences.getEvolution(context,
                    Integer.parseInt(evolutionUrl[evolutionUrl.length - 1])); //use id on parse url

            if (evolution == null)
                presenter.getPokemonEvolution(Integer.parseInt(
                        evolutionUrl[evolutionUrl.length - 1]));
            else onPokemonEvolutionSucess(evolution);
        }
    }

    @Override
    public void onPokemonEvolutionSucess(Evolution evolution) {
        if (evolution != null) {
            if (Preferences.getEvolution(context, evolution.getId()) == null)
                Preferences.saveEvolution(context, evolution);

            for (int i = 0; i < pokemon.getAbilities().size(); i++) {
                String[] ability = pokemon.getAbilities().get(i)
                        .getAbilitySimple().getUrl().split("/"); //Parse ability url

                AbilityInfo abilityInfo = Preferences.getAbility(context,
                        Integer.parseInt(ability[ability.length - 1])); //use id on parse url to shared preferences

                if (abilityInfo == null)
                    presenter.getAbility(Integer.parseInt(
                            ability[ability.length - 1])); //use id on parse url to call api
                else onAbilitySucess(abilityInfo);
            }

            for (int i = 0; i < evolution.getEvolvesTo().getEvolvesTo().size(); i++) {
                boolean isNull = false;
                EvolvesTo evolvesTo = evolution.getEvolvesTo();

                while (!isNull) { //form recursive to get pokémon evolutions
                    Pokemon pokemonSave = Preferences.getPokemon(context,
                            evolvesTo.getEvolutionInfo().getName());

                    if (pokemonSave == null) presenter.getPokemonVector(
                            evolvesTo.getEvolutionInfo().getName(), i);
                    else onPokemonSucess(pokemonSave, i);

                    if (evolvesTo != null && evolvesTo.getEvolvesTo().size() > 0)
                        evolvesTo = evolvesTo.getEvolvesTo().get(i);
                    else isNull = true;
                }
            }

            rvEvolution.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            rvEvolution.setAdapter(evolutionAdapter);
        }
    }

    @Override
    public void onBefore() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable error) {
        Toast.makeText(context, R.string.intern_error, Toast.LENGTH_SHORT).show();
        if (progressBar.isShown()) progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onComplete() {
        if (progressBar.isShown()) progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onAbilityClick(AbilityInfo abilityInfo) {
        StringBuilder abilityEntrie = new StringBuilder();
        abilityEntrie.append("Effects: \n \n");

        for (int i = 0; i < abilityInfo.getEffectEntries().size(); i++) { //Mount dialog ability text
            abilityEntrie.append(i + 1).append(" - ");
            abilityEntrie.append(abilityInfo.getEffectEntries().get(i).getEffect());
            abilityEntrie.append("\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(abilityEntrie.toString())
                .setTitle(abilityInfo.getName())
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) { }
                })
                .show();
    }
}
