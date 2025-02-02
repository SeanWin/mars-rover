# 🚀 Mars Rover

## 📜 Description

The **Mars Rover** project simulates the movement of rovers on a plateau surface of Mars.
The rovers navigate based on given instructions and report their final positions.
The project follows Object-Oriented Programming principles, making it easy to extend and maintain.


## ✨ Features

- **Object-Oriented Design**: Clean separation of concerns using OOP principles.
- **Input Parsing**: Flexible input handling with error checking.
- **Boundary Check**: Ensures rovers do not move outside the plateau bounds.
- **Testing**: Comprehensive unit and integration tests using JUnit.

## 🛠️ Technologies Used

- Java
- JUnit 5
- Maven

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed on your machine:

- **Java 21**: [Download Here](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven**: [Install Guide](https://maven.apache.org/install.html)

### Running the Project

- **Fork and clone the repository**: [GitHub link](https://github.com/SeanWin/mars-rover)
- **Run the Main method and follow the console prompts**
- **Input format**: The program expects input in the following format(which is an x coordinate, y coordinate and compass direction)
```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```
- **Output format**: The program calculates the final position of the Rover
```
1 3 N
5 1 E
```

## 📖 Usage Guide for Developers
- **Direction**: Enum with values N,E,S,W (North, East, South, West)
- **Instruction** Enum with values L,R,M (Left turn, Right turn, Move)
- **Position**: Data class for Rover position 
- **PlateauSize** Data class for the size of the plateau
- **Plateau**: Represents the grid size of the plateau and checks rover positions are within plateau bounds.
- **Rover**: Handles movement and turning logic based on the provided instructions.
- **MissionControl**: Manages multiple rovers on the plateau, executes their movement instructions and checks whether moves are valid.
- **InputParser**: Parses user input for plateau size, rover positions, and movement instructions.

## 📧 Contact
For any inquiries or feedback, please reach out to:
seanwinston@gmail.com




