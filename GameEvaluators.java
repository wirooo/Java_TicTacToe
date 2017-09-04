import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GameEvaluators {

    public static void printBoard(char[][] board){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j] != '\u0000') System.out.print(board[i][j]);
                else System.out.print('-');
                System.out.print(i + "," + j);
            }
            System.out.println();
        }
    }

    public static boolean isGameFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isGameWon(char[][] board, char turn) {
        //check rows
        for(int i=0; i<3; i++){
            if((board[i][0] == turn) && (board[i][1] == turn) && (board[i][2] == turn) && (board[i][0] != '\u0000') && (board[i][1] != '\u0000')  && (board[i][2] != '\u0000')){
                return true;
            }
        }
        //check columns
        for(int j=0; j<3; j++){
            if((board[0][j] == turn) && (board [1][j] ==turn) && (board[2][j] == turn) && (board[0][j] != '\u0000') && (board[1][j] != '\u0000')  && (board[2][j] != '\u0000')){
                return true;
            }
        }
        //check diagonals
        if((board[0][0] == turn) && (board[1][1] == turn) && (board[2][2] == turn) && (board[0][0] != '\u0000') && (board[1][1] != '\u0000')  && (board[2][2] != '\u0000')){
            return true;
        }
        if((board[0][2]==turn) && (board[1][1]==turn) && (board[2][0]==turn) && (board[0][2] != '\u0000') && (board[1][1] != '\u0000')  && (board[2][0] != '\u0000')){
            return true;
        }
        return false;
    }

    public static boolean isGameWon(char[][] board) {
        //check rows
        for(int i=0; i<3; i++){
            if((board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) && (board[i][0] != '\u0000') && (board[i][1]!= '\u0000')  && (board[i][2]!= '\u0000')){
                return true;
            }
        }
        //check columns
        for(int j=0; j<3; j++){
            if((board[0][j] == board[1][j]) && (board[1][j] == board[2][j]) && (board[0][j] != '\u0000') && (board[1][j]!= '\u0000')  && (board[2][j]!= '\u0000')){
                return true;
            }
        }
        //check diagonals
        if((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && (board[0][0] != '\u0000') && (board[1][1] != '\u0000')  && (board[2][2] != '\u0000')){
            return true;
        }
        if((board[0][2] == board[1][1]) && (board[1][1] == board[2][0]) && (board[0][2] != '\u0000') && (board[1][1] != '\u0000')  && (board[2][0] != '\u0000')){
            return true;
        }
        return false;
    }

    public static boolean isGameOver(char[][] board){
        return isGameWon(board) || isGameFull(board);
    }

    public static LinkedList<CustomPair<Integer, Integer>> findPossibleMoves(char[][] board) {
        LinkedList<CustomPair<Integer, Integer>> moves = new LinkedList<>();

        for(int i=0; i<3; i++){
            for(int j=0 ; j<3; j++){
                if(board[i][j] == '\u0000'){
                    moves.add(new CustomPair<>(i, j));
                }
            }
        }
    return moves;
    }

    public static int evaluate(char[][] board, int depth){

        if(isGameWon(board, 'X')){
            return 100 - depth;
        }else if(isGameWon(board, 'O')){
            return depth - 100;
        }else{
            return 0;
        }

    }

    public static char x2o(char turn){
        if(turn == 'X'){
            return 'O';
        }else if(turn == 'O'){
            return 'X';
        }
        return 'e';
    }

    public static char[][] addToBoard(char[][] board, CustomPair<Integer, Integer> coordinate, char turn){
        if(board[coordinate.x][coordinate.y] == '\u0000'){
            board[coordinate.x][coordinate.y] = turn;
        }
        return board;
    }

    public static void refreshBoard(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                Game.cells[i][j].repaint();
            }
        }
    }

    public static char[][] deepCopyBoard(char[][] board){
        final char[][] copiedBoard = new char[board.length][board.length];
        for(int i=0; i<board.length; i++){
            copiedBoard[i] = Arrays.copyOf(board[i], board.length);
        }
        return copiedBoard;
    }

    public static CustomPair<Integer, Integer> minimax(char[][] board, char turn, int justcalled, int depth){
        if(isGameOver(board)){
            return new CustomPair<Integer, Integer>(evaluate(board, depth), 0);
        }
        depth++;
        ArrayList<Integer> scores = new ArrayList<>();
        ArrayList<CustomPair<Integer, Integer>> moves = new ArrayList<>();
        for(CustomPair<Integer, Integer> coordinate : findPossibleMoves(board)){
            if(justcalled == 1){
                scores.add(minimax(addToBoard(deepCopyBoard(board), coordinate, turn), turn, 0, depth).x);
                moves.add(coordinate);
            }else{
                scores.add(minimax(addToBoard(deepCopyBoard(board), coordinate, x2o(turn)), x2o(turn), 0, depth).x);
                moves.add(coordinate);
            }
        }
        //maximizing player
        if(turn == 'X') {
            int bestMoveIndex = 0;
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(bestMoveIndex) < scores.get(i)) {
                    bestMoveIndex = i;
                }
            }
            if (justcalled == 1) {
                return moves.get(bestMoveIndex);
            }
            return new CustomPair<>(scores.get(bestMoveIndex), 0);
        }else{
            int bestMoveIndex = 0;
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(bestMoveIndex) > scores.get(i)) {
                    bestMoveIndex = i;
                }
            }
            if (justcalled == 1) {
                return moves.get(bestMoveIndex);
            }
            return new CustomPair<>(scores.get(bestMoveIndex), 0);
        }

    }

}
