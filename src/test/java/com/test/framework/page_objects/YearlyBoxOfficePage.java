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
    private static final String DOMESTIC_PERCENTAGE = "Domestic_%";
    private static final String OVERSEAS_PERCENTAGE = "Overseas_%";
    private static final String TITLE = "Title";
    private static final String STUDIO = "Studio";

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
        List<WebElement> rows = driver.findElement(By.xpath(BOX_OFFICE_TABLE_XPATH)).findElements(By.tagName("tr"));
        // Setup the columns.
        List<String> columns = rows.get(0).findElements(By.tagName("td")).stream()
                .map(x -> x.getText().split(" ")[0]).collect(Collectors.toList());
        columns.removeIf(p -> p.startsWith("Filter"));
        columns.set(2, STUDIO);
        columns.set(1, TITLE);
        columns.add(5, DOMESTIC_PERCENTAGE);
        columns.add(7, OVERSEAS_PERCENTAGE);
        // Write a data structure to convert the web table data into Json objects.
        for(int i = 2; i < rows.size(); i++) {
            List<String> currentRow = rows.get(i).findElements(By.tagName("td")).stream()
                    .map(x -> x.getText()).collect(Collectors.toList());
            JsonObject obj = new JsonObject();
            for(int j = 0; j < columns.size(); j++) {
                obj.addProperty(columns.get(j), currentRow.get(j));
            }
            // Add the objects to an array.
            arr.add(obj);
        }
        // Return the array.
        return arr;
    }

}
