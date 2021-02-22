package com.farfocle.accountsservice.register_data_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordData;
import com.farfocle.accountsservice.register_data_validator.RegisterData;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailFormatRuleTest {

    // TODO: https://gist.github.com/cjaoude/fd9910626629b53c4d25

    @Test
    public void shouldReturnFalseWhenInvalidEmail(){
        Rule rule = new EmailFormatRule();
        RegisterData data1 = new RegisterData("a", "plainaddress");
        RegisterData data2 = new RegisterData("a", "#@%^%#$@#$@#.com");
        RegisterData data3 = new RegisterData("a", "@example.com");
        RegisterData data4 = new RegisterData("a", "Joe Smith <email@example.com>");
        RegisterData data5 = new RegisterData("a", "email.example.com");
        RegisterData data6 = new RegisterData("a", "email@example@example.com");
        RegisterData data7 = new RegisterData("a", ".email@example.com");
        RegisterData data8 = new RegisterData("a", "email.@example.com");
        RegisterData data9 = new RegisterData("a", "email..email@example.com");
        RegisterData data10 = new RegisterData("a", "あいうえお@example.com");
        RegisterData data11 = new RegisterData("a", "email@example.com (Joe Smith)");
        RegisterData data12 = new RegisterData("a", "email@example");
        RegisterData data13 = new RegisterData("a", "email@-example.com");
//        RegisterData data14 = new RegisterData("a", "email@example.web");
//        RegisterData data15 = new RegisterData("a", "email@111.222.333.44444");
        RegisterData data16 = new RegisterData("a", "email@example..com");
        RegisterData data17 = new RegisterData("a", "Abc..123@example.com");

        assertFalse(rule.validate(data1));
        assertFalse(rule.validate(data2));
        assertFalse(rule.validate(data3));
        assertFalse(rule.validate(data4));
        assertFalse(rule.validate(data5));
        assertFalse(rule.validate(data6));
        assertFalse(rule.validate(data7));
        assertFalse(rule.validate(data8));
        assertFalse(rule.validate(data9));
        assertFalse(rule.validate(data10));
        assertFalse(rule.validate(data11));
        assertFalse(rule.validate(data12));
        assertFalse(rule.validate(data13));
//        assertFalse(rule.validate(data14));
//        assertFalse(rule.validate(data15));
        assertFalse(rule.validate(data16));
        assertFalse(rule.validate(data17));
    }

    @Test
    public void shouldReturnTrueWhenEmailIsValid(){
        Rule rule = new EmailFormatRule();

        RegisterData data1 = new RegisterData("a", "email@example.com");
        RegisterData data2 = new RegisterData("a", "firstname.lastname@example.com");
        RegisterData data3 = new RegisterData("a", "email@subdomain.example.com");
        RegisterData data4 = new RegisterData("a", "firstname+lastname@example.com");
        RegisterData data5 = new RegisterData("a", "email@123.123.123.123");
//        RegisterData data6 = new RegisterData("a", "email@[123.123.123.123]");
//        RegisterData data7 = new RegisterData("a", "\"email\"@example.com");
        RegisterData data8 = new RegisterData("a", "1234567890@example.com");
        RegisterData data9 = new RegisterData("a", "email@example-one.com");
        RegisterData data10 = new RegisterData("a", "_______@example.com");
        RegisterData data11 = new RegisterData("a", "email@example.name");
        RegisterData data12 = new RegisterData("a", "email@example.museum");
        RegisterData data13 = new RegisterData("a", "email@example.co.jp");
        RegisterData data14 = new RegisterData("a", "firstname-lastname@example.com");

        assertTrue(rule.validate(data1));
        assertTrue(rule.validate(data2));
        assertTrue(rule.validate(data3));
        assertTrue(rule.validate(data4));
        assertTrue(rule.validate(data5));
//        assertTrue(rule.validate(data6));
//        assertTrue(rule.validate(data7));
        assertTrue(rule.validate(data8));
        assertTrue(rule.validate(data9));
        assertTrue(rule.validate(data10));
        assertTrue(rule.validate(data11));
        assertTrue(rule.validate(data12));
        assertTrue(rule.validate(data13));
        assertTrue(rule.validate(data14));
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsNull(){
        Rule rule = new EmailFormatRule();
        RegisterData nullData = null;
        assertThatThrownBy(()->rule.validate(nullData)).isInstanceOf(NullPointerException.class);

        RegisterData nullEmail = new RegisterData("dada", null);
        assertThatThrownBy(()->rule.validate(nullEmail)).isInstanceOf(NullPointerException.class);
    }

}
