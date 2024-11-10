package com.example.backend_or_lab2.api;

import com.example.backend_or_lab2.model.Graph;
import com.example.backend_or_lab2.model.SearchFilter;
import com.example.backend_or_lab2.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/graph")
@CrossOrigin(origins = {"http://localhost:63342"}, allowCredentials = "true",
        allowedHeaders = {"Content-Type", "*"}, methods = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST})
public class GraphController {

    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @PostMapping(value = "/getGraphsJson")
    @CrossOrigin(origins = "http://localhost:63342", allowCredentials = "true", allowedHeaders = "*")
    public ArrayList<Graph> getFilteredDataJson(@RequestBody SearchFilter filter) {
        return graphService.getFilteredDataJson(filter);
    }


}
