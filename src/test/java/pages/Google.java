package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Google {
    public static final String URL = "https://www.google.kz";

    @FindBy(how = How.LINK_TEXT, using="Text Input")
    private SelenideElement searchString;

    public Google clickSearch() {
        searchString.click();
        return this;
    }
}
