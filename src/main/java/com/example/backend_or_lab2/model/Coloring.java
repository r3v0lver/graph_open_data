package com.example.backend_or_lab2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coloring {

    private String graphId;
    private Integer[] colorAssignment;

    public Coloring(@JsonProperty("graphId") String graphId, @JsonProperty("colorAssignment") Integer[] colorAssignment) {
        this.graphId = graphId;
        this.colorAssignment = colorAssignment;
    }

    public String getGraphId() {
        return graphId;
    }

    public Integer[] getColorAssignment() {
        return colorAssignment;
    }
}
