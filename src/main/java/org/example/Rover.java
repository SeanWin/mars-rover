package org.example;

public class Rover {
    private Position position;

    public Rover(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void turn(Instruction instruction){
        if (position.getDirection() == null) {
            throw new IllegalArgumentException("Invalid direction");
        }
        if(instruction.equals(Instruction.L)){
            switch (position.getDirection()){
                case Direction.N:
                position.setDirection(Direction.W);
                break;

                case Direction.E:
                    position.setDirection(Direction.N);
                    break;

                case Direction.S:
                    position.setDirection(Direction.E);
                    break;

                case Direction.W:
                    position.setDirection(Direction.S);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid direction");
            }
        }
        else if(instruction.equals(Instruction.R)){
            switch (position.getDirection()){
                case Direction.N:
                    position.setDirection(Direction.E);
                    break;

                case Direction.E:
                    position.setDirection(Direction.S);
                    break;

                case Direction.S:
                    position.setDirection(Direction.W);
                    break;

                case Direction.W:
                    position.setDirection(Direction.N);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid direction");
            }
        }
        else{
            throw new IllegalArgumentException("Invalid instruction");
        }
    }



}
