package com.example.poc.pdfgen.rest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {

    public MyModel getModel() {
        final var id = generateRandomIntBetween(1, 1000);
        final var itemsCount = generateRandomIntBetween(100, 1000);
        return getModel(id, itemsCount);
    }

    public MyModel getModel(long id, int itemsCount) {
        return generateRandomModel(id, itemsCount);
    }

    private static final String[] loremIpsumWords = "Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur Excepteur sint occaecat cupidatat non proident sunt in culpa qui officia deserunt mollit anim id est laborum Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur Excepteur sint occaecat cupidatat non proident sunt in culpa qui officia deserunt mollit anim id est laborum"
            .split(" ");

    private static MyModel generateRandomModel(long id, int itemsCount) {
        return MyModel.builder()
                .id(id)
                .title(generateRandomLoremIpsumString(generateRandomIntBetween(2, 5)))
                .description(generateRandomLoremIpsumString(generateRandomIntBetween(3, 10)))
                .datetime(generateRandomLocalDateBetween(2010, 2020))
                .total(generateRandomBigDecimalBetween(5_000, 250_000, 2))
                .items(generateRandomItems(id, itemsCount))
                .build();
    }

    private static List<MyModel.MyRow> generateRandomItems(long id, int count) {
        return IntStream.range(0, count).boxed().map(i -> MyModel.MyRow.builder()
                .id(Long.valueOf(generateRandomIntBetween(100, 2000)))
                .sequence(i)
                .name(generateRandomLoremIpsumString(generateRandomIntBetween(2, 5)))
                .type(generateRandomLoremIpsumString(generateRandomIntBetween(1, 1)))
                .price(generateRandomBigDecimalBetween(100, 10_000, 2))
                .build())
                .collect(Collectors.toList());
    }

    private static String generateRandomLoremIpsumString(int wordsCount) {
        StringBuilder sb = new StringBuilder(1000);

        IntStream.range(0, wordsCount).boxed().forEach(i -> {
            sb.append(loremIpsumWords[generateRandomIntBetween(0, loremIpsumWords.length - 1)]);
            sb.append(" ");
        });

        return sb.toString().trim();
    }

    private static LocalDateTime generateRandomLocalDateBetween(int startYear, int endYear) {
        int day = generateRandomIntBetween(1, 28);
        int month = generateRandomIntBetween(1, 12);
        int year = generateRandomIntBetween(startYear, endYear);
        int hour = generateRandomIntBetween(0, 23);
        int minute = generateRandomIntBetween(0, 59);
        int second = generateRandomIntBetween(0, 59);
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    private static BigDecimal generateRandomBigDecimalBetween(int start, int end, int decimalPart) {
        return new BigDecimal(
                generateRandomIntBetween(start, end) + "." + generateRandomIntBetween(0, decimalPart * 10));
    }

    private static int generateRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

}
