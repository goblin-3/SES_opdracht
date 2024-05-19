package be.kuleuven.candycrush.model;



import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class CandycrushModel {
    private String speler;
    private Board<Candy> speelbord;


    private boolean initialized = false;

    private int score;

    private BoardSize boardSize;

    private static CellCreator candyCellCreator;





    public CandycrushModel(String speler) {
        this.speler = speler;

    }

    public void initializeSpeelbord(){
        if (!initialized){
    //    speelbord = new ArrayList<>();

        boardSize = new BoardSize(4,4);
        speelbord = new Board<Candy>(boardSize);
       // width = 4;
       // height = 4;
        score =0;


            candyCellCreator = CreateCandyCellCreator();

        speelbord.fill(candyCellCreator);

   /*     for (int i = 0; i < boardSize.columns* boardSize.rows; i++){      //        for (int i = 0; i < width*height; i++){

            Candy newCandy = createNewCandy();



            speelbord.add(newCandy);
        }*/
        initialized = true;
        }
    }

    public static void main(String[] args) {
        CandycrushModel model = new CandycrushModel("arne");
        int i = 1;

        candyCellCreator = model.CreateCandyCellCreator();


      /*  Iterator<Candy> iter = model.getSpeelbord().iterator();
        while(iter.hasNext()){
            Candy candy = iter.next();
            System.out.print(candy);
            if(i% model.boardSize.columns()==0){
                System.out.print("\n");
                i = 1;
            }
            i++;
        }
        System.out.print("\n");*/

    }
    public String getSpeler() {
        return speler;
    }

    public Board<Candy> getSpeelbord() {
        return speelbord;
    }

    public void setSpeelbord(Board<Candy> speelbord){

        this.speelbord = speelbord;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(BoardSize boardSize) {
        this.boardSize = boardSize;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isInitialized() {
        return initialized;
    }



    public void candyWithIndexSelected(Position position){


        int index = position.toIndex();

        if (index >= 0 && index <= (boardSize.columns* boardSize.rows)-1){

            Iterable<Position> neighboursPositions = position.neighbourPositions();
            neighboursPositions = sameNeighbourPositions(neighboursPositions , position);


            Candy currentCandy = speelbord.getCellAt(position);
            Candy newCandy = createNewCandy();



            while (newCandy.equals(currentCandy)){

                 newCandy = createNewCandy();
            }
            speelbord.setCellAt(position,newCandy);
            score++;



            for (Position neighbour : neighboursPositions) {
                if (((speelbord.getCellAt(neighbour)).getColor())==(currentCandy.getColor())) {
                    newCandy = createNewCandy();

                    while (newCandy.equals(currentCandy)&&newCandy.getColor() == currentCandy.getColor()) {
                        newCandy = createNewCandy();
                    }

                    speelbord.setCellAt(neighbour, newCandy);
                    score++;
                }
            }



        }else{
            System.out.println("model:candyWithIndexSelected:indexWasOutOfBounds");
        }
    }



    public record BoardSize(int rows ,  int columns ){




        public BoardSize {

            if (rows < 1) throw new IllegalArgumentException("#row can't be smaller then 1");
            if (columns < 1) throw new IllegalArgumentException("#colums can't be smaller then 1");
        }

        public Collection<Position> positions(){
            ArrayList<Position> positions = new ArrayList<>();

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    positions.add(new Position(r, c, this));
                }
            }

            return positions;
        }

    }
    public record Position(int row,int column,BoardSize boardSize){

        public Position{
            if (row < 0 || row >= boardSize.rows() || column < 0 || column >= boardSize.columns()) throw new IllegalArgumentException("Out of bounds index");
        }
        public int toIndex(){
            int index = row() * boardSize().columns() + column();
            return  index;
        }
        public static  Position fromIndex(int index,BoardSize size){
            if (index<0 || index>(size.rows()*size.columns())-1){
                throw new IllegalArgumentException("index out of bounds");
            }

            int row = index/ size.columns();
            int column = index % size.columns();

            return new Position(row,column,size);
        }

        public Collection<Position> neighbourPositions(){
            ArrayList<Position> neighbours = new ArrayList<>();

            for (int r = -1; r<=1;r++){
                for (int c =-1;c<=1;c++){
                    if (r==0 && c==0) continue;

                    int nRow = row+ r;
                    int nColumn = column+c;

                    if (nRow>=0 && nRow < boardSize().rows() && nColumn>=0 && nColumn < boardSize().columns()){
                        neighbours.add(new Position(nRow,nColumn,boardSize));
                    }
                }
            }



            return neighbours;
        }

        public boolean isLastColumn() {
            return column == boardSize().columns() - 1;
        }

        public Stream<Position> walkLeft() {
            return IntStream.rangeClosed(0, column)
                    .mapToObj(col -> new Position(row, column - col, boardSize));
        }
        public Stream<Position>walkRight(){
            return IntStream.rangeClosed(0,boardSize.columns() - column-1).mapToObj(col -> new Position(row,column + col,boardSize));
        }

        public Stream<Position> walkUp() {
            return IntStream.rangeClosed(0, row)
                    .mapToObj(r -> new Position(row - r, column, boardSize));
        }

        public Stream<Position> walkDown() {
            return IntStream.rangeClosed(0, boardSize.rows() - row - 1)
                    .mapToObj(r -> new Position(row + r, column, boardSize));
        }

    }
    public Collection<Position> sameNeighbourPositions(Iterable<Position> allNeighboursPositions,Position selectedPosition){
        ArrayList<Position> sameNeighbourPositions = new ArrayList<>();

        for (Position position :allNeighboursPositions) {
           // int currentIndex = position.toIndex();
            if (speelbord.getCellAt(selectedPosition).equals(speelbord.getCellAt(position))) {
                sameNeighbourPositions.add(position);
            }
        }

        return  sameNeighbourPositions;
    }

    public  Candy createNewCandy(){
        Random random1 = new Random();
        Random random2 = new Random();

        int randomGetal1 = random1.nextInt(4);
        int randomGetal2 = random2 .nextInt(100);
        Candy newCandy;

        if (randomGetal2<80) {
            newCandy = new Candy.NormalCandy(randomGetal1);
        } else if (randomGetal2 <90) {
            newCandy = new Candy.CaveCandy(0);
        } else if (randomGetal2<95) {
            newCandy = new Candy.ChocolatCandy(1);
        } else if (randomGetal2<99) {
            newCandy = new Candy.ExplosiveCandy(2);
        } else {
            newCandy = new Candy.NuclearCandy(3);
        }
        return  newCandy;
    }
    public CellCreator CreateCandyCellCreator(){

        candyCellCreator = new CellCreator<Candy>() {

            @Override
            public Candy createCell() {
                Random random1 = new Random();
                Random random2 = new Random();

                int randomGetal1 = random1.nextInt(4);
                int randomGetal2 = random2 .nextInt(100);
                Candy newCandy;

                if (randomGetal2<80) {
                    newCandy = new Candy.NormalCandy(randomGetal1);
                } else if (randomGetal2 <90) {
                    newCandy = new Candy.CaveCandy(0);
                } else if (randomGetal2<95) {
                    newCandy = new Candy.ChocolatCandy(1);
                } else if (randomGetal2<99) {
                    newCandy = new Candy.ExplosiveCandy(2);
                } else {
                    newCandy = new Candy.NuclearCandy(3);
                }



                return newCandy;
            }
        };
        return candyCellCreator;

    }

    public boolean firstTwoHaveCandy(Candy candy, Stream<Position> positions) {
        List<Position> positionList = positions.limit(2).collect(Collectors.toList());
        return positionList.size() == 2 &&
                speelbord.getCellAt(positionList.get(0)).equals(candy) &&
                speelbord.getCellAt(positionList.get(1)).equals(candy);
    }


    public Stream<Position> horizontalStartingPositions() {
        return boardSize.positions().stream()
                .filter(pos -> pos.column() < boardSize.columns() - 2)
                .filter(pos -> {
                    Candy currentCandy = speelbord.getCellAt(pos);
                    Candy leftCandy = pos.column() == 0 ? null : speelbord.getCellAt(new Position(pos.row(), pos.column() - 1, boardSize));
                    return currentCandy != null && (leftCandy == null || !currentCandy.equals(leftCandy));
                });
    }



    public Stream<Position> verticalStartingPositions() {
        return boardSize.positions().stream()
                .filter(pos -> pos.row() < boardSize.rows() - 2)
                .filter(pos -> {
                    Candy currentCandy = speelbord.getCellAt(pos);
                    Candy aboveCandy = pos.row() == 0 ? null : speelbord.getCellAt(new Position(pos.row() - 1, pos.column(), boardSize));
                    return currentCandy != null && (aboveCandy == null || !currentCandy.equals(aboveCandy));
                });
    }



    private List<Position> longestMatchToRight(Position start) {
        List<Position> match = new ArrayList<>();
        match.add(start);

        Candy startCandy = speelbord.getCellAt(start);
        if (startCandy == null) {
            return match;
        }

        for (int col = start.column() + 1; col < boardSize.columns(); col++) {
            Position nextPos = new Position(start.row(), col, boardSize);
            Candy nextCandy = speelbord.getCellAt(nextPos);
            if (nextCandy == null || !startCandy.equals(nextCandy)) {
                break;
            }
            match.add(nextPos);
        }

        return match;
    }



    public List<Position> longestMatchDown(Position pos) {
        Candy candy = speelbord.getCellAt(pos);
        return pos.walkDown()
                .takeWhile(p -> speelbord.getCellAt(p).equals(candy))
                .collect(Collectors.toList());
    }


    public Set<List<Position>> findAllMatches() {
        Set<List<Position>> matches = new HashSet<>();

        horizontalStartingPositions().forEach(pos -> {
            List<Position> match = longestMatchToRight(pos);
            if (match.size() >= 3) {
                matches.add(match);
            }
        });

        verticalStartingPositions().forEach(pos -> {
            List<Position> match = longestMatchDown(pos);
            if (match.size() >= 3) {
                matches.add(match);
            }
        });

        return matches;
    }

    public void clearMatch(List<Position> match){
        int  size = match.size();

        if (match.isEmpty()|| match == null){
            return;
        }


            Position position = match.get(size-1);
            speelbord.setCellAt(position,null);
            match.remove(size-1);

            clearMatch(match);



    }
    public void fallDownTo(Position pos){
        if(pos.row()==0){
            return;
        }

        Position positionAbove = new Position(pos.row()-1, pos.column(), boardSize);
        Candy candyAbove = speelbord.getCellAt(positionAbove);
        Candy candyPos = speelbord.getCellAt(pos);
        if(candyAbove!=null &&candyPos ==null){
            speelbord.setCellAt(pos,candyAbove);
            speelbord.setCellAt(positionAbove,null);
            fallDownTo(positionAbove);

        }
        if (candyAbove!=null){
            fallDownTo(positionAbove);
        }




    }

    public boolean updateBoard() {
        Set<List<Position>> matches = findAllMatches();
        score = matches.stream().mapToInt(List::size).sum();

        if (matches.isEmpty()) {
            return false;
        }

        for (List<Position> match : matches) {
            clearMatch(match);
            score=score+3;
        }

        for (int col = 0; col < boardSize.columns(); col++) {
            for (int row = boardSize.rows() - 1; row >= 0; row--) {
                Position pos = new Position(row, col, boardSize);
                fallDownTo(pos);
            }
        }


        boolean moreMatches = updateBoard();
        return true || moreMatches;
    }

    private static Candy characterToCandy(char c) {
        return switch(c) {
            case '.' -> null;
            case 'o' -> new Candy.NormalCandy(0);
            case '*' -> new Candy.NormalCandy(1);
            case '#' -> new Candy.NormalCandy(2);
            case '@' -> new Candy.NormalCandy(3);
            default -> throw new IllegalArgumentException("Unexpected value: " + c);
        };
    }

    private boolean areNeighbors(Position pos1, Position pos2) {
        int rowDiff = Math.abs(pos1.row() - pos2.row());
        int colDiff = Math.abs(pos1.column() - pos2.column());

        return (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1);
    }


    public int maximizeScore(Board<Candy> board, int currentScore) {
        int maxScore = currentScore;

        for (int row = 0; row < boardSize.rows(); row++) {
            for (int col = 0; col < boardSize.columns(); col++) {
                Position currentPos = new Position(row, col, boardSize);
                List<Position> potentialMoves = getPotentialMoves(currentPos);

                for (Position move : potentialMoves) {
                    if (isValidSwap(currentPos, move) && matchAfterSwitch(currentPos, move)) {
                        performSwap(currentPos, move);
                        int scoreFromMove = updateBoardAndCalculateScore();
                        int totalScore = maximizeScore(board, currentScore + scoreFromMove);
                        maxScore = Math.max(maxScore, totalScore);
                        undoSwap(currentPos, move);
                    }
                }
            }
        }

        return maxScore;
    }

    private List<Position> getPotentialMoves(Position pos) {
        List<Position> moves = new ArrayList<>();
        if (pos.row() > 0) moves.add(new Position(pos.row() - 1, pos.column(), boardSize)); // Up
        if (pos.row() < boardSize.rows() - 1) moves.add(new Position(pos.row() + 1, pos.column(), boardSize)); // Down
        if (pos.column() > 0) moves.add(new Position(pos.row(), pos.column() - 1, boardSize)); // Left
        if (pos.column() < boardSize.columns() - 1) moves.add(new Position(pos.row(), pos.column() + 1, boardSize)); // Right
        return moves;
}
    private boolean isValidSwap(Position pos1, Position pos2) {
        return areNeighbors(pos1, pos2) &&
                speelbord.getCellAt(pos1) != null &&
                speelbord.getCellAt(pos2) != null;
    }

    private boolean matchAfterSwitch(Position pos1, Position pos2) {
        performSwap(pos1, pos2);
        Set<List<Position>> matches = findAllMatches();
        performSwap(pos1, pos2);  // Undo the swap
        return !matches.isEmpty();
    }

    private void performSwap(Position pos1, Position pos2) {
        Candy temp = speelbord.getCellAt(pos1);
        speelbord.setCellAt(pos1, speelbord.getCellAt(pos2));
        speelbord.setCellAt(pos2, temp);
    }
    private void undoSwap(Position pos1, Position pos2) {
        performSwap(pos1, pos2);
    }

    public  void fillBoardFromString(String configuration) {
        var lines = configuration.toLowerCase().lines().toList();
        for (int row = 0; row < lines.size(); row++) {
            var line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                speelbord.setCellAt(new Position(row, col, boardSize), characterToCandy(line.charAt(col)));
            }
        }

    }

    private int updateBoardAndCalculateScore() {
        int totalScore = 0;

        while (true) {
            Set<List<Position>> matches = findAllMatches();
            if (matches.isEmpty()) {
                break;
            }

            int scoreFromMatches = matches.stream().mapToInt(List::size).sum();
            totalScore += scoreFromMatches;

            for (List<Position> match : matches) {
                clearMatch(match);
            }

            for (int col = 0; col < boardSize.columns(); col++) {
                for (int row = boardSize.rows() - 1; row >= 0; row--) {
                    Position pos = new Position(row, col, boardSize);
                    fallDownTo(pos);
                }
            }
        }

        return totalScore;
    }

}