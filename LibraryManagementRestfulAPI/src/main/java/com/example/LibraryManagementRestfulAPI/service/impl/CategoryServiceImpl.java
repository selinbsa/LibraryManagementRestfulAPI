package com.example.LibraryManagementRestfulAPI.service.impl;

import com.example.LibraryManagementRestfulAPI.core.exception.NotFoundException;
import com.example.LibraryManagementRestfulAPI.dto.request.CategorySaveRequest;
import com.example.LibraryManagementRestfulAPI.dto.response.CategoryResponse;
import com.example.LibraryManagementRestfulAPI.entity.Category;
import com.example.LibraryManagementRestfulAPI.repository.BookRepository;
import com.example.LibraryManagementRestfulAPI.repository.CategoryRepository;
import com.example.LibraryManagementRestfulAPI.service.interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryResponse save(CategorySaveRequest request) {
        Category category = modelMapper.map(request, Category.class);
        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CategoryResponse.class))
                .toList();
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kategori bulunamadı: " + id));
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse update(Long id, CategorySaveRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Kategori bulunamadı: " + id));

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    @Override
    public String delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Kategori bulunamadı: " + id);
        }

        boolean hasBooks = bookRepository.existsByCategories_Id(id);
        if (hasBooks) {
            return "Bu kategoriye ait kitap var. Bu kategori silinemedi.";
        }

        categoryRepository.deleteById(id);
        return "Kategori başarıyla silindi.";
    }
}

