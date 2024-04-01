package extensions;

import config.ConfigurationManager;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class KaspiShopExtension extends BaseKaspiShopExtension {

    private static String ssoTicket;

    protected Map<String, String> createCookies(Set<String> tags) {
        var cookies = new HashMap<String, String>() {
            {
                put("is_mobile_app", "true");
                put("is_test_auth", "true");
                put("installId", "asd-asd-asd");
                put("mobapp_version", "40");
                put("ma_ver", "5.34-DEBUG");
                put("ks.tg", "192");
                put("kaspi.storefront.cookie.city", "750000000");
            }
        };
        if (tags.contains("needLogin")) {
            cookies.put("ticket", getSsoTicket());
        }
        if (tags.contains("needs-installId")) {
            cookies.put("installId", String.valueOf(ConfigurationManager.configuration().testInstallId()));
        }
        if (tags.contains("hundred-items-cart-ready")) {
            cookies.put("ks.cart", "64be316e1fcd127a4d925c79");
        }
        if (tags.contains("express-delivery-slots")) {
            cookies.put("ks.ab", "express-delivery-slots=2");
        }

        return cookies;
    }

    private synchronized String getSsoTicket() {
        if (ssoTicket != null) {
            return ssoTicket;
        }

        var signInService = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(KaspiSignInService.class, "https://test.kaspi.kz");
        var signInRequest = new KaspiSignInService.SignInRequest();
        signInRequest.data = new KaspiSignInService.SignInData();
        signInRequest.data.login = "7752730978";
        signInRequest.data.password = "12345678";
        signInRequest.data.clientAuthType = "password";
        signInRequest.data.retrieverId = "password";

        signInRequest.deviceInfo = new KaspiSignInService.DeviceInfo();
        signInRequest.deviceInfo.installId = "617ba8df-9be939209e61-0dd920";
        signInRequest.deviceInfo.deviceId = "3aab636c44";
        signInRequest.deviceInfo.platformType = "ANDROID";
        signInRequest.deviceInfo.platformVersion = "12";
        signInRequest.deviceInfo.appVersion = "5.34-DEBUG";
        signInRequest.deviceInfo.appBuild = "545";
        signInRequest.deviceInfo.deviceBrand = "google";
        signInRequest.deviceInfo.deviceModel = "sdk_gphone64_arm64";

        var signInResponse = signInService.signIn(signInRequest);
        if (signInResponse.message != null) {
            signInRequest.data.smsCode = "9999";
            signInResponse = signInService.signIn(signInRequest);
        }
        System.out.println(signInResponse.data.ssoTicket);

        ssoTicket = signInResponse.data.ssoTicket;
        return signInResponse.data.ssoTicket;
    }
}