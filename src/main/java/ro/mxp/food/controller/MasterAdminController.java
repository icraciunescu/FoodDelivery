package ro.mxp.food.controller;

import org.springframework.web.bind.annotation.*;
import ro.mxp.food.dto.MasterAdminDto;
import ro.mxp.food.service.MasterAdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class MasterAdminController {

    private MasterAdminService masterAdminService;

    public MasterAdminController(MasterAdminService masterAdminService) {
        this.masterAdminService = masterAdminService;
    }

    @GetMapping
    public List<MasterAdminDto> getAllMasterAdminDto() {
        return masterAdminService.getAllMasterAdmin();
    }

    @PostMapping
    public void addMasterAdminDto(@RequestBody MasterAdminDto masterAdminDto) throws Exception {
        masterAdminService.addMasterAdmin(masterAdminDto);
    }

    @PutMapping("/{id}")
    public void updateMasterAdminDto(@PathVariable Long id, @RequestParam String email, @RequestParam String username, @RequestParam String password ) {
        masterAdminService.updateMasterAdmin(id, email, username, password);
    }


//    @ExceptionHandler({Exception.class})
//    public ResponseEntity handleException() {
//        return ResponseEntity.badRequest().body(Exception.class);
//    }

}
