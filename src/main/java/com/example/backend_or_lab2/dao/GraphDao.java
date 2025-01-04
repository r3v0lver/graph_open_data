package com.example.backend_or_lab2.dao;

import com.example.backend_or_lab2.model.Coloring;
import com.example.backend_or_lab2.model.Graph;
import com.example.backend_or_lab2.model.SearchFilter;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GraphDao {

    ArrayList<Graph> getFilteredData(SearchFilter filter);

    ArrayList<Graph> getAllGraphs();

    Graph getGraphById(String id);

    ArrayList<Coloring> getColoringsForGraph(String graphId);

    void insertColoring(Coloring coloring) throws SQLException;

    void updateChromaticNumber(String id, int chromaticNumber);

    void deleteGraph(String id);
}
