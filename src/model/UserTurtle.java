package model;

import javafx.scene.paint.Color;

/**
 * Created by DavidTran on 10/23/17.
 */
public interface UserTurtle {

    public void setPenColor(int index);

    public void translate(double dx, double dy);

    public void rotate(double dtheta);
}
