package com.example.b2w_challenger.ui.presenter;

import com.example.b2w_challenger.models.Pokedex.Pokedex;
import com.example.b2w_challenger.models.Pokemon.Pokemon;
import com.example.b2w_challenger.models.PokemonType.Type;
import com.example.b2w_challenger.services.PokemonService;
import com.example.b2w_challenger.ui.contracts.PokedexContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PokedexPresenter implements PokedexContract.PokedexPresenterInterface {
    private PokedexContract.PokedexRequestListener pokedexInterface;
    private PokemonService pokemonService;

    @Inject
    Retrofit retrofit;

    public PokedexPresenter(PokedexContract.PokedexRequestListener pokedexInterface) {
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
                    protected void onStart() {
                        super.onStart();
                        pokedexInterface.onBefore();
                    }

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
        pokemonService = retrofit.create(PokemonService.class);
        pokemonService.getNextPagePokedex(offset, limit)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Pokedex>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        pokedexInterface.onBefore();
                    }

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

    @Override
    public void getPokemon(String pokemonName) {
        pokemonService = retrofit.create(PokemonService.class);
        pokemonService.getPokemonInfo(pokemonName)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Pokemon>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        pokedexInterface.onBefore();
                    }

                    @Override
                    public void onNext(Pokemon pokemon) {
                        pokedexInterface.onPokemonSucess(pokemon);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pokedexInterface.OnPokemonError(e);
                    }

                    @Override
                    public void onComplete() {
                        pokedexInterface.onComplete();
                    }
                });
    }

    @Override
    public void getPokemonType(String type) {
        pokemonService = retrofit.create(PokemonService.class);
        pokemonService.getPokemonType(type)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Type>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        pokedexInterface.onBefore();
                    }

                    @Override
                    public void onNext(Type pokemonType) {
                        pokedexInterface.onPokemonTypeSucess(pokemonType);
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