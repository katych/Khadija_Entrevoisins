package com.openclassrooms.entrevoisins.events;

import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavNeighbourEvents {

    public Neighbour neighbour;



    public DeleteFavNeighbourEvents(Neighbour neighbour) {
        Log.d("DEBUG", "DeleteFavNeighbourEvents: Delete neighbour from favorite list ");
        this.neighbour = neighbour;
    }

}
