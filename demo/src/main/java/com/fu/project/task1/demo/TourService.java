package com.fu.project.task1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    // Получение всех туров
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    // Получение тура по ID
    public Tour getTourById(int id) {
        Optional<Tour> tour = Optional.ofNullable(tourRepository.findById(id));
        return tour.orElse(null);
    }

    // Создание нового тура
    public Tour createTour(Tour tour) {
        return tourRepository.save(tour);
    }

    // Обновление тура
    public Tour updateTour(int id, Tour tourDetails) {
        Optional<Tour> optionalTour = Optional.ofNullable(tourRepository.findById(id));

        if (optionalTour.isPresent()) {
            Tour existingTour = optionalTour.get();
            existingTour.setDestination(tourDetails.getDestination());
            existingTour.setPrice(tourDetails.getPrice());
            existingTour.setDepartureDate(tourDetails.getDepartureDate());
            return tourRepository.save(existingTour);
        }
        return null;
    }

    public boolean deleteTour(long id) {
        if (tourRepository.existsById(id)) {
            tourRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Tour saveTour(Tour tour) {
        return tourRepository.save(tour);
    }
}