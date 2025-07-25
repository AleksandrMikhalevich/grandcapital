package by.mikhalevich.grandcapitaltesttask.service.impl;

import by.mikhalevich.grandcapitaltesttask.service.intrfc.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    @Cacheable(cacheNames = "accountInitialBalance", key = "#userId")
    public BigDecimal getInitialBalance(Long userId, BigDecimal balance) {
        return balance;
    }
}
