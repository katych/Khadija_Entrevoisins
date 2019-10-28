package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {



    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    private List<Neighbour> favoritesNeighbours = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavoritesNeighbour() {

        for (int  i=0 ; i< neighbours.size(); i++ ){

        Neighbour neighbour = getNeighbours().get(i);

            if ( neighbour.isFavorite()==true  && !favoritesNeighbours.contains(neighbour))
            {
            favoritesNeighbours.add(neighbour);
        }

        }
        return favoritesNeighbours;
    }

    @Override
    public void addFavoritesNeighbour(int position) {

            neighbours.get(position).setFavorite(true);

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void deleteFavorites(Neighbour neighbour) {
        if(favoritesNeighbours.contains(neighbour)){
            neighbour.setFavorite(false);
            favoritesNeighbours.remove(neighbour);}
    }

}
