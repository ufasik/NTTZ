package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class task3 {

    public static void main(String[] args) {
        String testsJsonPath = "tests.json";
        String valuesJsonPath = "values.json";
        String reportJsonPath = "report.json";

        try {
            
            String testsJson = new String(Files.readAllBytes(Paths.get(testsJsonPath)));
            JsonObject testsObject = JsonParser.parseString(testsJson).getAsJsonObject();


            String valuesJson = new String(Files.readAllBytes(Paths.get(valuesJsonPath)));
            JsonObject valuesObject = JsonParser.parseString(valuesJson).getAsJsonObject();


            updateTestValues(testsObject, valuesObject);


            Gson gson = new Gson();
            String reportJson = gson.toJson(testsObject);

            try (FileWriter writer = new FileWriter(reportJsonPath)) {
                writer.write(reportJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateTestValues(JsonObject testsObject, JsonObject valuesObject) {
        int testId = testsObject.get("id").getAsInt();
        String testValue = getValueValue(valuesObject, testId);

        testsObject.addProperty("value", testValue);

        JsonArray valuesArray = testsObject.getAsJsonArray("values");
        if (valuesArray != null) {
            for (JsonElement valueElement : valuesArray) {
                JsonObject valueObject = valueElement.getAsJsonObject();
                int valueId = valueObject.get("id").getAsInt();

                String valueValue = getValueValue(valuesObject, valueId);
                valueObject.addProperty("value", valueValue);
            }
        }
    }

    private static String getValueValue(JsonObject valuesObject, int valueId) {
        JsonArray valuesArray = valuesObject.getAsJsonArray("values");

        for (JsonElement valueElement : valuesArray) {
            JsonObject valueObject = valueElement.getAsJsonObject();
            int id = valueObject.get("id").getAsInt();

            if (id == valueId) {
                return valueObject.get("value").getAsString();
            }
        }

        return "";
    }
}