package com.zlotran.happyhours.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class Record {

    private LocalDateTime date;
    private State state;
    private long epoch;

    public Record() {
    }

    public Record(final LocalDateTime date, final State state) {
        this.date = date;
        this.state = state;
        this.epoch = date.toEpochSecond(ZoneOffset.UTC);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
        this.epoch = date.toEpochSecond(ZoneOffset.UTC);
    }

    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;
    }

    public long getEpoch() {
        return epoch;
    }

    @Override public String toString() {
        return date + " " + state;
    }

    @Override public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Record record = (Record) object;
        return getEpoch() == record.getEpoch() &&
            Objects.equals(getDate(), record.getDate()) &&
            getState() == record.getState();
    }

    @Override public int hashCode() {
        return Objects.hash(getDate(), getState(), getEpoch());
    }
}
