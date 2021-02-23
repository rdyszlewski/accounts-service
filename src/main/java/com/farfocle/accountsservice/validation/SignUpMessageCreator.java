package com.farfocle.accountsservice.validation;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

// TODO: przemyśleć jak to przetestować. Przemyśleć też, jak to ma działać z wieloma językami
public class SignUpMessageCreator {

    private Properties properties;

    public SignUpMessageCreator(String filepath) throws IOException {
        Resource resource = new ClassPathResource(filepath);
        properties = new Properties();
        properties.load(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
    }

    public String getMessage(SignUpError error, String value){
        String message = (String) properties.get(error.name());
        if(value != null){
            message = message.replace("#", value);
        }
        return message;
    }
}
