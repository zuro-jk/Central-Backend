package com.centrral.centralres.features.products.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.centrral.centralres.features.products.dto.category.request.CategoryRequest;
import com.centrral.centralres.features.products.dto.category.response.CategoryResponse;
import com.centrral.centralres.features.products.exceptions.CategoryNotFoundException;
import com.centrral.centralres.features.products.model.Category;
import com.centrral.centralres.features.products.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getAll() {
        List<CategoryResponse> categories = categoryRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
        return categories;
    }

    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Categoría no encontrada con id: " + id));
        return mapToResponse(category);
    }

    public CategoryResponse create(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .build();
        Category savedCategory = categoryRepository.save(category);
        return mapToResponse(savedCategory);
    }

    public CategoryResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Categoría no encontrada con id: " + id));
        category.setName(request.getName());
        Category updatedCategory = categoryRepository.save(category);
        return mapToResponse(updatedCategory);
    }

    public Void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Categoría no encontrada con id: " + id);
        }
        categoryRepository.deleteById(id);
        return null;
    }

    private CategoryResponse mapToResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
