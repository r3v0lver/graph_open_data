package com.example.backend_or_lab2.service;

import com.example.backend_or_lab2.dao.GraphDao;
import com.example.backend_or_lab2.dao.GraphDataAccessService;
import com.example.backend_or_lab2.model.Coloring;
import com.example.backend_or_lab2.model.Graph;
import com.example.backend_or_lab2.model.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class GraphService {
    private final GraphDao graphDao;

    @Autowired
    public GraphService(@Qualifier("graphDao") GraphDao graphDao) throws InterruptedException {
        this.graphDao = graphDao;
    }

    public ArrayList<Graph> getFilteredDataJson(SearchFilter filter) {
        ArrayList<Graph> graphs = graphDao.getFilteredData(filter);
        return graphs;
    }

    public ArrayList<Graph> getAllGraphs() {
        return graphDao.getAllGraphs();
    }

    public Graph getGraphById(String id) {
        return graphDao.getGraphById(id);
    }

    public ArrayList<Coloring> getColoringsByGraphId(String graphId) {
       return graphDao.getColoringsForGraph(graphId);
    }

    public int getVertexNumberById(String id) {
        return graphDao.getGraphById(id).getVertexNumber();
    }

    public int getChromaticNumberById(String id) {
        return graphDao.getGraphById(id).getChromaticNumber();
    }

    public void insertColoring(Coloring coloring) throws SQLException {
        graphDao.insertColoring(coloring);
    }

    public void updateChromaticNumberById(String id, int chromaticNumber) {
        graphDao.updateChromaticNumber(id, chromaticNumber);
    }

    public void deleteGraphById(String id) {
        graphDao.deleteGraph(id);
    }
}





