package com.netcracker.project.controller;

import com.netcracker.project.aggregator.Aggregator;
import com.netcracker.project.entity.Filter;
import com.netcracker.project.entity.dto.Sorter;
import com.netcracker.project.entity.Training;
import com.netcracker.project.entity.dto.TrainingDTO;
import com.netcracker.project.entity.enums.FilterOperator;
import com.netcracker.project.entity.enums.SorterDirection;
import com.netcracker.project.repository.TrainingRepository;
import com.netcracker.project.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * Controller for training
 */
@RestController
@RequestMapping("/trainings")
public class TrainingController {

    /**
     * Training service
     */
    private final TrainingService trainingService;
    /**
     * Training repository
     */
    private final TrainingRepository trainingRepository;

    /**
     * Constructor
     */
    @Autowired
    public TrainingController(TrainingService trainingService,
                              TrainingRepository trainingRepository) {
        this.trainingService = trainingService;
        this.trainingRepository = trainingRepository;
    }

    /**
     * Gets training
     * @param trainingId - training id
     * @return training
     */
    @GetMapping("/{trainingId}")
    public ResponseEntity<Training> getTraining(@PathVariable UUID trainingId){
        return trainingService.findById(trainingId).map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Gets trainings by customer
     * @param principal - user
     * @return list trainings
     */
    @GetMapping("/myTrainings")
    public List<Training> getUserTrainings(Principal principal) {
        return trainingService.getUserTrainings(principal.getName());
    }

    /**
     * Gets trainings by creator
     * @param principal - user
     * @return list trainings
     */
    @GetMapping("/mentorTrainings")
    public List<Training> getMentorTrainings(Principal principal) {
        return trainingService.getMentorTrainings(principal.getName());
    }

    /**
     * Gets all trainings
     * @return list all trainings
     */
    @GetMapping
    public List<Training> getTrainings(){
        return trainingService.findAll();
    }

    /**
     * Add training
     * @param trainingDTO - training information
     * @param principal - creator
     * @return training or code error
     */
    @PostMapping("/add")
    public ResponseEntity<Training> addTraining(@RequestBody TrainingDTO trainingDTO,
                                                Principal principal) {

        return trainingService.addTraining(trainingDTO, principal.getName()).map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());

    }

    /**
     * Update training
     * @param trainingDTO - training information
     * @param principal - creator
     * @return training or code error
     */
    @PutMapping("/editTraining")
    public ResponseEntity<Training> updateTraining(@RequestBody TrainingDTO trainingDTO,
                                                   Principal principal) {

        return trainingService.editTraining(trainingDTO.getId(), trainingDTO, principal.getName())
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());

    }

    /**
     * Delete training
     * @param id - training id
     * @param principal - creator
     * @return training or code error
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Training> deleteTraining(@PathVariable UUID id,
                                                   Principal principal) {

        return trainingService.delete(id, principal.getName()).map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());

    }

    /**
     * Get count page
     * @param size - size page
     * @param property - sorter property
     * @param direction - sorter direction
     * @param filterProperty - filter property
     * @param filterValue - filter value
     * @param filterOperator - filter operator
     * @return count page
     */
    @GetMapping(value = "/countPages", params = { "size", "sorterProperty", "sorterDirection",
                                                "filterProperty", "filterValue", "filterOperator" })
    public ResponseEntity<?> getCountPage(@RequestParam("size") double size,
                                          @RequestParam("sorterProperty") String property,
                                          @RequestParam("sorterDirection") String direction,
                                          @RequestParam("filterProperty") String filterProperty,
                                          @RequestParam("filterValue") String filterValue,
                                          @RequestParam("filterOperator") String filterOperator) {
        Aggregator<Training> aggregator = new Aggregator<>();
        Sorter sorter = new Sorter(property, SorterDirection.valueOf(direction));
        if (!filterOperator.equals("null")) {
            Filter filter = new Filter(List.of(filterValue), filterProperty, FilterOperator.valueOf(filterOperator));
            return ResponseEntity.ok((int) Math.ceil(aggregator.getResultsNumber(trainingService.findAll(), new Sorter[]{sorter}, new Filter[]{filter})/size));
        }
        double a = Math.ceil(aggregator.getResultsNumber(trainingService.findAll(), new Sorter[]{sorter}, null));
        return ResponseEntity.ok((int) Math.ceil(aggregator.getResultsNumber(trainingService.findAll(), new Sorter[]{sorter}, null)/size));
    }

    @GetMapping(value = "/aggregate", params = { "page", "size", "sorterProperty", "sorterDirection",
                                                "filterProperty", "filterValue", "filterOperator" })
    public List<Training> getSomeTrainings(@RequestParam("page") int page, @RequestParam("size") double size,
                                           @RequestParam("sorterProperty") String property,
                                           @RequestParam("sorterDirection") String direction,
                                           @RequestParam("filterProperty") String filterProperty,
                                           @RequestParam("filterValue") String filterValue,
                                           @RequestParam("filterOperator") String filterOperator) {
        Aggregator<Training> aggregator = new Aggregator<>();
        int count = (int)(trainingService.findAll().size() - (page - 1) * size); // остаток
        if(count >= size)
            count = (int)size;
        Sorter sorter = new Sorter(property, SorterDirection.valueOf(direction));
        if (!filterOperator.equals("null")) {
            Filter filter = new Filter(List.of(filterValue), filterProperty, FilterOperator.valueOf(filterOperator));
            return aggregator.aggregateObjectsForUI(trainingService.findAll(), new Sorter[]{sorter}, new Filter[]{filter}, page, count, (int) size);
        }
        return aggregator.aggregateObjectsForUI(trainingService.findAll(), new Sorter[]{sorter}, null, page, count, (int) size);
    }

    /**
     * Block or unblock trainings
     * @param id - training id
     * @return message about result
     */
    @PostMapping("/deleteTraining")
    public ResponseEntity<Training> blockOrUnblock(@RequestBody UUID id) {
        Training training;
        if (trainingRepository.findById(id).isPresent()) {
            training = trainingRepository.findById(id).get();
            training.setIsDelete(true);
            trainingRepository.save(training);
        }
        return ResponseEntity.ok().build();
    }
}
