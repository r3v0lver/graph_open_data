package com.example.backend_or_lab2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchFilter {
    private String search;
    private boolean searchId;
    private boolean searchVertexNumber;
    private boolean searchAdjMatrix;
    private boolean searchSimpleGraph;
    private boolean searchChromaticNumber;
    private boolean searchIsBipartite;
    private boolean searchEdgeCount;
    private boolean searchConnectedComponents;
    private boolean searchDensity;
    private boolean searchMaxVertexDegree;

    public SearchFilter(@JsonProperty("search") String search, @JsonProperty("searchId") boolean searchId, @JsonProperty("searchVertexNumber")  boolean searchVertexNumber,
                        @JsonProperty("searchAdjMatrix") boolean searchAdjMatrix, @JsonProperty("searchSimpleGraph") boolean searchSimpleGraph,
                        @JsonProperty("searchChromaticNumber") boolean searchChromaticNumber, @JsonProperty("searchIsBipartite") boolean searchIsBipartite,
                        @JsonProperty("searchEdgeCount") boolean searchEdgeCount, @JsonProperty("searchConnectedComponents") boolean searchConnectedComponents,
                        @JsonProperty("searchDensity") boolean searchDensity, @JsonProperty("searchMaxVertexDegree") boolean searchMaxVertexDegree) {
        this.search = search;
        this.searchId = searchId;
        this.searchVertexNumber = searchVertexNumber;
        this.searchAdjMatrix = searchAdjMatrix;
        this.searchSimpleGraph = searchSimpleGraph;
        this.searchChromaticNumber = searchChromaticNumber;
        this.searchIsBipartite = searchIsBipartite;
        this.searchEdgeCount = searchEdgeCount;
        this.searchConnectedComponents = searchConnectedComponents;
        this.searchDensity = searchDensity;
        this.searchMaxVertexDegree = searchMaxVertexDegree;
    }

    public String getSearch() {
        return search;
    }

    public boolean isSearchId() {
        return searchId;
    }

    public boolean isSearchVertexNumber() {
        return searchVertexNumber;
    }

    public boolean isSearchAdjMatrix() {
        return searchAdjMatrix;
    }

    public boolean isSearchSimpleGraph() {
        return searchSimpleGraph;
    }

    public boolean isSearchChromaticNumber() {
        return searchChromaticNumber;
    }

    public boolean isSearchIsBipartite() {
        return searchIsBipartite;
    }

    public boolean isSearchEdgeCount() {
        return searchEdgeCount;
    }

    public boolean isSearchConnectedComponents() {
        return searchConnectedComponents;
    }

    public boolean isSearchDensity() {
        return searchDensity;
    }

    public boolean isSearchMaxVertexDegree() {
        return searchMaxVertexDegree;
    }
}
