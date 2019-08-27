import java.io.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
        import com.google.api.client.googleapis.json.GoogleJsonResponseException;
        import com.google.api.client.http.javanet.NetHttpTransport;
        import com.google.api.client.json.JsonFactory;
        import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
        import com.google.api.services.youtube.model.SearchListResponse;

import java.security.GeneralSecurityException;
        import java.util.Arrays;
        import java.util.Collection;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class YoutubeFindMain {

    private static final String DEVELOPER_KEY = "AIzaSyDHITSFXOf2aAJJonPxo2FvkQv7DJsOD1s";

    private static final String APPLICATION_NAME = "API code samples";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */
    public static void main(String[] args)
            throws GeneralSecurityException, IOException {
        YouTube youtubeService = getService();
        YouTube.Search.List request = youtubeService.search()
                .list("snippet");
        SearchListResponse response = request.setKey(DEVELOPER_KEY)
                .setMaxResults(15L)
                .setOrder("date")
                .setPublishedAfter(DateTime.parseRfc3339("2019-08-04T00:00:00Z"))
                .setQ("javascript|python -basics")
                .setFields("items(snippet(publishedAt,title))")
                .setPrettyPrint(true)
                .execute();
        System.out.println(response.toPrettyString());

        StringToFile converter = new StringToFile();
        converter.convertToJSON(response.toPrettyString());
    }
}

