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

    public void move(Instruction instruction) {
        if (instruction.equals(Instruction.L) || instruction.equals(Instruction.R)) {
            throw new IllegalArgumentException("Invalid instruction");
        }
        if (position.getDirection() == null) {
            throw new IllegalArgumentException("Invalid direction");
        }
        switch (position.getDirection()) {
            case Direction.N:
                position.setY(position.getY()+1);
                break;

            case Direction.E:
                position.setX(position.getX()+1);
                break;

            case Direction.S:
                position.setY(position.getY()-1);
                break;

            case Direction.W:
                position.setX(position.getX()-1);
                break;

            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }


}