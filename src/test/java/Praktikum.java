import org.junit.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Praktikum {

    @Test
    public void test() {
        // создай драйвер для браузера Chrome
        // перейди на страницу тестового стенда
        open("https://qa-mesto.praktikum-services.ru/signin");
        // выполни авторизацию
        $(byName("name")).setValue("dmitri.procenko@gmail.com");
        $(byName("password")).setValue("12345678");
        $(byText("Войти")).click();

        String cardText = $$(byClassName("card")).get(1).getText();
        $(cardText).find(byClassName("card__title"));
        System.out.println(cardText);
    }
    // закрой браузер
}
