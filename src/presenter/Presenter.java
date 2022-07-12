package presenter;

import views.Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Presenter implements ActionListener, FocusListener {

    private Views view;

    public Presenter() {
        view = new Views(this,this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "DRAWING_SHAPE_TOOL_CIRCLE":
                view.changeDrawingTool(0);
                break;
            case "DRAWING_SHAPE_TOOL_SQUARE":
                view.changeDrawingTool(1);
                break;
            case "DRAWING_SHAPE_TOOL_TRIANGLE":
                view.changeDrawingTool(2);
                break;
            case "DRAWING_SHAPE_TOOL_DIAMOND":
                view.changeDrawingTool(3);
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        System.out.println("FOCUS ACTIVATION");
        }

    @Override
    public void focusLost(FocusEvent e) {
        System.out.println("FOCUS LOST");
    }
}

