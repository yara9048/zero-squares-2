import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class ArrayManipulatorGUI extends JFrame {
    public JPanel gridPanel;
    public setting gameSetting;

    public ArrayManipulatorGUI() {
        setTitle("Array Manipulator Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] levels = {"one", "two", "three"};
        String selectedLevel = (String) JOptionPane.showInputDialog(
                this, "Select a Level", "Level Selection",
                JOptionPane.QUESTION_MESSAGE, null, levels, levels[0]);

        if (selectedLevel != null) {
            gameSetting = new setting(selectedLevel); 
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
                        ArrayManipulator.up(gameSetting.stt.getGrid());
                        updateGridDisplay();
                        break;
                    case KeyEvent.VK_DOWN:
                        ArrayManipulator.down(gameSetting.stt.getGrid());
                        updateGridDisplay();
                        break;
                    case KeyEvent.VK_LEFT:
                        ArrayManipulator.left(gameSetting.stt.getGrid());
                        updateGridDisplay();
                        break;
                    case KeyEvent.VK_RIGHT:
                        ArrayManipulator.right(gameSetting.stt.getGrid());
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

                if ("aim".equals(elem.getAim())) {
                    Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 3);
                    cell.setBorder(blueBorder);
                }

            
                gridPanel.add(cell); 
            }
        }

        gridPanel.revalidate(); 
        gridPanel.repaint();
    }


}
