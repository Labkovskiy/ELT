package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

  final String BASE_URL = "https://avilon-master.elt-poisk.com/";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();//На весь Экран
        driver.get(BASE_URL);
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        WebElement login =  driver.findElement(By.id("code"));//логин
        login.sendKeys("vvtestov");
        WebElement password =  driver.findElement(By.id("pwd"));//Пароль
        password.sendKeys("12345");
        driver.findElement(By.id("btn")).click();
        Thread.sleep(3000);
        //тут iFrame
        WebElement iframe = driver.findElement(By.cssSelector("#carrot-popup-frame"));
        driver.switchTo().frame(iframe);//вход

            WebElement popupClose = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                    ("//div[@id='carrotquest-messenger-body-big-cont']//div[contains(@class,'popup__close-button')]")));
            popupClose.click();

        driver.switchTo().defaultContent();

        driver.findElement(By.id("ext-gen106")).click();//клик Мультипродукт



        driver.quit();
    }
}