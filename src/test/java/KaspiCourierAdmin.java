import org.junit.jupiter.api.Test;
import pages.KaspiCourier;

import static com.codeborne.selenide.Selenide.*;
import static pages.KaspiCourier.*;

public class KaspiCourierAdmin {
    @Test
    public void loginCourierAdmin() {
        open(URL);
        KaspiCourier kaspiCourier = new KaspiCourier();
        kaspiCourier.EMAIL.setValue(EMAIL_INPUT);
        kaspiCourier.PASS.setValue(PASSWORD_INPUT);
        kaspiCourier.BUTTON_LOGIN.click();
        kaspiCourier.DELIVERY_REPORT.click();
        kaspiCourier.CALENDAR.click();
        kaspiCourier.TODAY_CALENDAR.doubleClick();
        kaspiCourier.APPLAY.click();
        sleep(5000);

    }
}
