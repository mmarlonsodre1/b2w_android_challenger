package com.example.b2w_challenger.services;

import com.example.b2w_challenger.models.Ability.AbilityInfo;
import com.example.b2w_challenger.models.Evolution.Evolution;
import com.example.b2w_challenger.models.Pokedex.Pokedex;
import com.example.b2w_challenger.models.Pokemon.Pokemon;
import com.example.b2w_challenger.models.PokemonType.Type;
import com.example.b2w_challenger.models.Specie.Specie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonService {
    public static String BASE_API_URL = "https://pokeapi.co/api/v2/";
    public static String BASE_IMAGE_URL = "https://pokeres.bastionbot.org/images/pokemon/";

    @GET("pokemon")
    Observable<Pokedex> getPokedex();

    @GET("pokemon")
    Observable<Pokedex> getNextPagePokedex(@Query("offset") int ofsset, @Query("limit") int limit);

    @GET("pokemon/{namePokemon}")
    Observable<Pokemon> getPokemonInfo(@Path("namePokemon") String namePokemon);

    @GET("pokemon-species/{namePokemon}")
    Observable<Specie> getPokemonSpecie(@Path("namePokemon") String namePokemon);

    @GET("evolution-chain/{idEvolution}")
    Observable<Evolution> getPokemonEvolution(@Path("idEvolution") int idEvolution);

    @GET("ability/{idAbility}")
    Observable<AbilityInfo> getAbilityInfo(@Path("idAbility") int idAbility);

    @GET("type/{type}")
    Observable<Type> getPokemonType(@Path("type") String type);
}