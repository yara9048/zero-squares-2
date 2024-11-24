import java.util.*;

public class Algorithms {
    public ArrayList<state> DFS(state root) {
        ArrayList<state> visitedStates = new ArrayList<>();
        Stack<state> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            if(ArrayManipulator.win
            ){
                System.out.println("winnnnnn");
               }
            state currentState = stack.pop();
            visitedStates.add(currentState);

            System.out.println("Starting with");
            currentState.printGrid();

            if (ArrayManipulator.win) {
                System.out.println("Reach aim");
                return buildPath(currentState, visitedStates, "DFS");
            }

            List<move> nextMoves = ArrayManipulator.NextState(currentState.getGrid());
            //System.out.print("Valid moves: ");
            for (move m : nextMoves) {
                System.out.print("[" + m.getDirection() + "] ");
            }
            System.out.println();

            for (move nextMove : nextMoves) {
                state nextState = performMove(currentState, nextMove);
                if (nextState != null && !visitedStates.contains(nextState)) {
                    stack.push(nextState);
                }
            }
        }
        return null;
    }

    public ArrayList<state> BFS(state root) {
        ArrayList<state> visitedStates = new ArrayList<>();
        Queue<state> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            state currentState = queue.poll();
            if(ArrayManipulator.win){
                System.out.println("winnnnnn");
                return buildPath(currentState, visitedStates, "BFS");}
            System.out.println("Starting with");
            currentState.printGrid();

            if (ArrayManipulator.win) {
                System.out.println("reeached aim");
                return buildPath(currentState, visitedStates, "BFS");
            }

            List<move> nextMoves = ArrayManipulator.NextState(currentState.getGrid());
            //System.out.print("Valid moves: ");
            for (move m : nextMoves) {
                System.out.print("[" + m.getDirection() + "] ");
            }
            System.out.println();

            for (move nextMove : nextMoves) {
                state nextState = performMove(currentState, nextMove);
                if (nextState != null && !visitedStates.contains(nextState)) {
                    queue.add(nextState);
                    nextState.parent=currentState;
                }
            }
        }
        return null;
    }

    public ArrayList<state> buildPath(state goalState, ArrayList<state> visitedStates, String algorithmName) {
        ArrayList<state> path = new ArrayList<>();
        state currentState = goalState;

        while (currentState != null) {
            path.add(currentState);
            currentState = currentState.getParent();
        }
        System.out.println( algorithmName + " Path found!");
        System.out.println("Number of visited states: " + visitedStates.size());
        System.out.println("Number of states in the path: " + path.size());

        System.out.println("\nPath:");
        for (state s : path) {
            s.printGrid();
            System.out.println("-----");
    
        }
        System.out.println("End of path\n");
        return path;
    }

    public state performMove(state currentState, move nextMove) {
        Element[][] currentGrid = currentState.getGrid();
        state newState = null;

        switch (nextMove.getDirection()) {
            case "up":
                newState = ArrayManipulator.up(currentGrid);
                break;
            case "down":
                newState = ArrayManipulator.down(currentGrid);
                break;
            case "left":
                newState = ArrayManipulator.left(currentGrid);
                break;
            case "right":
                newState = ArrayManipulator.right(currentGrid);
                break;
        }

        if (newState != null) {
            newState.setParent(currentState); 
        }

        return newState;
    }

    public ArrayList<state> UCS(state root) {
        ArrayList<state> visitedStates = new ArrayList<>();
        PriorityQueue<state> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(state::getCost));
        root.setCost(0); 
        priorityQueue.add(root);

        while (!priorityQueue.isEmpty()) {
            state currentState = priorityQueue.poll();
            if (ArrayManipulator.win) {
                System.out.println("winnnnnn");
                return buildPath(currentState, visitedStates, "UCS");
            }

            System.out.println("Starting with");
            currentState.printGrid();

            if (ArrayManipulator.win) {
                System.out.println("reached aim");
                return buildPath(currentState, visitedStates, "UCS");
            }

            List<move> nextMoves = ArrayManipulator.NextState(currentState.getGrid());
            for (move m : nextMoves) {
                System.out.print("[" + m.getDirection() + "] ");
            }
            System.out.println();

            for (move nextMove : nextMoves) {
                state nextState = performMove(currentState, nextMove);
                if (nextState != null && !visitedStates.contains(nextState)) {
                    int newCost = currentState.getCost() + 1;
                    nextState.setCost(newCost);
                    nextState.setParent(currentState);
                    priorityQueue.add(nextState);
                    
                }
            }
            visitedStates.add(currentState);
        }
        return null; 
    }

}

