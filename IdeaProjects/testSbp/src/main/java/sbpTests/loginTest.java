package sbpTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
//import org.hamcrest.Matcher.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.containsString;


public class loginTest {
    BaseSteps bS = new BaseSteps();
    @BeforeAll
    static void beforeConfig(){
        SelenideLogger.addListener("listenerAllure",new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.timeout = 3000; // Умное ожидание появление элемента на странице
        Configuration.browserSize = "1920x1080"; // Умно
    }
    @BeforeEach
    void before(){
        open(bS.baseUrl);
        bS.logoImg.shouldBe(visible);
    };

    @Test
    @Description("Тест смены аватарки")
    void avatarSm() {
        bS.loginStep();
        bS.CheckName();
        bS.setNewAva();
    }

    @Test
    @Description("Тест блокировки банковской карты")
    void card() {
        bS.loginStep();
        bS.cardBlock();
        bS.cardUnBlock();
    }


    @AfterEach
    void after() {
        closeWebDriver();
    }

}
