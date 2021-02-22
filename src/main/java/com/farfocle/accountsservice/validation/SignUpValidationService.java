package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.dto.SignUpData;
import com.farfocle.accountsservice.password_validator.*;
import com.farfocle.accountsservice.user_validator.UserData;
import com.farfocle.accountsservice.user_validator.UserValidationError;
import com.farfocle.accountsservice.user_validator.UserValidationResult;
import com.farfocle.accountsservice.user_validator.UserValidator;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SignUpValidationService {

    private final PasswordValidator passwordValidator;
    private final UserValidator userValidator;
    private final SignUpMessageCreator messageCreator;

    @Autowired
    public SignUpValidationService(@NotNull final PasswordValidator passwordValidator, @NotNull final UserValidator userValidator, @NotNull final SignUpMessageCreator messageCreator){
        this.passwordValidator = passwordValidator;
        this.userValidator = userValidator;
        this.messageCreator = messageCreator;
    }

    public SignUpValidationResult validate(SignUpData data){
        SignUpValidationResult result = new SignUpValidationResult();
        UserData userData = new UserData(data.getUsername(), data.getEmail());
        UserValidationResult userValidationResult = userValidator.validate(userData);
        getErrorsDetailsFromUserValidation(userValidationResult).forEach(result::addError);
        if(userValidationResult.isValid()){
            PasswordData passwordData = new PasswordData(data.getPassword(), data.getUsername());
            ValidationResult passwordValidationResult = passwordValidator.validate(passwordData);
            getErrorsFromPasswordValidation(passwordValidationResult).forEach(result::addError);
        }
        return result;
    }

    private List<SignUpErrorDetails> getErrorsDetailsFromUserValidation(UserValidationResult validationResult){
        return validationResult.getErrors().stream().map(this::getErrorDetails).collect(Collectors.toList());
    }

    private SignUpErrorDetails getErrorDetails(UserValidationError error){
        // TODO: przemyśleć, jak to powinno być robione poprawnie
        // TODO: trzeba jakoś dodać odpowiednie komunikaty i pobrać wymagane dane, jeżeli będzie to potrzebne
        switch (error){
            case USERNAME_TOO_SHORT:
                return new SignUpErrorDetails(SignUpError.USERNAME_TOO_SHORT,null,null);
            case USERNAME_TOO_LONG:
                return new SignUpErrorDetails(SignUpError.USERNAME_TOO_LONG, null, null);
            case USERNAME_TAKEN:
                return new SignUpErrorDetails(SignUpError.USERNAME_TAKEN, null, null);
            case EMAIL_TAKEN:
                return new SignUpErrorDetails(SignUpError.EMAIL_TAKEN, null, null);
            case INVALID_EMAIL:
                return new SignUpErrorDetails(SignUpError.INVALID_EMAIL, null, null);
            default:
                throw new IllegalArgumentException();
        }
    }



    private List<SignUpErrorDetails> getErrorsFromPasswordValidation(ValidationResult validationResult){
        return validationResult.getErrors().stream().map(this::createErrorDetails).collect(Collectors.toList());
    }

    private SignUpErrorDetails createErrorDetails(ErrorDetails errorDetails){
        switch (errorDetails.getError()){
            case TOO_SHORT:
                return new SignUpErrorDetails(SignUpError.PASS_TOO_SHORT, errorDetails.getValidValue(), null);
            case TOO_LONG:
                return new SignUpErrorDetails(SignUpError.PASS_TOO_LONG, errorDetails.getValidValue(), null);
            case LOWERCASE:
                return new SignUpErrorDetails(SignUpError.PASS_NO_LOWERCASE, errorDetails.getValidValue(), null);
            case UPPERCASE:
                return new SignUpErrorDetails(SignUpError.PASS_NO_UPPERCASE, errorDetails.getValidValue(), null );
            case DIGITS:
                return new SignUpErrorDetails(SignUpError.PASS_NO_DIGITS, errorDetails.getValidValue(), null);
            case SPECIAL_CHARACTERS:
                return new SignUpErrorDetails(SignUpError.PASS_NO_SPECIALS, errorDetails.getValidValue(), null);
            case WHITESPACE:
                return new SignUpErrorDetails(SignUpError.PASS_HAS_WHITESPACE, errorDetails.getValidValue(), null);
            case USERNAME:
                return new SignUpErrorDetails(SignUpError.PASS_IS_USERNAME, errorDetails.getValidValue(), null);
            default:
                throw new IllegalArgumentException();
        }
    }
}
