package com.test.page.objects;

import com.test.framework.ParentPage;
import org.openqa.selenium.WebDriver;

public class MainPage extends ParentPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickTab(String tab) {
        click(tab);
    }

}