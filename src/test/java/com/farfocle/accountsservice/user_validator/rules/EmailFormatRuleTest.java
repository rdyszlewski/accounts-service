package com.farfocle.accountsservice.user_validator.rules;

import com.farfocle.accountsservice.user_validator.UserData;
import org.junit.Test;

import static com.farfocle.accountsservice.user_validator.test_utils.ExceptionUtil.testEmailSuccess;
import static com.farfocle.accountsservice.user_validator.test_utils.ExceptionUtil.testUsernameFail;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EmailFormatRuleTest {

    // TODO: https://gist.github.com/cjaoude/fd9910626629b53c4d25

    @Test
    public void shouldReturnFalseWhenInvalidEmail() {
        Rule rule = new EmailFormatRule();

        testUsernameFail("plainaddress", rule);
        testUsernameFail("#@%^%#$@#$@#.com", rule);
        testUsernameFail("@example.com", rule);
        testUsernameFail("Joe Smith <email@example.com>", rule);
        testUsernameFail("email.example.com", rule);
        testUsernameFail("email@example@example.com", rule);
        testUsernameFail(".email@example.com", rule);
        testUsernameFail("email.@example.com", rule);
        testUsernameFail("email..email@example.com", rule);
        testUsernameFail("あいうえお@example.com", rule);
        testUsernameFail("email@example.com (Joe Smith)", rule);
        testUsernameFail("email@example", rule);
        testUsernameFail("email@-example.com", rule);
//        testUsernameFail("email@example.web", rule);
//        testUsernameFail("email@111.222.333.44444", rule);
        testUsernameFail("email@example..com", rule);
        testUsernameFail("Abc..123@example.com", rule);
    }

    @Test
    public void shouldReturnTrueWhenEmailIsValid() {
        Rule rule = new EmailFormatRule();

        testEmailSuccess("email@example.com", rule);
        testEmailSuccess("firstname.lastname@example.com", rule);
        testEmailSuccess("email@subdomain.example.com", rule);
        testEmailSuccess("firstname+lastname@example.com", rule);
        testEmailSuccess("email@123.123.123.123", rule);
//        testEmailSuccess("email@[123.123.123.123]", rule);
//        testEmailSuccess("\"email\"@example.com", rule);
        testEmailSuccess("1234567890@example.com", rule);
        testEmailSuccess("email@example-one.com", rule);
        testEmailSuccess("_______@example.com", rule);
        testEmailSuccess("email@example.name", rule);
        testEmailSuccess("email@example.museum", rule);
        testEmailSuccess("email@example.co.jp", rule);
        testEmailSuccess("firstname-lastname@example.com", rule);

    }

    @Test
    public void shouldThrowExceptionWhenEmailIsNull() {
        Rule rule = new EmailFormatRule();
        UserData nullData = null;
        assertThatThrownBy(() -> rule.validate(nullData)).isInstanceOf(NullPointerException.class);

        UserData nullEmail = new UserData("dada", null);
        assertThatThrownBy(() -> rule.validate(nullEmail)).isInstanceOf(NullPointerException.class);
    }

}
