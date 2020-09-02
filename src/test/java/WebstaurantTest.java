import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebstaurantTest {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.get("https://www.webstaurantstore.com/");
        driver.findElement(By.id("searchval")).sendKeys("stainless work table");
        driver.findElement(By.xpath("//button[@value='Search']")).click();
        List<WebElement> paginator = driver.findElements(By.xpath("//ul[@unselectable='unselectable']//li"));
        System.out.println(paginator.size());

        for (int k = 1; k <= paginator.size(); k++){
            System.out.println("Page #: " + k);
            List<WebElement> goods = driver.findElements(By.xpath("//div[@id='product_listing']//a[@class='description']"));


            for (int i = 0; i < goods.size(); i++) {

                System.out.println(goods.get(i).getText());
                String str = goods.get(i).getText();
                if (str.contains("Table")) {System.out.println("true");}
                else System.out.println("false");
                if (i==goods.size()-1) {
                    driver.findElement(By.name("addToCartButton")).click();
                }

            }
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,2200)", "");
            Thread.sleep(3000);
            driver.findElement(By.xpath("//button[contains(text(),'" + k + "')]")).click();



        }

        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(), 'View Cart')]")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Empty Cart')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(text(), 'Empty Cart')]")).click();
        Thread.sleep(7000);
        driver.quit();
    }
}
