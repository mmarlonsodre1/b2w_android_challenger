package com.example.b2w_challenger.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.b2w_challenger.MyApplication;
import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.Pokedex;
import com.example.b2w_challenger.models.viewModels.PokedexViewModel;
import com.example.b2w_challenger.ui.adapter.PokedexAdapter;
import com.example.b2w_challenger.ui.contracts.PokedexContract;
import com.example.b2w_challenger.ui.presenter.PokedexPresenter;
import com.example.b2w_challenger.util.Preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.b2w_challenger.services.PokemonService.BASE_URL;

public class HomeFragment extends Fragment
        implements PokedexContract.PokedexRequestListener,
        PokedexContract.PokedexClickListener {

    private RecyclerView rvPokemons;
    private ProgressBar progressBar;
    private PokedexAdapter pokedexAdapter;
    private boolean isLoading;
    private PokedexPresenter presenter;
    private PokedexViewModel pokedexViewModel;

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
        ((MyApplication) getActivity().getApplication()).setServiceComponent(BASE_URL);
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
        progressBar = view.findViewById(R.id.progress_bar);
        rvPokemons = view.findViewById(R.id.rv_pokemons);
        rvPokemons.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        initScrollListener();
        rvPokemons.setAdapter(pokedexAdapter);

        Pokedex pokedex = Preferences.getPokedex(getContext());

        if (pokedexAdapter.getItemCount() == 0) {
            if (pokedex == null) {
                progressBar.setVisibility(View.VISIBLE);
                presenter.getPokedex();
            }
            else onPokedexSucess(pokedex);
        }
    }

    private void setupLiveData() {
        pokedexViewModel = ViewModelProviders.of(this).get(PokedexViewModel.class);
        final Observer<Pokedex> pokedexObserver = new Observer<Pokedex>() {
            @Override
            public void onChanged(Pokedex pokedex) {
                Preferences.savePokedex(getContext(), pokedex);
                pokedexAdapter.setPokemonList(pokedex.getResults());
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
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == pokedexViewModel.getPokedex().getValue().getResults().size() - 1) {
                        isLoading = true;
                        progressBar.setVisibility(View.VISIBLE);
                        presenter.getNextPokedex(Integer.parseInt(
                                getQueryMap(nextPage).get("offset")),
                                Integer.parseInt(getQueryMap(nextPage).get("limit")));
                    }
                }
            }
        });
    }

    @Override
    public void onPokedexSucess(Pokedex pokedex) {
        nextPage = pokedex.getNext();
        pokedexViewModel.getPokedex().setValue(pokedex);
        Preferences.savePokedex(getContext(), pokedex);
    }

    @Override
    public void onNextPokedexSucess(Pokedex pokedex) {
        nextPage = pokedex.getNext();
        pokedexViewModel.getPokedex().getValue().getResults().addAll(pokedex.getResults());
        pokedexViewModel.getPokedex().setValue(pokedexViewModel.getPokedex().getValue());
    }

    @Override
    public void onError(Throwable error) {
        Toast.makeText(getContext(), "Erro ao carregar", Toast.LENGTH_SHORT);
    }

    @Override
    public void onComplete() {
        Toast.makeText(getContext(), "Completou", Toast.LENGTH_SHORT);
        isLoading = false;
        if (progressBar.isShown()) progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPokemonClick(Pokedex.PokemonSimple pokemon, View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("POKEMON", pokemon);

        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_pokemonFragment, bundle);
    }
}
