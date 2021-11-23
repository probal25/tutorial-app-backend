package com.probal.tutorial_app.service;


import com.probal.tutorial_app.exception.ResourceNotFoundException;
import com.probal.tutorial_app.model.Tutorial;
import com.probal.tutorial_app.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    public List<Tutorial> getAllTutorials() {
        return tutorialRepository.findAll();
    }

    public Tutorial createTutorial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public Tutorial getTutorialById(Long id) {
        return tutorialRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Tutorial not exist with id :" + id));
    }

    public Tutorial updateTutorial(Long id, Tutorial tutorialDetails) {
        Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Tutorial does not exist with this id : " + id)
        );

        tutorial.setTitle(tutorialDetails.getTitle());
        tutorial.setDescription(tutorialDetails.getDescription());
        tutorial.setPublished(tutorialDetails.isPublished());

        Tutorial updatedTutorial = tutorialRepository.save(tutorial);
        return updatedTutorial;
    }

    public void deleteTutorial(Long id) {

        Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Tutorial does not exist with this id : " + id)
        );

        tutorialRepository.delete(tutorial);
    }
}
