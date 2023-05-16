package org.calma.pig.etc.repositories.coordinates;

import org.calma.pig.etc.models.coordinates.Coordinates;

import java.util.List;

public interface ICoordinatesRepository {

    public List<Coordinates> findAll();

    public Coordinates findById(int id);

    public Coordinates findByName(String name);
}
