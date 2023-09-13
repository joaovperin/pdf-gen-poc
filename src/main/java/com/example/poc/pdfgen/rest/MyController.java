package com.example.poc.pdfgen.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private MyService service;

    @GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
    public Resource getHTML() throws Exception {
        final var data = service.generateHtmlResource();
        return data;
    }

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public Resource getPDF() throws Exception {
        final var data = service.generatePdfResource();
        return data;
    }

    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyModel> getItems() {
        final var items = service.getModel();
        return ResponseEntity.ok().body(items);
    }

}
