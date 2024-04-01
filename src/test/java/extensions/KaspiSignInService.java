package extensions;

import feign.Headers;
import feign.RequestLine;

public interface KaspiSignInService {

    @RequestLine("POST /sessions/api/v1/ExtSession/SignIn")
    @Headers("Content-Type: application/json")
    SignInResponse signIn(SignInRequest request);

    class SignInRequest {
        public SignInData data;
        public DeviceInfo deviceInfo;
    }

    class SignInData {
        public String login;
        public String password;
        public String clientAuthType;
        public String retrieverId;
        public String smsCode;
    }

    class DeviceInfo {
        public String installId;
        public String deviceId;
        public String platformType;
        public String platformVersion;
        public String appVersion;
        public String appBuild;
        public String deviceBrand;
        public String deviceModel;
    }

    class SignInResponse {
        public String message;
        public User data;
    }

    class User {
        public String firstName;
        public String lastName;
        public String ssoTicket;
    }

}