package com.thebest.thebestpc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

public class DetailPageTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoadProductDetailPage() throws InterruptedException {
        long productId = 1L; // giả sử sản phẩm ID 1 luôn tồn tại
        driver.get("http://localhost:9090/TheBestPc/detail/" + productId);
        Thread.sleep(1000);

        WebElement title = driver.findElement(By.id("productName"));
        WebElement price = driver.findElement(By.id("productPrice"));
        WebElement stock = driver.findElement(By.id("productStock"));

        Assertions.assertFalse(title.getText().isEmpty(), "Tên sản phẩm không hiển thị");
        Assertions.assertFalse(price.getText().isEmpty(), "Giá sản phẩm không hiển thị");
        Assertions.assertTrue(stock.getText().contains("sản phẩm có sẵn"), "Tồn kho không đúng định dạng");
    }

    @Test
    public void testAddToCartAnonymousUser() throws InterruptedException {
        long productId = 1L;
        driver.get("http://localhost:9090/TheBestPc/detail/" + productId);
        Thread.sleep(1000);

        WebElement buyNowButton = driver.findElement(By.cssSelector(".buy-now"));
        buyNowButton.click();

        Thread.sleep(1500);
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("success=Thêm+vào+giỏ+hàng+thành+công"), "Không hiện success trên URL");

        WebElement notification = driver.findElement(By.cssSelector("#successNotification .tb-notification-content span span"));
        String notiText = notification.getText().trim();
        Assertions.assertEquals("Thêm vào giỏ hàng thành công", notiText);
    }

    @Test
    public void testAddToCartAsLoggedInUser() throws InterruptedException {
        login("valid@mail.com", "123456");

        long productId = 1L;
        driver.get("http://localhost:9090/TheBestPc/detail/" + productId);
        Thread.sleep(1000);

        WebElement buyNowButton = driver.findElement(By.cssSelector(".buy-now"));
        buyNowButton.click();
        Thread.sleep(1500);

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.contains("success=Thêm+vào+giỏ+hàng+thành+công"), "Không hiện success sau khi login");

        logout();
    }

    private void login(String email, String password) throws InterruptedException {
        driver.get("http://localhost:9090/TheBestPc/login");
        Thread.sleep(1000);

        driver.findElement(By.id("username-login")).sendKeys(email);
        driver.findElement(By.id("password-login")).sendKeys(password);
        driver.findElement(By.id("button-login")).click();
        Thread.sleep(1500);
    }

    private void logout() {
        driver.get("http://localhost:9090/TheBestPc/logout");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
