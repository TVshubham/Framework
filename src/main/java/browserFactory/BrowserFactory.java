package browserFactory;



import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;



import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


public class BrowserFactory {

    static WebDriver driver;

    public static WebDriver getBrowserInstance()
    {
        return driver;
    }


    public static WebDriver startBrowser(String browserName,String applicationURL) throws MalformedURLException
    {


        if(browserName.contains("Chrome") || browserName.contains("GC") || browserName.contains("Google Chrome"))
        {


            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Run in headless mode
//            options.addArguments("--window-size=1920,1080"); // Optional: set window size

            // Initialize WebDriver with Chrome options
            driver = new ChromeDriver(options);
//            driver = new ChromeDriver();


        }





        else if(browserName.contains("Firefox"))
        {
            driver=new FirefoxDriver();
        }
        else if(browserName.contains("Safari"))
        {
            driver=new SafariDriver();
        }
        else if(browserName.contains("Edge"))
        {
            driver=new EdgeDriver();
        }
        else {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Run in headless mode
//            options.addArguments("--window-size=1920,1080"); // Optional: set window size

            // Initialize WebDriver with Chrome options
            WebDriver driver = new ChromeDriver(options);
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();



        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));

        driver.get(applicationURL);

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        return driver;
    }

}
