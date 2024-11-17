package org.example;

import java.util.Scanner;

public class UI {
    private State state;
    private MissionControl missionControl;
    private Scanner scanner;

    public UI() {
        state = State.SETUP_PLATEAU;
        scanner = new Scanner(System.in);
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
                System.out.println("Invalid size, please reenter");
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
                System.out.println("Please reenter a position within plateau bounds");
            }
        }
    }

}
