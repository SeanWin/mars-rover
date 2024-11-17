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

        if (getRoverPositions().stream().anyMatch(pos -> pos.getX() == rover.getPosition().getX() && pos.getY() == rover.getPosition().getY())) {
            throw new IllegalStateException("Cannot deploy here, position already occupied");
        }
        rovers.add(rover);
    }

    public List<Position> getRoverPositions(){
        ArrayList<Position> positions = new ArrayList<>();
        for (Rover rover:rovers) {
            positions.add(rover.getPosition());
        }
        return positions;
    }

    public Position executeInstructions(Rover rover, Instruction[] instructions){
        for(Instruction instruction:instructions){
            if(instruction.equals(Instruction.M)){
                Position nextPosition = rover.nextPosition();
                if(!plateau.isWithinBounds(nextPosition.getX(), nextPosition.getY())){
                    throw new IllegalStateException("Rover out of bounds");
                }
                if (getRoverPositions().stream().anyMatch(pos -> pos.getX() == nextPosition.getX() && pos.getY() == nextPosition.getY())) {
                    throw new IllegalArgumentException("Cannot move here, position already occupied");
                }
                rover.move();
            }
            else{
                rover.turn(instruction);
            }
        }
        return rover.getPosition();
    }
}
