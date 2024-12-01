import java.util.ArrayList;
import java.util.List;

public class ArrayManipulator {

    static boolean win = false;
    public static List<move> movesList = new ArrayList<>();

    public static state left(Element[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j].equals(new Element("blue", "square", "not aim"))) {
                    if (j - 1 < 0) { 
                        System.out.println("Border");
                        return new state(array);
                    }
                    int targetCol = -1;
                    for (int k = j - 1; k >= 0; k--) {
                        if (checkWinCondition(array, i, k)) {
                            array[i][k] = new Element("blue", "square", "aim");
                            array[i][j] = new Element("white", "road", "not aim");
                            isVisited(i, k, "left");
                            return new state(array);
                        }
    
                        if (array[i][j - 1].equals(new Element("black", "block", "not aim"))) {
                            System.out.println("Blocked");
                            return new state(array);
                        }
    
                        if (array[i][k].equals(new Element("black", "block", "not aim"))) {
                            targetCol = k + 1;
                            break;
                        }
                    }
    
                    if (targetCol == -1) {
                        targetCol = 0;
                    }
    
                    array[i][targetCol] = new Element("blue", "square", "not aim");
                    array[i][j] = new Element("white", "road", "not aim");
                    isVisited(i, targetCol, "left");
                    return new state(array);
                }
            }
        }
        return null;
    }
    
    public static state right(Element[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j].equals(new Element("blue", "square", "not aim"))) {
                    if (j + 1 >= array[i].length) { 
                        System.out.println("Border");
                        return new state(array);
                    }
                    int targetCol = -1;
                    for (int k = j + 1; k < array[i].length; k++) {
                        if (checkWinCondition(array, i, k)) {
                            array[i][k] = new Element("blue", "square", "aim");
                            array[i][j] = new Element("white", "road", "not aim");
                            isVisited(i, k, "right");
                            return new state(array);
                        }
    
                        if (array[i][j + 1].equals(new Element("black", "block", "not aim"))) {
                            System.out.println("Blocked");
                            return new state(array);
                        }
    
                        if (array[i][k].equals(new Element("black", "block", "not aim"))) {
                            targetCol = k - 1;
                            break;
                        }
                    }
    
                    if (targetCol == -1) {
                        targetCol = array[i].length - 1;
                    }
    
                    array[i][targetCol] = new Element("blue", "square", "not aim");
                    array[i][j] = new Element("white", "road", "not aim");
                    isVisited(i, targetCol, "right");
                    return new state(array);
                }
            }
        }
        return null;
    }
    
    public static state up(Element[][] array) {
        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {
                if (array[i][j].equals(new Element("blue", "square", "not aim"))) {
                    if (i - 1 < 0) {
                        System.out.println("Border");
                        return new state(array);
                    }
    
                    int targetRow = -1;
                    for (int k = i - 1; k >= 0; k--) {
                        if (checkWinCondition(array, k, j)) {
                            array[k][j] = new Element("blue", "square", "aim");
                            array[i][j] = new Element("white", "road", "not aim");
                            isVisited(k, j, "up");
                            return new state(array);
                        }
    
                        if (array[i - 1][j].equals(new Element("black", "block", "not aim"))) {
                            System.out.println("Blocked");
                            return new state(array);
                        }
    
                        if (array[k][j].equals(new Element("black", "block", "not aim"))) {
                            targetRow = k + 1;
                            break;
                        }
                    }
    
                    if (targetRow == -1) {
                        targetRow = 0;
                    }
    
                    array[targetRow][j] = new Element("blue", "square", "not aim");
                    array[i][j] = new Element("white", "road", "not aim");
                    isVisited(targetRow, j, "up");
                    return new state(array);
                }
            }
        }
        return null;
    }
    
    public static state down(Element[][] array) {
        for (int j = 0; j < array[0].length; j++) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i][j].equals(new Element("blue", "square", "not aim"))) {
                    if (i + 1 >= array.length) { 
                        System.out.println("Border");
                        return new state(array);
                    }
    
                    int targetRow = -1;
                    for (int k = i + 1; k < array.length; k++) {
                        if (checkWinCondition(array, k, j)) {
                            array[k][j] = new Element("blue", "square", "aim");
                            array[i][j] = new Element("white", "road", "not aim");
                            isVisited(k, j, "down");
                            return new state(array);
                        }
    
                        if (array[i + 1][j].equals(new Element("black", "block", "not aim"))) {
                            System.out.println("Blocked");
                            return new state(array);
                        }
    
                        if (array[k][j].equals(new Element("black", "block", "not aim"))) {
                            targetRow = k - 1;
                            break;
                        }
                    }
    
                    if (targetRow == -1) {
                        targetRow = array.length - 1;
                    }
    
                    array[targetRow][j] = new Element("blue", "square", "not aim");
                    array[i][j] = new Element("white", "road", "not aim");
                    isVisited(targetRow, j, "down");
                    return new state(array);
                }
            }
        }
        return null;
    }

    public static boolean checkWinCondition(Element[][] array, int row, int col) {
        if (row >= 0 && row < array.length && col >= 0 && col < array[0].length) {
            if ("aim".equals(array[row][col].getAim())) {
                win = true;
                System.out.println("Win! Reached the aim cell at: (" + row + ", " + col + ")");
                return true;
            }
        }
        return false;
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

    public static List<move> NextState(Element[][] array) {
        List<move> nextStateList = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j].equals(new Element("blue", "square", "not aim"))) {
                    if (i - 1 >= 0 && array[i - 1][j].equals(new Element("white", "road", "not aim"))) {
                        nextStateList.add(new move(i, j, "up"));
                    }
                    if (i + 1 < array.length && array[i + 1][j].equals(new Element("white", "road", "not aim"))) {
                        nextStateList.add(new move(i, j, "down"));
                    }
                    if (j - 1 >= 0 && array[i][j - 1].equals(new Element("white", "road", "not aim"))) {
                        nextStateList.add(new move(i, j, "left"));
                    }
                    if (j + 1 < array[0].length && array[i][j + 1].equals(new Element("white", "road", "not aim"))) {
                        nextStateList.add(new move(i, j, "right"));
                    }
                }
            }
        }

        System.out.println("Valid moves: " + nextStateList);
        System.out.println("Win: " + win);

        return nextStateList;
    }
}