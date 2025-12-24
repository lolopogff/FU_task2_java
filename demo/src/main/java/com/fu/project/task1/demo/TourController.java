package com.fu.project.task1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/")
    public String getAllTours(Model model, Authentication auth) { // auth для проверки
        List<Tour> tours = tourService.getAllTours();
        model.addAttribute("tours", tours);
        model.addAttribute("isAdmin", auth != null && auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        return "index";
    }

    @GetMapping("/{id}")
    public String getTourById(@PathVariable int id, Model model) {
        // доступно всем авторизованным
        Tour tour = tourService.getTourById(id);
        model.addAttribute("tour", tour);
        return "view";
    }

    // Страница создания тура
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tour", new Tour());
        return "create"; // create.html в templates
    }

    // Создание тура
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public String createTour(@ModelAttribute Tour tour) {
        tourService.createTour(tour);
        return "redirect:/tours/";
    }


    // Страница редактирования тура
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Tour tour = tourService.getTourById(id);
        model.addAttribute("tour", tour);
        return "edit"; // edit.html в templates
    }

    // Обновление тура
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public String updateTour(@PathVariable int id, @ModelAttribute Tour tour) {
        tourService.updateTour(id, tour);
        return "redirect:/tours/";
    }

    // Удаление тура
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteTour(@PathVariable int id) {
        tourService.deleteTour(id);
        return "redirect:/tours/";
    }
}