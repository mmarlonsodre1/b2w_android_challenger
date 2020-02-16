package com.example.b2w_challenger.ui.presenter;

import com.example.b2w_challenger.models.Pokedex;
import com.example.b2w_challenger.services.PokemonService;
import com.example.b2w_challenger.ui.interfaces.PokedexContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PokedexPresenter implements PokedexContract.PokedexPresenterInterface {
    private PokedexContract.PokedexRequest pokedexInterface;
    private PokemonService pokemonService;

    @Inject
    Retrofit retrofit;

    public PokedexPresenter(PokedexContract.PokedexRequest pokedexInterface) {
        this.pokedexInterface = pokedexInterface;
    }

    @Override
    public void getPokedex() {
        pokemonService = retrofit.create(PokemonService.class);
        pokemonService.getPokedex()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Pokedex>() {
                    @Override
                    public void onNext(Pokedex pokedex) {
                        pokedexInterface.onPokedexSucess(pokedex);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pokedexInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        pokedexInterface.onComplete();
                    }
                });
    }

    @Override
    public void getNextPokedex(int offset, int limit) {
        pokemonService.getNextPagePokedex(offset, limit)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Pokedex>() {
                    @Override
                    public void onNext(Pokedex pokedex) {
                        pokedexInterface.onNextPokedexSucess(pokedex);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pokedexInterface.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        pokedexInterface.onComplete();
                    }
                });
    }
}