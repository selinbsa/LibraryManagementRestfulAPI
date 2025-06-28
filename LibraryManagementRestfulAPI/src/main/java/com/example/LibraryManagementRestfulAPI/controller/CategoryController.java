package com.example.LibraryManagementRestfulAPI.controller;

import com.example.LibraryManagementRestfulAPI.dto.request.CategorySaveRequest;
import com.example.LibraryManagementRestfulAPI.dto.response.CategoryResponse;
import com.example.LibraryManagementRestfulAPI.service.interfaces.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse save(@RequestBody @Valid CategorySaveRequest request) {
        return categoryService.save(request);
    }

    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody @Valid CategorySaveRequest request) {
        return categoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String message = categoryService.delete(id);
        return ResponseEntity.ok(message);
    }
}

