package omegaRogue;

import javafx.event.ActionEvent;
import javafx.event.Event;

public class Trigger {
    public double x;
    public double y;
    public double x2;
    public double y2;
    public Trigger(double x, double y, double x2, double y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }
    public int hit() {
        return 1;
    }

}
