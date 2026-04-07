package org.example.utils;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {

    private static final JSONObject json;

    static {
        try {
            String content = new String(
                    Files.readAllBytes(Paths.get("src/test/category.Json"))
            );
            json = new JSONObject(content);
        } catch (Exception e) {
            throw new RuntimeException("category.json ვერ წაიკითხა: "); // ✅ შეცდომა ჩანს
        }
    }

    public static JSONObject getJson() {
        return json;
    }
}