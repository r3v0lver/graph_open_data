package com.example.backend_or_lab2.service;

import com.example.backend_or_lab2.dao.GraphDao;
import com.example.backend_or_lab2.model.Coloring;
import com.example.backend_or_lab2.model.Graph;
import com.example.backend_or_lab2.model.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
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
}





