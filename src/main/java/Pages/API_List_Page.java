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

import static Reusable.Utility.WaitForVisibilityOfElement;
import static Reusable.Utility.clickElement;

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

    public @FindBy(xpath = "//u[text()='API 3: Get All Brands List'] ")
    WebElement API_3;

    public @FindBy(xpath = "//u[text()='API 9: DELETE To Verify Login']")
    WebElement API_4;

    public @FindBy(xpath = "//u[text()='API 4: PUT To All Brands List']")
    WebElement API_5;


    @FindBy(xpath = "//div[@id='collapse1']//li[contains(., 'API URL:')]//a")
    private WebElement apiUrlElement;

    @FindBy(xpath = "//div[@id='collapse1']//li[contains(., 'Request Method:')]")
    private WebElement requestMethodElement;

    @FindBy(xpath = "//div[@id='collapse1']//li[contains(., 'Response Code:')]")
    private WebElement responseCodeElement;

    @FindBy(xpath = "//div[@id='collapse1']//li[contains(., 'Response JSON:')]")
    private WebElement responseJsonElement;


    @FindBy(xpath = "//div[@id='collapse2']//li[contains(., 'API URL:')]//a")
    private WebElement apiUrlElement2;

    @FindBy(xpath = "//div[@id='collapse2']//li[contains(., 'Request Method:')]")
    private WebElement requestMethodElement2;

    @FindBy(xpath = "//div[@id='collapse2']//li[contains(., 'Response Code:')]")
    private WebElement responseCodeElement2;

    @FindBy(xpath = "//div[@id='collapse2']//li[contains(., 'Response Message:')]")
    private WebElement responseMessageElement2;


    // Locators for capturing each detail from the expanded view
    @FindBy(xpath = "//div[@id='collapse3']//li[contains(., 'API URL:')]//a")
    private WebElement apiUrlElement3;

    @FindBy(xpath = "//div[@id='collapse3']//li[contains(., 'Request Method:')]")
    private WebElement requestMethodElement3;

    @FindBy(xpath = "//div[@id='collapse3']//li[contains(., 'Response Code:')]")
    private WebElement responseCodeElement3;

    @FindBy(xpath = "//div[@id='collapse3']//li[contains(., 'Response JSON:')]")
    private WebElement responseJsonElement3;




     public void Verify_API_1_getAPIDetails() throws InterruptedException {

         WaitForVisibilityOfElement(driver ,apiUrlElement  );

         String apiUrl = apiUrlElement.getAttribute("href");

         WaitForVisibilityOfElement(driver , requestMethodElement );
         String requestMethod = requestMethodElement.getText();

         WaitForVisibilityOfElement(driver , responseCodeElement  );
         String responseCode = responseCodeElement.getText();

         WaitForVisibilityOfElement(driver , responseJsonElement  );
         String responseJson = responseJsonElement.getText();

         System.out.println("API URL: " + apiUrl);
         System.out.println("Request Method: " + requestMethod);
         System.out.println("Response Code: " + responseCode);
         System.out.println("Response JSON: " + responseJson);


         ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", apiUrl);
         ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
         driver.switchTo().window(tabs.get(1)); // Switch to the newly opened tab


         String bodyText = driver.findElement(By.tagName("body")).getText();
         System.out.println("Body content: " + bodyText);

         // Convert body content to JSON object
         JSONObject jsonObject = new JSONObject(bodyText);

         // Extract specific values from the JSON response
         String responseCodeFromJson = String.valueOf(jsonObject.get("responseCode"));

         // Verify if the responseCode captured from UI contains the value from JSON
         Assert.assertTrue(responseCode.contains(responseCodeFromJson));

         // Close the new tab and switch back to the original tab
         driver.close();
         driver.switchTo().window(tabs.get(0));
         Thread.sleep(1000);
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("window.scrollBy(0, 250);");  // Scrolls 250px downwards (y-axis)
         Thread.sleep(1000);
     }

     public void Verify_API_2_Post_APIDetails()
     {
         WaitForVisibilityOfElement(driver ,apiUrlElement2  );
         String apiUrl = apiUrlElement2.getAttribute("href");
         WaitForVisibilityOfElement(driver ,requestMethodElement2  );
         String requestMethod = requestMethodElement2.getText();
         WaitForVisibilityOfElement(driver ,requestMethodElement2  );
         String responseCode = responseCodeElement2.getText();
         WaitForVisibilityOfElement(driver ,responseMessageElement2  );
         String responseMessage = responseMessageElement2.getText();

         // Print captured details for verification
         System.out.println("API URL: " + apiUrl);
         System.out.println("Request Method: " + requestMethod);
         System.out.println("Response Code: " + responseCode);
         System.out.println("Response Message: " + responseMessage);

         // Open the API URL in a new tab
         ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", apiUrl);
         ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
         driver.switchTo().window(tabs.get(1)); // Switch to the new tab

         // Capture and parse the body content as JSON
         String bodyText = driver.findElement(By.tagName("body")).getText();
         System.out.println("Body content: " + bodyText);

         // Convert body content to JSON object
         JSONObject jsonObject = new JSONObject(bodyText);

         // Extract specific values from the JSON response
         String responseCodeFromJson = String.valueOf(jsonObject.get("responseCode"));

         System.out.println(responseCodeFromJson);
         // Verify if the responseCode captured from UI contains the value from JSON
         Assert.assertFalse(responseCode.contains(responseCodeFromJson));

         // Close the new tab and switch back to the original tab
         driver.close();
         driver.switchTo().window(tabs.get(0));
         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].scrollIntoView(true);",API_3 );

     }



    public void Verify_API_3_Get_APIDetails()
    {
        WaitForVisibilityOfElement(driver , apiUrlElement3  );
        String apiUrl = apiUrlElement3.getAttribute("href");
        WaitForVisibilityOfElement(driver ,  requestMethodElement3  );
        String requestMethod = requestMethodElement3.getText();
        WaitForVisibilityOfElement(driver , responseCodeElement3   );
        String responseCode = responseCodeElement3.getText();
        WaitForVisibilityOfElement(driver ,  responseJsonElement3  );
        String responseJson = responseJsonElement3.getText();

        System.out.println("API URL: " + apiUrl);
        System.out.println("Request Method: " + requestMethod);
        System.out.println("Response Code: " + responseCode);
        System.out.println("Response Message: " +  responseJson );

        // Open the API URL in a new tab
        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", apiUrl);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); // Switch to the new tab

        // Capture and parse the body content as JSON
        String bodyText = driver.findElement(By.tagName("body")).getText();
        System.out.println("Body content: " + bodyText);

        // Convert body content to JSON object
        JSONObject jsonObject = new JSONObject(bodyText);

        // Extract specific values from the JSON response
        String responseCodeFromJson = String.valueOf(jsonObject.get("responseCode"));

        System.out.println(responseCodeFromJson);
        // Verify if the responseCode captured from UI contains the value from JSON
        Assert.assertTrue(responseCode.contains(responseCodeFromJson));

        // Close the new tab and switch back to the original tab
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }




}
