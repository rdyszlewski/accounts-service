package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotAllowedCharacterRuleTest {

    List<Character> notAllowedCharacters = Arrays.asList(' ', ';');

    @Test
    public void shouldReturnTrueWhenAllAllowedChars(){
        Rule rule = new NotAllowedCharacterRule(this.notAllowedCharacters);
        UserData data = new UserData("aaa", null);
        assertTrue(rule.validate(data));
    }

    @Test
    public void shouldReturnFalseWhenNonAllowedChar(){
        Rule rule = new NotAllowedCharacterRule(this.notAllowedCharacters);
        UserData data = new UserData("aa a", null);
        assertFalse(rule.validate(data));
    }
}
