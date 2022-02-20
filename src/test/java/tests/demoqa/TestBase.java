package tests.demoqa;

import com.codeborne.selenide.Configuration;
import config.Credentials;
import config.CredentialsConfig;
import helpers.Attach;
import io.qameta.allure.Step;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

public class TestBase {
    @BeforeAll
    @Step("Конфигурация параметров запуска тестов")
    static void setUp() {
        String login = Credentials.config.login();
        String password = Credentials.config.password();
        String browser = System.getProperty("browser");
        String browserVersion = System.getProperty("version"); // versions: chrome - 90, 91 opera - 76, 77 firefox - 88, 89
        String browserSize = System.getProperty("browserSize");
        String remoteUrl = System.getProperty("remoteUrl"); //Получаем значение из параметров Jenkins


        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = browserSize;
        Configuration.remote = format("https://%s:%s@%s", login, password, remoteUrl);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
