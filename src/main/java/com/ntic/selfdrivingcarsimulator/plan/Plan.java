package com.ntic.selfdrivingcarsimulator.plan;

public abstract class Plan {

    public boolean condition;

    public abstract void body();

    public void setCondition(boolean condition) {
        this.condition = condition;
        this.body();
        this.condition = false;
    }

}
