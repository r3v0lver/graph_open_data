package com.example.backend_or_lab2.dao;

import com.example.backend_or_lab2.model.Coloring;
import com.example.backend_or_lab2.model.Graph;
import com.example.backend_or_lab2.model.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository("postgres")
@Qualifier("graphDao")
public class GraphDataAccessService implements GraphDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GraphDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ArrayList<Graph> getFilteredData(SearchFilter filter) {
        StringBuilder whereStatement = new StringBuilder();
        String search = filter.getSearch();
        boolean hasConditions = false;

        // Kreiranje WHERE klauzule na temelju filtera
        if (filter.isSearchId()) {
            whereStatement.append("id LIKE '%").append(search).append("%'");
            hasConditions = true;
        }
        if (filter.isSearchVertexNumber()) {
            if (hasConditions) whereStatement.append(" OR ");
            whereStatement.append("vNum::text LIKE '%").append(search).append("%'");
            hasConditions = true;
        }
        if (filter.isSearchAdjMatrix()) {
            if (hasConditions) whereStatement.append(" OR ");
            whereStatement.append("adjMatrix::text LIKE '%").append(search).append("%'");
            hasConditions = true;
        }
        if (filter.isSearchSimpleGraph()) {
            if (hasConditions) whereStatement.append(" OR ");
            whereStatement.append("simpleGraph::text LIKE '%").append(search).append("%'");
            hasConditions = true;
        }
        if (filter.isSearchChromaticNumber()) {
            if (hasConditions) whereStatement.append(" OR ");
            whereStatement.append("chromaticNumber::text LIKE '%").append(search).append("%'");
            hasConditions = true;
        }
        if (filter.isSearchIsBipartite()) {
            if (hasConditions) whereStatement.append(" OR ");
            whereStatement.append("isBipartite::text LIKE '%").append(search).append("%'");
            hasConditions = true;
        }
        if (filter.isSearchEdgeCount()) {
            if (hasConditions) whereStatement.append(" OR ");
            whereStatement.append("edgeCount::text LIKE '%").append(search).append("%'");
            hasConditions = true;
        }
        if (filter.isSearchConnectedComponents()) {
            if (hasConditions) whereStatement.append(" OR ");
            whereStatement.append("connectedComponents::text LIKE '%").append(search).append("%'");
            hasConditions = true;
        }
        if (filter.isSearchDensity()) {
            if (hasConditions) whereStatement.append(" OR ");
            whereStatement.append("density::text LIKE '%").append(search).append("%'");
            hasConditions = true;
        }
        if (filter.isSearchMaxVertexDegree()) {
            if (hasConditions) whereStatement.append(" OR ");
            whereStatement.append("maxVertexDegree::text LIKE '%").append(search).append("%'");
            hasConditions = true;
        }

        // Kreiranje SQL upita
        String sql = "SELECT * FROM graph";
        if (hasConditions) {
            sql += " WHERE " + whereStatement;
        }

        // Dohvat grafova koristeći JdbcTemplate
        List<Graph> graphs = jdbcTemplate.query(sql, (rs, rowNum) -> {
            ArrayList<Coloring> colorings = getColoringsForGraph(rs.getString("id"));
            Graph graph = new Graph(
                    rs.getString("id"), rs.getInt("vNum"), (Integer[][]) rs.getArray("adjMatrix").getArray(),
                    rs.getBoolean("simpleGraph"), rs.getInt("chromaticNumber"), rs.getBoolean("isBipartite"),
                    rs.getInt("edgeCount"),  rs.getInt("connectedComponents"), rs.getFloat("density"),
                    rs.getInt("maxVertexDegree"), colorings);
            return graph;
        });

        return new ArrayList<>(graphs);
    }

    private ArrayList<Coloring> getColoringsForGraph(String graphId) {
        String sql = "SELECT graph_id, coloring FROM coloring WHERE graph_id = ?";

        List<Coloring> colorings = jdbcTemplate.query(sql, new Object[]{graphId}, (rs, rowNum) -> {
            String id = rs.getString("graph_id");
            Integer[] colorAssignment = (Integer[]) rs.getArray("coloring").getArray();
            return new Coloring(id, colorAssignment);
        });

        return new ArrayList<>(colorings);
    }
}
