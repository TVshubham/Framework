import Base.BaseClass;
import Pages.API_List_Page;
import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static Reusable.Utility.WaitForVisibilityOfElement;
import static Reusable.Utility.clickElement;

public class TestCases extends BaseClass {



    @Test
    public void testcase1() {
        HomePage home = new HomePage(driver);
        WaitForVisibilityOfElement(driver ,home.API_List_tab );
        clickElement(driver ,home.API_List_tab);
        API_List_Page apiListPage = new API_List_Page(driver);
        WaitForVisibilityOfElement(driver , apiListPage.API_1 );
        clickElement(driver , apiListPage.API_1);
        apiListPage.Verify_API_1_getAPIDetails();
        WaitForVisibilityOfElement(driver , apiListPage.API_2 );
        clickElement(driver , apiListPage.API_2);





    }
}
