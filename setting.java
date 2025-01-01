import java.util.ArrayList;
import java.util.List;

public final class setting {
    state stt;
    String level;
    String AimColor;
    List<Square> squares ;

    public setting(String level) {
        if (level == null) {
            throw new IllegalArgumentException("Level cannot be null");
        }
        this.level = level;
    
        this.squares = new ArrayList<>();  
    
        Element ele1 = new Element("black", "block", "not aim");
        Element ele2 = new Element("white", "road", "not aim");
        Element ele3 = new Element("white", "road", "aim");
        Element ele6 = new Element("red", "square", "not aim");
        Element ele4 = new Element("blue", "square", "not aim");
        Element ele5 = new Element("pink", "square", "not aim");
    
        stt = new state();
    
        switch (level) {
            case "one" -> setUpLevelOne(squares, ele1, ele2, ele3, ele4, ele5, ele6);
            case "two" -> setUpLevelTwo(squares, ele1, ele2, ele3, ele4, ele5);
            case "three" -> setUpLevelThree(squares, ele1, ele2, ele3, ele4, ele5);
            default -> throw new IllegalArgumentException("Unknown level: " + level);
        }
    }
    

    public void setUpLevelOne(List<Square> squares, Element ele1, Element ele2, Element ele3, Element ele4, Element ele5,Element ele6) {
        stt.setElement(1, 0, new Element("white", "road", "pink"));
        stt.setElement(1, 1, ele2);
        stt.setElement(1, 2, ele2);
        stt.setElement(1, 3, ele2);
        stt.setElement(1, 4, ele5);

        stt.setElement(0, 0, ele1);
        stt.setElement(0, 1, ele2);
        stt.setElement(0, 2, ele2);
        stt.setElement(0, 3, ele2);
        stt.setElement(0, 4, ele1);

        stt.setElement(2, 0, ele3);
        stt.setElement(2, 1, ele2);
        stt.setElement(2, 2, ele2);
        stt.setElement(2, 3, ele2);
        stt.setElement(2, 4, ele4);

        stt.setElement(3, 0, ele3);
        stt.setElement(3, 1, ele2);
        stt.setElement(3, 2, ele2);
        stt.setElement(3, 3, ele2);
        stt.setElement(3, 4, ele6);

        stt.setElement(4, 0, ele1);
        stt.setElement(4, 1, ele1);
        stt.setElement(4, 2, ele1);
        stt.setElement(4, 3, ele2);
        stt.setElement(4, 4, ele2);

        squares.add(new Square("red", 3, 4, 3, 0)); 
        squares.add(new Square("blue", 2, 4, 2, 0)); 
        squares.add(new Square("pink", 1, 4, 1, 0)); 

      }

     void setUpLevelTwo(List<Square> squares, Element ele1, Element ele2, Element ele3, Element ele4, Element ele5) {
        stt.setElement(0, 0, ele1);
        stt.setElement(0, 1, ele2);
        stt.setElement(0, 2, ele2);
        stt.setElement(0, 3, ele2);
        stt.setElement(0, 4, ele1);

        stt.setElement(1, 0, ele2);
        stt.setElement(1, 1, ele2);
        stt.setElement(1, 2, ele1);
        stt.setElement(1, 3, ele2);
        stt.setElement(1, 4, ele2);

        stt.setElement(2, 0, ele2);
        stt.setElement(2, 1, ele1);
        stt.setElement(2, 2, ele1);
        stt.setElement(2, 3, ele1);
        stt.setElement(2, 4, ele2);

        stt.setElement(3, 0, ele3);
        stt.setElement(3, 1, ele2);
        stt.setElement(3, 2, ele2);
        stt.setElement(3, 3, ele2);
        stt.setElement(3, 4, ele2);

        stt.setElement(4, 0, ele1);
        stt.setElement(4, 1, ele2);
        stt.setElement(4, 2, ele1);
        stt.setElement(4, 3, ele1);
        stt.setElement(4, 4, ele4);

        squares.add(new Square("blue", 1, 4, 3, 0)); 
    }

    public void setUpLevelThree(List<Square> squares, Element ele1, Element ele2, Element ele3, Element ele4,Element ele5) {
        stt.setElement(0, 0, ele1);
        stt.setElement(0, 1, ele2);
        stt.setElement(0, 2, ele2);
        stt.setElement(0, 3, ele2);
        stt.setElement(0, 4, ele1);

        stt.setElement(1, 0, ele2);
        stt.setElement(1, 1, ele2);
        stt.setElement(1, 2, ele1);
        stt.setElement(1, 3, ele2);
        stt.setElement(1, 4, ele2);

        stt.setElement(2, 0, ele2);
        stt.setElement(2, 1, ele2);
        stt.setElement(2, 2, ele1);
        stt.setElement(2, 3, ele1);
        stt.setElement(2, 4, ele2);

        stt.setElement(3, 0, ele3);
        stt.setElement(3, 1, ele2);
        stt.setElement(3, 2, ele2);
        stt.setElement(3, 3, ele1);
        stt.setElement(3, 4, ele4);

        stt.setElement(4, 0, ele1);
        stt.setElement(4, 1, ele2);
        stt.setElement(4, 2, ele2);
        stt.setElement(4, 3, ele1);
        stt.setElement(4, 4, ele1);

        squares.add(new Square("blue", 1, 4, 3, 0)); 
    }
}
