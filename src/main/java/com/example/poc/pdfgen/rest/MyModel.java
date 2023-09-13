package com.example.poc.pdfgen.rest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyModel {

    private static final java.time.format.DateTimeFormatter DATE_FT = java.time.format.DateTimeFormatter
            .ofPattern("dd/MM/yyyy HH:mm:ss");

    private Long id;
    private String title;
    private String description;
    private LocalDateTime datetime;
    private BigDecimal total;
    private List<MyRow> items;

    public String fmtDateTime() {
        return datetime.format(DATE_FT);
    }

    @Data
    @Builder
    public static class MyRow {
        private Long id;
        private Integer sequence;
        private String name;
        private String type;
        private BigDecimal price;
    }

}
