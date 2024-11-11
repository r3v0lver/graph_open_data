package com.example.backend_or_lab2.dao;

import com.example.backend_or_lab2.model.Graph;
import com.example.backend_or_lab2.model.SearchFilter;

import java.util.ArrayList;

public interface GraphDao {

    ArrayList<Graph> getFilteredData(SearchFilter filter);

}
