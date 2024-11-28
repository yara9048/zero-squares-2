public class setting {
    state stt;

    public setting() {
        stt = new state();

        Element ele1 = new Element("black", "block", "not aim");
        Element ele2 = new Element("white", "road", "not aim");
        Element ele3 = new Element("white", "road", "aim");
        Element ele4 = new Element("blue", "square", "not aim");

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
        stt.setElement(4, 2, ele2);
        stt.setElement(4, 3, ele2);
        stt.setElement(4, 4, ele4);
    }
}
