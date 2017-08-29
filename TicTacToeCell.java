import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TicTacToeCell extends JPanel{

    private int x, y;

    public TicTacToeCell(int x, int y){

        this.x = x;
        this.y = y;
        setBorder(new LineBorder(Color.BLACK, 1));
        setBackground(Color.WHITE);
        addMouseListener(new InputHandler(this));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(GameValues.getInst().getCellValue(x, y) == 'X'){
            g.drawLine(10, 10, getWidth()-10, getHeight()-10);
            g.drawLine(10, getHeight()-10, getWidth()-10, 10);
        }else if(GameValues.getInst().getCellValue(x, y) == 'O'){
            g.drawOval(10, 10, getWidth()-20, getHeight()-20);
        }

    }

    public int xCoord(){
        return x;
    }
    public int yCoord(){
        return y;
    }


    /*@Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }*/
}
