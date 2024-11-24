package org.MarsRover.logic;

import org.MarsRover.Input.Instruction;
import org.MarsRover.Input.Position;

import java.util.ArrayList;
import java.util.List;

public class MissionControl {
    private Plateau plateau;
    private List<Rover> rovers;

    public MissionControl(Plateau plateau) {
        this.plateau = plateau;
        this.rovers = new ArrayList<>();
    }

    public List<Rover> getRovers() {
        return rovers;
    }

    public void addRover(Rover rover){
        if(!plateau.isWithinBounds(rover.getPosition().getX(),rover.getPosition().getY()))
            throw new IllegalStateException("Error: rover deployment out of bounds detected! Please reenter a position within plateau bounds");

        if (getRoverPositions().stream().anyMatch(pos -> pos.getX() == rover.getPosition().getX() && pos.getY() == rover.getPosition().getY())) {
            throw new IllegalStateException("Error: rover deployment collision detected! Please reenter a position which isn't already occupied");
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
        // Validate the movement with simulation first
        simulateInstructions(rover, instructions);

        // If simulation is successful, execute the real instructions
        for (Instruction instruction : instructions) {
            if (instruction.equals(Instruction.M)) {
                rover.move();
            } else {
                rover.turn(instruction);
            }
        }
        return rover.getPosition();
    }

    public void simulateInstructions(Rover rover, Instruction[] instructions) {
        Position simulatedPosition = new Position(
                rover.getPosition().getX(),
                rover.getPosition().getY(),
                rover.getPosition().getDirection()
        );
        Rover simulatedRover = new Rover(simulatedPosition);
        List<Position> otherRoverPositions = getOtherRoverPositions(rover);

        for(Instruction instruction:instructions){
            if(instruction.equals(Instruction.M)){
                Position nextPosition = simulatedRover.nextPosition();
                if(!plateau.isWithinBounds(nextPosition.getX(), nextPosition.getY())){
                    throw new IllegalStateException("Error: rover movement out of bounds detected! please reenter valid instructions");
                }
                if (otherRoverPositions.stream().anyMatch(pos -> pos.getX() == nextPosition.getX() && pos.getY() == nextPosition.getY())) {
                    throw new IllegalStateException("Error: rover movement collision detected! Please reenter valid instructions");
                }
                simulatedRover.move();
            }
            else{
                simulatedRover.turn(instruction);
            }
        }
    }

    public List<Position> getOtherRoverPositions(Rover currentRover) {
        List<Position> otherRoverPositions = new ArrayList<>();

        for (Rover rover : rovers) {
            // Skip the rover whose movement is being simulated
            if (!rover.equals(currentRover)) {
                otherRoverPositions.add(rover.getPosition());
            }
        }

        return otherRoverPositions;
    }

}
