package com.CEN30241.nflms.Repositories;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stats {

    private double passingAttempts;
    private double passingCompletions;
    private double passingYards;
    private double passingTouchdowns;
    private double interceptions;
    private double rushingAttempts;
    private double rushingYards;
    private double rushingTouchdowns;
    private double receptions;
    private double receivingYards;
    private double receivingTouchdowns;
    private double fieldGoalsAttempted;
    private double fieldGoalsMade;
    private double extraPointsMade;
    private double fieldGoalPercentage;


    public Stats() {
        this.passingAttempts = 0;
        this.passingCompletions = 0;
        this.passingYards = 0;
        this.passingTouchdowns = 0;
        this.interceptions = 0;
        this.rushingAttempts = 0;
        this.rushingYards = 0;
        this.rushingTouchdowns = 0;
        this.receptions = 0;
        this.receivingYards = 0;
        this.receivingTouchdowns = 0;
        this.fieldGoalsAttempted = 0;
        this.fieldGoalsMade = 0;
        this.extraPointsMade = 0;
        this.fieldGoalPercentage = 0;
    }


    public Stats(double passingAttempts, double passingCompletions, double passingYards, double passingTouchdowns,
                 double interceptions, double rushingAttempts, double rushingYards, double rushingTouchdowns,
                 double receptions, double receivingYards, double receivingTouchdowns,
                 double fieldGoalsAttempted, double fieldGoalsMade, double extraPointsMade, double fieldGoalPercentage) {

        this.passingAttempts = passingAttempts;
        this.passingCompletions = passingCompletions;
        this.passingYards = passingYards;
        this.passingTouchdowns = passingTouchdowns;
        this.interceptions = interceptions;
        this.rushingAttempts = rushingAttempts;
        this.rushingYards = rushingYards;
        this.rushingTouchdowns = rushingTouchdowns;
        this.receptions = receptions;
        this.receivingYards = receivingYards;
        this.receivingTouchdowns = receivingTouchdowns;
        this.fieldGoalsAttempted = fieldGoalsAttempted;
        this.fieldGoalsMade = fieldGoalsMade;
        this.extraPointsMade = extraPointsMade;
        this.fieldGoalPercentage = fieldGoalPercentage;
    }
}



