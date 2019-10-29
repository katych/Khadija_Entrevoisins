package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsNeighbour extends AppCompatActivity {

private NeighbourApiService mApiService;
private List<Neighbour> mNeighbours;

  private int position  ;

    @BindView(R.id.neighbours_image_profile)
   ImageView imageView ;

    @BindView(R.id.neighbours_name_Profile)
    TextView nameInUserImage;

    @BindView(R.id.neighbours_name)
    TextView name_user;

    @BindView(R.id.user_adresse)
    TextView user_adresse;

    @BindView(R.id.user_phone)
    TextView user_phone;

    @BindView(R.id.user_mail)
    TextView user_facebook;

    @BindView(R.id.user_message)
    TextView user_message;

    @BindView(R.id.button_favorites)
    ImageButton btn_favorites;

    @BindView(R.id.btnBack)
    ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_neighbours);

        mApiService=DI.getNeighbourApiService();
        mNeighbours= mApiService.getNeighbours();

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position1");

        Neighbour mNeighbour = mNeighbours.get(position);

        ButterKnife.bind(this);

        Glide.with(imageView.getContext())
                .load(mNeighbour.getAvatarUrl())
                .into(imageView);

        nameInUserImage.setText(mNeighbour.getName());
        name_user.setText(mNeighbour.getName());
        user_adresse.setText(mNeighbour.getAdresse());
        user_phone.setText(mNeighbour.getUser_phone());
        user_facebook.setText(mNeighbour.getFacebook());
        user_message.setText(mNeighbour.getUser_message());


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsNeighbour.this,ListNeighbourActivity.class);
                startActivity(intent);

            }
        });

       btn_favorites.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v ) {

               btn_favorites.setImageResource(R.drawable.ic_star_white_24dp);

                   mApiService.addFavoritesNeighbour(position);


             FavoritesNeighboursList.mAdapter.notifyDataSetChanged();
             Log.d("DEBUG_APP", "Taille FavListe : " + mApiService.getFavoritesNeighbour().size());

           }

       });




        }

    }





