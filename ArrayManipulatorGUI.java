import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

public class ArrayManipulatorGUI extends JFrame {
    public JPanel gridPanel;
    public setting gameSetting;
    public static String level; 

    public ArrayManipulatorGUI() {
        setTitle("Array Manipulator Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] levels = {"one", "two", "three"};
        level = (String) JOptionPane.showInputDialog(
                this, "Select a Level", "Level Selection",
                JOptionPane.QUESTION_MESSAGE, null, levels, levels[0]);

        if (level != null) {
            gameSetting = new setting(level); 
        } else {
            System.exit(0); 
        }

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(5, 5)); 
        updateGridDisplay();

        add(gridPanel, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (ArrayManipulator.win) {
                    JOptionPane.showMessageDialog(ArrayManipulatorGUI.this, "Game Ended. You won!");
                    System.exit(0);
                    return;
                }

                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        ArrayManipulator.up(gameSetting.stt.getGrid(), gameSetting.squares);
                        updateGridDisplay();
                        break;
                    case KeyEvent.VK_DOWN:
                        ArrayManipulator.down(gameSetting.stt.getGrid(), gameSetting.squares);
                        updateGridDisplay();
                        break;
                    case KeyEvent.VK_LEFT:
                        ArrayManipulator.left(gameSetting.stt.getGrid(), gameSetting.squares);
                        updateGridDisplay();
                        break;
                    case KeyEvent.VK_RIGHT:
                        ArrayManipulator.right(gameSetting.stt.getGrid(), gameSetting.squares);
                        updateGridDisplay();
                        break;
                    case KeyEvent.VK_ENTER:
                    case KeyEvent.VK_ESCAPE:
                        if (ArrayManipulator.win) {
                            System.exit(0);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        setFocusable(true);
    }

    public void updateGridDisplay() {
        gridPanel.removeAll();
    
        Element[][] grid = gameSetting.stt.getGrid();
        List<Square> squares = gameSetting.squares;
    
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Element elem = grid[i][j];
                JPanel cell = new JPanel();
                    switch (elem.getColor()) {
                    case "blue":
                        cell.setBackground(new Color(0, 0, 250));
                        break;
                    case "black":
                        cell.setBackground(new Color(50, 50, 50));
                        break;
                    case "white":
                        cell.setBackground(Color.WHITE);
                        break;
                    case "red":
                        cell.setBackground(new Color(255, 99, 71));
                        break;
                    default:
                        cell.setBackground(Color.GRAY);
                        break;
                }
    
                cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    
                for (Square square : squares) {
                    if (square.getTargetRow() == i && square.getTargetCol() == j) {
                        Color squareColor;
                        switch (square.getColor()) {
                            case "blue":
                                squareColor = new Color(0, 0, 250);
                                break;
                            case "red":
                                squareColor = new Color(255, 99, 71);
                                break;
                            case "pink":
                                squareColor = new Color(255, 182, 193);
                                break;
                            default:
                                squareColor = Color.GRAY;
                                break;
                        }
    
                        Border existingBorder = cell.getBorder();
                        Border targetBorder = BorderFactory.createLineBorder(squareColor, 3);
                        cell.setBorder(BorderFactory.createCompoundBorder(existingBorder, targetBorder));
                    }
                }
    
                gridPanel.add(cell);
            }
        }
    
        gridPanel.revalidate();
        gridPanel.repaint(); 
    }
    }
