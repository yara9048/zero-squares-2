import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.Border;

public class ArrayManipulatorGUI extends JFrame {
    public JPanel gridPanel;
    public JTextArea outputArea;
    public setting gameSetting;

    public ArrayManipulatorGUI() {
        setTitle("Array Manipulator Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gameSetting = new setting();
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(5, 5));
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setPreferredSize(new Dimension(300, 100));
        updateGridDisplay(); 
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        add(gridPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

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
                if (elem.getColor().equals("blue")) {
                    cell.setBackground(Color.BLUE);
                } else if (elem.getColor().equals("black")) {
                    cell.setBackground(Color.BLACK);
                } else if (elem.getColor().equals("white")) {
                    cell.setBackground(Color.WHITE);
                } else if (elem.getColor().equals("red")) {
                    cell.setBackground(Color.RED); 
                }
                if ("aim".equals(elem.getAim())) {
                    Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 3);
                    cell.setBorder(blueBorder); 
                }

                gridPanel.add(cell);
            }
        }

        gridPanel.revalidate();  
        gridPanel.repaint();         }

  
}
