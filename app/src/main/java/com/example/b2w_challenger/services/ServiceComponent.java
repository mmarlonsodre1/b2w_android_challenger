package com.example.b2w_challenger.services;

import com.example.b2w_challenger.ui.presenter.PokemonPresenter;
import com.example.b2w_challenger.ui.presenter.PokedexPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ServiceModule.class})
public interface ServiceComponent {
    void inject(PokedexPresenter presenter);
    void inject(PokemonPresenter presenter);
}