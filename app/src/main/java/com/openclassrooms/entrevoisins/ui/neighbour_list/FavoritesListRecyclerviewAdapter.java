package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavNeighbourEvents;
import com.openclassrooms.entrevoisins.model.Neighbour;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesListRecyclerviewAdapter extends RecyclerView.Adapter<FavoritesListRecyclerviewAdapter.mViewHolder> {
    private List<Neighbour> mFavoritesNeighbours;



    public FavoritesListRecyclerviewAdapter(List<Neighbour> mFavoritesNeighbours) {
        this.mFavoritesNeighbours = mFavoritesNeighbours;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_neighbour, parent, false);
        mViewHolder v = new mViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder mViewHolder, int position) {
        Neighbour fav_neighbour = mFavoritesNeighbours.get(position);

        mViewHolder.textNom.setText(fav_neighbour.getName());
        Glide.with(mViewHolder.avatarImage.getContext())
                .load(fav_neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(mViewHolder.avatarImage);

       mViewHolder. deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().post(new DeleteFavNeighbourEvents(fav_neighbour));

                Log.d("DEBUG", "onClick:delete favoris" + mFavoritesNeighbours.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mFavoritesNeighbours.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_list_avatar)
        ImageView avatarImage;

        @BindView(R.id.item_list_name)
        TextView textNom;

        @BindView(R.id.item_list_delete_button)
        ImageButton deleteButton;


        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }


    }
}