package by.mikhalevich.grandcapitaltesttask.service.util;

/**
 * Класс с набором констант валидации входных данных
 * @author Alex Mikhalevich
 * @created 2025-04-26 14:16
 */
public class ValidationConstants {

    private ValidationConstants() {
    }

    public static final String EMAIL_PATTERN = "^(?=.{6,}$)[\\s]*[a-zA-Z0-9]+([!\"#$%&'()*+,\\-.\\/:;<=>?\\[\\]" +
            "\\^_{}][a-zA-z0-9]+)*@([\\w]+(-[\\w]+)?)(\\.[\\w]+[-][\\w]+)*" +
            "(\\.[a-z]{2,})+[\\s]*$";
}
