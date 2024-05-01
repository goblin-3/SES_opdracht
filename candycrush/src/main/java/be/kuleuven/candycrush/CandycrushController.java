package be.kuleuven.candycrush;

import java.net.URL;
import java.util.ResourceBundle;

import be.kuleuven.candycrush.model.CandycrushModel;
import be.kuleuven.candycrush.view.CandycrushView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CandycrushController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label;

    @FXML
    private Button btn;

    @FXML
    private AnchorPane paneel;

    @FXML
    private AnchorPane speelbord;

    @FXML
    private TextField textInput;

    @FXML
    private Button btn1;

    @FXML
    private Label Label1;

    @FXML
    private   Label ScoreLabel;
    private CandycrushModel model;
    private CandycrushView view;

    private String playerName;
    @FXML
    void initialize() {
        assert Label != null : "fx:id=\"Label\" was not injected: check your FXML file 'candycrush-view.fxml'.";
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'candycrush-view.fxml'.";
        assert paneel != null : "fx:id=\"paneel\" was not injected: check your FXML file 'candycrush-view.fxml'.";
        assert speelbord != null : "fx:id=\"speelbord\" was not injected: check your FXML file 'candycrush-view.fxml'.";
        assert textInput != null : "fx:id=\"textInput\" was not injected: check your FXML file 'candycrush-view.fxml'.";
        assert  ScoreLabel != null : "fx:id=\"Label\" was not injected: check your FXML file 'candycrush-view.fxml'.";

        btn.setOnAction(event -> onStartBtnClicked());
        btn1.setOnAction(event -> onResetBtnClicked());

    }

    public void update(){
        if(view!=null) {
            view.update();
            ScoreLabel.setText(String.valueOf(model.getScore()));
        }
    }

    public void onCandyClicked(MouseEvent me){
        CandycrushModel.Position candyPosition = view.getPositionOfClicked(me);
        model.candyWithIndexSelected(candyPosition);
        update();
    }

    public void onStartBtnClicked(){
         playerName = textInput.getText();

        if (!playerName.isEmpty()){
            model = new CandycrushModel(playerName);
            model.initializeSpeelbord();
            view = new CandycrushView(model);
            speelbord.getChildren().add(view);
            view.setOnMouseClicked(this::onCandyClicked);

            textInput.setVisible(false);
            Label.setText("welcome " + playerName);
            btn.setVisible(false);
            btn1.setVisible(true);
            Label1.setVisible(true);
            ScoreLabel.setVisible(true);

        }

    }

    public void onResetBtnClicked(){
        speelbord.getChildren().clear();

        model = new CandycrushModel(playerName);
        model.initializeSpeelbord();
        view = new CandycrushView(model);
        speelbord.getChildren().add(view);
        view.setOnMouseClicked(this::onCandyClicked);
        model.setScore(0);
        ScoreLabel.setText(String.valueOf(model.getScore()));
    }



}
