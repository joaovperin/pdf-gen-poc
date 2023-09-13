package com.example.poc.pdfgen.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
public class MyController {

    @Autowired
    private MyService service;

    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyModel> getItems(RequestOptions options) {
        final var items = service.getModel(options);
        return ResponseEntity.ok().body(items);
    }

    @PostMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
    public Resource getHTML(@RequestBody MyModel model) throws Exception {
        final var data = service.generateHtmlResource(model);
        return new ByteArrayResource(data.toByteArray());
    }

    @PostMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public Resource getPDF(@RequestBody MyModel model) throws Exception {
        final var data = service.generatePdfResource(model);
        return new ByteArrayResource(data.toByteArray());
    }

    @Data
    public static class RequestOptions {
        public int rowCount = 1000;
    }

}
