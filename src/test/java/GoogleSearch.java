import org.junit.jupiter.api.Test;
import pages.Google;

import static com.codeborne.selenide.Selenide.open;

public class GoogleSearch {
    @Test
    public void changeButton() {
        open(Google.URL, Google.class)
                .clickSearch();
        //$(By.linkText("Text Input")).scrollIntoView(true).click();
        //$(By.id("newButtonName")).setValue("new button name");
        //$(By.id("updatingButton")).click();
        //$(By.id("updatingButton")).shouldHave(text("new button name"));

    }
}
