package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d","Adresse Caroline","numero Caroline","facebook Caroline", "message Caroline"),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e","Adresse Jack","numero Jack","facebook Jack","message Jack"),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f","Adresse Chloé","numero Chloé","facebook Chloé"," message Chloé "),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a","Adresse Vincent","numero Vincent","facebook Vincent","message Vincent"),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b","Adresse Elodie","numero Elodie","facebook Elodie","message Elodie"),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c","Adresse Sylvain","numero Sylvain","facebook Sylvain","message Sylvain"),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d","Adresse Laetitia","numero Laetitia","facebook Laetitia","message Laetitia"),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b","Adresse Dan","numero Dan","facebook Dan","message Dan "),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d","Adresse Joseph","numero Joseph","facebook Joseph","message Joseph"),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d","Adresse Emma","numero Emma","facebook Emma","message Emma"),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d","Adresse Patrick","numero Patrick","facebook Patrick","message Patrick"),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d","Adresse Ludovic","numero Ludovic","facebook Ludovic","message Ludovic")
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }

}
