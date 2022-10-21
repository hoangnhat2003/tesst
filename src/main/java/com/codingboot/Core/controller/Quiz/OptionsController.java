package com.codingboot.Core.controller.Quiz;


import com.codingboot.Core.domain.entity.Options;
import com.codingboot.Core.service.Quiz.OptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class OptionsController {

    private final OptionsService optionsService;

    @GetMapping("/options")
    public ResponseEntity<List<Options>> getOptions(){
        return ResponseEntity.ok().body(optionsService.getOptions());
    }
}
