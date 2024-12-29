package com.example.forms;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {
    private final FormRepository formRepository;

    public FormController(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @GetMapping
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @PostMapping
    public Form createForm(@RequestBody Form form) {
        return formRepository.save(form);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Form> getForm(@PathVariable Long id) {
        return formRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable Long id, @RequestBody Form updatedForm) {
        return formRepository.findById(id)
                .map(form -> {
                    form.setName(updatedForm.getName());
                    form.setPublished(updatedForm.isPublished());
                    return ResponseEntity.ok(formRepository.save(form));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable Long id) {
        if (formRepository.existsById(id)) {
            formRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<Form> publishForm(@PathVariable Long id) {
        return formRepository.findById(id)
                .map(form -> {
                    form.setPublished(true);
                    return ResponseEntity.ok(formRepository.save(form));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/published")
    public List<Form> getPublishedForms() {
        return formRepository.findByIsPublishedTrue();
    }
}
