package ro.mxp.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.PendingDto;
import ro.mxp.food.service.PendingService;

import java.util.List;

@RestController
@RequestMapping("/pending")
public class PendingController {

    private PendingService pendingService;
    @Autowired
    public PendingController(PendingService pendingService) {
        this.pendingService = pendingService;
    }

    @GetMapping
    public List<PendingDto> getAllPending() {
        return pendingService.getAllPending();
    }



}
