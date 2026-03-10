package com.birthday.demo.service;

import com.birthday.demo.entity.Gift;
import com.birthday.demo.repo.GiftRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//TODO: теперь нужно будет добавить 3 игру. нажимает на кнопку 3 подарка и появляется экран телефона
// высвечивается оповещение "пожалуйста включите авиа режим" он нажимает на кнопку авиа режима
// и начинается игра самолётиком нужно пролететь какую-нибудь полосу препятствий и потом высвечивается вы приземлились
// и вручается подарок. 3 жизни даётся и можно переиграть
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

