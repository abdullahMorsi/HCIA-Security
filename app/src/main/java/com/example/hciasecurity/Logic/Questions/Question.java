package com.example.hciasecurity.Logic.Questions;

import androidx.annotation.Nullable;

import com.example.hciasecurity.Logic.GUIHandlers.QustionGUI;

import java.io.Serializable;
import java.util.LinkedList;

public abstract class Question<T,D>implements Serializable{
    public enum MyType{
        SINGLE_CHOICE,
        True_And_False,
        MULTIPLE_CHOICE
    }
    private String img_url;
    protected MyType myType;
    private int points;
    private String question;
    protected T correct_answer;
    protected D wrong_answer;
    private QustionGUI<T> qustionGui;

    public Question( String question, T correct_answer, D wrong_answer) {
        this.question = question;
        this.correct_answer = correct_answer;
        this.wrong_answer = wrong_answer;
    }

    public Question() {

    }

    // firebase getter and setters

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public MyType getMyType() {
        return myType;
    }

    public void setMyType(MyType myType) {
        this.myType = myType;
    }
      public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    //end of firbase getters and setters

    public T mgetCorrect_answer() {
        return correct_answer;
    }

    public void msetCorrect_answer(T correct_answer) {
        this.correct_answer = correct_answer;
    }

    public D mgetWrong_answer() {
        return wrong_answer;
    }
    public void msetWrong_answer(D wrong_answer) {
        this.wrong_answer = wrong_answer;
    }
    public QustionGUI mgetQustionGui() {
        return qustionGui;
    }

    public void attachQuestionGUI(QustionGUI qustionGui) {
        this.qustionGui = qustionGui;
    }



    public int mgetDesevedPoints(){
      T t=  qustionGui.getUserAnswer();
      if (isCorrect(t)){
          return points;
      }else {
          return 0;
      }
    };

    public abstract boolean isCorrect(T t);
    public abstract LinkedList<String> mgetAll_answers();

    @Override
    public String toString() {
        return "Question{" +
                "img_url='" + img_url + '\'' +
                ", myType=" + myType +
                ", points=" + points +
                ", question='" + question + '\'' +
                ", correct_answer=" + correct_answer +
                ", wrong_answer=" + wrong_answer +
                '}';
    }
}
