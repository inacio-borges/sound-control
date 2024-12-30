package com.ibo.soundcontrol.model;

public class User {
    private String userName;
    private int option1;
    private int option2;
    private int option3;
    private int option4;
    private int option5;

    public User() {
        this.userName = "Default";
        this.option1 = 12;
        this.option2 = 12;
        this.option3 = 12;
        this.option4 = 12;
        this.option5 = 12;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOption4() {
        return option4;
    }

    public void setOption4(int option4) {
        this.option4 = option4;
    }

    public int getOption5() {
        return option5;
    }

    public void setOption5(int option5) {
        this.option5 = option5;
    }

    public int getOption1() {
        return option1;
    }

    public void setOption1(int option1) {
        this.option1 = option1;
    }

    public int getOption2() {
        return option2;
    }

    public void setOption2(int option2) {
        this.option2 = option2;
    }

    public int getOption3() {
        return option3;
    }

    public void setOption3(int option3) {
        this.option3 = option3;
    }
}
