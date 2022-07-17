package presenter;

import views.Views;

import java.awt.event.*;

public class Presenter implements ActionListener, MouseListener {

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
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        view.changeColor();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

