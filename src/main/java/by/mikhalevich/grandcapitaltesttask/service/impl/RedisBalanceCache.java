package by.mikhalevich.grandcapitaltesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static by.mikhalevich.grandcapitaltesttask.service.util.Constants.INITIAL_BALANCE_KEY_PREFIX;

/**
 * Класс-сервис для прихранивания первоначального баланса счета пользователя в кэше Redis
 * @author Alex Mikhalevich
 * @created 2025-04-27 17:59
 */
@Component
@RequiredArgsConstructor
public class RedisBalanceCache {

    /**
     * RedisTemplate
     */
    private final RedisTemplate<String, BigDecimal> redisTemplate;

    /**
     * Возвращает первоначальный баланс пользователя.
     * Если значение отсутствует, оно сохраняется в Redis с ключом INITIAL_BALANCE_KEY_PREFIX + clientId.
     */
    @SuppressWarnings("ConstantConditions")
    public BigDecimal getInitialBalance(Long clientId, BigDecimal currentBalance) {
        String key = INITIAL_BALANCE_KEY_PREFIX + clientId;
        BigDecimal initialBalance = redisTemplate.opsForValue().get(key);
        if (initialBalance == null) {
            redisTemplate.opsForValue().set(key, currentBalance);
            initialBalance = currentBalance;
        }
        return initialBalance;
    }
}
