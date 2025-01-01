import java.util.*;

public class Algorithms {
    static setting game = new setting("one");
   
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
           
        for (move validMove : ArrayManipulator.NextState(currentState.getGrid() , game.squares)) {
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
        System.out.println("Current State:");
        currentState.printState(game.squares);  
        
        if (ArrayManipulator.win) {
            System.out.println("Found the path");
            List<state> path = buildPath(currentState);
            printPath(path);
            System.out.println("Visited states: " + visited.size());
            System.out.println("Path length: " + path.size());
            return path;
        }
        
        visited.add(currentState);
        for (move validMove : ArrayManipulator.NextState(currentState.getGrid(), game.squares)) {
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
            for (move validMove : ArrayManipulator.NextState(currentState.getGrid(),game.squares)) {
            state newState = performMove(currentState, validMove);
            if (newState != null && !visited.contains(newState)) {
                newState.setCost(currentState.getCost() + 1);
                System.out.println("fkdjnvckdfjnv");
                System.out.println(newState.getCost());
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
                newState = ArrayManipulator.up(arrayCopy,game.squares);
                break;
            case "down":
                newState = ArrayManipulator.down(arrayCopy,game.squares);
                break;
            case "left":
                newState = ArrayManipulator.left(arrayCopy,game.squares);
                break;
            case "right":
                newState = ArrayManipulator.right(arrayCopy,game.squares);
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
           state.printState(game.squares);
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
   
    public static int calculateHeuristic(state State) {
        Element[][] grid = State.getGrid();
        int blueRow = -1, blueCol = -1;
        int aimRow = -1, aimCol = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if ("blue".equals(grid[i][j].getColor())) {
                    blueRow = i;
                    blueCol = j;
                }
                if ("aim".equals(grid[i][j].getAim())) {
                    aimRow = i;
                    aimCol = j;
                }
            }
        }
        if (blueRow == -1 || aimRow == -1) {
            return Integer.MAX_VALUE;         }

        return Math.abs(blueRow - aimRow) + Math.abs(blueCol - aimCol);
    }

    public static List<state> AStar(state initialState) {
        PriorityQueue<state> openSet = new PriorityQueue<>(Comparator.comparingInt(s -> s.getCost() + calculateHeuristic(s)));
        Set<state> closedSet = new HashSet<>();
    
        initialState.setCost(0); 
        openSet.add(initialState);
    
        while (!openSet.isEmpty()) {
            state currentState = openSet.poll();
    
            if (ArrayManipulator.win) {
                System.out.println("Path found using A*:");
                List<state> path = buildPath(currentState);
                printPath(path);
                System.out.println("Number of visited states: " + closedSet.size());
                System.out.println("Path length: " + path.size());
                return path;
            }
    
            closedSet.add(currentState);
    
            for (move validMove : ArrayManipulator.NextState(currentState.getGrid(),game.squares)) {
                state newState = performMove(currentState, validMove);
                if (newState == null || closedSet.contains(newState)) {
                    continue;
                }
    
                int tentativeCost = currentState.getCost() + 1;
                int heuristic = calculateHeuristic(newState);
                int finallllll = tentativeCost + heuristic;
            
    
                if (!openSet.contains(newState) || tentativeCost < newState.getCost()) {
                    newState.setCost(tentativeCost);
                    openSet.add(newState);
                }
            }
        }
        System.out.println("No path found using A*.");
        return new ArrayList<>();
    }
 
    public static state simpleHillClimbing(state initialState) {
        state current = initialState;
    
        while (true) {
            // Generate all neighbors of the current state
            List<state> neighbors = generateNeighbors(current);
            state bestNeighbor = null;
            int currentHeuristic = calculateHeuristic(current); // Heuristic of the current state
            int bestHeuristic = currentHeuristic;
    
            // Find the neighbor with the best (lowest) heuristic
            for (state neighbor : neighbors) {
                int neighborHeuristic = calculateHeuristic(neighbor);
                if (neighborHeuristic < bestHeuristic) {
                    bestHeuristic = neighborHeuristic;
                    bestNeighbor = neighbor;
                }
            }
    
            // If no improvement is found or no better neighbors exist, terminate
            if (bestNeighbor == null || bestHeuristic >= currentHeuristic) {
                System.out.println("Stuck in a local optimum.");
                return current;
            }
    
            // Move to the best neighbor
            current = bestNeighbor;
    
            if (checkAllSquaresReached(current.getGrid(), game.squares)) {
                System.out.println("Goal reached!");
                return current;
            }
        }
    }
    
    public static List<state> generateNeighbors(state currentState) {
        List<state> neighbors = new ArrayList<>();
        for (move validMove : ArrayManipulator.NextState(currentState.getGrid(), game.squares)) {
            state newState = performMove(currentState, validMove);
            if (newState != null) {
                neighbors.add(newState);
            }
        }
        return neighbors;
    }
    
    public static boolean checkAllSquaresReached(Element[][] grid, List<Square> squares) {
        for (Square square : squares) {
            if (!square.isReachedTarget()) { 
                return false;
            }
        }
        return true;
    }
    
    public static state steepestHillClimbing(state initialState) {
        state current = initialState;
    
        while (true) {
            // Generate all neighbors of the current state
            List<state> neighbors = generateNeighbors(current);
            state bestNeighbor = null;
            int currentHeuristic = calculateHeuristic(current); // Heuristic of the current state
            int bestHeuristic = Integer.MAX_VALUE;
    
            // Find the neighbor with the best (lowest) heuristic
            for (state neighbor : neighbors) {
                int neighborHeuristic = calculateHeuristic(neighbor);
                if (neighborHeuristic < bestHeuristic) {
                    bestHeuristic = neighborHeuristic;
                    bestNeighbor = neighbor;
                }
            }
    
            // If the best neighbor does not improve the current state, terminate
            if (bestNeighbor == null || bestHeuristic >= currentHeuristic) {
                System.out.println("Stuck in a local optimum.");
                return current;
            }
    
            // Move to the best neighbor
            current = bestNeighbor;
    
            // Check if the goal is achieved
            if (checkAllSquaresReached(current.getGrid(), game.squares)) {
                System.out.println("Goal reached!");
                return current;
            }
        }
    }
    
    
}
