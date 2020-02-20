package com.example.androidtask.data.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.androidtask.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class ItemHome implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("photos")
    @Expose
    private List<String> photos;

    public String getName() {
        if (name != null && name.length() > 20) {
            name = name.substring(0, 20).concat("...");
        }
        return name;

    }

    public String getDescription() {
        if (description != null && description.length() > 30) {
            description = description.substring(0, 20).concat("...");
        }
        return description;

    }

    public List<String> getPhotos() {
        return photos;
    }

    @BindingAdapter("load_image")
    public static void loadImage(ImageView view, List<String> imageUrls) {
        if (!imageUrls.isEmpty()) {
            Picasso.get()
                    .load(imageUrls.get(0))
                    .placeholder(R.drawable.image_holder)
                    .into(view);

        }

    }
}
