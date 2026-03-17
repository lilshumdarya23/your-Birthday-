package com.birthday.demo.controller;

import com.birthday.demo.dto.GiftDto;
import com.birthday.demo.service.GiftUnlockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@Controller
@Slf4j
public class OneController {
    private final GiftUnlockService giftUnlockService;

    public OneController(GiftUnlockService giftUnlockService) {
        this.giftUnlockService = giftUnlockService;
    }

    @GetMapping("/one")
    public String oneMission() {
        return "one";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<GiftDto> allGifts() {
        return giftUnlockService.getAllGifts()
                .stream()
                .map(g -> new GiftDto(g.getId(), g.isLocked()))
                .toList();
    }

    @GetMapping("/unlock")
    @ResponseBody
    public String unlockGift(@RequestParam Long id) {
        try {
            boolean unlocked = giftUnlockService.unlock(id);
            return unlocked ? "Подарок разблокирован!" : "Неверный код";
        } catch (Exception e) {
            log.error("Ошибка при разблокировке подарка", e);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return sw.toString();
        }
    }
}

