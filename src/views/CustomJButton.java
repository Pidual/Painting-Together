package views;

import javax.swing.*;
import java.awt.*;

public class CustomJButton extends JButton {

    public CustomJButton(ImageIcon icon, ImageIcon hoverIcon , ImageIcon pressedIcon){
        this.setForeground(Color.CYAN);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setContentAreaFilled(false);
        this.setBorder(null);


        this.setIcon(icon);
        this.setRolloverIcon(hoverIcon);
        this.setPressedIcon(pressedIcon);


        this.setBorderPainted(false);
    }

}
