package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.validation.exceptions.register.EmailIsTakenException;
import com.farfocle.accountsservice.validation.exceptions.register.EmptyUsernameException;
import com.farfocle.accountsservice.validation.exceptions.register.InvalidLoginException;
import org.passay.*;
import org.passay.PasswordValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RulePasswordValidator {

    private PasswordValidator validator;

    public RulePasswordValidator(){
        List<Rule> rules = Arrays.asList(
                new LengthRule(8, 25),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1)
        );
        validator = new PasswordValidator(rules);
    }




    public boolean validate(String password){

        List<ArrayList<? extends InvalidLoginException>> list = new ArrayList<>();
        ArrayList<EmptyUsernameException> exceptions1 = new ArrayList<>();
        exceptions1.add(new EmptyUsernameException());
        ArrayList<EmailIsTakenException> exceptions2 = new ArrayList<>();
        exceptions2.add(new EmailIsTakenException());
        list.add(exceptions1);
        list.add(exceptions2);
        PasswordData passwordData = new PasswordData(password);
        RuleResult result = validator.validate(passwordData);
        return result.isValid();
    }
}
