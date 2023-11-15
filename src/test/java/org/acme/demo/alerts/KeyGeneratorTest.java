package org.acme.demo.alerts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyGeneratorTest {

    @Test
    @DisplayName("Given a valid alert of type1, with a valid program, then the key contains all the elements")
    void givenFullAlertOfType1_thenValidKey() {
        Alert alert = Alert.builder()
                .type("type1")
                .program(Program.builder()
                        .id(123)
                        .name("alert123")
                        .enabled(true)
                        .build())
                .build();

        KeyGenerator keyGenerator = new KeyGenerator();
        assertEquals("123-alert123-true", keyGenerator.generateKey(alert));
    }

    @Test
    @DisplayName("Given a valid alert of type1, with a valid program that has a null name, then the key is valid")
    void givenAlertOfType1_withMissingProgramName_thenValidKey() {
        Alert alert = Alert.builder()
                .type("type1")
                .program(Program.builder()
                        .id(123)
                        .enabled(true)
                        .build())
                .build();

        KeyGenerator keyGenerator = new KeyGenerator();
        assertEquals("123-null-true", keyGenerator.generateKey(alert));
    }

    @Test
    @DisplayName("Given a valid alert of type1, with a null program, then the key is valid but composed of all nulls")
    void givenAlertOfType1WithNoProgram_thenValidKey() {
        Alert alert = Alert.builder()
                .type("type1")
                .build();

        KeyGenerator keyGenerator = new KeyGenerator();
        assertEquals("null-null-null", keyGenerator.generateKey(alert));
    }

    @Test
    @DisplayName("Given a valid alert of type1, with a program that has all null fields, then the key is valid but composed of all nulls")
    void givenAlertOfType1WithEmptyProgram_thenValidKey() {
        Alert alert = Alert.builder()
                .type("type1")
                .program(Program.builder().build())
                .build();

        KeyGenerator keyGenerator = new KeyGenerator();
        assertEquals("null-null-null", keyGenerator.generateKey(alert));
    }

    @Test
    @DisplayName("Given a valid alert of type2, with a valid program, then the key contains 2 non-null elements")
    void givenFullAlertOfType2_thenValidKey() {
        Alert alert = Alert.builder()
                .type("type2")
                .program(Program.builder()
                        .id(123)
                        .name("alert123")
                        .enabled(true)
                        .build())
                .build();

        KeyGenerator keyGenerator = new KeyGenerator();
        assertEquals("123-alert123", keyGenerator.generateKey(alert));
    }

    @Test
    @DisplayName("Given a valid alert of type2, with a program that has all null fields, then the key contains 2 'null' elements")
    void givenAlertOfType2WithEmptyProgram_thenValidKey() {
        Alert alert = Alert.builder()
                .type("type2")
                .program(Program.builder().build())
                .build();

        KeyGenerator keyGenerator = new KeyGenerator();
        assertEquals("null-null", keyGenerator.generateKey(alert));
    }

    @Test
    @DisplayName("Given a valid alert of any other type, then the key contains only non-null element")
    void givenFullAlertOfOtherType_thenValidKey() {
        Alert alert = Alert.builder()
                .type("other")
                .program(Program.builder()
                        .id(123)
                        .name("alert123")
                        .enabled(true)
                        .build())
                .build();

        KeyGenerator keyGenerator = new KeyGenerator();
        assertEquals("123", keyGenerator.generateKey(alert));
    }

}