package com.example.poc.pdfgen.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.example.poc.pdfgen.rest.MyController.RequestOptions;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class MyService {

    @Autowired
    private MyRepository repository;
    @Autowired
    private Template freemarkerTemplate;

    public MyModel getModel(RequestOptions options) {
        return repository.getModel(options.getRowCount());
    }

    public ByteArrayOutputStream generatePdfResource(MyModel model) throws IOException, TemplateException {
        final Map<String, Object> data = Map.of("myModel", model);
        try (final var htmlOut = new StringWriter(model.getItems().size() * 1024)) {
            freemarkerTemplate.process(data, htmlOut);

            try (final var outputStream = new ByteArrayOutputStream()) {
                final Document document = Jsoup.parse(htmlOut.toString(), "UTF-8");
                document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

                final ITextRenderer renderer = new ITextRenderer();
                final SharedContext sharedContext = renderer.getSharedContext();
                sharedContext.setPrint(true);
                sharedContext.setInteractive(false);
                renderer.setDocumentFromString(document.html());
                renderer.layout();
                renderer.createPDF(outputStream);
                return outputStream;
            }
        }
    }

    public ByteArrayOutputStream generateHtmlResource(MyModel model) throws IOException, TemplateException {
        final Map<String, Object> data = Map.of("myModel", model);
        try (final var outputStream = new ByteArrayOutputStream()) {
            freemarkerTemplate.process(data, new OutputStreamWriter(outputStream));
            return outputStream;
        }
    }

}
