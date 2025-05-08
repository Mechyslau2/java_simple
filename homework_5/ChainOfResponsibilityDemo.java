package homework_5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class StringValidator {
    protected StringValidator next;

    public static StringValidator use(StringValidator first, StringValidator... validators) {
        if (first == null) {
            throw new NullPointerException();
        }

        StringValidator head = first;
        for (StringValidator validator : validators) {
            head = head.next = validator;
        }
        return first;
    }

    public abstract boolean isValid(String value);

    protected boolean nextValidator(String value) {
        return next == null ? true : next.isValid(value);
    }
}


class MinLengthOfStringValidator extends StringValidator {
    private int minLength;

    public MinLengthOfStringValidator(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean isValid(String value) {
        if (value.trim().length() < minLength) {
            System.out.println("The string should contain the more  than " + minLength + " chars");
            return false;
        }
        return nextValidator(value);
    }
}


class StringContainsSpecialCharsValidator extends StringValidator {
    private final static String pattern = "[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]";

    @Override
    public boolean isValid(String value) {
        Pattern regExp = Pattern.compile(pattern);
        Matcher matcher = regExp.matcher(value);
        boolean isContains = matcher.find();
        if (!isContains) {
            System.out.println("The string should contain the special chars");
            return false;
        }

        return nextValidator(value);
    }

}


public class ChainOfResponsibilityDemo {
    public static final int MIN_LENGTH = 5;

    public static void main(String[] args) {

        StringValidator validator = StringValidator.use(new MinLengthOfStringValidator(MIN_LENGTH),
                new StringContainsSpecialCharsValidator());

        boolean isValid = validator.isValid("some text 12");
        System.out.println(isValid);
    }
}
