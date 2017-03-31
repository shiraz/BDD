package com.test.framework.page_objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.test.framework.ParentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * The page object and utilities for the "Yearly Box Office" page.
 * </p>
 */
public class YearlyBoxOfficePage extends ParentPage {

    /**
     * Locators.
     */
    private static final String BOX_OFFICE_TABLE_XPATH = "//table[@cellpadding > 1]/tbody";

    /**
     * Constants.
     */
    public static final String DOMESTIC = "Domestic";
    private static final String DOMESTIC_PERCENTAGE = "Domestic_%";
    private static final String OVERSEAS_PERCENTAGE = "Overseas_%";
    private static final String STUDIO = "Studio";
    public static final String TITLE = "Title";
    private static final String UNIVERSAL = "Uni.";
    public static final String WORLDWIDE = "Worldwide";

    /**
     * Tag constants.
     */
    private static final String TAG_TD = "td";
    private static final String TAG_TR = "tr";

    /**
     * <p>
     * Constructor.
     * </p>
     * @param driver {@link WebDriver} - The driver instance.
     */
    public YearlyBoxOfficePage(WebDriver driver) {
        super(driver);
    }

    /**
     * <p>
     * Converts the dynamic box office web table into a Json array.
     * </p>
     *
     * @return {@link JsonArray} - The successful conversion of the web table into the JsonArray data structure.
     */
    public JsonArray convertWebTableDataToJson() {
        // Initialize a JsonArray to store the data.
        JsonArray arr = new JsonArray();
        // Get all the rows from the web table.
        List<WebElement> rows = driver.findElement(By.xpath(BOX_OFFICE_TABLE_XPATH)).findElements(By.tagName(TAG_TR));
        // Get the list of columns. The columns will be the property names in JSON.
        List<String> columns = getColumnsFromWebTable();
        // Write a data structure to convert the web table data into Json objects.
        for(int i = 2; i < rows.size(); i++) {
            List<String> currentRow = rows.get(i).findElements(By.tagName(TAG_TD)).stream()
                    .map(x -> x.getText()).collect(Collectors.toList());
            // Initialize a JSON object.
            JsonObject obj = new JsonObject();
            // Create a custom mapping with the list of column names to its corresponding values in the web table.
            for(int j = 0; j < columns.size(); j++) {
                // Add the corresponding properties to the JSON object.
                obj.addProperty(columns.get(j), currentRow.get(j));
            }
            // Add the objects to an array.
            arr.add(obj);
        }
        // Return the array.
        return arr;
    }

    /**
     * <p>
     * Filters the box office data based on the studio. For example, if "Uni." is passed,
     * then all box office data pertaining to the Unviersal brand is returned.
     * </p>
     *
     * @param boxOfficeData {@link JsonArray} - The array containing all box office data.
     * @param studio {@link String} - The studio name.
     * @return {@link JsonArray} - All box office data pertaining to the studio.
     */
    public JsonArray filterBoxOfficeDataBasedOnStudio(JsonArray boxOfficeData, String studio) {
        // Initialize a JsonArray to store all the filtered objects.
        JsonArray filteredData = new JsonArray();
        boxOfficeData.forEach(x -> {
            JsonObject currentObj = x.getAsJsonObject();
            if(currentObj.get(STUDIO).getAsString().equalsIgnoreCase(studio)) {
                filteredData.add(currentObj);
            }
        });
        // Return the filtered data.
        return filteredData;
    }

    /**
     * <p>
     * Returns all box office data pertaining to Universal Pictures.
     * </p>
     *
     * @param boxOfficeData {@link JsonArray} - The array containing all box office data.
     * @return {@link JsonArray} - All box office data pertaining to Universal.
     */
    public JsonArray getBoxOfficeDataOfUniversal(JsonArray boxOfficeData) {
        return filterBoxOfficeDataBasedOnStudio(boxOfficeData, UNIVERSAL);
    }

    /**
     * <p>
     * Get the columns from the web table.
     * </p>
     *
     * @return {@link List} - The list column names.
     */
    private List<String> getColumnsFromWebTable() {
        // Get all the rows from the web table.
        List<WebElement> rows = driver.findElement(By.xpath(BOX_OFFICE_TABLE_XPATH)).findElements(By.tagName(TAG_TR));
        // Using a Lambda expression, get the text of all column Web elements, and store it in a list.
        List<String> columns = rows.get(0).findElements(By.tagName(TAG_TD)).stream()
                .map(x -> x.getText().split(" ")[0]).collect(Collectors.toList());
        columns.removeIf(p -> p.startsWith("Filter"));
        columns.set(2, STUDIO);
        columns.set(1, TITLE);
        columns.add(5, DOMESTIC_PERCENTAGE);
        columns.add(7, OVERSEAS_PERCENTAGE);
        // Return the columns list.
        return columns;
    }

}