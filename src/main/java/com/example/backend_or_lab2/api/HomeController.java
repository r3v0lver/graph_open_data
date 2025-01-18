package com.example.backend_or_lab2.api;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OidcUser principal, Model model) {
        if (principal != null) {
            model.addAttribute("name", principal.getFullName());
        }
        return "index";
    }

    @GetMapping("/database")
    public String getDatabasePage() {
        return "database";
    }

    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal OAuth2User principal) {
        return "profile";
    }

    @GetMapping("/data/graph_db.json")
    public ResponseEntity<String> getGraphDataJSON() {
        try {
            Path path = Paths.get("src/main/resources/static/data/graph_db.json");
            String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading graph data file");
        }
    }

    @GetMapping("/graph_db.csv")
    public ResponseEntity<Resource> getCsvData() {
        Path path = Paths.get("src/main/resources/static/data/graph_db.csv");
        Resource resource = new FileSystemResource(path);

        // Postavljanje odgovarajućeg MIME tipa
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"graph_db.csv\"");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
