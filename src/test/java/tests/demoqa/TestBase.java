package tests.demoqa;

import com.codeborne.selenide.Configuration;
import helpers.Attach;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    @Step("Конфигурация параметров запуска тестов")
    static void setUp() {
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        String url = System.getProperty("url");
        String browser = System.getProperty("browser");
        String browserVersion = System.getProperty("version"); // versions: chrome - 90, 91 opera - 76, 77 firefox - 88, 89
        String browserSize = System.getProperty("browserSize");
        String remoteUrl = "https://" + login + ":" + password + "@" + url;

        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = browserSize;
        Configuration.remote = remoteUrl;

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
