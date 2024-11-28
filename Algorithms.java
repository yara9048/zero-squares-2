import java.util.*;

public class Algorithms {

    public static List<state> BFS(state initialState) {
        Set<state> visited = new HashSet<>();
        return BFS(initialState, visited);
    }
    
    public static List<state> BFS(state currentState, Set<state> visited) {
        if (visited.contains(currentState)) {
            return new ArrayList<>();
        }
            visited.add(currentState);
            if (ArrayManipulator.win) {
            System.out.println("Path found:");
            List<state> path = buildPath(currentState);
            printPath(path);
            System.out.println("Number of visited states: " + visited.size());
            System.out.println("Path length: " + path.size());
            return path;
        }
            for (move validMove : ArrayManipulator.NextState(currentState.getGrid())) {
            state newState = performMove(currentState, validMove);
            if (newState != null && !visited.contains(newState)) {
                List<state> result = BFS(newState, visited);
                if (!result.isEmpty()) {
                    return result;
                }
            }
        }
    
        return new ArrayList<>();
    }
    
    public static List<state> DFS(state currentState, Queue visited) {
        if (visited.isEmpty()) {
            System.out.println("Initial State:");
            currentState.printGrid();
        }
        if (ArrayManipulator.win) {
            System.out.println("Found the path");
            List<state> path = buildPath(currentState);
            printPath(path);
    
            System.out.println("Visited states: " + visited.size());
            System.out.println("Path length: " + path.size());
    
            return path;
        }
            visited.add(currentState);
            for (move validMove : ArrayManipulator.NextState(currentState.getGrid())) {
            state newState = performMove(currentState, validMove);
                if (newState != null && !visited.contains(newState)) {
                List<state> result = DFS(newState, visited);  
                if (!result.isEmpty()) {
                    return result;  
                }
            }
        }
        return new ArrayList<>();
    }
    
    public static List<state> DFS(state initialState) {
        Queue<state> visited = new LinkedList<>();
        return DFS(initialState, visited);  
    }
   
    public static List<state> UCS(state initialState) {
        Set<state> visited = new HashSet<>();
        return UCS(initialState, visited, new PriorityQueue<>(Comparator.comparingInt(state::getCost)));
    }
    
    public static List<state> UCS(state currentState, Set<state> visited, PriorityQueue<state> prique) {
        if (visited.contains(currentState)) {
            return new ArrayList<>();
        }
        visited.add(currentState);
        if (ArrayManipulator.win) {
            System.out.println("Path found:");
            List<state> path = buildPath(currentState);
            printPath(path);
            System.out.println("Number of visited states: " + visited.size());
            System.out.println("Path length: " + path.size());
            return path;
        }
        prique.add(currentState);
            for (move validMove : ArrayManipulator.NextState(currentState.getGrid())) {
            state newState = performMove(currentState, validMove);
            if (newState != null && !visited.contains(newState)) {
                newState.setCost(currentState.getCost() + 1);
                List<state> result = UCS(newState, visited, prique);
                if (!result.isEmpty()) {
                    return result;
                }
            }
        }

        return new ArrayList<>();
    }
    
    public static state performMove(state currentState, move validMove) {
        Element[][] arrayCopy = copyArray(currentState.getGrid());
        state newState = null;

        switch (validMove.getDirection()) {
            case "up":
                newState = ArrayManipulator.up(arrayCopy);
                break;
            case "down":
                newState = ArrayManipulator.down(arrayCopy);
                break;
            case "left":
                newState = ArrayManipulator.left(arrayCopy);
                break;
            case "right":
                newState = ArrayManipulator.right(arrayCopy);
                break;
        }

        if (newState != null) {
            newState.setParent(currentState);
            newState.setCost(currentState.getCost() + 1); 
            return newState;
        }

        return null;
    }

    public static List<state> buildPath(state winningState) {
        List<state> path = new ArrayList<>();
        state current = winningState;

        while (current != null) {
            path.add(0, current); 
            current = current.getParent();
        }

        return path;
    }

    public static void printPath(List<state> path) {
        System.out.println("Path from initial state to goal:");
        for (state state : path) {
           state.printGrid();
            System.out.println();
        }
    }

    public static Element[][] copyArray(Element[][] original) {
        int rows = original.length;
        int cols = original[0].length;
        Element[][] copy = new Element[rows][cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, cols);
        }

        return copy;
    }
}
