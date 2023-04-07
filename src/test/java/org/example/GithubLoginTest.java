package org.example;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GithubLoginTest {
    private WebDriver driver;
    private String baseUrl;
    private String username;
    private String password;

    @BeforeTest
    @Parameters({"browser", "url"})
    public void setUp(String browser, String url) throws Exception {
        // Load the config.properties file
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream("src/main/resources/config.properties");
        prop.load(input);
        username = prop.getProperty("ramankumarsingh45");
        password = prop.getProperty("raman@#1234%");

        // Initialize the webdriver
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "https://www.google.com/");
            driver = new ChromeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser type: " + browser);
        }
        baseUrl = url;
    }

    @Test
    public void testGithubLogin() throws Exception {

        driver.get(baseUrl + "/login");

        // Find the username and password input fields and enter the login credentials
        WebElement usernameInput = driver.findElement(By.cssSelector(""));
        WebElement passwordInput = driver.findElement(By.cssSelector(""));
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        passwordInput.submit();

        // Verify the successful login by checking the presence of the user avatar element
        WebElement userAvatar = driver.findElement(By.cssSelector(".avatar-user"));
        Assert.assertTrue(userAvatar.isDisplayed(), "Login failed.");
    }

    @AfterTest
    public void tearDown() throws Exception {
        // Close the webdriver
        driver.quit();
    }
}
