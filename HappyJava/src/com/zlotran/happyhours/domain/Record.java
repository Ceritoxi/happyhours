package com.zlotran.happyhours.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Record {

    private LocalDateTime date;
    private State state;
    private long epoch;

    public Record() {
    }

    public Record(LocalDateTime date, State state) {
        this.date = date;
        this.state = state;
        this.epoch = date.toEpochSecond(ZoneOffset.UTC);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
        this.epoch = date.toEpochSecond(ZoneOffset.UTC);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getEpoch() {
        return epoch;
    }

    @Override public String toString() {
        return date + " " + state;
    }
}
