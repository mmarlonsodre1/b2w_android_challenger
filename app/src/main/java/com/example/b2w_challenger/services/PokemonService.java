package com.example.b2w_challenger.services;

import com.example.b2w_challenger.models.AbilityInfo;
import com.example.b2w_challenger.models.Pokedex;
import com.example.b2w_challenger.models.Pokemon;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonService {
    String BASE_URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Observable<Pokedex> getPokedex();

    @GET("pokemon")
    Observable<Pokedex> getNextPagePokedex(@Query("offset") int ofsset, @Query("limit") int limit);

    @GET("pokemon/{namePokemon}")
    Observable<Pokemon> getPokemonInfo(@Path("namePokemon") String namePokemon);

    @GET("ability/{idAbility}")
    Observable<AbilityInfo> getAbilityInfo(@Path("idAbility") int idAbility);
}