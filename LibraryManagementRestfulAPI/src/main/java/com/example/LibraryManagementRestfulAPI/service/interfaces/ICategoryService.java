package com.example.LibraryManagementRestfulAPI.service.interfaces;

import com.example.LibraryManagementRestfulAPI.dto.request.CategorySaveRequest;
import com.example.LibraryManagementRestfulAPI.dto.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    CategoryResponse save(CategorySaveRequest request);
    List<CategoryResponse> getAll();
    CategoryResponse getById(Long id);
    CategoryResponse update(Long id, CategorySaveRequest request);
    String delete(Long id);  // özel iş kuralı: kitap varsa silme!
}

