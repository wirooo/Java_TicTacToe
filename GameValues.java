import javax.swing.*;

public class GameValues {

    private static GameValues gameValue;
    private char[][] cells;
    private JLabel jlblstatus;
    private char turn;
    private boolean gameOver;


    private GameValues(int rows, int cols){
        cells = new char[rows][cols];
        jlblstatus = new JLabel("X's turn");
        turn = 'X';
        gameOver = false;
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

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (GameValues.getInst().getCellValue(i, j) == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameWon() {
        //check rows
        for(int i=0; i<3; i++){
            if((GameValues.getInst().getCellValue(i, 0)==GameValues.getInst().getCellValue(i, 1)) && ((GameValues.getInst().getCellValue(i, 1))==GameValues.getInst().getCellValue(i, 2)) &&
                    (GameValues.getInst().getCellValue(i, 0) != '\u0000') && (GameValues.getInst().getCellValue(i, 1)!='\u0000')  && (GameValues.getInst().getCellValue(i, 2)!='\u0000')){
                return true;
            }
        }
        //check columns
        for(int j=0; j<3; j++){
            if((GameValues.getInst().getCellValue(0, j)==GameValues.getInst().getCellValue(1, j)) && (GameValues.getInst().getCellValue(1, j)==GameValues.getInst().getCellValue(2, j))
                    && (GameValues.getInst().getCellValue(0, j)!='\u0000') && (GameValues.getInst().getCellValue(1, j)!='\u0000') && (GameValues.getInst().getCellValue(2, j)!='\u0000')){
                return true;
            }
        }
        //check diagonals
        if((GameValues.getInst().getCellValue(0,0)==GameValues.getInst().getCellValue(1,1)) && (GameValues.getInst().getCellValue(1,1)==GameValues.getInst().getCellValue(2,2))
                && (GameValues.getInst().getCellValue(0, 0)!='\u0000') && (GameValues.getInst().getCellValue(1, 1)!='\u0000') && (GameValues.getInst().getCellValue(2, 2)!='\u0000')){
            return true;
        }
        if((GameValues.getInst().getCellValue(0, 2)==GameValues.getInst().getCellValue(1,1)) && (GameValues.getInst().getCellValue(1,1)==GameValues.getInst().getCellValue(2,0))
                && (GameValues.getInst().getCellValue(0, 2)!='\u0000') && (GameValues.getInst().getCellValue(1, 1)!='\u0000') && (GameValues.getInst().getCellValue(2, 0)!='\u0000')){
            return true;
        }
        return false;
    }

}
