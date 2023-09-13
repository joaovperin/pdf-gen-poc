package com.example.poc.pdfgen.rest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class MyService {

    @Autowired
    private MyRepository repository;
    @Autowired
    private Template freemarkerTemplate;

    public MyModel getModel() {
        return repository.getModel();
    }

    public Resource generatePdfResource() throws IOException, TemplateException {
        // WIP: develop the PDF generation
        return generateHtmlResource();
    }

    public Resource generateHtmlResource() throws IOException, TemplateException {
        final var model = repository.getModel();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("myModel", model);

        try (final var out = new ByteArrayOutputStream()) {
            freemarkerTemplate.process(data, new OutputStreamWriter(out));
            return new ByteArrayResource(out.toByteArray());
        }
    }

    public void generateHtml() {
        try {
            final var model = repository.getModel();

            // Build the data-model
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("myModel", model);

            // Console output
            try (final Writer out = new OutputStreamWriter(System.out)) {
                freemarkerTemplate.process(data, out);
                out.flush();
            }

            // File output
            try (final Writer file = new FileWriter(
                    new File("D:\\Projects\\java\\pdf-gen\\pdf-gen-poc\\tmp\\FTL_helloworld.txt"))) {

                freemarkerTemplate.process(data, file);
                file.flush();
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            e.printStackTrace();
        }
    }

}
