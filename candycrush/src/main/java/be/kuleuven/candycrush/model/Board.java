package be.kuleuven.candycrush.model;


import java.util.ArrayList;
import java.util.Iterator;

public class Board<T> extends ArrayList {
private final CandycrushModel.BoardSize boardSize;
private  ArrayList<T> board;



    public Board(CandycrushModel.BoardSize boardSize){
        if (boardSize.rows() <= 0 || boardSize.columns() <= 0) {
            throw new IllegalArgumentException("Invalid board size");
        }

        this.boardSize = boardSize;
        this.board = createBoard(boardSize.rows(),boardSize.columns());


    }

    private ArrayList<T> createBoard(int rows, int columns) {
        ArrayList<T> board =new ArrayList<>(rows*columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                board.add(null);
            }

        }



        return board;

    }

    public CandycrushModel.BoardSize getBoardSize() {
        return boardSize;
    }

    public T getCellAt(CandycrushModel.Position position) {
        if (position.row() < 0 || position.row() >= boardSize.rows()) throw new IllegalArgumentException("#row out of bounds");
        if (position.column() < 0|| position.column() >= boardSize.columns()) throw new IllegalArgumentException("#colums out of bounds");
        return board.get(position.toIndex());
    }

    public void setCellAt(CandycrushModel.Position position, T object){
        if (position.row() < 0 || position.row() >= boardSize.rows()) throw new IllegalArgumentException("#row out of bounds");
        if (position.column() < 0|| position.column() >= boardSize.columns()) throw new IllegalArgumentException("#colums out of bounds");
        board.set(position.toIndex(),object);


    }

    public void fill(CellCreator cellCreator) {
        for (CandycrushModel.Position position : boardSize.positions()) {
            board.set(position.toIndex(), (T) cellCreator.createCell());
        }

    }

    public void copyTo(Board otherBoard){
        if (!this.boardSize.equals(otherBoard.getBoardSize())) {
            throw new IllegalArgumentException("Board sizes do not match");
        }
        for (int i = 0; i < board.size(); i++) {
            otherBoard.board.set(i,this.board.get(i));
        }
    }

    public void setBoard(ArrayList<T> arrayList){
        this.board = arrayList;
    }


    public record Cell<T>(CandycrushModel.Position position,T t){

        }
    };



