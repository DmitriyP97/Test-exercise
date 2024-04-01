package extensions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.AllureManager;
import config.ConfigurationManager;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Map;
import java.util.Set;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseKaspiShopExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        var tags = context.getTags();
        if (tags.contains("debug")) {
            ConfigurationManager.setDebugSettings();
        }

        open("https://uatks6.kaspi.kz/robots.txt");
        AllureManager.addBrowserInformationOnAllureReport();

        var cookies = createCookies(tags);
        setCookies(cookies);
    }

    protected Map<String, String> createCookies(final Set<String> tags) {
        return Map.of();
    }

    @Step("Проставить cookies")
    protected void setCookies(final Map<String, String> cookies) {
        if (cookies.isEmpty()) {
            return;
        }

        Selenide.executeJavaScript(generateSetCookiesJs(cookies));
        Selenide.refresh();
    }

    private String generateSetCookiesJs(final Map<String, String> cookies) {
        var jsCode = new StringBuilder();
        for (var cookie : cookies.entrySet()) {
            jsCode.append(String.format("document.cookie='%s=%s;path=/';", cookie.getKey(), cookie.getValue()));
        }
        return jsCode.toString();
    }

    @Step("Завершить тест")
    @Override
    public void afterEach(ExtensionContext context) {
        closeWebDriver();
    }
}