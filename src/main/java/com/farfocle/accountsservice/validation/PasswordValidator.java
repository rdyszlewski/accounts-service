package com.farfocle.accountsservice.validation;

import com.farfocle.accountsservice.validation.exceptions.password.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PasswordValidator {

    @Value("${farfocle.password.min}")
    private int minPasswordLength;

    @Value("${farfocle.password.max}")
    private int maxPasswordLength;

    @Value("${farfocle.password.numbers}")
    private int requiredNumbers;

    @Value("${farfocle.password.uppercase}")
    private int requiredUppercase;

    @Value("${farfocle.password.special}")
    private int requiredSpecialCharacters;

    private Set<Integer> specialCharactersSet;

    public PasswordValidator(@Value("${farfocle.password.special-chars}") String specialCharacters){
        specialCharactersSet = specialCharacters.chars().filter(x->!Character.isSpaceChar(x)).map(x->(int)x).boxed().collect(Collectors.toSet());
    }

    public boolean validate(final String password) throws PasswordException {
        // TODO: obsłużyć poprawnie nulla
        if(password == null){
            return false;
        }
        if(password.length() < minPasswordLength){
            // TODO: dodać opis do wyjątku
            throw new PasswordTooShortException();
        }
        if(password.length() > maxPasswordLength){
            throw new PasswordTooLongException();
        }
        if(!containsEnoughNumbers(password)){
            throw new PasswordRequiresNumbersException();
        }
        if(!containsEnoughUppercaseLetters(password)){
            throw new PasswordRequiresUppercaseLettersException();
        }
        if(!containsEnoughSpecialCharacters(password)){
            throw new PasswordRequiresSpecialCharactersException();
        }
        return true;
    }

    private boolean containsEnoughNumbers(String password){
        if(requiredNumbers == 0){
            return true;
        }
        long count = password.chars().filter(Character::isDigit).limit(requiredNumbers).count();
        return count >= requiredNumbers;
    }

    private boolean containsEnoughUppercaseLetters(String password){
        if(requiredUppercase == 0){
            return true;
        }
        long uppercase = password.chars().filter(Character::isUpperCase).limit(requiredUppercase).count();
        return uppercase >= requiredUppercase;
    }

    private boolean containsEnoughSpecialCharacters(String password){
        if(requiredSpecialCharacters == 0){
            return true;
        }
        long count = password.chars().filter(x->specialCharactersSet.contains(x)).limit(requiredSpecialCharacters).count();
        return count >= requiredNumbers;
    }
}
