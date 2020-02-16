package com.example.b2w_challenger.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b2w_challenger.MyApplication;
import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.Pokedex;
import com.example.b2w_challenger.models.viewModels.PokedexViewModel;
import com.example.b2w_challenger.ui.adapter.PokedexAdapter;
import com.example.b2w_challenger.ui.interfaces.PokedexContract;
import com.example.b2w_challenger.ui.presenter.PokedexPresenter;

import java.util.HashMap;
import java.util.Map;

import static com.example.b2w_challenger.services.PokemonService.BASE_URL;

public class HomeActivity extends AppCompatActivity implements PokedexContract.PokedexRequest {
    private RecyclerView rvPokemons;
    private ProgressBar progressBar;
    private PokedexAdapter pokedexAdapter;
    private boolean isLoading;
    private PokedexPresenter presenter;
    private PokedexViewModel pokedexViewModel;

    private String nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        presenter = new PokedexPresenter(this);
        ((MyApplication) getApplication()).setServiceComponent(BASE_URL);
        ((MyApplication) getApplication()).getServiceComponent().inject(presenter);

        setupView();
        setupLiveData();
    }

    private void setupView() {
        progressBar = findViewById(R.id.progress_bar);
        rvPokemons = findViewById(R.id.rv_pokemons);
        rvPokemons.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        initScrollListener();

        progressBar.setVisibility(View.VISIBLE);
        presenter.getPokedex();
    }

    private void setupLiveData() {
        pokedexViewModel = ViewModelProviders.of(this).get(PokedexViewModel.class);
        final Observer<Pokedex> pokedexObserver = new Observer<Pokedex>() {
            @Override
            public void onChanged(Pokedex pokedex) {
                if (pokedexAdapter == null) {
                    pokedexAdapter = new PokedexAdapter(pokedexViewModel.getPokedex().getValue().getResults());
                    rvPokemons.setAdapter(pokedexAdapter);
                }
                pokedexAdapter.notifyDataSetChanged();
            }
        };
        pokedexViewModel.getPokedex().observe(this, pokedexObserver);
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
    }

    @Override
    public void onNextPokedexSucess(Pokedex pokedex) {
        nextPage = pokedex.getNext();
        pokedexViewModel.getPokedex().getValue().getResults().addAll(pokedex.getResults());
        pokedexViewModel.getPokedex().setValue(pokedexViewModel.getPokedex().getValue());
    }

    @Override
    public void onError(Throwable error) {
        Toast.makeText(this, "Erro ao carregar", Toast.LENGTH_SHORT);
    }

    @Override
    public void onComplete() {
        Toast.makeText(this, "Completou", Toast.LENGTH_SHORT);
        isLoading = false;
        progressBar.setVisibility(View.GONE);
    }
}
