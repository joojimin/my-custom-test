package com.example.mycustomtest.jsonpath;

import static org.assertj.core.api.Assertions.assertThat;

import com.jayway.jsonpath.JsonPath;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JsonPathTest {

    private String example;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("./test.json"));
        example = br.lines().collect(Collectors.joining());
        System.out.println("example = " + this.example);
    }


    @DisplayName("jsonPath Test#1: store 속 book 리스트중 author(작가) 리스트 추출")
    @Test
    void jsonPathTest_1() {
        // given
        List<String> expected = List.of("Nigel Rees",
                                        "Evelyn Waugh",
                                        "Herman Melville",
                                        "J. R. R. Tolkien");

        // when
//        List<String> authors = JsonPath.parse(example).read("$.store.book[*].author");
        List<String> authors = JsonPath.parse(example).read("$['store']['book'][*]['author']");

        // then
        assertThat(authors).hasSize(4)
                           .containsAnyElementsOf(expected);
    }

    @DisplayName("jsonPath Test#2: 모든 author 추출")
    @Test
    void jsonPathTest_2() {
        // given
        List<String> expected = List.of("jimin",
                                        "Nigel Rees",
                                        "Evelyn Waugh",
                                        "Herman Melville",
                                        "J. R. R. Tolkien",
                                        "joojimin");

        // when
        List<String> authors = JsonPath.parse(example).read("$..author");

        // then
        assertThat(authors).hasSize(6).containsAnyElementsOf(expected);
    }


    @DisplayName("jsonPath Test#3: store 안의 모든 값들")
    @Test
    void jsonPathTest_3() {

        // when
        List<String> values = JsonPath.parse(example).read("$.store.*");

        // then
        assertThat(values).isNotEmpty();
    }

    @DisplayName("jsonPath Test#4: store 안의 price 키 값 추출")
    @Test
    void jsonPathTest_4() {
        // given
        List<Double> expected = List.of(8.95,
                                        12.99,
                                        8.99,
                                        22.99,
                                        19.95,
                                        10.222);

        // when
        List<Double> prices = JsonPath.parse(example).read("$.store..price");

        // then
        assertThat(prices).hasSize(6)
                          .containsAnyElementsOf(expected);
    }

    @DisplayName("jsonPath Test#5: 모든 book 안의 3번째 값 추출")
    @Test
    void jsonPathTest_5() {

        // when
        List<String> books = JsonPath.parse(example).read("$..book[2]");

        // then
        assertThat(books).isNotEmpty();
    }


    @DisplayName("jsonPath Test#6: 모든 book의 끝에서 2번째 값 추출")
    @Test
    void jsonPathTest_6() {

        // when
        List<String> books = JsonPath.parse(example).read("$..book[-2]");

        // then
        assertThat(books).isNotEmpty();
    }

    @DisplayName("jsonPath Test#6-add: 모든 book의 끝에서 2개의 추출")
    @Test
    void jsonPathTest_6_add() {

        // when
        List<String> books = JsonPath.parse(example).read("$..book[-2:]");

        // then
        assertThat(books).isNotEmpty();
    }

    @DisplayName("jsonPath Test#7: 모든 book 중에 isbn값을 가지고 있는 객체 추출")
    @Test
    void jsonPathTest_7() {

        // when
        List<String> isns = JsonPath.parse(example).read("$..book[?(@.isbn)]");

        // then
        assertThat(isns).hasSize(2);
    }

}
