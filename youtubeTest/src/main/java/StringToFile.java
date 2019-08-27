import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;

public class StringToFile {
    public void convertToJSON (String str) throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(String.valueOf(str));
        try (FileWriter file = new FileWriter("/Users/andrew/Desktop/youtubeTest/src/main/resources/output.json")) {
            file.write(str);
            System.out.println("Successfully Copied JSON Object to File...");
        }
    }
}
