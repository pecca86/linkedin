package com.pekka.cmdlineapp;

import com.pekka.jsonapi.StackQuery;
import com.pekka.jsonapi.data.ResultData;
import com.pekka.jsonapi.parsers.IJsonParser;
import com.pekka.jsonapi.parsers.JacksonJsonParser;
import com.pekka.jsonapi.parsers.JsonpJsonParser;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ApiTest {
    @Test
    public void testConnection() throws IOException {
        StackQuery query = new StackQuery();

        query.setSearchTerm("Hello World");
        query.setSortBy(StackQuery.SortBy.RELEVANCE);
        query.setSortOrder(StackQuery.SortOrder.ASCENDING);

        System.out.println(query.buildUrl());
        List<ResultData> result = query.execute();
        //System.out.println("Result count = " + result.size());
        assertNotEquals(0, result.size());
    }


    public static void main(String[] args) {
        ApiTest test = new ApiTest();
        try {
            test.testConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Test
    public void testUrl() throws MalformedURLException {
        StackQuery query = new StackQuery();

        query.setSearchTerm("Hello World");
        query.setSortOrder(StackQuery.SortOrder.ASCENDING);
        query.setSortBy(StackQuery.SortBy.RELEVANCE);

        URL url = query.buildUrl();
        String goodUrl = "https://api.stackexchange.com/2.2/search?" +
                "site=stackoverflow&pagesize=5&order=asc&sortrelevance&" +
                "tagged=java&intitle=Hello%20World";
        assertEquals(goodUrl, url.toString());
    }


    @Test
    public void jsonpParserTest() throws IOException {
        IJsonParser parser = new JsonpJsonParser();
        try (FileInputStream in = new FileInputStream("Example.json")) {
            List<ResultData> results = parser.parseJson(in);
            checkTestParse(results);
        }
    }

    public void checkTestParse(List<ResultData> results) {
        // test against local Json Example.js file
        assertNotNull(results);
        assertEquals(2, results.size());

        ResultData result1 = results.get(0);
        assertEquals("java", result1.getTags()[0]);
        assertEquals("urlconnection", result1.getTags()[1]);
        assertEquals(1234, result1.getOwner().getReputation());
        // ...etc.
    }


    @Test
    public void jacksonParserTest() throws IOException {
        IJsonParser parser = new JacksonJsonParser();
        try (FileInputStream in = new FileInputStream("Example.json")) {
            List<ResultData> results = parser.parseJson(in);
            checkTestParse(results);
        }
    }
}