package ru.sberbank.task_6.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.task_6.model.dto.CounterDTO;
import ru.sberbank.task_6.model.Counter;
import ru.sberbank.task_6.services.CounterService;

import java.util.List;

@RestController
@RequestMapping("/counters/")
public class CounterController {
    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping
    public ResponseEntity<CounterDTO> addCounter(@RequestBody CounterDTO counterDTO) {
        CounterDTO result = counterService.addCounter(counterDTO);
        if (result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/increment")
    public ResponseEntity<Counter> incrementCounter(@RequestBody CounterDTO counterDTO) {
        Counter counter = counterService.incrementCounter(counterDTO);
        if (counter == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(counter, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Counter> getValueByName(@PathVariable String name) {
        Counter counter = counterService.getValueByName(name);
        if (counter == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(counter, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<CounterDTO> deleteByName(@RequestBody CounterDTO counterDTO) {
        CounterDTO result = counterService.deleteByName(counterDTO);
        if (result == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<Integer> getTotalValue() {
        return new ResponseEntity<>(counterService.getTotalValue(), HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getNames() {
        return new ResponseEntity<>(counterService.getNames(), HttpStatus.OK);
    }
}
