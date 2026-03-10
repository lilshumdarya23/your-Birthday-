package com.birthday.demo.controller;

import com.birthday.demo.service.GiftUnlockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/two")
@RequiredArgsConstructor
@Slf4j
public class TwoController {
    private final GiftUnlockService giftUnlockService;

    @GetMapping()
    public String oneMission() {
        return "two";
    }

    @PostMapping("/unlock")
    @ResponseBody
    public String unlockSecondGift() {
        try {
            boolean unlocked = giftUnlockService.unlockTwo(2L);
            return unlocked ? "Подарок #2 разблокирован!" : "Ошибка разблокировки";
        } catch (Exception e) {
            log.error("Ошибка при разблокировке подарка #2", e);
            return e.getMessage();
        }
    }

}
