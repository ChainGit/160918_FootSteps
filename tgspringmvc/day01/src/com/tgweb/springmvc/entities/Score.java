package com.tgweb.springmvc.entities;

public class Score {

    private int chinese;
    private int math;
    private int english;

    @Override
    public String toString() {
        return "Address{" +
                "chinese=" + chinese +
                ", math=" + math +
                ", english=" + english +
                '}';
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public Score() {

    }

    public Score(int chinese, int math, int english) {

        this.chinese = chinese;
        this.math = math;
        this.english = english;
    }
}
