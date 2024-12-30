package com.ibo.soundcontrol.dao;

public interface Persistence {
    public abstract int create();
    public abstract void read();
    public abstract void update();
    public abstract int delete();
}
