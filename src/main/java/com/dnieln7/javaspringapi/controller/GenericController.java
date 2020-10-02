package com.dnieln7.javaspringapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface GenericController<T> {

    @GetMapping
    public ResponseEntity<Iterable<T>> get();

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id);

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody T entity);

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable Integer id, @RequestBody T entity);

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id);
}
