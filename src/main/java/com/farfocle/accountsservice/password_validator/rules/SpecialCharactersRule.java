package com.farfocle.accountsservice.password_validator.rules;

import com.farfocle.accountsservice.password_validator.PasswordError;

import javax.xml.stream.events.Characters;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SpecialCharactersRule extends CharactersRule{

    private final String DEFAULT_SPECIALS = "@ % + / \\\\ \\' \\# ! $ ^ : . ( ) { } [ ] ~ _ -";

    private Set<Integer> specialCharacters;

    private final PasswordError errorType = PasswordError.SPECIAL_CHARACTERS;

    public SpecialCharactersRule(int value){
        super(value, PasswordError.SPECIAL_CHARACTERS);
        loadSpecialCharacters(null);
    }

    public SpecialCharactersRule(int value, boolean interrupting){
        super(value, interrupting, PasswordError.SPECIAL_CHARACTERS);
        loadSpecialCharacters(null);
    }

    public SpecialCharactersRule(int value, List<Character> specialCharacters) {
        super(value, PasswordError.SPECIAL_CHARACTERS);
       loadSpecialCharacters(specialCharacters);
    }

    public SpecialCharactersRule(int value, boolean interrupting, List<Character> specialCharacters){
        super(value, interrupting, PasswordError.SPECIAL_CHARACTERS);
        loadSpecialCharacters(specialCharacters);
    }

    private void loadSpecialCharacters(List<Character> specialCharacters){
        if(specialCharacters != null){
            this.specialCharacters = specialCharacters.stream().map(x->(int)x).collect(Collectors.toSet());
        } else {
            this.specialCharacters = DEFAULT_SPECIALS.chars().filter(x->!Character.isSpaceChar(x)).map(x->(int)x).boxed().collect(Collectors.toSet());
        }
    }

    @Override
    protected boolean checkCharacter(int character) {
        return specialCharacters.contains(character);
    }
}
