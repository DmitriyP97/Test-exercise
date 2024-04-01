package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AllureManager {

    private static final Logger log = LoggerFactory.getLogger(AllureManager.class);

    private static final String ALLURE_RESULTS_PATH = "build/allure-results/";
    private static final ConcurrentSkipListSet<String> browsers = new ConcurrentSkipListSet<>();

    private AllureManager() {
    }

    public static void setAllureEnvironmentInformation() {
        var timeout = ConfigurationManager.configuration().globalTimeout() != null ? ConfigurationManager.configuration().globalTimeout() : Configuration.timeout;
        var browser = ConfigurationManager.configuration().browser() != null ? ConfigurationManager.configuration().browser() : Configuration.browser;

        var basicInfo = new LinkedHashMap<String, String>() {{
            put("Test URL", ConfigurationManager.configuration().baseUrl());
            put("Environment", ConfigurationManager.configuration().environment());
            put("Target execution", ConfigurationManager.configuration().targetExecution());
            put("Global timeout", timeout / 1000 + "s");
            put("Used Browsers", browsers.toString());
            put("Browser", browser);
            put("Tags", System.getenv("GRADLE_JUNIT_TAGS"));
            put("http.proxyHost", System.getProperty("http.proxyHost"));
            put("http.proxyPort", System.getProperty("http.proxyPort"));
            put("http.nonProxyHosts", System.getProperty("http.nonProxyHosts"));
            put("HTTP_PROXY", System.getenv("HTTP_PROXY"));
            put("HTTPS_PROXY", System.getenv("HTTPS_PROXY"));
            put("NO_PROXY", System.getenv("NO_PROXY"));
        }};

        writeAllureEnvironment(basicInfo);
    }


    private static void writeAllureEnvironment(Map<String, String> environmentValuesSet) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element environment = doc.createElement("environment");
            doc.appendChild(environment);
            environmentValuesSet.forEach((k, v) -> {
                Element parameter = doc.createElement("parameter");
                Element key = doc.createElement("key");
                Element value = doc.createElement("value");
                key.appendChild(doc.createTextNode(k));
                value.appendChild(doc.createTextNode(v));
                parameter.appendChild(key);
                parameter.appendChild(value);
                environment.appendChild(parameter);
            });

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File allureResultsDir = new File(ALLURE_RESULTS_PATH);
            if (!allureResultsDir.exists()) allureResultsDir.mkdirs();
            StreamResult result = new StreamResult(new File(ALLURE_RESULTS_PATH + "environment.xml"));
            transformer.transform(source, result);
            log.info("Allure environment data saved.");
        } catch (ParserConfigurationException | TransformerException pce) {
            log.warn("Failed to save allure environment file", pce);
        }
    }

    @Attachment(value = "Browser information", type = "text/plain")
    public static String addBrowserInformationOnAllureReport() {
        var cap = ((RemoteWebDriver) getWebDriver()).getCapabilities();
        var browserName = cap.getBrowserName();
        var platform = cap.getPlatformName().toString();
        var version = cap.getBrowserVersion();
        browsers.add(String.format("%s/%s/%s", browserName, version, platform));

        return String.format("browser: %s\nversion: %s\nplatform: %s", browserName, version, platform);
    }

    @Attachment
    public static byte[] takeScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}