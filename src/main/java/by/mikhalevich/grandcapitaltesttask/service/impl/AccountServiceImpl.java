package by.mikhalevich.grandcapitaltesttask.service.impl;

import by.mikhalevich.grandcapitaltesttask.dao.model.Account;
import by.mikhalevich.grandcapitaltesttask.dao.repository.AccountRepository;
import by.mikhalevich.grandcapitaltesttask.service.intrfc.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Класс-сервис для работы со счетами пользователя
 * @author Alex Mikhalevich
 * @created 2025-04-27 16:14
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    /**
     * Репозиторий счетов
     */
    private final AccountRepository accountRepository;

    /**
     * Сервис кэша Redis
     */
    private final RedisBalanceCache redisBalanceCache;

    /**
     * Увеличение баланса счетов всех пользователей каждые 30 секунд на 10%
     */
    @Override
    @Scheduled(fixedRate = 30000)
    @Transactional
    public void updateBalances() {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            BigDecimal initialDeposit = redisBalanceCache.getInitialBalance(account.getId(), account.getBalance());
            BigDecimal maxBalance = initialDeposit.multiply(BigDecimal.valueOf(2.07));
            BigDecimal currentBalance = account.getBalance();
            BigDecimal candidate = currentBalance.multiply(BigDecimal.valueOf(1.1));
            if (candidate.compareTo(maxBalance) > 0) {
                candidate = maxBalance;
            }
            account.setBalance(candidate);
        }
        accountRepository.saveAll(accounts);
    }
}
