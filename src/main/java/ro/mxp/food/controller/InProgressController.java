package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.InProgressDto;
import ro.mxp.food.service.InProgressService;

import java.util.List;

@RestController
@RequestMapping("/in_progress")
public class InProgressController {

    private InProgressService inProgressService;
    @Autowired
    public InProgressController(InProgressService inProgressService) {
        this.inProgressService = inProgressService;
    }

    @GetMapping
    public List<InProgressDto> getAllInProgress() {
        return inProgressService.getAllInProgress();
    }

    @DeleteMapping("/delivered/{id}")
    public void deleteInProgress(@PathVariable Long id) {
        inProgressService.deleteInProgress(id);
    }
}
