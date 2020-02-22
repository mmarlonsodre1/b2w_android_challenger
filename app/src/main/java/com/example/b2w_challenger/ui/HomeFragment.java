package com.example.b2w_challenger.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b2w_challenger.MyApplication;
import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.Pokedex;
import com.example.b2w_challenger.models.Pokemon;
import com.example.b2w_challenger.models.viewModels.PokedexViewModel;
import com.example.b2w_challenger.ui.adapter.PokedexAdapter;
import com.example.b2w_challenger.ui.contracts.PokedexContract;
import com.example.b2w_challenger.ui.presenter.PokedexPresenter;
import com.example.b2w_challenger.util.Preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.b2w_challenger.services.PokemonService.BASE_API_URL;

public class HomeFragment extends Fragment
        implements PokedexContract.PokedexRequestListener,
        PokedexContract.PokedexClickListener, SearchView.OnQueryTextListener {

    private RecyclerView rvPokemons;
    private ProgressBar progressBar;
    private PokedexAdapter pokedexAdapter;
    private boolean isLoading;
    private boolean isSearch;
    private PokedexPresenter presenter;
    private PokedexViewModel pokedexViewModel;
    private SearchView svPokemon;

    private String nextPage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pokedexAdapter = new PokedexAdapter(new ArrayList<Pokedex.PokemonSimple>(), HomeFragment.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new PokedexPresenter(this);
        ((MyApplication) getActivity().getApplication()).setServiceComponent(BASE_API_URL);
        ((MyApplication) getActivity().getApplication()).getServiceComponent().inject(presenter);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupLiveData();
        setupView(view);
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupView(View view) {
        svPokemon = view.findViewById(R.id.sv_pokemon);
        progressBar = view.findViewById(R.id.progress_bar);
        rvPokemons = view.findViewById(R.id.rv_pokemons);

        rvPokemons.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        initScrollListener();
        rvPokemons.setAdapter(pokedexAdapter);

        svPokemon.setOnQueryTextListener(this);

        if (pokedexAdapter.getItemCount() == 0) setupPokemonList();
    }

    private void setupPokemonList() {
        Pokedex pokedex = Preferences.getPokedex(getContext());
        if (pokedex == null) {
            presenter.getPokedex();
        }
        else onPokedexSucess(pokedex);
    }

    private void setupLiveData() {
        pokedexViewModel = ViewModelProviders.of(this).get(PokedexViewModel.class);
        final Observer<List<Pokedex.PokemonSimple>> pokedexObserver = new Observer<List<Pokedex.PokemonSimple>>() {
            @Override
            public void onChanged(List<Pokedex.PokemonSimple> pokemonSimpleList) {
                pokedexAdapter.setPokemonList(pokemonSimpleList);
                pokedexAdapter.notifyDataSetChanged();
            }
        };
        pokedexViewModel.getPokedex().observe(getActivity(), pokedexObserver);
    }

    private Map<String, String> getQueryMap(String url) {
        String[] params = url.split("[?]");
        params = params[1].split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params)
        {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    private void initScrollListener() {
        rvPokemons.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (!isSearch) {
                        if (linearLayoutManager != null
                                && linearLayoutManager.findLastCompletelyVisibleItemPosition()
                                == pokedexViewModel.getPokedex().getValue().size() - 1) {
                            isLoading = true;
                            presenter.getNextPokedex(Integer.parseInt(
                                    getQueryMap(nextPage).get("offset")),
                                    Integer.parseInt(getQueryMap(nextPage).get("limit")));
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onBefore() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPokedexSucess(Pokedex pokedex) {
        nextPage = pokedex.getNext();
        pokedexViewModel.getPokedex().setValue(pokedex.getResults());
        Preferences.savePokedex(getContext(), pokedex);
    }

    @Override
    public void onNextPokedexSucess(Pokedex pokedex) {
        nextPage = pokedex.getNext();
        pokedexViewModel.getPokedex().getValue().addAll(pokedex.getResults());
        pokedexViewModel.getPokedex().setValue(pokedexViewModel.getPokedex().getValue());
        Preferences.savePokedex(getContext(), pokedex);
    }

    @Override
    public void onPokemonSucess(Pokemon pokemon) {
        List<Pokedex.PokemonSimple> pokemonList = new ArrayList<>();
        Pokedex.PokemonSimple pokemonSimple = new Pokedex.PokemonSimple(
                pokemon.getName(), pokemon.getId());
        pokemonList.add(pokemonSimple);
        pokedexViewModel.getPokedex().setValue(pokemonList);
    }

    @Override
    public void onError(Throwable error) {
        Toast.makeText(getContext(), "Erro ao carregar", Toast.LENGTH_SHORT).show();
        isLoading = false;
        if (progressBar.isShown()) progressBar.setVisibility(View.GONE);
    }

    @Override
    public void OnPokemonError(Throwable error) {
        Toast.makeText(getContext(), getString(R.string.no_pokemon), Toast.LENGTH_SHORT).show();
        isLoading = false;
        if (progressBar.isShown()) progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onComplete() {
        isLoading = false;
        if (progressBar.isShown()) progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPokemonClick(Pokedex.PokemonSimple pokemon, View view,
                               FragmentNavigator.Extras extras) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("POKEMON", pokemon);
        Navigation.findNavController(view).navigate(
                R.id.action_homeFragment_to_pokemonFragment, bundle, null, extras);
        setupPokemonList();
        svPokemon.setQuery("", false);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        isSearch = true;
        presenter.getPokemon(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        isSearch = false;
        if (newText.equals("")) {
            setupPokemonList();
        }
        return false;
    }
}
