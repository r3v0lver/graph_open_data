package com.example.backend_or_lab2.api;

import com.example.backend_or_lab2.model.FilteredDataCsv;
import com.example.backend_or_lab2.model.Graph;
import com.example.backend_or_lab2.model.SearchFilter;
import com.example.backend_or_lab2.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/graph")
@CrossOrigin(origins = {"https://graph-tensor.onrender.com", "http://localhost:63342"}, allowCredentials = "true",
        allowedHeaders = {"Content-Type", "*"}, methods = {RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST})
public class GraphController {

    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping(value = "/getGraphsJson")
    public ArrayList<Graph> getFilteredDataJson(@RequestBody SearchFilter filter) {
        return graphService.getFilteredDataJson(filter);
    }

    @GetMapping(value = "/getGraphsCsv")
    public ResponseEntity<String> getFilteredDataCsv(@RequestBody SearchFilter filter) {

        String csvContent = graphService.getFilteredDataCsv(filter);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=graphs.csv");
        headers.add("Content-Type", "text/csv");

        return new ResponseEntity<>(csvContent, headers, HttpStatus.OK);
    }

}
