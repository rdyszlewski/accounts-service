package com.farfocle.accountsservice.validation;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class SignUpMessageCreator {

    private Properties properties;

    public SignUpMessageCreator(String filepath) throws IOException {
        File file = ResourceUtils.getFile(this.getClass().getResource("/"+filepath));
        properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        assert properties.size() == SignUpError.values().length;
    }

    public String getMessage(SignUpError error, String value){
        String message = (String) properties.get(error.name());
        if(value != null){
            message = message.replace("#", value);
        }
        return message;
    }
}
