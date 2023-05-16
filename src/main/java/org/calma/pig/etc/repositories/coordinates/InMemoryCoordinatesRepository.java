package org.calma.pig.etc.repositories.coordinates;

import org.calma.pig.etc.models.coordinates.Coordinates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryCoordinatesRepository implements ICoordinatesRepository {

    private List<Coordinates> data;

    public InMemoryCoordinatesRepository() {
        this.data = new ArrayList<>();

        loadFromMemory();
    }

    public void loadFromMemory(){
        data.add(new Coordinates(5,5));
        data.add(new Coordinates(5,5));
        data.add(new Coordinates(5,5));
        data.add(new Coordinates(5,5));
        data.add(new Coordinates(5,5));
    }

    @Override
    public List<Coordinates> findAll() {
        return this.data;
    }
    @Override
    public Coordinates findById(int id) {
        List<Coordinates> allCoordinates = this.data;

        for(Iterator<Coordinates> iterator = allCoordinates.iterator(); iterator.hasNext(); ) {
            Coordinates coordinates =  iterator.next();
            if(coordinates.getId() == id){
                return coordinates;
            }
        }

        return null;
    }
    @Override
    public Coordinates findByName(String name) {
        List<Coordinates> allCoordinates = this.data;

        for(Iterator<Coordinates> iterator = allCoordinates.iterator(); iterator.hasNext(); ) {
            Coordinates coordinates =  iterator.next();
            if(coordinates.getName().compareTo(name) == 0){
                return coordinates;
            }
        }

        return null;
    }
}
