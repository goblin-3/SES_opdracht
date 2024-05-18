package be.kuleuven.candycrush.view;

import be.kuleuven.candycrush.CandycrushController;
import be.kuleuven.candycrush.model.Candy;
import be.kuleuven.candycrush.model.CandycrushModel;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class CandycrushView extends Region {
    private CandycrushModel model;
    private int widthCandy;
    private int heigthCandy;

    private Label scoreLabel;

    public CandycrushView(CandycrushModel model) {
        this.model = model;
        widthCandy = 30;
        heigthCandy = 30;

        update();
    }

    public void update(){
        getChildren().clear();


        for(int r = 0 ; r< model.getBoardSize().rows();r++){
            for(int c = 0 ; c< model.getBoardSize().columns();c++){
                CandycrushModel.Position position = new CandycrushModel.Position(r,c,model.getBoardSize());
                Candy candy = model.getSpeelbord().getCellAt(position);
                Rectangle rectangle = new Rectangle(c* widthCandy,r* heigthCandy,widthCandy, heigthCandy);
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                Node candyShape = makeCandyShape(position,candy);


                getChildren().addAll(rectangle,candyShape);


                }
            }
        }





    public CandycrushModel.Position getPositionOfClicked(MouseEvent me){

        int row = (int) me.getY()/heigthCandy;
        int column = (int) me.getX()/widthCandy;

        return new CandycrushModel.Position(row,column,model.getBoardSize());
    }
    Node makeCandyShape(CandycrushModel.Position position,Candy candy){
         switch (candy) {
             case Candy.NormalCandy normalCandy :
                  return   new Circle((position.column() * widthCandy)+(widthCandy/2) , (position.row() * heigthCandy)+(heigthCandy/2), 10, colorSelector(normalCandy.color()));
             case Candy.CaveCandy caveCandy:
                    Rectangle rectangle = new Rectangle((position.column() * widthCandy)+(widthCandy/4),(position.row() * heigthCandy)+(heigthCandy/4),widthCandy/2,heigthCandy/2);
                    rectangle.setFill(colorSelector(caveCandy.color()));
                    return rectangle;

             case Candy.ChocolatCandy chocolatCandy :
                    rectangle = new Rectangle((position.column() * widthCandy)+(widthCandy/4), (position.row() * heigthCandy)+(heigthCandy/4), widthCandy/2,heigthCandy/2);
                    rectangle.setFill(colorSelector(chocolatCandy.color()));
                    return rectangle;

             case Candy.ExplosiveCandy explosiveCandy :
                    rectangle= new Rectangle((position.column() * widthCandy)+(widthCandy/4), (position.row() * heigthCandy)+(heigthCandy/4),widthCandy/2,heigthCandy/2 );
                    rectangle.setFill(colorSelector(explosiveCandy.color()));
                    return rectangle;
             case Candy.NuclearCandy nuclearCandy :
                    rectangle= new Rectangle((position.column() * widthCandy)+(widthCandy/4), (position.row() * heigthCandy)+(heigthCandy/4),widthCandy/2,heigthCandy/2 );
                    rectangle.setFill(colorSelector(nuclearCandy.color()));
                    return rectangle;
             default : throw new IllegalStateException("Unexpected value");
        }

    }
    Color colorSelector(int index){
        return switch (index) {
            case 0 -> Color.RED;
            case 1 -> Color.BLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.BLACK;
            default -> throw new IllegalArgumentException("incorrect color value");
        };



    }


}
