package com.pages;

import org.openqa.selenium.WebDriver;

public class PhpTravels {
    private WebDriver driver;
    private String homepageUrl = "https://phptravels.com/";
    private String expectedHomepageTitle =
            "PHPTRAVELS booking script and system for hotels airline flights tours cars online application - PHPTRAVELS";

    // Locators

    // Constructor to initialize the driver
    public PhpTravels(WebDriver driver) {
        this.driver = driver;
    }

    // Getters and Setters
    public String getHomepageUrl() {
        return homepageUrl;
    }

    public String getExpectedHomepageTitle() {
        return expectedHomepageTitle;
    }

    // Page actions
}
