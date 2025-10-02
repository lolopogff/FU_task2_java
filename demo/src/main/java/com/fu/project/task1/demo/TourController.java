package com.fu.project.task1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tours")
public class TourController {

    @Autowired
    private TourService tourService;

    // Главная страница - список всех туров
    @GetMapping("/")
    public String getAllTours(Model model) {
        List<Tour> tours = tourService.getAllTours();
        model.addAttribute("tours", tours);
        return "index"; // index.html в templates
    }

    // Страница создания тура
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tour", new Tour());
        return "create"; // create.html в templates
    }

    // Создание тура
    @PostMapping("/")
    public String createTour(@ModelAttribute Tour tour) {
        tourService.createTour(tour);
        return "redirect:/tours/";
    }

    // Просмотр одного тура
    @GetMapping("/{id}")
    public String getTourById(@PathVariable int id, Model model) {
        Tour tour = tourService.getTourById(id);
        model.addAttribute("tour", tour);
        return "view"; // view.html в templates
    }

    // Страница редактирования тура
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Tour tour = tourService.getTourById(id);
        model.addAttribute("tour", tour);
        return "edit"; // edit.html в templates
    }

    // Обновление тура
    @PostMapping("/{id}")
    public String updateTour(@PathVariable int id, @ModelAttribute Tour tour) {
        tourService.updateTour(id, tour);
        return "redirect:/tours/";
    }

    // Удаление тура
    @PostMapping("/{id}/delete")
    public String deleteTour(@PathVariable int id) {
        tourService.deleteTour(id);
        return "redirect:/tours/";
    }
}