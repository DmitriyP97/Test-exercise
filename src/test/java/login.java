import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class login {
    @Test
    public void changeButton() {
        open("http://uitestingplayground.com/");
        $(By.linkText("Text Input")).scrollIntoView(true).click();
        $(By.id("newButtonName")).setValue("new button name");
        $(By.id("updatingButton")).click();
        $(By.id("updatingButton")).shouldHave(text("new button name"));

    }
    @Test
    public void loginUser() {
        open("http://uitestingplayground.com/");
        $(By.linkText("Sample App")).scrollIntoView(true).click();
        $(By.name("UserName")).setValue("Dima Pro");
        $(By.name("Password")).setValue("pwd");
        $(By.id("login")).click();
        $(By.id("login")).shouldHave(text("Log Out"));
    }
}
