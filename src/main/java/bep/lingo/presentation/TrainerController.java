package bep.lingo.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/board/{boardID}/tasklist/{tasklistId}/task")
public class TrainerController {
    @GetMapping
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String id) {
        var task = taskService.getById(id);
        taskService.deleteTask(task);

        return ResponseEntity.ok().build();
    }
}
