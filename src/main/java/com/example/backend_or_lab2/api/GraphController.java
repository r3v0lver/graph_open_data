package com.example.backend_or_lab2.api;

import com.example.backend_or_lab2.api.wrappers.ApiResponse;
import com.example.backend_or_lab2.model.Coloring;
import com.example.backend_or_lab2.model.Graph;
import com.example.backend_or_lab2.model.SearchFilter;
import com.example.backend_or_lab2.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("api/graph")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true",
        allowedHeaders = {"Content-Type", "Authorization", "X-Requested-With", "Accept", "Origin", "Access-Control-Allow-Origin"})
public class GraphController {

    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @PostMapping(value = "/getGraphsJson")
    public ArrayList<Graph> getFilteredDataJson(@RequestBody SearchFilter filter) {
        return graphService.getFilteredDataJson(filter);
    }

    @PostMapping("/refresh")
    public ApiResponse<String> refreshFiles() {
       return graphService.refreshFiles();
    }
    @GetMapping("/profile")
    public Map<String, Object> getProfile(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Niste prijavljeni!");
        }
        return principal.getAttributes();
    }

    @GetMapping("/getSpecification")
    public ApiResponse<String> getSpecification() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("specification/openapi.json");

            if (inputStream == null) {
                throw new Exception("Resource not found: specification/openapi.json");
            }
            String specification = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            return new ApiResponse<>("OK", "Fetched OpenAPI specification", specification);
        } catch (Exception e) {
            return new ApiResponse<>("ERROR", "Failed to fetch OpenAPI specification: " + e.getMessage(), null);
        }
    }

    @GetMapping("/getAllGraphs")
    public ApiResponse<ArrayList<Graph>> getAllGraphs() {
        try {
            ArrayList<Graph> graphs = graphService.getAllGraphs();
            return new ApiResponse<>("OK", "Fetched all graphs", graphs);
        } catch (Exception e) {
            return new ApiResponse<>("ERROR", "Failed to fetch graphs: " + e.getMessage(), null);
        }
    }

    @GetMapping("/getGraph/{id}")
    public ApiResponse<Map<String, Object>> getGraphById(@PathVariable String id) {
        try {
            Graph graph = graphService.getGraphById(id);

            Map<String, Object> jsonLdResponse = new LinkedHashMap<>();
            jsonLdResponse.put("@context", "https://schema.org");
            jsonLdResponse.put("@type", "Graph");
            jsonLdResponse.put("id", graph.getId());

            Map<String, Object> vertexNumber = new HashMap<>();
            vertexNumber.put("@type", "QuantitativeValue");
            vertexNumber.put("value", graph.getVertexNumber());
            vertexNumber.put("unitText", "count");
            jsonLdResponse.put("vertexNumber", vertexNumber);

            Map<String, Object> chromaticNumber = new HashMap<>();
            chromaticNumber.put("@type", "QuantitativeValue");
            chromaticNumber.put("value", graph.getChromaticNumber());
            chromaticNumber.put("unitText", "color");
            jsonLdResponse.put("chromaticNumber", chromaticNumber);

            jsonLdResponse.put("adjMatrix", graph.getAdjMatrix());
            jsonLdResponse.put("isBipartite", graph.isBipartite());
            jsonLdResponse.put("edgeCount", graph.getEdgeCount());
            return new ApiResponse<>("OK", "Fetched graph object", jsonLdResponse);
        } catch (RuntimeException e) {
            return new ApiResponse<>("ERROR", "Graph not found with id: " + id, null);
        }
    }

    @GetMapping("/getColorings/{graphId}")
    public ApiResponse<ArrayList<Coloring>> getColoringsByGraphId(@PathVariable String graphId) {
        try {
            ArrayList<Coloring> colorings = graphService.getColoringsByGraphId(graphId);
            return new ApiResponse<>("OK", "Fetched colorings for graph", colorings);
        } catch (Exception e) {
            return new ApiResponse<>("ERROR", "Failed to fetch colorings for graph with id: " + graphId, null);
        }
    }

    @GetMapping("/getVertexNumber/{id}")
    public ApiResponse<Integer> getVertexNumberById(@PathVariable String id) {
        try {
            int vertexNumber = graphService.getVertexNumberById(id);
            return new ApiResponse<>("OK", "Fetched vertex number", vertexNumber);
        } catch (Exception e) {
            return new ApiResponse<>("ERROR", "Failed to fetch vertex number for graph with id: " + id, null);
        }
    }

    @GetMapping("/getChromaticNumber/{id}")
    public ApiResponse<Integer> getChromaticNumberById(@PathVariable String id) {
        try {
            int chromaticNumber = graphService.getChromaticNumberById(id);
            return new ApiResponse<>("OK", "Fetched chromatic number", chromaticNumber);
        } catch (Exception e) {
            return new ApiResponse<>("ERROR", "Failed to fetch chromatic number for graph with id: " + id, null);
        }
    }

    @PostMapping("/insertColoring")
    public ApiResponse<Void> insertColoring(@RequestBody Coloring coloring) {
        try {
            graphService.insertColoring(coloring);
            return new ApiResponse<>("OK", "Inserted coloring", null);
        } catch (Exception e) {
            return new ApiResponse<>("ERROR", "Failed to insert coloring: " + e.getMessage(), null);
        }
    }

    @PutMapping("/updateChromaticNumber/{id}")
    public ApiResponse<Void> updateChromaticNumberById(@PathVariable String id, @RequestBody int chromaticNumber) {
        try {
            if(chromaticNumber <= 0) return new ApiResponse<>("ERROR", "Chromatic number cannot be 0!", null);
            graphService.updateChromaticNumberById(id, chromaticNumber);
            return new ApiResponse<>("OK", "Updated chromatic number", null);
        } catch (Exception e) {
            return new ApiResponse<>("ERROR", "Failed to update chromatic number for graph with id: " + id, null);
        }
    }

    @DeleteMapping("/deleteGraph/{id}")
    public ApiResponse<Void> deleteGraphById(@PathVariable String id) {
        try {
            graphService.deleteGraphById(id);
            return new ApiResponse<>("OK", "Deleted graph", null);
        } catch (Exception e) {
            return new ApiResponse<>("ERROR", "Failed to delete graph with id: " + id, null);
        }
    }

}
