package com.example.backend_or_lab2.service;

import com.example.backend_or_lab2.dao.GraphDao;
import com.example.backend_or_lab2.model.Coloring;
import com.example.backend_or_lab2.model.FilteredDataCsv;
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

    public String getFilteredDataCsv(SearchFilter filter) {
        StringWriter csvWriter = new StringWriter();
        ArrayList<Graph> graphs = graphDao.getFilteredData(filter);

        // Header za CSV
        csvWriter.append("id,vertexNumber,chromaticNumber,adjacencyMatrix,simpleGraph,isBipartite,edgeCount,connectedComponents,density,maxVertexDegree,colorings\n");

        for (Graph graph : graphs) {
            csvWriter.append(graph.getId()).append(",");
            csvWriter.append(String.valueOf(graph.getVertexNumber())).append(",");
            csvWriter.append(String.valueOf(graph.getChromaticNumber())).append(",");

            // Pretvorba adjacencyMatrix u string
            String adjacencyMatrixStr = matrixToString(graph.getAdjMatrix());
            csvWriter.append("\"").append(adjacencyMatrixStr).append("\",");

            csvWriter.append(String.valueOf(graph.isSimpleGraph())).append(",");
            csvWriter.append(String.valueOf(graph.isBipartite())).append(",");
            csvWriter.append(String.valueOf(graph.getEdgeCount())).append(",");
            csvWriter.append(String.valueOf(graph.getConnectedComponents())).append(",");
            csvWriter.append(String.valueOf(graph.getDensity())).append(",");
            csvWriter.append(String.valueOf(graph.getMaxVertexDegree())).append(",");

            // Dodavanje svih bojanja kao jedan string
            String coloringsStr = formatColorings(graph.getColorings());
            csvWriter.append("\"").append(coloringsStr).append("\"\n");
        }

        return csvWriter.toString();
    }

    private String formatColorings(ArrayList<Coloring> colorings) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < colorings.size(); i++) {
            Coloring coloring = colorings.get(i);

            // Formatiramo jedno bojanje kao niz integera (boje vrhova)
            sb.append(arrayToString(coloring.getColorAssignment()));

            // Dodajemo separator između bojanja ako nije zadnje
            if (i < colorings.size() - 1) {
                sb.append("; ");
            }
        }

        return sb.toString();
    }

    private String matrixToString(Integer[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append("[");
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]);
                if (j < matrix[i].length - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
            if (i < matrix.length - 1) {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    // Metoda za pretvorbu niza u string
    private String arrayToString(Integer[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}





