package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "/roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDice(@PathVariable int n, Model model) {
        int random = (int) (Math.random() * 6 + 1);

        boolean correctGuess = random == n;

        model.addAttribute("userGuess", n);
        model.addAttribute("random", random);
        model.addAttribute("correctGuess", correctGuess);
        return "/roll-dice";
    }
}
