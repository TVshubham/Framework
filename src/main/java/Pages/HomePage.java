package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class HomePage {


    WebDriver driver;
    String url = "";


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






    }


