
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        /////////////TO START THE GAME AS A PLAYER (using gui)
       //SwingUtilities.invokeLater(() -> new ArrayManipulatorGUI().setVisible(true));

        //////////////TO START ALGO(try each algo separetly , eith console only)
        setting initialSetting = new setting("one");
        state initialState = initialSetting.stt;
        Algorithms algorithms = new Algorithms();
        ///////////write instead of BFS the name of the algo in upper case
       algorithms.AStar(initialState);
     }
}