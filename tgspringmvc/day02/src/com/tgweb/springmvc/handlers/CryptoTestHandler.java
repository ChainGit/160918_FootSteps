package com.tgweb.springmvc.handlers;


import com.chain.utils.crypto.CryptoFactoryBean;
import com.chain.utils.crypto.RSAUtils;
import com.tgweb.springmvc.common.domain.JsonMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;

@RequestMapping("day02")
@Controller
public class CryptoTestHandler {

    @Autowired
    private CryptoFactoryBean cryptoFactoryBean;

    @ResponseBody
    @RequestMapping(value = "/testrsa", method = RequestMethod.POST)
    public JsonMap testRsa(@RequestBody @RequestParam("s") JsonMap map) {
        System.out.println("json: " + map);

        String text = (String) map.get("t");

        Instant start = Instant.now();

        RSAUtils rsa = cryptoFactoryBean.getRsaUtils(true);
        String entext = rsa.encryptByPrivateKey(text);
        System.out.println(entext);
        String detext = rsa.decryptByPublicKey(entext);
        System.out.println(detext);
        Instant end = Instant.now();

        System.out.println("spendTime: " + Duration.between(end, start).getNano());

        JsonMap result = new JsonMap();
        result.put("r", detext);

        return result;
    }
}
