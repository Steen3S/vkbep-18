package bep.game.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bep.game.presentation.dto.PlayerDto;




@RestController
@RequestMapping("/game")
public class TrainerController {


    /* ----------------------------- Start new game ----------------------------- */
    @PostMapping
    public ResponseEntity<Void> start(@RequestBody PlayerDto playerDto ) {


        return ResponseEntity.ok().build();
    }


    
    @GetMapping
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String id) {

        return ResponseEntity.ok().build();
    }
}
