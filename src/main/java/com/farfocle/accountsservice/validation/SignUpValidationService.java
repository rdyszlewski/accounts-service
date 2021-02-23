package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.dto.SignUpData;
import com.farfocle.accountsservice.password_validator.*;
import com.farfocle.accountsservice.user_validator.*;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class SignUpValidationService {

    private final PasswordValidator passwordValidator;
    private final UserValidator userValidator;
    private final SignUpMessageCreator messageCreator;

    private final Map<UserValidationError, SignUpError> USER_ERRORS_MAP = new ConcurrentHashMap<>(){{
        put(UserValidationError.USERNAME_TOO_SHORT, SignUpError.USERNAME_TOO_SHORT);
        put(UserValidationError.USERNAME_TOO_LONG, SignUpError.USERNAME_TOO_LONG);
        put(UserValidationError.USERNAME_TAKEN, SignUpError.USERNAME_TAKEN);
        put(UserValidationError.EMAIL_TAKEN, SignUpError.EMAIL_TAKEN);
        put(UserValidationError.INVALID_EMAIL, SignUpError.INVALID_EMAIL);
    }};
    // TODO: postarać się przygotować te obiekty, aby nie było trzeba ich cały czas zwracać

    private final Map<PasswordError, SignUpError> PASSWORD_ERRORS_MAP = new ConcurrentHashMap<>(){{
        put(PasswordError.TOO_SHORT, SignUpError.PASS_TOO_SHORT);
        put(PasswordError.TOO_LONG, SignUpError.PASS_TOO_LONG);
        put(PasswordError.LOWERCASE, SignUpError.PASS_NO_LOWERCASE);
        put(PasswordError.UPPERCASE, SignUpError.PASS_NO_UPPERCASE);
        put(PasswordError.DIGITS, SignUpError.PASS_NO_DIGITS);
        put(PasswordError.SPECIAL_CHARACTERS, SignUpError.PASS_NO_SPECIALS);
        put(PasswordError.WHITESPACE, SignUpError.PASS_HAS_WHITESPACE);
        put(PasswordError.USERNAME, SignUpError.PASS_IS_USERNAME);
    }};

    private final Map<UserValidationError, SignUpErrorDetails> userErrorDetailsMap = new ConcurrentHashMap<>();
    private final Map<PasswordError, SignUpErrorDetails> passwordErrorDetailsMap = new ConcurrentHashMap<>();

    @Autowired
    public SignUpValidationService(@NotNull final PasswordValidator passwordValidator, @NotNull final UserValidator userValidator, @NotNull final SignUpMessageCreator messageCreator) {
        // TODO: dodać komunikaty do tych asercji
        assert USER_ERRORS_MAP.size() == UserValidationError.values().length;
        assert PASSWORD_ERRORS_MAP.size() == PasswordError.values().length;

        this.passwordValidator = passwordValidator;
        this.userValidator = userValidator;
        this.messageCreator = messageCreator;

        passwordValidator.getAvailableErrorDetails().forEach(x->{
            String message = messageCreator.getMessage(PASSWORD_ERRORS_MAP.get(x.getError()), x.getValidValue());
            SignUpErrorDetails details = new SignUpErrorDetails(PASSWORD_ERRORS_MAP.get(x.getError()), x.getValidValue(), message);
            passwordErrorDetailsMap.put(x.getError(), details);
        });
    }

    public SignUpValidationResult validate(SignUpData data) {
        SignUpValidationResult result = new SignUpValidationResult();
        UserData userData = new UserData(data.getUsername(), data.getEmail());
        UserValidationResult userValidationResult = userValidator.validate(userData);
        getErrorsDetailsFromUserValidation(userValidationResult).forEach(result::addError);

        PasswordData passwordData = new PasswordData(data.getPassword(), data.getUsername());
        ValidationResult passwordValidationResult = passwordValidator.validate(passwordData);
        getErrorsFromPasswordValidation(passwordValidationResult).forEach(result::addError);

        return result;
    }

    private List<SignUpErrorDetails> getErrorsDetailsFromUserValidation(UserValidationResult validationResult) {
        return validationResult.getErrors().stream().map(this::getErrorDetails).collect(Collectors.toList());
    }

    private SignUpErrorDetails getErrorDetails(UserErrorDetails error) {
        assert USER_ERRORS_MAP.containsKey(error.getError());
        String message = messageCreator.getMessage(USER_ERRORS_MAP.get(error.getError()), error.getValue());
        return new SignUpErrorDetails(USER_ERRORS_MAP.get(error.getError()), error.getValue(), message);
    }

    private List<SignUpErrorDetails> getErrorsFromPasswordValidation(ValidationResult validationResult) {
        return validationResult.getErrors().stream().map(this::createErrorDetails).collect(Collectors.toList());
    }

    private SignUpErrorDetails createErrorDetails(ErrorDetails errorDetails) {
        assert PASSWORD_ERRORS_MAP.containsKey(errorDetails.getError());
        return passwordErrorDetailsMap.get(errorDetails.getError());
    }
}
