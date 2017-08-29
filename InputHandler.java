import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputHandler extends MouseAdapter{

    private TicTacToeCell cell;
    private boolean emptyCell;

    public InputHandler(TicTacToeCell cell){
        this.cell = cell;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(GameValues.getInst().getCellValue(cell.xCoord(), cell.yCoord()) == '\u0000'){
            emptyCell = true;
        }else{
            emptyCell = false;
        }

        if(!GameValues.getInst().isGameOver()){
            if (GameValues.getInst().getCellValue(cell.xCoord(), cell.yCoord()) == '\u0000'){
                GameValues.getInst().setCellValue(cell.xCoord(), cell.yCoord(), GameValues.getInst().getTurn());
                cell.repaint();
            }

            if(GameValues.getInst().isGameWon()){
                GameValues.getInst().setJlblstatus("Game Over! " + GameValues.getInst().getTurn() + " won!");
                GameValues.getInst().setGameOver(true);
            }
            else if (GameValues.getInst().isGameFull()){
                GameValues.getInst().setJlblstatus("Tie Game!");
                GameValues.getInst().setGameOver(true);
            } else if (emptyCell) {
                if (GameValues.getInst().getTurn() == 'X' && !GameValues.getInst().isGameOver()) {
                    GameValues.getInst().setTurn('O');
                    GameValues.getInst().setJlblstatus("O's Turn");
                } else if (GameValues.getInst().getTurn() == 'O' && !GameValues.getInst().isGameOver()){
                    GameValues.getInst().setTurn('X');
                    GameValues.getInst().setJlblstatus("X's Turn");
                }
            }
        }


    }
}
