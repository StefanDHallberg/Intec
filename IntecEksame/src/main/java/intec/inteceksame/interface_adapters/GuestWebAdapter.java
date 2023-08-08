package intec.inteceksame.interface_adapters;

import intec.inteceksame.entity.Guest;
import intec.inteceksame.usecase.GuestServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
public class GuestWebAdapter {
    private final GuestServiceHandler guestServiceHandler;

    @Autowired
    public GuestWebAdapter(GuestServiceHandler guestServiceHandler) {
        this.guestServiceHandler = guestServiceHandler;
    }

    @GetMapping("/change-lang")
    public String changeLanguage(@RequestParam("lang") String lang, Model model) {
        model.addAttribute("lang", lang);
        return "redirect:/register";
    }

    @PostMapping("/change-lang")
    public String changeLanguage(@RequestParam String lang, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("lang", lang);
        return "redirect:/register";
    }

    @GetMapping("/register")
    public String showGuestRegistrationForm(@RequestParam(required = false) String lang, Model model) {
        ZonedDateTime currentTime = ZonedDateTime.now();

        // If lang is null or isn't "en" or "dk", set to DK timezone.
        if (lang == null || (!lang.equals("en") && !lang.equals("dk"))) {
            if (currentTime.getZone().equals(ZoneId.of("Europe/Copenhagen"))) {
                lang = "dk"; // Danish.
            } else {
                lang = "en"; // English for anything else.
            }
        }
        model.addAttribute("lang", lang);
        model.addAttribute("guest", new Guest());
        model.addAttribute("companies", guestServiceHandler.getAllCompanies());
        return "register";
    }

    @PostMapping("/register")
    public String registerGuest(@ModelAttribute Guest guest, @RequestParam String lang, Model model, RedirectAttributes redirectAttributes) {
        guest.setCheckInTime(String.valueOf(LocalDateTime.now()));

        // User selected language, instead of using timezone language.
        guest.setSelectedLang(lang);

        String registrationResult = guestServiceHandler.registerGuest(guest);

        model.addAttribute("guest", guest);
        model.addAttribute("lang", lang);
        redirectAttributes.addFlashAttribute("registrationResult", registrationResult);
        redirectAttributes.addFlashAttribute("registrationLang", lang);

        return "redirect:/register";
    }
}

