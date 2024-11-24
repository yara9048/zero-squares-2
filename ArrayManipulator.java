
import java.util.ArrayList;
import java.util.List;

public class ArrayManipulator {

    static boolean win = false;
    public static List<move> movesList = new ArrayList<>();

    public static state left(Element[][] array) {
        boolean foundOne = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j].equals(new Element("blue", "square", "not aim"))) {
                  //  System.out.println("i :" + i + " j :" + j);
                    if (i == 3 && j == 4) {
                        win = true;
                        System.out.println("Win! Reached the aim cell.");
                    }
                    if (j == 0) {
                        System.out.println("Border.");
                        state Array = new state();
                        return Array;
                    }
                    if (array[i][j - 1].equals(new Element("black", "block", "not aim"))) {
                        System.out.println("Blocked");
                        state Array = new state();
                        return Array;
                    } else {
                        for (int k = j - 1; k >= 0; k--) {
                            if (array[i][k].equals(new Element("black", "block", "not aim"))) {
                                array[i][k + 1] = new Element("blue", "square", "not aim");
                                foundOne = true;
                                isVisited(i, k + 1, "left");
                                break;
                            }
                        }
                        if (!foundOne) {
                            array[i][0] = new Element("blue", "square", "not aim");
                            isVisited(i, 0, "left");
                        }
                    }
                    array[i][j] = new Element("white", "road", "not aim");

                    state Array = new state();
                    List<move> nextStates = NextState(array);
                    return Array;
                }
            }
        }
        state Array = new state();
        return Array;
    }

    public static state right(Element[][] array) {
        boolean foundOne = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j].equals(new Element("blue", "square", "not aim"))) {
                    if (i == 3 && j == 4) {
                        win = true;
                        System.out.println("Win! Reached the aim cell.");
                    }
                    if (j == array[i].length - 1) {
                        System.out.println("Border.");
                        state Array = new state();
                        return Array;
                    }
                    if (array[i][j + 1].equals(new Element("black", "block", "not aim"))) {
                        System.out.println("Blocked");
                        state Array = new state();
                        return Array;
                    } else {
                        for (int k = j + 1; k < array[i].length; k++) {
                            if (array[i][k].equals(new Element("black", "block", "not aim"))) {
                                array[i][k - 1] = new Element("blue", "square", "not aim");
                                array[i][k] = new Element("black", "block", "not aim");
                                foundOne = true;
                                isVisited(i, k - 1, "right");
                                break;
                            }
                        }
                        if (!foundOne) {
                            array[i][array[i].length - 1] = new Element("blue", "square", "not aim");
                            isVisited(i, array[i].length - 1, "right");
                        }
                    }
                    array[i][j] = new Element("white", "road", "not aim");
                    state Array = new state();
                    List<move> nextStates = NextState(array);
                    return Array;
                }
            }
        }
        state Array = new state();
        return Array;
    }

    public static state up(Element[][] array) {
        boolean foundOne = false;
        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {
                if (array[i][j].equals(new Element("blue", "square", "not aim"))) {
                    if (i == 3 && j == 4) {
                        win = true;
                        System.out.println("Win! Reached the aim cell.");
                    }
                    if (i - 1 >= 0 && array[i - 1][j].equals(new Element("white", "road", "aim"))) {
                        win = true;
                        System.out.println("Win: " + win);
                    }
                    if (i == 0) {
                        System.out.println("Border.");
                        state Array = new state();
                        return Array;
                    }
                    if (array[i - 1][j].equals(new Element("black", "block", "not aim"))) {
                        System.out.println("Blocked");
                        state Array = new state();
                        return Array;
                    } else {
                        for (int k = i - 1; k >= 0; k--) {
                            if (array[k][j].equals(new Element("black", "block", "not aim"))) {
                                array[k + 1][j] = new Element("blue", "square", "not aim");
                                array[k][j] = new Element("black", "block", "not aim");
                                foundOne = true;
                                isVisited(k + 1, j, "up");
                                break;
                            }
                        }
                        if (!foundOne) {
                            array[0][j] = new Element("blue", "square", "not aim");
                            isVisited(0, j, "up");
                        }
                    }
                    array[i][j] = new Element("white", "road", "not aim");
                    state Array = new state();
                    List<move> nextStates = NextState(array);
                    return Array;
                }
            }
        }
        state Array = new state();
        return Array;
    }

    public static state down(Element[][] array) {
        boolean foundOne = false;
        for (int j = 0; j < array[0].length; j++) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i][j].equals(new Element("blue", "square", "not aim"))) {
                    //System.out.println("i :" + i + " j :" + j);
                    if (i == 1 && j == 0) {
                        win = true;
                        System.out.println("Win! Reached the aim cell.");
                    }

                    if (i + 1 < array.length && array[i + 1][j].equals(new Element("white", "road", "aim"))) {
                        win = true;
                        System.out.println("Win: " + win);
                    }
                    if (i == array.length - 1) {
                        System.out.println("Border");
                        state Array = new state();
                        return Array;
                    }
                    if (array[i + 1][j].equals(new Element("black", "block", "not aim"))) {
                        System.out.println("Blocked");
                        state Array = new state();
                        return Array;
                    } else {
                        for (int k = i + 1; k < array.length; k++) {
                            if (array[k][j].equals(new Element("black", "block", "not aim"))) {
                                array[k - 1][j] = new Element("blue", "square", "not aim");
                                array[k][j] = new Element("black", "block", "not aim");
                                foundOne = true;
                                isVisited(k - 1, j, "down");
                                break;
                            }
                        }
                        if (!foundOne) {
                            array[array.length - 1][j] = new Element("blue", "square", "not aim");
                            isVisited(array.length - 1, j, "down");
                        }
                    }
                    array[i][j] = new Element("white", "road", "not aim");
                    state Array = new state();
                    List<move> nextStates = NextState(array);
                    return Array;
                }
            }
        }
        state Array = new state();
        return Array;
    }

    public static boolean isVisited(int row, int col, String direction) {
        move newMove = new move(row, col, direction);
        if (movesList.contains(newMove)) {
            System.out.println("this move exist");
            return true;
        } else {
            movesList.add(newMove);
            System.out.println("this move added");
            return false;
        }
    }

    public static List<move> NextState(Element[][] array) {
        List<move> nextStateList = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j].equals(new Element("blue", "square", "not aim"))) {
                    if (i - 1 >= 0 && array[i - 1][j].equals(new Element("white", "road", "not aim"))) {
                        nextStateList.add(new move(i, j, "up"));
                    } else if (i - 1 >= 0 && array[i - 1][j].equals(new Element("white", "road", "aim"))) {
                        nextStateList.add(new move(i, j, "up"));
                        win = true;
                        System.out.println("Win: " + win);
                    }
                    if (i + 1 < array.length && array[i + 1][j].equals(new Element("white", "road", "not aim"))) {
                        nextStateList.add(new move(i, j, "down"));
                    } else if (i + 1 < array.length && array[i + 1][j].equals(new Element("white", "road", "aim"))) {
                        nextStateList.add(new move(i, j, "down"));
                        win = true;
                        System.out.println("Win: " + win);
                    }
                    if (j - 1 >= 0 && array[i][j - 1].equals(new Element("white", "road", "not aim"))) {
                        nextStateList.add(new move(i, j, "left"));
                    } else if (j - 1 >= 0 && array[i][j - 1].equals(new Element("white", "road", "aim"))) {
                        nextStateList.add(new move(i, j, "left"));
                        win = true;
                        System.out.println("Win: " + win);
                    }
                    if (j + 1 < array[0].length && array[i][j + 1].equals(new Element("white", "road", "not aim"))) {
                        nextStateList.add(new move(i, j, "right"));
                    } else if (j + 1 < array[0].length && array[i][j + 1].equals(new Element("white", "road", "aim"))) {
                        nextStateList.add(new move(i, j, "right"));
                        win = true;
                        System.out.println("Win: " + win);
                    }
                }
            }
        }

        System.out.println("Valid moves: " + nextStateList);
        System.out.println("Win: " + win);
        return nextStateList;
    }
}
