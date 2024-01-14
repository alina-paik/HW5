import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class DataProviderTests {
    private WebDriver webDriver;

    @BeforeMethod
    public void before() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://dou.ua/");
    }

    @AfterMethod
    public void after() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @DataProvider(name = "searchQuery")
    public Object[][] searchQuery() {
        return new Object[][] {
                {"SQL"},
                {"QA"},
                {"Java"},
                {"Ruby"}
        };
    }

    @Test(dataProvider = "searchQuery")
    public void search(String searchQuery) {
        webDriver.get("https://jobs.dou.ua/");

        WebElement searchInput = webDriver.findElement(By.xpath("//input[@name = 'search']"));
        searchInput.sendKeys(searchQuery);

        WebElement searchButton = webDriver.findElement(By.xpath("//input[@type = 'submit']"));
        searchButton.click();
    }

}
