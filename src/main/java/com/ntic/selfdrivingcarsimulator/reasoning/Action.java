package com.ntic.selfdrivingcarsimulator.reasoning;

public class Action {
    private String move;

    public Action(String move){
        this.move=move;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }
}
