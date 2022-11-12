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
    private final SelenideElement logoImg = $x("//img[@id='logo']");
    private final SelenideElement logInButton = $x("//button[@id='login-button']");
    private final SelenideElement userNameInput = $x("//input[@name='username']");
    private final SelenideElement passwordInput = $x("//input[@name='password']");
    private final SelenideElement smsInput = $x("//input[@id='otp-code']");
    private final SelenideElement codeButton = $x("//button[@id='login-otp-button']");
    private static final SelenideElement logoIn = $x("//a[@id='logo']");
    private static final String baseUrl = "https://idemo.bspb.ru/";
    private final SelenideElement Ava = $x("//a[@id='user-avatar']");
    private final SelenideElement ProvZ = $x("//*[@id='header']/div/div[1]");
    private final SelenideElement NewAva = $x("//div[@id='avatars']//img[@data-avatar='30.png']");
    private final SelenideElement ButSave = $x("//div[@class='form-actions']//button[@id='submit-button']");
    private final SelenideElement Error = $x("//div[@class='alert alert-error']");
    private final SelenideElement labelAva = $x("//div[@id='avatars-form']/label");
    private final SelenideElement cards = $x("//a[@id='cards-overview-index']");
    private final SelenideElement getPodt = $x("//*[@id='card-details-ownbank-10066']/div[2]/div[2]/div[1]");
    private final SelenideElement block = $x("//*[@id='card-details-ownbank-10066']/div[2]/div[2]/div[2]/div/div[2]/a/span");
    private final SelenideElement blockBut = $x("//*[@id='block-card']");
    private final SelenideElement smsCod = $x("//*[@id='otp-input']");
    private final SelenideElement butPodt =$x("//*[@id='confirm']");
    private final SelenideElement blockProv = $x("//*[@id='card-details-ownbank-10066']/div[2]/div[2]/div[1]");
    private final SelenideElement unBlockBut = $x("//*[@id='card-details-ownbank-10066']/div[2]/div[2]/div[2]/div/div[1]/a/span");

    @BeforeAll
    static void beforeConfig(){
        SelenideLogger.addListener("listenerAllure",new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.timeout = 3000; // Умное ожидание появление элемента на странице
        Configuration.browserSize = "1920x1080"; // Умно
    }
    @BeforeEach
    void before(){
        open(baseUrl);
        logoImg.shouldBe(visible);
    };

    @Test
    @Description("Тест смены аватарки")
    void avatarSm() {
        loginStep();
        CheckName();
        setNewAva();
    }

    @Test
    @Description("Тест блокировки банковской карты")
    void card() {
        loginStep();
        cardBlock();
        cardUnBlock();
    }
    @Step("Блокировка карты")
    void cardBlock() {
        cards.shouldBe(visible).click();
        getPodt.shouldHave(text("Действует"));
        block.shouldBe(visible).click();
        blockBut.shouldBe(visible).click();
        switchTo().frame($x("//*[@id='confirmation-frame']"));
        smsCod.shouldBe(visible).val("0000");
        butPodt.shouldBe(visible).click();
    }
    @Step("Разблокировка карты")
    void cardUnBlock() {
        blockProv.shouldHave(text("Заблокирована"));
        unBlockBut.shouldBe(visible).click();
        switchTo().frame($x("//*[@id='confirmation-frame']"));
        smsCod.shouldBe(visible).val("0000");
        butPodt.shouldBe(visible).click();
        getPodt.shouldHave(text("Действует"));

    }
    @Step("Авторизация")
     void loginStep() {
        open(baseUrl);
        logoImg.shouldBe(visible);
        userNameInput.shouldBe(visible).val("demo");
        passwordInput.shouldBe(visible).val("demo");
        logInButton.shouldBe(visible).click();
        smsInput.shouldBe(visible).val("0000");
        codeButton.shouldBe(visible).click();
        logoIn.shouldBe(visible);
    }

    @Step("Проверка")
     void CheckName() {
        ProvZ.shouldHave(text("банк"));
    }

    @Step("Новая аватарка")
    void setNewAva() {
        Ava.shouldBe(visible).click();
        switchTo().frame($x("(//div[@id='contentbar']/iframe)"));
        labelAva.shouldBe(visible);
        labelAva.shouldHave(text("Аватар"));
        NewAva.shouldBe(visible).click();
        ButSave.shouldBe(visible).click();
        Error.shouldBe(visible);
    }
    @Step("Выйти из фрейма")
    void frameOut(){
        switchTo().defaultContent();
        logoImg.shouldBe(visible).click();
    }

    @AfterEach
    void after() {
        closeWebDriver();
    }

}
