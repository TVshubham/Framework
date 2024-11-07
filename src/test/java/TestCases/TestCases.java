package TestCases;

import Base.BaseClass;
import Pages.API_List_Page;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Reusable.Utility.WaitForVisibilityOfElement;
import static Reusable.Utility.clickElement;

public class TestCases extends BaseClass {



    @Test(description = "Verify API Documentation",priority =1)
    public void Testcase1() throws InterruptedException {

        HomePage home = new HomePage(driver);
        WaitForVisibilityOfElement(driver ,home.API_List_tab );
        clickElement(driver ,home.API_List_tab);
        API_List_Page apiListPage = new API_List_Page(driver);
        WaitForVisibilityOfElement(driver , apiListPage.API_1 );
        clickElement(driver , apiListPage.API_1);
        apiListPage.Verify_API_1_getAPIDetails();
        WaitForVisibilityOfElement(driver , apiListPage.API_2 );

        try {
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='grippy-host']")).click();
        } catch (Exception e) {

        }
        clickElement(driver , apiListPage.API_2);
        apiListPage.Verify_API_2_Post_APIDetails();
        WaitForVisibilityOfElement(driver , apiListPage.API_3 );
        try {
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='grippy-host']")).click();
        } catch (Exception e) {

        }
        clickElement(driver , apiListPage.API_3);
        apiListPage.Verify_API_3_Get_APIDetails();

    }
    @Test(description = "Verify Home Page Elements ",priority =2)
    public void TestCase2()
    {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.areAllMenuOptionsVisible() , "Not all menu options are visible!");

        Assert.assertTrue(home.hasThreeSlides(), "The carousel does not contain exactly 3 slides!");

        Assert.assertTrue(home.hasThreeCategorySections(), "The categories section does not contain exactly 3 categories!");

        Assert.assertTrue(home.hasEightBrandLogos(), "The brands section does not contain exactly 8 brand logos!");

        Assert.assertTrue(home.areFeaturedProductsDisplayed());

        Assert.assertTrue(home.isSubscriptionSectionDisplayed()) ;

    }

    @Test(description = "Verify Shopping Cart Flow for T-shirts",priority =3)
    public void Testcase3() throws InterruptedException {
          HomePage homePage = new HomePage( driver);
          Assert.assertTrue( homePage.checkTshirtText());
          homePage.check_Shopping_Cart_Flow ("Pure Cotton Neon Green Tshirt","Premium Polo T-Shirts");

    }


}
