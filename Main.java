

public class Main {
    public static void main(String[] args) {
        ///////////////TO START THE GAME AS A PLAYER 
      //  SwingUtilities.invokeLater(() -> {
       //     ArrayManipulatorGUI gui = new ArrayManipulatorGUI(); // Create the GUI object
        ///    gui.setVisible(true); // Make the GUI visible
        //});

        //////////////TO START ALGO(FIRST TRY)
        setting initialSetting = new setting();
        state initialState = initialSetting.stt;
        Algorithms algorithms = new Algorithms();
        System.out.println("DDDFFFSSS");
        algorithms.UCS(initialState);
        //System.out.println("BBBFFFSSS");
        //algorithms.BFS(initialState);
     }
}
