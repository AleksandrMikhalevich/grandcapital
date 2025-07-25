package by.mikhalevich.grandcapitaltesttask.service.intrfc;


import java.math.BigDecimal;

public interface CacheService {

    BigDecimal getInitialBalance(Long userId, BigDecimal balance);
}
