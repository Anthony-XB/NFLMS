package com.CEN30241.nflms.Repositories;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * The Stats class represents various statistical metrics for an NFL player,
 * including passing, rushing, receiving, and kicking statistics.
 * It provides methods to initialize, serialize, and deserialize statistics.
 */
@Getter
@Setter
public class Stats {
    @JsonProperty
    private double PassingAttempts;
    @JsonProperty
    private double PassingCompletions;
    @JsonProperty
    private double PassingYards;
    @JsonProperty
    private double PassingTouchdowns;
    @JsonProperty
    private double Interceptions;
    @JsonProperty
    private double RushingAttempts;
    @JsonProperty
    private double RushingYards;
    @JsonProperty
    private double RushingTouchdowns;
    @JsonProperty
    private double Receptions;
    @JsonProperty
    private double ReceivingYards;
    @JsonProperty
    private double ReceivingTouchdowns;
    @JsonProperty
    private double FieldGoalsAttempted;
    @JsonProperty
    private double FieldGoalsMade;
    @JsonProperty
    private double ExtraPointsMade;
    @JsonProperty
    private double FieldGoalPercentage;

    /**
     * Default constructor that initializes all statistics to zero.
     */
    public Stats() {
        this.PassingAttempts = 0;
        this.PassingCompletions = 0;
        this.PassingYards = 0;
        this.PassingTouchdowns = 0;
        this.Interceptions = 0;
        this.RushingAttempts = 0;
        this.RushingYards = 0;
        this.RushingTouchdowns = 0;
        this.Receptions = 0;
        this.ReceivingYards = 0;
        this.ReceivingTouchdowns = 0;
        this.FieldGoalsAttempted = 0;
        this.FieldGoalsMade = 0;
        this.ExtraPointsMade = 0;
        this.FieldGoalPercentage = 0;
    }

    /**
     * Constructs a Stats object with the specified values for each statistic.
     *
     * @param passingAttempts       the number of passing attempts.
     * @param passingCompletions    the number of passing completions.
     * @param passingYards          the total passing yards.
     * @param passingTouchdowns     the number of passing touchdowns.
     * @param interceptions         the number of interceptions.
     * @param rushingAttempts       the number of rushing attempts.
     * @param rushingYards          the total rushing yards.
     * @param rushingTouchdowns     the number of rushing touchdowns.
     * @param receptions            the number of receptions.
     * @param receivingYards        the total receiving yards.
     * @param receivingTouchdowns   the number of receiving touchdowns.
     * @param fieldGoalsAttempted   the number of field goals attempted.
     * @param fieldGoalsMade        the number of field goals made.
     * @param extraPointsMade       the number of extra points made.
     * @param fieldGoalPercentage   the field goal success percentage.
     */
    public Stats(double passingAttempts, double passingCompletions, double passingYards, double passingTouchdowns,
                 double interceptions, double rushingAttempts, double rushingYards, double rushingTouchdowns,
                 double receptions, double receivingYards, double receivingTouchdowns,
                 double fieldGoalsAttempted, double fieldGoalsMade, double extraPointsMade, double fieldGoalPercentage) {

        this.PassingAttempts = passingAttempts;
        this.PassingCompletions = passingCompletions;
        this.PassingYards = passingYards;
        this.PassingTouchdowns = passingTouchdowns;
        this.Interceptions = interceptions;
        this.RushingAttempts = rushingAttempts;
        this.RushingYards = rushingYards;
        this.RushingTouchdowns = rushingTouchdowns;
        this.Receptions = receptions;
        this.ReceivingYards = receivingYards;
        this.ReceivingTouchdowns = receivingTouchdowns;
        this.FieldGoalsAttempted = fieldGoalsAttempted;
        this.FieldGoalsMade = fieldGoalsMade;
        this.ExtraPointsMade = extraPointsMade;
        this.FieldGoalPercentage = fieldGoalPercentage;
    }

    /**
     * Deserializes a JSON string into a Stats object.
     *
     * @param json the JSON string representing a Stats object.
     * @return a Stats object if parsing is successful; {@code null} otherwise.
     */
    public static Stats fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println("Parsing JSON: " + json);
            return objectMapper.readValue(json, Stats.class);
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Serializes the Stats object to a JSON string.
     *
     * @return a JSON representation of the Stats object, or an empty JSON object ({}) if serialization fails.
     */
    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
}



