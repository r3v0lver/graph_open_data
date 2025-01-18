package com.example.backend_or_lab2.service;

import com.example.backend_or_lab2.api.wrappers.ApiResponse;
import com.example.backend_or_lab2.dao.GraphDao;
import com.example.backend_or_lab2.dao.GraphDataAccessService;
import com.example.backend_or_lab2.model.Coloring;
import com.example.backend_or_lab2.model.Graph;
import com.example.backend_or_lab2.model.SearchFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

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

    public ApiResponse<String> refreshFiles() {
        try {
            ArrayList<Graph> graphs = getAllGraphs();

            Path jsonPath = Path.of("src/main/resources/static/data/graph_db.json");
            Files.createDirectories(jsonPath.getParent());

            try (FileWriter jsonFileWriter = new FileWriter(jsonPath.toFile())) {
                String jsonContent = new ObjectMapper().writeValueAsString(graphs);
                jsonFileWriter.write(jsonContent);
            }

            Path csvPath = Path.of("src/main/resources/static/data/graph_db.csv");
            Files.createDirectories(csvPath.getParent());

            try (FileWriter fileWriter = new FileWriter(csvPath.toFile())) {
                fileWriter.append("id,vNum,adjMatrix,simpleGraph,chromaticNumber,isBipartite,edgeCount,connectedComponents,density,maxVertexDegree,colorings\n");

                for (Graph graph : graphs) {
                    String coloringsStr = graph.getColorings().stream()
                            .map(Coloring::toCsvFormat)
                            .reduce((coloring1, coloring2) -> coloring1 + "," + coloring2)
                            .orElse("");

                    fileWriter.append(String.format("%s,%d,%s,%b,%d,%b,%d,%d,%f,%d,%s\n",
                            graph.getId(),
                            graph.getVertexNumber(),
                            matrixToString(graph.getAdjMatrix()),
                            graph.isSimpleGraph(),
                            graph.getChromaticNumber(),
                            graph.isBipartite(),
                            graph.getEdgeCount(),
                            graph.getConnectedComponents(),
                            graph.getDensity(),
                            graph.getMaxVertexDegree(),
                            coloringsStr));
                }
            }

            return new ApiResponse<>("OK", "Files refreshed successfully", null);

        } catch (IOException e) {
            e.printStackTrace();
            return new ApiResponse<>("ERROR", "Failed to refresh files: " + e.getMessage(), null);
        }
    }

    public String matrixToString(Integer[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append(Arrays.toString(matrix[i]));
            if (i < matrix.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
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





