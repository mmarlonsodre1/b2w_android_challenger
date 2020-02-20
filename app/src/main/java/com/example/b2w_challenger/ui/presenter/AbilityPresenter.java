package com.example.b2w_challenger.ui.presenter;

import com.example.b2w_challenger.models.AbilityInfo;
import com.example.b2w_challenger.models.Evolution;
import com.example.b2w_challenger.models.Pokemon;
import com.example.b2w_challenger.models.Specie;
import com.example.b2w_challenger.services.PokemonService;
import com.example.b2w_challenger.ui.contracts.AbilityContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class AbilityPresenter implements AbilityContract.AbilityPresenterInterface {
    private AbilityContract.AbilitiesRequestListener abilitiesInterface;
    private PokemonService abilitiesService;

    @Inject
    Retrofit retrofit;

    public AbilityPresenter(AbilityContract.AbilitiesRequestListener abilitiesInterface) {
        this.abilitiesInterface = abilitiesInterface;
    }

    @Override
    public void getAbility(int abilityId) {
        abilitiesService = retrofit.create(PokemonService.class);
        abilitiesService.getAbilityInfo(abilityId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AbilityInfo>() {
                    @Override
                    public void onNext(AbilityInfo abilityInfo) {
                        abilitiesInterface.onAbilitySucess(abilityInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        abilitiesInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        abilitiesInterface.onComplete();
                    }
                });
    }

    @Override
    public void getPokemon(String pokemonName) {
        abilitiesService = retrofit.create(PokemonService.class);
        abilitiesService.getPokemonInfo(pokemonName)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Pokemon>() {
                    @Override
                    public void onNext(Pokemon pokemon) {
                        abilitiesInterface.onPokemonSucess(pokemon);
                    }

                    @Override
                    public void onError(Throwable e) {
                        abilitiesInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        abilitiesInterface.onComplete();
                    }
                });
    }

    @Override
    public void getPokemonSpecie(String pokemonName) {
        abilitiesService = retrofit.create(PokemonService.class);
        abilitiesService.getPokemonSpecie(pokemonName)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Specie>() {
                    @Override
                    public void onNext(Specie specie) {
                        abilitiesInterface.onPokemonSpecieSucess(specie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        abilitiesInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        abilitiesInterface.onComplete();
                    }
                });
    }

    @Override
    public void getPokemonEvolution(int idEvolution) {
        abilitiesService = retrofit.create(PokemonService.class);
        abilitiesService.getPokemonEvolution(idEvolution)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Evolution>() {
                    @Override
                    public void onNext(Evolution evolution) {
                        abilitiesInterface.onPokemonEvolutionSucess(evolution);
                    }

                    @Override
                    public void onError(Throwable e) {
                        abilitiesInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        abilitiesInterface.onComplete();
                    }
                });
    }
}
