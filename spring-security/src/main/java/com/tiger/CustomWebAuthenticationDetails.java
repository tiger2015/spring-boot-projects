package com.tiger;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CustomWebAuthenticationDetails
 * @Description TODO
 * @Author zeng.h
 * @Date 2019/11/16 17:14
 * @Version 1.0
 **/
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private final String verifyCode;

    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        verifyCode = request.getParameter("verifyCode");
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; VerifyCode: ").append(this.getVerifyCode());
        return sb.toString();
    }
}
