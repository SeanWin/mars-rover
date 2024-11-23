package org.MarsRover.UI;

import org.MarsRover.Input.InputParser;
import org.MarsRover.Input.Instruction;
import org.MarsRover.Input.PlateauSize;
import org.MarsRover.Input.Position;
import org.MarsRover.logic.MissionControl;
import org.MarsRover.logic.Plateau;
import org.MarsRover.logic.Rover;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private State state;
    private MissionControl missionControl;
    private Scanner scanner;
    private List<String> stringFinalPositions;

    public UI() {
        state = State.SETUP_PLATEAU;
        scanner = new Scanner(System.in);
        stringFinalPositions = new ArrayList<>();
    }

    public State setupPlateau(){
        while(true){
            try{
                System.out.println("Enter plateau size");
                String input = scanner.nextLine();
                PlateauSize plateauSize = InputParser.parsePlateau(input);
                missionControl = new MissionControl(new Plateau(plateauSize));
                return State.DEPLOY_ROVER;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: invalid size, please reenter a valid size");
            }
        }
    }

    public State deployRover(){
        while(true){
            try{
                System.out.println("Enter rover position");
                String input = scanner.nextLine();
                Position position = InputParser.parsePosition(input);
                Rover rover = new Rover(position);
                missionControl.addRover(rover);
                return State.INPUT_INSTRUCTIONS;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: rover deployment out of bounds detected! Please reenter a position within plateau bounds");
            } catch (IllegalStateException e) {
                System.out.println("Error: rover deployment collision detected! Please reenter a position which isn't already occupied");;
            }
        }
    }

    public State inputInstructions(){
        while (true){
            try{
                System.out.println("Enter instructions");
                String input = scanner.nextLine();
                Instruction[] instructions = InputParser.parseInstructions(input);
                Rover rover = missionControl.getRovers().getLast();
                Position finalPosition = missionControl.executeInstructions(rover, instructions);
                System.out.println(finalPosition.getX()+" "+finalPosition.getY()+" "+finalPosition.getDirection());
                stringFinalPositions.add(finalPosition.getX()+" "+finalPosition.getY()+" "+finalPosition.getDirection());
                System.out.println("Add another rover? (y/n):");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    return State.DEPLOY_ROVER;
                } else {
                    return State.FINISH;
                }
            } catch (IllegalStateException e) {
                System.out.println("Error: rover movement out of bounds detected! please reenter valid instructions");;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: rover collision detected! Please reenter valid instructions");;
            }
        }
    }

    public void printPositions(){
        stringFinalPositions.forEach(System.out::println);
    }

    public void start(){
        while (state!=State.FINISH){
            switch (state){
                case SETUP_PLATEAU -> state = setupPlateau();
                case DEPLOY_ROVER -> state = deployRover();
                case INPUT_INSTRUCTIONS -> state = inputInstructions();
            }
        }
        System.out.println("Rover final positions are: ");
        printPositions();
        System.out.println("Mission completed!");
    }

}