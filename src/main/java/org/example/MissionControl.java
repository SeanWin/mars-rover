package org.example;

import java.util.ArrayList;
import java.util.List;

public class MissionControl {
    Plateau plateau;
    List<Rover> rovers;

    public MissionControl(Plateau plateau) {
        this.plateau = plateau;
        this.rovers = new ArrayList<>();
    }

    public void addRover(Rover rover){
        if(!plateau.isWithinBounds(rover.getPosition().getX(),rover.getPosition().getY()))
            throw new IllegalArgumentException("Rover position out of bounds");
        rovers.add(rover);
    }
}
