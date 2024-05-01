package be.kuleuven.candycrush.model;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;



public class CandycrushModel {
    private String speler;
    private ArrayList<Candy> speelbord;


    private boolean initialized = false;

    private int score;

    private BoardSize boardSize;



    public CandycrushModel(String speler) {
        this.speler = speler;

    }

    public void initializeSpeelbord(){
        if (!initialized){
        speelbord = new ArrayList<>();
        boardSize = new BoardSize(4,4);
       // width = 4;
       // height = 4;
        score =0;

        for (int i = 0; i < boardSize.columns* boardSize.rows; i++){      //        for (int i = 0; i < width*height; i++){

            Candy newCandy = createNewCandy();


            speelbord.add(newCandy);
        }
        initialized = true;
        }
    }

    public static void main(String[] args) {
        CandycrushModel model = new CandycrushModel("arne");
        int i = 1;
        Iterator<Candy> iter = model.getSpeelbord().iterator();
        while(iter.hasNext()){
            Candy candy = iter.next();
            System.out.print(candy);
            if(i% model.boardSize.columns()==0){
                System.out.print("\n");
                i = 1;
            }
            i++;
        }
        System.out.print("\n");

    }
    public String getSpeler() {
        return speler;
    }

    public ArrayList<Candy> getSpeelbord() {
        return speelbord;
    }

    public void setSpeelbord(ArrayList<Candy> speelbord){
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


            Candy currentCandy = speelbord.get(index);
            Candy newCandy = createNewCandy();



            while (newCandy.equals(currentCandy)){

                 newCandy = createNewCandy();
            }
            speelbord.set(index,newCandy);
            score++;



            for ( Position neighbour : neighboursPositions ){
                int neighbourIndex = neighbour.toIndex();
                currentCandy = speelbord.get(neighbourIndex);
                 newCandy = createNewCandy();



                while (newCandy.equals(currentCandy)) {
                     newCandy = createNewCandy();
                }
                speelbord.set(neighbourIndex, newCandy);
                score++;
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

        public Iterable<Position> positions(){
            List<Position> positions = new ArrayList<>();

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

        public Iterable<Position> neighbourPositions(){
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

    }
    public Iterable<Position> sameNeighbourPositions(Iterable<Position> allNeighboursPositions,Position selectedPosition){
        ArrayList<Position> sameNeighbourPositions = new ArrayList<>();

        for (Position position :allNeighboursPositions) {
            int currentIndex = position.toIndex();
            if (speelbord.get(selectedPosition.toIndex()).equals(speelbord.get(currentIndex))) {
                sameNeighbourPositions.add(position);
            }
        }

        return  sameNeighbourPositions;
    }

    public Candy createNewCandy(){
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

}
