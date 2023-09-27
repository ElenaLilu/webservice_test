package ru.netology;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebServiceTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setupAll() {

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void OpenPage() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Екатерина Иванова");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79352746655");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        Assertions.assertEquals( "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",
        driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText());

    }

    @Test
    void invalidName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ekaterina Ivanova");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79352746655");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText());

    }

    @Test
    void numbersInsteadOfLettersInName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("+79352746655");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79352746655");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText());

    }

    @Test
    void invalidPhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Екатерина Иванова");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+793527466555");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText());

    }

    @Test
    void phoneWithoutPlus() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Екатерина Иванова");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("79352746655");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText());

    }

    @Test
    void lessNumbersThan11() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Екатерина Иванова");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7935274665");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText());

    }

    @Test
    void lettersInsteadOfNumbersInPhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Екатерина Иванова");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("dhdkeekl");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",
                driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText());

    }
    }

