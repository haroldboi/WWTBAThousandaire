package com.example.quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private  Integer[] moneyLadder = {1,2,4,8,16,32,50,100,200,400,800,1600,3200,6400,10000};
    private String correctAnswer = "";
    private SceneController switcher = new SceneController();
    private ArrayList<Result> questions ;

    private Boolean stop = false ;
    private String correctId;
    private Integer questionCount = 0;

    @FXML Label currentWin;
    @FXML Label nextWin;
    @FXML Label secondWin;
    @FXML Button next;
    @FXML Label count ;
    @FXML Label message;
    @FXML Pane messagebox;
    @FXML Label question ;
    @FXML Label answerA;
    @FXML Label answerB;
    @FXML Label answerC;
    @FXML Label answerD;
    @FXML Polygon A;
    @FXML Polygon B;
    @FXML Polygon C;
    @FXML Polygon D;


    public GameController(){
        System.out.println("game started!");
    }

    public void questionM(int Category){
        Link link = new Link(Category);
        this.questions = link.makeObject();
        QNA();
    }

    private void win(){
        this.message.setText("Correct, You won "+currentWin.getText());
        this.message.setTextFill(Paint.valueOf("White"));
        this.messagebox.setOpacity(1);
        this.next.setDisable(false);
    }

    private void lose(){
        this.message.setText("Incorrect, You lost "+currentWin.getText());
        this.message.setTextFill(Paint.valueOf("Red"));
        this.messagebox.setOpacity(1);
        this.next.setOpacity(0);
    }
    public void click(MouseEvent Event){
        if(!this.stop){
      if(Event.getTarget().getClass().getSimpleName().equals("Polygon")){
          Polygon poly = (Polygon) Event.getTarget();
          this.stop = true;
          if(poly.getId().contains(correctId)){
                this.changeColour(poly.getId(),"green");
                this.win();
          }else{
              this.changeColour(poly.getId(), "red");
              this.changeColour(correctId,"green");
              this.lose();
          }
      }else if (Event.getTarget().getClass().getSimpleName().equals("LabeledText")) {
          this.stop = true;
          Text label = (Text) Event.getTarget();
          if (label.getText().equals(this.correctAnswer)) {
              this.changeColour(label.getParent().getId().substring(6), "green");
              this.win();
          } else {
              this.changeColour(label.getParent().getId().substring(6), "red");
              this.changeColour(correctId,"green");
              this.lose();
          }
      }
      }
    }

    private void changeColour(String Id,String colour){
        Polygon poly = new Polygon();
        switch (Id){
            case "A":
                poly = A;
                break;
            case "B":
                poly = B;
                break;
            case "C":
                poly = C;
                break;
            case "D":
                poly = D;
                break;
        }
        switch (colour){
            case "yellow":
                poly.setFill(new RadialGradient(
                        135.0, -0.1156, 0.5, 0.519, 0.8699, true, CycleMethod.NO_CYCLE,
                        new Stop(0.0, new Color(0.158, 0.1206, 0.8684, 1.0)),
                        new Stop(0.0067, new Color(0.9707, 1.0, 0.03, 1.0)),
                        new Stop(1.0, new Color(0.1787, 0.18, 0.1728, 1.0))));
                break;
            case "green":
               poly.setFill( new RadialGradient(
                       135.0, -0.1156, 0.5, 0.519, 0.8699, true, CycleMethod.NO_CYCLE,
                       new Stop(0.0, new Color(0.158, 0.1206, 0.8684, 1.0)),
                       new Stop(0.0067, new Color(0.0478, 0.7368, 0.0822, 1.0)),
                       new Stop(1.0, new Color(0.03, 0.03, 0.03, 1.0))));
               break;
            case "blue":
                poly.setFill(new RadialGradient(
                        135.0, -0.1156, 0.5, 0.519, 0.8699, true, CycleMethod.NO_CYCLE,
                        new Stop(0.0, new Color(0.158, 0.1206, 0.8684, 1.0)),
                        new Stop(0.0067, new Color(0.0588, 0.0275, 1.0, 1.0)),
                        new Stop(1.0, new Color(0.1787, 0.18, 0.1728, 1.0))));
                break;
            case "red":
                poly.setFill( new RadialGradient(
                        135.0, -0.1156, 0.5, 0.519, 0.8699, true, CycleMethod.NO_CYCLE,
                        new Stop(0.0, new Color(0.158, 0.1206, 0.8684, 1.0)),
                        new Stop(0.0067, new Color(1.0, 0.0667, 0.0, 1.0)),
                        new Stop(1.0, new Color(0.28, 0.2457, 0.2436, 1.0))));
                break;
        }

    }
    public void hover(MouseEvent Event){
        if(stop);
        else{
        if(Event.getTarget().getClass().getSimpleName().equals("Polygon")){
            Polygon poly = (Polygon) Event.getTarget();
            this.changeColour(poly.getId(),"yellow");
        }else if(Event.getTarget().getClass().getSimpleName().equals("Label")){
            Label label = (Label) Event.getTarget();
            this.changeColour(label.getId().substring(6),"yellow");
        }}
    }
    public void hoverleave(MouseEvent Event){
            if(stop);
            else{
        if(Event.getTarget().getClass().getSimpleName().equals("Polygon")){
            Polygon poly = (Polygon) Event.getTarget();
            this.changeColour(poly.getId(),"blue");
        }else if(Event.getTarget().getClass().getSimpleName().equals("Label")){
            Label label = (Label) Event.getTarget();
            this.changeColour(label.getId().substring(6),"blue");
        }}
    }

    private void reset(){
        this.messagebox.setOpacity(0);
        this.stop = false;
        this.changeColour("A","blue");
        this.changeColour("B","blue");
        this.changeColour("C","blue");
        this.changeColour("D","blue");
        this.questionCount++;
        this.count.setText(questionCount.toString());
        this.correctAnswer = StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).correct_answer);
        this.next.setDisable(true);
        question.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).question));
        System.out.println(moneyLadder[questionCount-1]);
        currentWin.setText("£"+(moneyLadder[questionCount-1]));
        nextWin.setText("£"+(moneyLadder[questionCount]));
        secondWin.setText("£"+(moneyLadder[questionCount+1]));
    }
    public void QNA(){
       this.reset();
       switch (new Random().nextInt(4)){
           case 0:
               this.correctId = "A";
               answerA.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).correct_answer));
               answerB.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(0)));
               answerC.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(2)));
               answerD.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(1)));
               break;
           case 1:
               this.correctId = "B";
               answerB.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).correct_answer));
               answerA.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(2)));
               answerC.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(1)));
               answerD.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(0)));
               break;
           case 2:
               this.correctId = "C";
               answerC.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).correct_answer));
               answerB.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(1)));
               answerD.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(2)));
               answerA.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(0)));
               break;
           case 3:
               this.correctId = "D";
               answerD.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).correct_answer));
               answerB.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(2)));
               answerC.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(0)));
               answerA.setText(StringEscapeUtils.unescapeHtml3(questions.get(questionCount-1).incorrect_answers.get(1)));
               break;
       }
    }

    public void cate(ActionEvent event) throws IOException {
        switcher.switchToHome(event);
    }
    public void menu(ActionEvent event) throws IOException {
        switcher.switchToMenu(event);
    }

}

