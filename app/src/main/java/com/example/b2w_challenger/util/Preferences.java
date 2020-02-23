package com.example.b2w_challenger.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.b2w_challenger.models.Ability.AbilityInfo;
import com.example.b2w_challenger.models.Pokemon.Ability;
import com.example.b2w_challenger.models.Evolution.Evolution;
import com.example.b2w_challenger.models.Pokedex.Pokedex;
import com.example.b2w_challenger.models.Pokemon.Pokemon;
import com.example.b2w_challenger.models.PokemonType.Type;
import com.example.b2w_challenger.models.Specie.Specie;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Preferences {
    private static final String POKEDEX = "home_pokemon_list";
    private static final String POKEMON_LIST = "pokemon_list";
    private static final String SPECIE_LIST = "specie_list";
    private static final String EVOLUTION_LIST = "evolution_list";
    private static final String ABILITY_LIST = "ability_list";
    private static final String POKEMON_TYPE_LIST = "pokemon_type_list";
    private static final String PREFS_COOKIES = "cookies_preferences";

    private static SharedPreferences preferencesCookies;

    /**
     * Preferences to save cookies inside application
     * @param context : context
     * @return the SharedPreferences intance
     */
    private static SharedPreferences getInstancePreferenceCookies(Context context){
        if(preferencesCookies == null){
            preferencesCookies = context.getSharedPreferences(PREFS_COOKIES, Context.MODE_PRIVATE);
        }
        return preferencesCookies;
    }

    //TODO: Pokedex
    public static void savePokedex(Context context, Pokedex pokedex){
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        SharedPreferences.Editor editor = preferencesCookies.edit();
        Gson gson = new Gson();
        String json = gson.toJson(pokedex);
        editor.putString(POKEDEX, json);
        editor.apply();
    }

    public static Pokedex getPokedex(Context context) {
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        Pokedex pokedex;
        if (preferencesCookies.contains(POKEDEX)) {
            String json = preferencesCookies.getString(POKEDEX, null);
            Gson gson = new Gson();
            pokedex = gson.fromJson(json, Pokedex.class);
        } else
            return null;
        return pokedex;
    }

    private static boolean existPokemon(List<Pokemon> pokemonList, Pokemon pokemon) {
        if (pokemonList != null) {
            for (Pokemon pokemon1 : pokemonList) {
                if (pokemon1.getName().equals(pokemon.getName())) return true;
            }
        }
        return false;
    }
    //Todo: Finish Pokedex

    //Todo: Pokemon
    public static void savePokemon(Context context, Pokemon pokemon){
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Pokemon> pokemonList = getPokemonsList(context);
        if (!existPokemon(pokemonList, pokemon)) {
            pokemonList.add(pokemon);
            SharedPreferences.Editor editor = preferencesCookies.edit();
            Gson gson = new Gson();
            String json = gson.toJson(pokemonList);
            editor.putString(POKEMON_LIST, json);
            editor.apply();
        }
    }

    public static ArrayList<Pokemon> getPokemonsList(Context context) {
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Pokemon> pokemonList;
        if (preferencesCookies.contains(POKEMON_LIST)) {
            String json = preferencesCookies.getString(POKEMON_LIST, null);
            Gson gson = new Gson();
            Pokemon[] pokemonsVector = gson.fromJson(json,
                    Pokemon[].class);
            pokemonList = Arrays.asList(pokemonsVector);
            pokemonList = new ArrayList<>(pokemonList);
            return (ArrayList<Pokemon>) pokemonList;
        }
         return new ArrayList<>();
    }

    public static Pokemon getPokemon(Context context, String namePokemon){
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Pokemon> pokemonList = getPokemonsList(context);
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getName().equals(namePokemon)) return pokemon;
        }
        return null;
    }
    //Todo: Finish Pokemon

    //Todo: Specie
    private static boolean existSpecie(List<Specie> specieList, Specie specie) {
        if (specieList != null) {
            for (Specie specie1 : specieList) {
                if (specie1.getName().equals(specie.getName())) return true;
            }
        }
        return false;
    }

    public static void saveSpecie(Context context, Specie specie){
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Specie> specieList = getSpecieList(context);
        if (!existSpecie(specieList, specie)) {
            specieList.add(specie);
            SharedPreferences.Editor editor = preferencesCookies.edit();
            Gson gson = new Gson();
            String json = gson.toJson(specieList);
            editor.putString(SPECIE_LIST, json);
            editor.apply();
        }
    }

    public static ArrayList<Specie> getSpecieList(Context context) {
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Specie> specieList;
        if (preferencesCookies.contains(SPECIE_LIST)) {
            String json = preferencesCookies.getString(SPECIE_LIST, null);
            Gson gson = new Gson();
            Specie[] specieVector = gson.fromJson(json,
                    Specie[].class);
            specieList = Arrays.asList(specieVector);
            specieList = new ArrayList<>(specieList);
            return (ArrayList<Specie>) specieList;
        }
        return new ArrayList<>();
    }

    public static Specie getSpecie(Context context, String namePokemon){
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Specie> specieList = getSpecieList(context);
        for (Specie specie : specieList) {
            if (specie.getName().equals(namePokemon)) return specie;
        }
        return null;
    }
    //Todo: Finish Specie

    //Todo: Evolution
    private static boolean existEvolution(List<Evolution> evolutionList, Evolution evolution) {
        if (evolutionList != null) {
            for (Evolution evolution1 : evolutionList) {
                if (evolution1.getId()== evolution.getId()) return true;
            }
        }
        return false;
    }

    public static void saveEvolution(Context context, Evolution evolution){
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Evolution> evolutionList = getEvolutionList(context);
        if (!existEvolution(evolutionList, evolution)) {
            evolutionList.add(evolution);
            SharedPreferences.Editor editor = preferencesCookies.edit();
            Gson gson = new Gson();
            String json = gson.toJson(evolutionList);
            editor.putString(EVOLUTION_LIST, json);
            editor.apply();
        }
    }

    public static ArrayList<Evolution> getEvolutionList(Context context) {
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Evolution> evolutionList;
        if (preferencesCookies.contains(EVOLUTION_LIST)) {
            String json = preferencesCookies.getString(EVOLUTION_LIST, null);
            Gson gson = new Gson();
            Evolution[] specieVector = gson.fromJson(json,
                    Evolution[].class);
            evolutionList = Arrays.asList(specieVector);
            evolutionList = new ArrayList<>(evolutionList);
            return (ArrayList<Evolution>) evolutionList;
        }
        return new ArrayList<>();
    }

    public static Evolution getEvolution(Context context, int idEvolution){
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Evolution> evolutionList = getEvolutionList(context);
        for (Evolution evolution : evolutionList) {
            if (evolution.getId() == idEvolution) return evolution;
        }
        return null;
    }
    //Todo: Finish Evolution

    //Todo: Ability
    private static boolean existAbility(List<AbilityInfo> abilityList, AbilityInfo ability) {
        if (abilityList != null) {
            for (AbilityInfo ability1 : abilityList) {
                if (ability1.getId() ==  ability.getId()) return true;
            }
        }
        return false;
    }

    public static void saveAbility(Context context, AbilityInfo ability){
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<AbilityInfo> abilityList = getAbilityList(context);
        if (!existAbility(abilityList, ability)) {
            abilityList.add(ability);
            SharedPreferences.Editor editor = preferencesCookies.edit();
            Gson gson = new Gson();
            String json = gson.toJson(abilityList);
            editor.putString(ABILITY_LIST, json);
            editor.apply();
        }
    }

    public static ArrayList<AbilityInfo> getAbilityList(Context context) {
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<AbilityInfo> abilityList;
        if (preferencesCookies.contains(ABILITY_LIST)) {
            String json = preferencesCookies.getString(ABILITY_LIST, null);
            Gson gson = new Gson();
            AbilityInfo[] abilityVector = gson.fromJson(json,
                    AbilityInfo[].class);
            abilityList = Arrays.asList(abilityVector);
            abilityList = new ArrayList<>(abilityList);
            return (ArrayList<AbilityInfo>) abilityList;
        }
        return new ArrayList<>();
    }

    public static AbilityInfo getAbility(Context context, int idAbility){
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<AbilityInfo> abilityList = getAbilityList(context);
        for (AbilityInfo ability : abilityList) {
            if (ability.getId() == idAbility )
                return ability;
        }
        return null;
    }
    //Todo: Finish Ability

    //Todo: Pokémon Type
    private static boolean existTypePokemon(List<Type> typePokemonList, Type typePokemon) {
        if (typePokemonList != null) {
            for (Type typePokemon1 : typePokemonList) {
                if (typePokemon1.getId() == typePokemon.getId()) return true;
            }
        }
        return false;
    }

    public static void saveTypePokemon(Context context, Type typePokemon){
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Type> typePokemonList = getTypePokemonList(context);
        if (!existTypePokemon(typePokemonList, typePokemon)) {
            typePokemonList.add(typePokemon);
            SharedPreferences.Editor editor = preferencesCookies.edit();
            Gson gson = new Gson();
            String json = gson.toJson(typePokemonList);
            editor.putString(POKEMON_TYPE_LIST, json);
            editor.apply();
        }
    }

    public static ArrayList<Type> getTypePokemonList(Context context) {
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Type> typePokemonList;
        if (preferencesCookies.contains(POKEMON_TYPE_LIST)) {
            String json = preferencesCookies.getString(POKEMON_TYPE_LIST, null);
            Gson gson = new Gson();
            Type[] typePokemonVector = gson.fromJson(json,
                    Type[].class);
            typePokemonList = Arrays.asList(typePokemonVector);
            typePokemonList = new ArrayList<>(typePokemonList);
            return (ArrayList<Type>) typePokemonList;
        }
        return new ArrayList<>();
    }

    public static Type getTypePokemon(Context context, Integer type){
        if(preferencesCookies == null){
            preferencesCookies = getInstancePreferenceCookies(context);
        }
        List<Type> typePokemonList = getTypePokemonList(context);
        for (Type typePokemon : typePokemonList) {
            if (typePokemon.getId() == type)
                return typePokemon;
        }
        return null;
    }
    //Todo: Finish Pokémon Type
}
