package com.farfocle.accountsservice.configuration;

import com.farfocle.accountsservice.password_validator.PasswordValidator;
import com.farfocle.accountsservice.password_validator.rules.*;
import com.farfocle.accountsservice.user_validator.UserValidator;
import com.farfocle.accountsservice.user_validator.rules.Rule;
import com.farfocle.accountsservice.user_validator.rules.*;
import com.farfocle.accountsservice.validation.SignUpMessageCreator;
import com.farfocle.accountsservice.validation.UserExistenceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class ValidationConfiguration {

    @Autowired
    private UserExistenceAdapter userExistenceAdapter;

    @Bean
    public UserValidator getUserValidator() {
        List<Rule> rules = Arrays.asList(
                new MinUsernameLengthRule(4, true),
                new MaxUsernameLengthRule(20, true),
                new UsernameTakenRule(userExistenceAdapter, true),
                new EmailTakenRule(userExistenceAdapter, true),
                new EmailFormatRule()
        );
        return new UserValidator(rules);
    }

    @Bean
    public PasswordValidator getPasswordValidator() {
        List<com.farfocle.accountsservice.password_validator.rules.Rule> rules = Arrays.asList(
                new MinLengthRule(8, true),
                new MaxLengthRule(25, true),
                new LowercaseRule(1),
                new UppercaseRule(1),
                new DigitsRule(1),
                new WhitespaceRule(),
                new UsernameRule()
        );
        return new PasswordValidator(rules);
    }

    @Bean
    public SignUpMessageCreator getSignUpMessageCreator() throws IOException {
        return new SignUpMessageCreator("validation-messages-pl.properties");
    }
}
