
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;


public class MainTest {
    WebDriver driver ;
    @BeforeEach
    void start(){
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown (){
        driver.quit();
    }

      @Test
       void FirstTest () throws InterruptedException {

            final String BASE_URL = "https://avilon-master.elt-poisk.com/";
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();//На весь Экран
            driver.get(BASE_URL);
            //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


            WebElement login = driver.findElement(By.id("code"));//логин
            login.sendKeys("vvtestov");
            WebElement password = driver.findElement(By.id("pwd"));//Пароль
            password.sendKeys("12345");
            driver.findElement(By.id("btn")).click();
            Thread.sleep(3000);
            //тут iFrame
            try {


                  WebElement iframe = driver.findElement(By.cssSelector("#carrot-popup-frame"));
                  driver.switchTo().frame(iframe);//вход

                  WebElement popupClose = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                          ("//div[@id='carrotquest-messenger-body-big-cont']//div[contains(@class,'popup__close-button')]")));
                  popupClose.click();
            } catch (Exception e) {
                  //-
            }

            driver.switchTo().defaultContent();

            driver.findElement(By.id("ext-gen235")).click(); //Закрыть выбор ДЦ
            driver.findElement(By.id("ext-gen106")).click(); //клик Мультипродукт
            //String multiWelcome
            Assert.assertEquals(driver.getCurrentUrl(),
                    "https://avilon-master.elt-poisk.com/frontend/insurance/calculate" );


            Thread.sleep(3000);

            driver.quit();
      }


}