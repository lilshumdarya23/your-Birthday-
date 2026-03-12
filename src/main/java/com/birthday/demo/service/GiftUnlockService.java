package com.birthday.demo.service;

import com.birthday.demo.entity.Gift;
import com.birthday.demo.repo.GiftRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO: теперь нужно будет добавить 4 окна
// найти фоточек несколько, написать стишок, добавить треков 3 каких-нибудь и найти ресторанчики
// он нажимает на кнопку авиа режима
// после игры высвечивается вы прорвались! в окошке есть либо повторить игру либо получить подарок
// при получении подарка появляется страница с сертификатом (если так можно) там будет выход на финальную страницу
// летят конфети играет трек чебурашки и надпись с днём рождения

@Service
public class GiftUnlockService {
    @Value("${gift.two.secret}")
    private String twoGiftSecret;

    private final GiftRepository giftRepository;

    public GiftUnlockService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public List<Gift> getLockedGifts() {
        return giftRepository.findAll()
                .stream()
                .filter(Gift::isLocked)
                .toList();
    }

    public List<Gift> getAllGifts() {
        return giftRepository.findAll();
    }

    @Transactional
    public boolean unlock(Long id) {
        Gift gift = giftRepository.findById(id).orElseThrow();

        String answer = null;
        if (!gift.getSecret().equalsIgnoreCase(answer)) {
            throw new RuntimeException("Неверный код, пока секрет не исправлен в сервисе!");
        }

        gift.setLocked(false);
        giftRepository.save(gift);
        return true;
    }

    @Transactional
    public boolean unlockTwo(Long id) {
        Gift gift = giftRepository.findById(id).orElseThrow();
        if (!gift.getSecret().equalsIgnoreCase(twoGiftSecret)) {
            throw new RuntimeException("Неверный код, пока секрет не исправлен!");
        }
        gift.setLocked(false);
        giftRepository.save(gift);
        return true;
    }

}

