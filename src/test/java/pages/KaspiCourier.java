package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class KaspiCourier {
    public static final String URL = "https://uatkl.kaspi.kz/courier/admin/account/login";
    public static final String EMAIL_INPUT = "dmitrii.protsenko@kaspi.kz";
    public static final String PASSWORD_INPUT= "12345678";
    public SelenideElement PASS = $("#password");
    public SelenideElement EMAIL = $("#emailaddress");
    public SelenideElement BUTTON_LOGIN = $(".btn").shouldBe(text("Войти"));
    public SelenideElement TODAY_CALENDAR = $("[data-date='" + localDate() + "']");
    public SelenideElement CALENDAR = $(".reportrange-text");
    public SelenideElement DELIVERY_REPORT = $(byText("Отчет о доставках"));
    public SelenideElement APPLAY = $(".applyBtn");
    public String localDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        return formattedDate;
    }

}

