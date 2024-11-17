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
                State nextState = State.DEPLOY_ROVER;
                return nextState;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid size, please reenter");
            }
        }
    }

}
