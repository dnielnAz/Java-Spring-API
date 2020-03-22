package com.dnieln7.javaspringapi.controller;

import com.dnieln7.javaspringapi.controller.response.DeleteResponse;
import com.dnieln7.javaspringapi.data.model.Store;
import com.dnieln7.javaspringapi.data.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/stores")
    public List<Store> getStore() {
        List<Store> stores = new ArrayList<>();

        storeRepository.findAll().forEach(stores::add);

        return stores;
    }

    @GetMapping("/stores/{id}")
    public Store getStoreById(@PathVariable int id) {
        return storeRepository.findById(id).orElse(null);
    }

    @PostMapping("/stores")
    public Store postStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    @PutMapping("/stores/{id}")
    public Store putStore(@PathVariable int id, @RequestBody Store store) {
        store.setId(id);

        return storeRepository.save(store);
    }

    @DeleteMapping("/stores/{id}")
    public DeleteResponse deleteStore(@PathVariable int id) {
        Store store = storeRepository.findById(id).orElse(null);

        if (store == null) {
            return new DeleteResponse(1, "Not found!");
        }

        storeRepository.delete(store);

        return new DeleteResponse(1, "Deleted!");
    }
}
