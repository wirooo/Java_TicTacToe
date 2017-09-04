import javax.swing.*;

public class GameValues {

    private static GameValues gameValue;
    private char[][] cells;
    private JLabel jlblstatus;
    private char turn;


    private GameValues(int rows, int cols){
        cells = new char[rows][cols];
        jlblstatus = new JLabel("X's turn");
        turn = 'X';
    }

    public static GameValues getInst(int rows, int cols){
        if(gameValue == null){
            gameValue = new GameValues(rows, cols);
        }
        return gameValue;
    }
    public static GameValues getInst(){
        return gameValue;
    }

    public char getCellValue(int x, int y) {
        return cells[x][y];
    }

    public void setCellValue(int x, int y, char c){
        cells[x][y] = c;
    }

    public JLabel getJlblstatus() {
        return jlblstatus;
    }

    public void setJlblstatus(String s) {
        jlblstatus.setText(s);
    }

    public char getTurn() {
        return turn;
    }

    public void setTurn(char turn) {
        this.turn = turn;
        setJlblstatus(turn + "'s turn");
    }

    public char[][] getBoard() {
        return cells;
    }

}
