package utils;

import com.github.javafaker.Faker;

public class DataGenerator {

    public static String generateNewTitle() {
        return new Faker().name().title();
    }

    public static String generateNewCode() {
        return new Faker().regexify("[A-Z]{2,10}");
    }

    public static String generateRandomStringExpression() {
        return new Faker().regexify("[A-Za-z]{3,10} [A-Za-z]{3,10} [A-Za-z]{3,10}");
    }
}
