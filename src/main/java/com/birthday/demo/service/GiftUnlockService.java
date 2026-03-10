package com.birthday.demo.service;

import com.birthday.demo.entity.Gift;
import com.birthday.demo.repo.GiftRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GiftUnlockService {

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
    public List<Gift> getAllGifts(){
        return giftRepository.findAll();
    }

    @Transactional
    public boolean unlock(Long id) {
        Gift gift = giftRepository.findById(id).orElseThrow();

        String answer = "beer";
        if (!gift.getSecret().equalsIgnoreCase(answer)) {
            throw new RuntimeException("Неверный код, пока секрет не исправлен в сервисе!");
        }

        gift.setLocked(false);
        giftRepository.save(gift);
        return true;
    }
}

