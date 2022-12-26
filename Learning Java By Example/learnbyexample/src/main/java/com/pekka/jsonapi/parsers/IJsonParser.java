package com.pekka.jsonapi.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.pekka.jsonapi.data.ResultData;

public interface IJsonParser {

	List<ResultData> parseJson(InputStream in) throws IOException;

}