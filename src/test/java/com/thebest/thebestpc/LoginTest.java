package com.thebest.thebestpc;

import com.thebest.thebestpc.dto.LoginUserDto;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, String expectedUrl, String expectedErrorMessage) throws InterruptedException {
        driver.get("http://localhost:9090/TheBestPc/login");
        Thread.sleep(1000);

        WebElement usernameInput = driver.findElement(By.id("username-login"));
        WebElement passwordInput = driver.findElement(By.id("password-login"));
        WebElement loginButton = driver.findElement(By.id("button-login"));

        usernameInput.clear();
        passwordInput.clear();
        usernameInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();

        Thread.sleep(1500); // đợi trang load

        // Nếu có expectedErrorMessage => phải check nội dung thông báo lỗi
        if (expectedErrorMessage != null && !expectedErrorMessage.isEmpty()) {
            WebElement errorElement = driver.findElement(By.cssSelector("#errorNotification .tb-notification-content span span"));
            String actualErrorMessage = errorElement.getText().trim();
            Assertions.assertEquals(expectedErrorMessage, actualErrorMessage,
                    "Nội dung thông báo không đúng. Mong đợi: " + expectedErrorMessage);
        }
        else {
            // Không có lỗi => kiểm tra redirect
            String currentUrl = driver.getCurrentUrl();
            Assertions.assertTrue(currentUrl.endsWith(expectedUrl), "Không redirect đến đúng URL. Mong đợi: " + expectedUrl);
            // logout lại nếu login thành công
            driver.get("http://localhost:9090/TheBestPc/logout");
        }
    }

    @Test
    public void testAccessLoginWhenAlreadyLoggedIn() throws InterruptedException {
        driver.get("http://localhost:9090/TheBestPc/login");

        // login thành công
        driver.findElement(By.id("username-login")).sendKeys("valid@mail.com");
        driver.findElement(By.id("password-login")).sendKeys("123456");
        driver.findElement(By.id("button-login")).click();
        Thread.sleep(1500);

        // vào lại login
        driver.get("http://localhost:9090/TheBestPc/login");
        Thread.sleep(1000);

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.endsWith("/"), "Đã đăng nhập mà không redirect về trang chủ");

        // logout lại
        driver.get("http://localhost:9090/TheBestPc/logout");
    }


    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
                // Đăng nhập thành công
                {"valid@mail.com", "123456", "/banner", null},

                // Sai mật khẩu
                {"valid@mail.com", "999999", null, "Sai mật khẩu hoặc tài khoản"},

                // Email không tồn tại
                {"unknown@mail.com", "123456", null, "Tài khoản không tìm thấy"},

                // Tài khoản bị khóa
                {"locked@mail.com", "123456", null, "Tài khoản bị khóa"},
        };
    }


    @AfterClass
    public void teardowm() {
        if (driver != null) driver.quit();
    }
}
