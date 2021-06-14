package com.example.cdr.controller;

import com.example.cdr.exception.RecordNotFoundException;
import com.example.cdr.model.Record;
import com.example.cdr.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecordController {

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/cdr/records")
    public List<Record> getRecords() {
        return (List<Record>) recordRepository.findAll();
    }

    @GetMapping("/cdr/record/{id}")
    public Record getRecord(@PathVariable Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @PostMapping("/cdr/record")
    @ResponseStatus(HttpStatus.CREATED)
    public Record createRecord(@RequestBody Record record) {
        return recordRepository.save(record);
    }

    @PutMapping("/cdr/record")
    public Record updateRecord(@RequestBody Record record) {
        return recordRepository.findById(record.getId())
                .map(r -> {
                    if (record.getDomainName() != null) r.setDomainName(record.getDomainName());
                    if (record.getCallerName() != null) r.setCallerName(record.getCallerName());
                    if (record.getCallerNumber() != null) r.setCallerNumber(record.getCallerNumber());
                    if (record.getDestinationNumber() != null) r.setDestinationNumber(record.getDestinationNumber());
                    if (record.getDirection() != null) r.setDirection(record.getDirection());
                    if (record.getCallStart() != null) r.setCallStart(record.getCallStart());
                    if (record.getRingStart() != null) r.setRingStart(record.getRingStart());
                    if (record.getAnswerStart() != null) r.setAnswerStart(record.getAnswerStart());
                    if (record.getCallEnd() != null) r.setCallEnd(record.getCallEnd());
                    if (record.getDuration() != null) r.setDuration(record.getDuration());
                    if (record.getRecording() != null) r.setRecording(record.getRecording());
                    if (record.isClickToCall() != null) r.setClickToCall(record.isClickToCall());
                    if (record.getClickToCallData() != null) r.setClickToCallData(record.getClickToCallData());
                    if (record.getAction() != null) r.setAction(record.getAction());
                    return recordRepository.save(r);
                })
                .orElseThrow(() -> new RecordNotFoundException(record.getId()));
    }

    @DeleteMapping("/cdr/record/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecord(@PathVariable Long id) {
        recordRepository.deleteById(id);
    }
}
