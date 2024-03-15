package be.kuleuven.candycrush.model;

import org.example.CheckNeighboursInGrid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;



public class CandycrushModel {
    private String speler;
    private ArrayList<Integer> speelbord;
    private int width;
    private int height;

    private boolean initialized = false;

    private int score;



    public CandycrushModel(String speler) {
        this.speler = speler;

    }

    public void initializeSpeelbord(){
        if (!initialized){
        speelbord = new ArrayList<>();
        width = 4;
        height = 4;
        score =0;

        for (int i = 0; i < width*height; i++){
            Random random = new Random();
            int randomGetal = random.nextInt(5) + 1;
            speelbord.add(randomGetal);
        }
        initialized = true;
        }
    }

    public static void main(String[] args) {
        CandycrushModel model = new CandycrushModel("arne");
        int i = 1;
        Iterator<Integer> iter = model.getSpeelbord().iterator();
        while(iter.hasNext()){
            int candy = iter.next();
            System.out.print(candy);
            if(i% model.getWidth()==0){
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

    public ArrayList<Integer> getSpeelbord() {
        return speelbord;
    }

    public void setSpeelbord(ArrayList<Integer> speelbord){
        this.speelbord = speelbord;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public void candyWithIndexSelected(int index){

        ArrayList<Integer> neighbours = (ArrayList<Integer>) CheckNeighboursInGrid.getSameNeighboursIds(getSpeelbord(),getWidth(),getHeight(),index);


        if (index >= 0 && index <= (width*height)-1){
            int currentValue = speelbord.get(index);
            Random random = new Random();
            int randomGetal = random.nextInt(5) + 1;
            while (randomGetal == currentValue){randomGetal = random.nextInt(5) + 1;}
            speelbord.set(index,randomGetal);
            score++;

            for ( Integer elem : neighbours ){
                currentValue = speelbord.get(elem);
                Random random1 = new Random();
                int randomGetal1 = random1.nextInt(5) + 1;
                while (randomGetal1 == currentValue){randomGetal1 = random.nextInt(5) + 1;}
                speelbord.set(elem,randomGetal1);
                score++;
            }



        }else{
            System.out.println("model:candyWithIndexSelected:indexWasOutOfBounds");
        }
    }

    public int getIndexFromRowColumn(int row, int column) {
        return column+row*width;
    }
}
