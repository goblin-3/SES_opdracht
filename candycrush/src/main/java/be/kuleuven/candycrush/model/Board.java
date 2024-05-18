package be.kuleuven.candycrush.model;


import java.util.*;

public class Board<T>  {
private final CandycrushModel.BoardSize boardSize;
private Map<CandycrushModel.Position,T> board;
private Map<T, List<CandycrushModel.Position>> reverseBoard;



    public Board(CandycrushModel.BoardSize boardSize){
        if (boardSize.rows() <= 0 || boardSize.columns() <= 0) {
            throw new IllegalArgumentException("Invalid board size");
        }

        this.boardSize = boardSize;
        this.board = createBoard(boardSize.rows(),boardSize.columns());
        this.reverseBoard = new HashMap<>();


    }

    private Map<CandycrushModel.Position,T> createBoard(int rows, int columns) {
        Map<CandycrushModel.Position,T> board =new HashMap<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                board.put(new CandycrushModel.Position(r,c,boardSize),null);
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
        return board.get(position);
    }

    public void setCellAt(CandycrushModel.Position position, T object){
        if (position.row() < 0 || position.row() >= boardSize.rows()) throw new IllegalArgumentException("#row out of bounds");
        if (position.column() < 0|| position.column() >= boardSize.columns()) throw new IllegalArgumentException("#colums out of bounds");
        T oldObject = board.put(position,object);

        if (oldObject != null) {
            reverseBoard.get(oldObject).remove(position);
            if (reverseBoard.get(oldObject).isEmpty()) {
                reverseBoard.remove(oldObject);
            }
        }

        reverseBoard.computeIfAbsent(object, k -> new ArrayList<>()).add(position);

    }

    public void fill(CellCreator cellCreator) {
        for (CandycrushModel.Position position : boardSize.positions()) {
            T cell = (T) cellCreator.createCell();
            board.put(position, cell);
            reverseBoard.computeIfAbsent(cell,k->new ArrayList<>()).add(position);
        }

    }

    public void copyTo(Board<T> otherBoard) {
        if (!this.boardSize.equals(otherBoard.getBoardSize())) {
            throw new IllegalArgumentException("Board sizes do not match");
        }
        otherBoard.clearBoard();

        for (Map.Entry<CandycrushModel.Position, T> entry : this.board.entrySet()) {
            CandycrushModel.Position position = entry.getKey();
            T cell = entry.getValue();
            otherBoard.board.put(position, cell);
            otherBoard.reverseBoard.computeIfAbsent(cell, k -> new ArrayList<>()).add(position);
        }
    }

    public void setBoard(Map<CandycrushModel.Position, T> newBoard) {
        clearBoard();

        for (Map.Entry<CandycrushModel.Position, T> entry : newBoard.entrySet()) {
            CandycrushModel.Position position = entry.getKey();
            T cell = entry.getValue();
            this.board.put(position, cell);
            this.reverseBoard.computeIfAbsent(cell, k -> new ArrayList<>()).add(position);
        }
    }

    private void clearBoard() {
        this.board.clear();
        this.reverseBoard.clear();
    }
    public List<CandycrushModel.Position> getPositionsOfElement(T element) {
        return Collections.unmodifiableList(reverseBoard.getOrDefault(element, Collections.emptyList()));
    }

    };



