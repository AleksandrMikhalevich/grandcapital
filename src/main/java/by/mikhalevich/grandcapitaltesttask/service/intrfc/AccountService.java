package by.mikhalevich.grandcapitaltesttask.service.intrfc;

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
}
