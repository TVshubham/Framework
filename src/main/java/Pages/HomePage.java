package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.List;

import static Reusable.Utility.*;

public class HomePage {


    WebDriver driver;
    String url = "";
    String productname ;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void navigateToHomePage() {
        //   System.out.println(driver.getCurrentUrl());
        if (!driver.getCurrentUrl().equals(this.url)) {
            driver.get(this.url);
        }

    }

  public @FindBy(xpath = "//button[text()='APIs list for practice']")
    WebElement API_List_tab;

    @FindBy(xpath = "//a[@href='/' and contains(text(), 'Home')]")
    private WebElement homeLink;

    @FindBy(xpath = "//a[@href='/products' and contains(text(), 'Products')]")
    private WebElement productsLink;

    @FindBy(xpath = "//a[@href='/view_cart' and contains(text(), 'Cart')]")
    private WebElement cartLink;

    @FindBy(xpath = "//a[@href='/login' and contains(text(), 'Signup / Login')]")
    private WebElement signupLoginLink;

    @FindBy(xpath = "//a[@href='/test_cases' and contains(text(), 'Test Cases')]")
    private WebElement testCasesLink;

    @FindBy(xpath = "//a[@href='/api_list' and contains(text(), 'API Testing')]")
    private WebElement apiTestingLink;

    @FindBy(xpath = "//a[@href='https://www.youtube.com/c/AutomationExercise' and contains(text(), 'Video Tutorials')]")
    private WebElement videoTutorialsLink;

    @FindBy(xpath = "//a[@href='/contact_us' and contains(text(), 'Contact us')]")
    private WebElement contactUsLink;

    @FindBy(xpath = "//div[@class='carousel-inner']/div[@class='item']")
    private List<WebElement> carouselSlides;


    @FindBy(xpath = "//div[@class='panel-group category-products']/div[@class='panel panel-default']")
    private List<WebElement> categorySections;

    @FindBy(xpath = "//div[@class= 'features_items']//div[@class='col-sm-4']")
    private List<WebElement> featuredProducts;


    // Locator for each brand in the brands section
    @FindBy(xpath = "//div[@class='brands-name']/ul[@class='nav nav-pills nav-stacked']/li")
    private List<WebElement> brandLogos;


    @FindBy(xpath = "//footer//input[@id='susbscribe_email']")
    private WebElement subscriptionEmailTextbox;

    // Locate the footer subscription submit button
    @FindBy(xpath = "//footer//button[@id='subscribe']")
    private WebElement subscriptionSubmitButton;

    @FindBy(xpath = "//*[@id=\"accordian\"]/div[2]//h4/a/span")
    private WebElement Men_Button;


    @FindBy(xpath = "//div[@id=\"Men\"]/div/ul/li[1]/a")
    private WebElement Tshirt_Button;


    @FindBy(xpath = "//div[@id='cartModal']/following-sibling::div[@class='col-sm-4']/div/div/div/p")
    private List<WebElement> List_of_Tshirts;


    @FindBy(xpath = "//img[@alt='ecommerce website products']/..//p[text()='Pure Cotton Neon Green Tshirt']")
    private WebElement Pure_Cotton_Neon_Green_Tshirt;

    @FindBy(xpath = "//h4[text()='Added!']")
    private WebElement  Added_Message;

    @FindBy(xpath = "//button[text()='Continue Shopping']")
    private WebElement   Continue_Shopping_Button;





    // Method to verify if there are exactly 8 brand logos displayed
    public boolean hasEightBrandLogos() {
        return brandLogos.size() == 8;
    }

    // Method to verify if there are exactly 3 category sections
    public boolean hasThreeCategorySections() {
        return categorySections.size() == 3;
    }
    // Method to verify if there are exactly 3 slides in the carousel
    public boolean hasThreeSlides() {
        return carouselSlides.size() == 3;
    }

    public boolean areFeaturedProductsDisplayed() {
        return !featuredProducts.isEmpty();
    }







    public boolean areAllMenuOptionsVisible() {
        return homeLink.isDisplayed() &&
                productsLink.isDisplayed() &&
                cartLink.isDisplayed() &&
                signupLoginLink.isDisplayed() &&
                testCasesLink.isDisplayed() &&
                apiTestingLink.isDisplayed() &&
                videoTutorialsLink.isDisplayed() &&
                contactUsLink.isDisplayed();
    }


    public void scrollToFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", subscriptionEmailTextbox);
    }

    // Method to verify the subscription elements are displayed
    public boolean isSubscriptionSectionDisplayed() {
        scrollToFooter();
        return subscriptionEmailTextbox.isDisplayed() && subscriptionSubmitButton.isDisplayed();
    }


    public boolean checkTshirtText() {
        // Iterate through each WebElement in the List
        boolean result = false ;
        WaitForVisibilityOfElement(driver , Men_Button  );
        scrollToElement(driver,Men_Button);
        clickElement(driver ,Men_Button);

        WaitForVisibilityOfElement(driver , Tshirt_Button  );
        clickElement(driver , Tshirt_Button);
        for (WebElement tshirtElement : List_of_Tshirts) {
            // Get the text from each WebElement and check if it contains "tshirt"
            if (tshirtElement.getText().toLowerCase().contains("shirt")) {
                result = true ;
                System.out.println("Found a tshirt: " + tshirtElement.getText());
            } else {
                result =false ;
//                System.out.println("Not a tshirt: " + tshirtElement.getText());
            }

        }
        return result ;
    }



    public boolean check_VisibiltiyOf_Tshirt(String productname)
    {
        WebElement product = driver.findElement(By.xpath("//img[@alt='ecommerce website products']/..//p[text()='"+productname+"']"));
        WaitForVisibilityOfElement(driver ,  product  );
        scrollToElement(driver, product);

        WebElement Add_to_CartButton =driver.findElement(By.xpath("   //img[@alt='ecommerce website products']/..//p[text()='"+productname+"']/../a"));
        WaitForVisibilityOfElement(driver ,  Add_to_CartButton  );

        WebElement View_product_details =driver.findElement(By.xpath("   //img[@alt='ecommerce website products']/..//p[text()='"+productname+"']/../../following-sibling::div/ul/li/a"));
        WaitForVisibilityOfElement(driver ,View_product_details);

        Actions actions = new Actions(driver);
        // Perform the hover action
        actions.moveToElement(product).perform();
        Assert.assertTrue(product.isDisplayed());

        clickElement(driver,Add_to_CartButton);
        WaitForVisibilityOfElement(driver , Added_Message);
        Assert.assertTrue(Added_Message.isDisplayed());

//        WaitForVisibilityOfElement(driver ,Continue_Shopping_Button);
//        clickElement(driver,Continue_Shopping_Button);
//
//        WebElement Add_to_CartButton2 =driver.findElement(By.xpath("   //img[@alt='ecommerce website products']/..//p[text()='Green Side Placket Detail T-Shirt']/../a"));
//        WaitForVisibilityOfElement(driver ,  Add_to_CartButton2  );













        return product.isDisplayed()
                && View_product_details.isDisplayed()
                &&Add_to_CartButton.isDisplayed();
    }






    }


