package com.example.b2w_challenger.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.b2w_challenger.MyApplication;
import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.AbilityInfo;
import com.example.b2w_challenger.models.Pokedex;
import com.example.b2w_challenger.models.Pokemon;
import com.example.b2w_challenger.models.Types;
import com.example.b2w_challenger.ui.contracts.AbilityContract;
import com.example.b2w_challenger.ui.presenter.AbilityPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

import static com.example.b2w_challenger.services.PokemonService.BASE_URL;

public class PokemonFragment extends Fragment
        implements AbilityContract.AbilitiesRequestListener {
    private AbilityPresenter presenter;
    private List<AbilityInfo> abilityList;

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
    }

    private void setupView(View view) {
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

    @Override
    public void onAbilitySucess(AbilityInfo abilityInfo) {
        if (abilityInfo != null) {
            abilityList.add(abilityInfo);
        }
    }

    @Override
    public void onPokemonSucess(Pokemon pokemon) {
        if (pokemon != null) {
            Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/" + pokemon.getId() + ".png")
                    .placeholder(R.drawable.ball)
                    .fit()
                    .into(imgPoke);

            forPokemonTypes(pokemon.getTypes());

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
