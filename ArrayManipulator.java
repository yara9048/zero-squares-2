import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayManipulator {

    static boolean win = false;
    static Map<String, Boolean> colorReached = new HashMap<>();
    public static List<move> movesList = new ArrayList<>();
   

    public static state left(Element[][] array, List<Square> squares) {
        return moveSquares(array, squares, "left");
    }
    
    public static state right(Element[][] array, List<Square> squares) {
        return moveSquares(array, squares, "right");
    }
    
    public static state up(Element[][] array, List<Square> squares) {
        return moveSquares(array, squares, "up");
    }
    
    public static state down(Element[][] array, List<Square> squares) {
        return moveSquares(array, squares, "down");
    }
    
    private static state moveSquares(Element[][] array, List<Square> squares, String direction) {
        NextState(array, squares);
        boolean anySquareMoved;
        do {
            anySquareMoved = false;
    
            for (Square square : squares) {
                if (square.isReachedTarget()) {
                    continue;
                }
    
                int[] position = findSquarePosition(array, square.getColor());
                if (position == null) continue;
    
                int currentRow = position[0];
                int currentCol = position[1];
    
                int[] targetPosition = findTarget(array, currentRow, currentCol, direction);
                int targetRow = targetPosition[0];
                int targetCol = targetPosition[1];
    
                if (targetRow != currentRow || targetCol != currentCol) {
                    moveSquare(array, square, currentRow, currentCol, targetRow, targetCol);
                    anySquareMoved = true;
                }
            }
    
        } while (anySquareMoved);
    
        checkAndEndGame(squares);
        return new state(array);
    }
      
    private static int[] findSquarePosition(Element[][] array, String color) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j].getColor().equals(color)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    
    private static int[] findTarget(Element[][] array, int currentRow, int currentCol, String direction) {
        switch (direction) {
            case "left":
                for (int j = currentCol - 1; j >= 0; j--) {
                    if (isBlocker(array[currentRow][j]) || !array[currentRow][j].getColor().equals("white")) {
                        return new int[]{currentRow, j + 1};
                    }
                }
                return new int[]{currentRow, 0};
            case "right":
                for (int j = currentCol + 1; j < array[currentRow].length; j++) {
                    if (isBlocker(array[currentRow][j]) || !array[currentRow][j].getColor().equals("white")) {
                        return new int[]{currentRow, j - 1};
                    }
                }
                return new int[]{currentRow, array[currentRow].length - 1};
            case "up":
                for (int i = currentRow - 1; i >= 0; i--) {
                    if (isBlocker(array[i][currentCol]) || !array[i][currentCol].getColor().equals("white")) {
                        return new int[]{i + 1, currentCol};
                    }
                }
                return new int[]{0, currentCol};
            case "down":
                for (int i = currentRow + 1; i < array.length; i++) {
                    if (isBlocker(array[i][currentCol]) || !array[i][currentCol].getColor().equals("white")) {
                        return new int[]{i - 1, currentCol};
                    }
                }
                return new int[]{array.length - 1, currentCol};
            default:
                return new int[]{currentRow, currentCol};
        }
    }
    
    private static void moveSquare(Element[][] array, Square square, int currentRow, int currentCol, int targetRow, int targetCol) {
        array[targetRow][targetCol] = new Element(square.getColor(), "square", "not aim");
        array[currentRow][currentCol] = new Element("white", "road", "not aim");
    
        System.out.println("Square moved from (" + currentRow + ", " + currentCol + ") to (" + targetRow + ", " + targetCol + ")");
    
        if (targetRow == square.getTargetRow() && targetCol == square.getTargetCol()) {
            square.setReachedTarget(true);
            System.out.println("Square " + square.getColor() + " reached its target at (" + targetRow + ", " + targetCol + ")");
        }
    }
    
    public static boolean isBlocker(Element element) {
        return !element.getColor().equals("white") && element.getAim().equals("aim");
    }
    
    private static void checkAndEndGame(List<Square> squares) {
        boolean allReached = squares.stream().allMatch(Square::isReachedTarget);
    
        if (allReached) {
            System.out.println("All squares have reached their targets! Game Over!");
            win=true;
        }
    }

    public static boolean isVisited(int row, int col, String direction) {
        move newMove = new move(row, col, direction);
        if (movesList.contains(newMove)) {
            System.out.println("This move exists.");
            return true;
        } else {
            movesList.add(newMove);
            System.out.println("Move added: " + newMove);
            return false;
        }
    }

    public static List<move> NextState(Element[][] array, List<Square> squares) {
        List<move> allMoves = new ArrayList<>();
    
        for (Square square : squares) {
            List<move> squareMoves = new ArrayList<>();
            int[] position = findSquarePosition(array, square.getColor());
    
            if (position != null) {
                int row = position[0];
                int col = position[1];
    
                if (row - 1 >= 0 && array[row - 1][col].getColor().equals("white")) {
                    squareMoves.add(new move(row, col, "up"));
                }
                if (row + 1 < array.length && array[row + 1][col].getColor().equals("white")) {
                    squareMoves.add(new move(row, col, "down"));
                }
                if (col - 1 >= 0 && array[row][col - 1].getColor().equals("white")) {
                    squareMoves.add(new move(row, col, "left"));
                }
                if (col + 1 < array[0].length && array[row][col + 1].getColor().equals("white")) {
                    squareMoves.add(new move(row, col, "right"));
                }
    
                System.out.println("Valid moves for square " + square.getColor() + ": " + squareMoves);
                allMoves.addAll(squareMoves);
            }
        }
    
        System.out.println("All valid moves: " + allMoves);
        return allMoves;
    }
    
 
}
