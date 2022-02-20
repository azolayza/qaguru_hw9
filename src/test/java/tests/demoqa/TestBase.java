package tests.demoqa;

import com.codeborne.selenide.Configuration;
import config.Credentials;
import helpers.Attach;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

public class TestBase {
    @BeforeAll
    @Step("Конфигурация параметров запуска тестов")
    static void setUp() {
        String remoteUrl = System.getProperty("remoteUrl"); //Получаем значение из параметров Jenkins
        String login = Credentials.config.login();
        String password = Credentials.config.password();

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "91");
        Configuration.browserSize = System.getProperty("browserSize", "1024x768");
        Configuration.remote = format("https://%s:%s@%s", login, password, remoteUrl);


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
