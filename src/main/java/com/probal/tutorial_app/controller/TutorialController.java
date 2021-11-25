package com.probal.tutorial_app.controller;


import com.probal.tutorial_app.dto.TutorialDto;
import com.probal.tutorial_app.exception.ResourceNotFoundException;
import com.probal.tutorial_app.model.Tutorial;
import com.probal.tutorial_app.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:8000")
public class TutorialController {

    @Autowired
    TutorialService tutorialService;

    @GetMapping("/tutorials")
    public List<Tutorial> getTutorialList() {
        return tutorialService.getAllTutorials();
    }


    @PostMapping("/tutorials")
    public Tutorial createTutorial(@RequestBody TutorialDto tutorial) {
        return tutorialService.createTutorial(tutorial);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable Long id) {
        Tutorial tutorial = tutorialService.getTutorialById(id);
        return ResponseEntity.ok(tutorial);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") Long id, @RequestBody Tutorial tutorialDetails) {
        Tutorial updatedTutorial = tutorialService.updateTutorial(id, tutorialDetails);
        return ResponseEntity.ok(updatedTutorial);
    }


    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTutorial(@PathVariable Long id) {
        Map<String, Boolean> response = new HashMap<>();
        try {
            tutorialService.deleteTutorial(id);
            response.put("deleted", Boolean.TRUE);
        } catch (Exception e) {
            response.put("deleted", Boolean.FALSE);
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);

    }

}
