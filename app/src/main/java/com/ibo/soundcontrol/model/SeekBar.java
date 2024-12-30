package com.ibo.soundcontrol.model;

public class SeekBar {
    private int[] seekBarValues = new int[5];

    public int getSeekBarValue(int index) {
        return seekBarValues[index];
    }

    public void setSeekBarValue(int index, int value) {

        seekBarValues[index] = value;
    }
}