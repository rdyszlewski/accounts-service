package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpecialCharactersRuleTest {

    @Test
    public void shouldReturnFalseWhenNotEnoughSpecialCharacters(){
        Rule rule = new SpecialCharactersRule(2);
        PasswordData noSpecialsPassword = new PasswordData("aaaa");
        assertFalse(rule.validate(noSpecialsPassword));

        PasswordData notEnoughSpecialsPassword1 = new PasswordData("aa}aa");
        assertFalse(rule.validate(notEnoughSpecialsPassword1));

        PasswordData notEnoughSpecialsPassword2 = new PasswordData("[aaaa");
        assertFalse(rule.validate(notEnoughSpecialsPassword2));

        PasswordData notEnoughSpecialsPassword3 = new PasswordData("aaaa_");
        assertFalse(rule.validate(notEnoughSpecialsPassword3));
    }

    @Test
    public void shouldReturnTrueWhenEnoughSpecialCharacters(){
        Rule rule = new SpecialCharactersRule(2);
        PasswordData manySpecialsPassword = new PasswordData("//ddd#)(");
        assertTrue(rule.validate(manySpecialsPassword));

        PasswordData onlySpecialsPassword = new PasswordData("#_.,()");
        assertTrue(rule.validate(onlySpecialsPassword));

        PasswordData exactlySpecialsPassword = new PasswordData("a#a[a");
        assertTrue(rule.validate(exactlySpecialsPassword));
    }

    @Test
    public void shouldThrowNullPasswordException() {
        Rule rule = new SpecialCharactersRule(2);
        PasswordData nullData = null;
        assertThatThrownBy(()->rule.validate(nullData)).isInstanceOf(NullPointerException.class);
        // TODO: można tutaj sprawdzić komunikat

        PasswordData nullPassword = new PasswordData(null);
        assertThatThrownBy(()->rule.validate(nullPassword)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldReturnTrueWhenGetSpecialsFromList(){
        List<Character> list1 = Arrays.asList('#','/','_');
        Rule rule = new SpecialCharactersRule(2, list1);

        PasswordData password1 = new PasswordData("das#das_");
        assertTrue(rule.validate(password1));

        PasswordData password2 = new PasswordData("das#dj-");
        assertFalse(rule.validate(password2));
    }
}
