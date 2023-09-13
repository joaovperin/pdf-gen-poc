package com.example.poc.pdfgen.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private MyService service;

    @GetMapping("/gen-html")
    public ResponseEntity<Object> generateHTML() {
        service.generateHtml();
        return ResponseEntity.ok().body(Map.of("ok", true));
    }

    @GetMapping("/html")
    public Resource getHTML() throws Exception {
        final var data = service.generateHtmlResource();
        return data;
    }

    @GetMapping("/pdf")
    public Resource getPDF() throws Exception {
        final var data = service.generatePdfResource();
        return data;
    }

    @GetMapping("/items")
    public ResponseEntity<MyModel> getItems() {
        final var items = service.getModel();
        return ResponseEntity.ok().body(items);
    }

}
