package by.mikhalevich.grandcapitaltesttask.service.util;

/**
 * Класс с набором констант
 * @author Alex Mikhalevich
 * @created 2025-04-26 14:16
 */
public class Constants {

    private Constants() {
    }

    public static final String EMAIL_PATTERN = "^(?=.{6,}$)[\\s]*[a-zA-Z0-9]+([!\"#$%&'()*+,\\-.\\/:;<=>?\\[\\]" +
            "\\^_{}][a-zA-z0-9]+)*@([\\w]+(-[\\w]+)?)(\\.[\\w]+[-][\\w]+)*" +
            "(\\.[a-z]{2,})+[\\s]*$";

    public static final String PHONE_PATTERN = "^(?=.{0,}$)[0-9]*$";

    public static final String LOG_MESSAGE_403 = "Попытка неавторизованного доступа пользователя c userId {}";

    public static final String MESSAGE_403 = "Доступ запрещен";

    public static final String LOG_AUTH_ERROR_MESSAGE = "Вход с email {} не удался";

    public static final String AUTH_ERROR_MESSAGE = "Неверный email или пароль";

    public static final String LOG_NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "Email {} уже используется";

    public static final String LOG_NOT_UNIQUE_PHONE_ERROR_MESSAGE = "Телефон {} уже используется";

    public static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "Email %s уже используется";

    public static final String NOT_UNIQUE_PHONE_ERROR_MESSAGE = "Телефон %s уже используется";

    public static final String LOG_USER_NOT_FOUND_MESSAGE = "Пользователь с email {} не найден";

    public static final String LOG_USER_FOUND_MESSAGE = "Пользователь найден по email: {}";

    public static final String USER_ID_NOT_FOUND_MESSAGE = "Пользователь c Id %s не найден";

    public static final String LOG_EMAIL_ID_NOT_FOUND_MESSAGE = "EmailData с Id {} не найдено для пользователя с Id {}";

    public static final String EMAIL_NOT_FOUND_MESSAGE = "Email не найден для пользователя с Id %s";

    public static final String LOG_PHONE_ID_NOT_FOUND_MESSAGE = "PhoneData с Id {} не найдено для пользователя с Id {}";

    public static final String PHONE_NOT_FOUND_MESSAGE = "Телефон не найден для пользователя с Id %s";

    public static final String INITIAL_BALANCE_KEY_PREFIX = "initialBalance:";


}

