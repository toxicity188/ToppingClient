package kor.toxicity.topping.util;

import java.time.LocalDateTime;

public final class DateUtil {
    private DateUtil() {
        throw new RuntimeException();
    }
    public static LocalDateTime parse(String name) {
        return LocalDateTime.parse(name.substring(0,name.length() - 1));
    }
}
