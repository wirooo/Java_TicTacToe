import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Game {

    private JFrame frame;

    public static void main(String[] args) {
        new Game().run();
    }

    private void run(){
        frame = new JFrame("Improved Basic Tic Tac Toe");

        JPanel panel = new JPanel(new GridLayout(3, 3));

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                panel.add(new TicTacToeCell(i, j));
            }
        }

        panel.setBorder(new LineBorder(Color.BLACK, 1));
        GameValues.getInst(3, 3);
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(GameValues.getInst().getJlblstatus(), BorderLayout.SOUTH);
        frame.setVisible(true);

    }

}
