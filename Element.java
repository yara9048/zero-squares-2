import java.util.Objects;

public class Element {
        public String color;
        public String shape;
        public String aim;
    
        public Element(String color, String shape, String aim) {
            this.color = color;
            this.shape = shape;
            this.aim = aim;
        }

    public String getColor() {
        return color;
    }

    public String getShape() {
        return shape;
    }

    public String getAim() {
        return aim;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Element other = (Element) obj;
        return color.equals(other.color) &&
                shape.equals(other.shape) &&
                aim.equals(other.aim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, shape, aim);
    }
}
