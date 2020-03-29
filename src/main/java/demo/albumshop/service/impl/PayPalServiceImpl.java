/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.service.impl;

import demo.albumshop.paypal.PayPalConfig;
import demo.albumshop.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


/**
 *
 * @author rancha
 */
@PropertySources({
    @PropertySource("classpath:repository_config.properties")

})
@Service("payPalService")
public class PayPalServiceImpl implements PayPalService{
    @Autowired
    private Environment environment;

    @Override
    public PayPalConfig getPayPalConfig() {
        PayPalConfig payPalConfig = new PayPalConfig();
        payPalConfig.setAuthToken(environment.getProperty("paypal.authtoken"));
        payPalConfig.setPosturl(environment.getProperty("paypal.posturl"));
        payPalConfig.setBusiness(environment.getProperty("paypal.business"));
        payPalConfig.setReturnurl(environment.getProperty("paypal.returnurl"));
        return payPalConfig;
    }
    
}
