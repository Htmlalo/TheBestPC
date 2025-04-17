package com.thebest.thebestpc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

public class RegisterTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "registerData")
    public void testRegister(String fullName, String email, String password,
                             String expectedErrorMessage, boolean expectSuccess) throws InterruptedException {
        driver.get("http://localhost:9090/TheBestPc/login");
        Thread.sleep(1000);

        // Bật form đăng ký
        WebElement toggleBtn = driver.findElement(By.className("toggle-btn"));
        toggleBtn.click();
        Thread.sleep(1000);

        WebElement nameInput = driver.findElement(By.id("username-register"));
        WebElement emailInput = driver.findElement(By.id("email-register"));
        WebElement passInput = driver.findElement(By.id("password-register"));
        WebElement registerBtn = driver.findElement(By.id("button-register"));

        nameInput.clear();
        emailInput.clear();
        passInput.clear();

        nameInput.sendKeys(fullName);
        emailInput.sendKeys(email);
        passInput.sendKeys(password);
        registerBtn.click();
        Thread.sleep(1500);

        if (expectSuccess) {
            // Thành công → redirect đến trang login + hiển thị thông báo

            WebElement noti = driver.findElement(By.cssSelector(".tb-notification-content"));
            Assertions.assertTrue(noti.getText().toLowerCase().contains("thành công"), "Không có thông báo thành công");
        } else {
            WebElement error = driver.findElement(By.cssSelector(".tb-notification-content"));
            String actualError = error.getText().trim();
            Assertions.assertEquals(expectedErrorMessage, actualError, "Thông báo lỗi không khớp");
        }
    }

    @DataProvider(name = "registerData")
    public Object[][] getRegisterData() {
        return new Object[][]{
                // TC_LOGIN_006: Thành công
                {"Nguyễn Văn A", "test@example.com", "12345678", null, true},

                // TC_LOGIN_007: Email sai định dạng
                {"Nguyễn Văn A", "abc@loz", "12345678", "Email không đúng định dạng", false},

                // TC_LOGIN_008: Password quá ngắn
                {"Nguyễn Văn A", "test@example.com", "123", "Mật khẩu phải từ 6 đến 100 ký tự", false},

                // TC_LOGIN_009: Họ tên quá dài
                {new String(new char[101]).replace("\0", "A"), "test@example.com", "12345678", "Họ tên tối đa 100 ký tự", false},

                // TC_LOGIN_010: Email đã tồn tại
                {"Nguyễn Văn A", "test@example.com", "12345678", "Email đã được sử dụng", false},
        };
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
