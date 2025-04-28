package by.mikhalevich.grandcapitaltesttask.service.intrfc;

import java.math.BigDecimal;

/**
 * Интерфейс-сервис для работы со счетами пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-27 16:13
 */
public interface AccountService {

    /**
     * Увеличение баланса счетов всех пользователей
     */
    void updateBalances();

    /**
     * Выполняет операцию перевода денег от одного пользователя к другому
     * @param fromUserId Id отправителя
     * @param toUserId Id получателя
     * @param amount сумма перевода
     */
    void transferMoney(Long fromUserId, Long toUserId, BigDecimal amount);
}
