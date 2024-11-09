package com.example.backend_or_lab2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Graph {

    private String Id;
    private int vertexNumber;
    private Integer[][] adjMatrix;
    private boolean simpleGraph;
    private int chromaticNumber;
    private boolean isBipartite;
    private int edgeCount;
    private int connectedComponents;
    private float density;
    private int maxVertexDegree;
    private ArrayList<Coloring> colorings;

    public Graph(@JsonProperty("id") String id, @JsonProperty("vertexNumber") int vertexNumber, @JsonProperty("adjMatrix") Integer[][] adjMatrix,
                 @JsonProperty("simpleGraph") boolean simpleGraph, @JsonProperty("chromaticNumber") int chromaticNumber,
                 @JsonProperty("isBipartite") boolean isBipartite, @JsonProperty("edgeCount") int edgeCount, @JsonProperty("connectedComponents") int connectedComponents,
                 @JsonProperty("density") float density, @JsonProperty("maxVertexDegree")  int maxVertexDegree, @JsonProperty("colorings") ArrayList<Coloring> colorings) {
        Id = id;
        this.vertexNumber = vertexNumber;
        this.adjMatrix = adjMatrix;
        this.simpleGraph = simpleGraph;
        this.chromaticNumber = chromaticNumber;
        this.isBipartite = isBipartite;
        this.edgeCount = edgeCount;
        this.connectedComponents = connectedComponents;
        this.density = density;
        this.maxVertexDegree = maxVertexDegree;
        this.colorings = colorings;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    public void setVertexNumber(int vertexNumber) {
        this.vertexNumber = vertexNumber;
    }

    public Integer[][] getAdjMatrix() {
        return adjMatrix;
    }

    public void setAdjMatrix(Integer[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public boolean isSimpleGraph() {
        return simpleGraph;
    }

    public void setSimpleGraph(boolean simpleGraph) {
        this.simpleGraph = simpleGraph;
    }

    public int getChromaticNumber() {
        return chromaticNumber;
    }

    public void setChromaticNumber(int chromaticNumber) {
        this.chromaticNumber = chromaticNumber;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public void setBipartite(boolean bipartite) {
        isBipartite = bipartite;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void setEdgeCount(int edgeCount) {
        this.edgeCount = edgeCount;
    }

    public int getConnectedComponents() {
        return connectedComponents;
    }

    public void setConnectedComponents(int connectedComponents) {
        this.connectedComponents = connectedComponents;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public int getMaxVertexDegree() {
        return maxVertexDegree;
    }

    public void setMaxVertexDegree(int maxVertexDegree) {
        this.maxVertexDegree = maxVertexDegree;
    }

    public ArrayList<Coloring> getColorings() {
        return colorings;
    }

    public void setColorings(ArrayList<Coloring> colorings) {
        this.colorings = colorings;
    }
}
