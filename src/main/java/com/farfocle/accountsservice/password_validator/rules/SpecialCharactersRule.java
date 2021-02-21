package com.farfocle.accountsservice.password_validator.rules;

import javax.xml.stream.events.Characters;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SpecialCharactersRule extends CharactersRule{

    private final String DEFAULT_SPECIALS = "@ % + / \\\\ \\' \\# ! $ ^ : . ( ) { } [ ] ~ _ -";

    private Set<Integer> specialCharacters;

    public SpecialCharactersRule(int value){
        super(value);
        loadSpecialCharacters(null);
    }

    public SpecialCharactersRule(int value, boolean interrupting){
        super(value, interrupting);
        loadSpecialCharacters(null);
    }

    public SpecialCharactersRule(int value, List<Character> specialCharacters) {
        super(value);
       loadSpecialCharacters(specialCharacters);
    }

    public SpecialCharactersRule(int value, boolean interrupting, List<Character> specialCharacters){
        super(value, interrupting);
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
