package com.example.b2w_challenger.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.b2w_challenger.R;
import com.example.b2w_challenger.models.Pokemon.Sprites;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImagePokemonAdapter extends PagerAdapter {
    Context context;
    private Sprites sprites;
    private List<String> urlImageList;
    private String defaultImage;

    public ImagePokemonAdapter(Context context, Sprites sprites, String defaultImage) {
        this.context = context;
        this.sprites = sprites;
        this.defaultImage = defaultImage;

        //Addictions urls
        urlImageList = new ArrayList<>();
        urlImageList.add(defaultImage);
        urlImageList.add(sprites.getFront_default());
        urlImageList.add(sprites.getBack_default());
        urlImageList.add(sprites.getFront_female());
        urlImageList.add(sprites.getBack_female());
        urlImageList.add(sprites.getFront_shiny());
        urlImageList.add(sprites.getBack_shiny());
        urlImageList.add(sprites.getFront_shiny_female());
        urlImageList.add(sprites.getBack_shiny_female());

        //Remove nullable in list
        urlImageList.removeAll(Collections.singleton(null));
    }

    @Override
    public int getCount() {
        if (urlImageList != null) return urlImageList.size();
        else return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.adapter_pokemon_images, container, false);

        ImageView imgPokemon = view.findViewById(R.id.img_poke);
        Picasso.get()
                .load(urlImageList.get(position))
                .placeholder(R.drawable.ic_ball)
                .into(imgPokemon);

        container.addView(view);
        return view;
    }


}
