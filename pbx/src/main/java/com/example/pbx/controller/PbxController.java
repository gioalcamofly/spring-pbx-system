package com.example.pbx.controller;

import com.example.pbx.exception.PbxNotFoundException;
import com.example.pbx.model.Pbx;
import com.example.pbx.repository.PbxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PbxController {

    @Autowired
    private PbxRepository pbxRepository;

    @GetMapping("/pbx")
    public List<Pbx> getPbxs() {
        return (List<Pbx>) pbxRepository.findAll();
    }

    @GetMapping("/pbx/{id}")
    public Pbx getPbx(@PathVariable Long id) {
        return pbxRepository.findById(id)
                .orElseThrow(() -> new PbxNotFoundException(id));
    }

    @PostMapping("/pbx")
    @ResponseStatus(HttpStatus.CREATED)
    public Pbx createPbx(@RequestBody Pbx pbx) {
        return pbxRepository.save(pbx);
    }

    @PutMapping("/pbx")
    public Pbx updatePbx(@RequestBody Pbx pbx) {
        return pbxRepository.findById(pbx.getId())
                .map(p -> {
                    if (pbx.getName() != null) p.setName(p.getName());
                    if (pbx.getUrl() != null) p.setUrl(p.getUrl());
                    if (pbx.getEndpoints() != null) p.setEndpoints(p.getEndpoints());
                    return pbxRepository.save(p);
                })
                .orElseThrow(() -> new PbxNotFoundException(pbx.getId()));
    }

    @DeleteMapping("/pbx/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePbx(@PathVariable Long id) {
        pbxRepository.deleteById(id);
    }
}
