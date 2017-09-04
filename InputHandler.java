import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputHandler extends MouseAdapter{

    private TicTacToeCell cell;

    public InputHandler(TicTacToeCell cell){
        this.cell = cell;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(!GameEvaluators.isGameOver(GameValues.getInst().getBoard())){
            //if game isn't full, repaint cell based on turn
            if (GameValues.getInst().getCellValue(cell.xCoord(), cell.yCoord()) == '\u0000'){
                GameValues.getInst().setCellValue(cell.xCoord(), cell.yCoord(), GameValues.getInst().getTurn());
                cell.repaint();
                //use minimax algorithm to calculate computer's move
                if(!GameEvaluators.isGameOver(GameValues.getInst().getBoard())){
                    CustomPair<Integer, Integer> computerMove = GameEvaluators.minimax(GameValues.getInst().getBoard(), 'O', 1, 0);
                    GameValues.getInst().setCellValue(computerMove.x, computerMove.y, 'O');
                    GameEvaluators.refreshBoard();
                    GameValues.getInst().setTurn('X');
                }

            }

            if(GameEvaluators.isGameWon(GameValues.getInst().getBoard(), 'X')){
                GameValues.getInst().setJlblstatus("Game Over! X won!");
            }else if (GameEvaluators.isGameWon(GameValues.getInst().getBoard(), 'O')){
                GameValues.getInst().setJlblstatus("Game Over! O won!");
            } else if (GameEvaluators.isGameFull(GameValues.getInst().getBoard())){
                GameValues.getInst().setJlblstatus("Tie Game!");
            }
        }



    }
}
