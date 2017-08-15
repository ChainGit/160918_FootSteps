package com.tgweb.springmvc.common.intercepters;


import com.chain.utils.StringUtils;
import com.chain.utils.crypto.CryptoFactoryBean;
import com.chain.utils.crypto.RSAUtils;
import com.tgweb.springmvc.common.dictionary.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CryptoJsonIntercepter implements HandlerInterceptor {

    @Autowired
    private CryptoFactoryBean cryptoFactoryBean;

    private RSAUtils rsa;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (rsa == null)
            rsa = cryptoFactoryBean.getRsaUtils(true);

        String s = httpServletRequest.getParameter(Constant.JSON_KEY);

        if (StringUtils.isEmpty(s)) {

        } else {
            String decryptStr = rsa.decryptByPrivateKey(s);
            if (StringUtils.isEmpty(decryptStr))
                return false;
            httpServletRequest.setAttribute(Constant.JSON_KEY, decryptStr);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
