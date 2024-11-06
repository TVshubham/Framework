package Pages;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;

public class API_List_Page {


    WebDriver driver;
    String url = "";


    public  API_List_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void navigateToHomePage() {
        //   System.out.println(driver.getCurrentUrl());
        if (!driver.getCurrentUrl().equals(this.url)) {
            driver.get(this.url);
        }

    }

    public @FindBy(xpath = "//u[text()='API 1: Get All Products List']")
    WebElement API_1;

    public @FindBy(xpath = " //u[text()='API 2: POST To All Products List']")
    WebElement API_2;

    public @FindBy(xpath = "//u[text()='API 4: PUT To All Brands List']")
    WebElement API_3;

    public @FindBy(xpath = "//u[text()='API 9: DELETE To Verify Login']")
    WebElement API_4;

    public @FindBy(xpath = "//u[text()='API 3: Get All Brands List']")
    WebElement API_5;




     public void Verify_API_1_getAPIDetails()
     {
         String apiUrl = driver.findElement(By.xpath("//div[@id='collapse1']//li[contains(., 'API URL:')]//a")).getAttribute("href");
         String requestMethod = driver.findElement(By.xpath("//div[@id='collapse1']//li[contains(., 'Request Method:')]")).getText();
         String responseCode = driver.findElement(By.xpath("//div[@id='collapse1']//li[contains(., 'Response Code:')]")).getText();
         String responseJson = driver.findElement(By.xpath("//div[@id='collapse1']//li[contains(., 'Response JSON:')]")).getText();

         ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", apiUrl);

         ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
         driver.switchTo().window(tabs.get(1)); // Switch to the newly opened tab
         String bodyText = driver.findElement(By.tagName("body")).getText();
         System.out.println("Body content: " + bodyText);
         JSONObject jsonObject = new JSONObject(bodyText);

         Assert.assertEquals(jsonObject.get("responseCode"),responseCode);

         driver.close();
         driver.switchTo().window(tabs.get(0));
     }




}
