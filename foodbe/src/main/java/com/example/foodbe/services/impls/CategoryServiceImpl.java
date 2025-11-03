package com.example.foodbe.services.impls;

import com.example.foodbe.dto.CategoryRequestDTO;
import com.example.foodbe.dto.category.CategoryResponseDTO;
import com.example.foodbe.dto.category.CreateCategoryDTO;
import com.example.foodbe.dto.category.UpdateCategoryDTO;
import com.example.foodbe.exception.NotFoundException;
import com.example.foodbe.mapper.CategoryMapper;
import com.example.foodbe.models.Category;
import com.example.foodbe.repositories.CategoryRepository;
import com.example.foodbe.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category->categoryMapper.toDTO(category))
                .collect(Collectors.toList());

    }

    @Override
    public CategoryResponseDTO findById(Long id) {
      Category category=  categoryRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));
     return categoryMapper.toDTO(category);
    }

    @Override
    public CategoryResponseDTO create(CreateCategoryDTO createCategoryDTO) {
        Category category= categoryMapper.toEntity(createCategoryDTO);
        return categoryMapper.toDTO( categoryRepository.save(category)) ;

    }

    @Override
    public CategoryResponseDTO updateById(Long id,UpdateCategoryDTO updateCategoryDTO) {
        // 1. Tìm entity cũ trong DB
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));

        // cập nhật
        categoryMapper.updateEntityFromDto(updateCategoryDTO,existingCategory);

        //lưu lại db
        Category category= categoryRepository.save(existingCategory);
        return categoryMapper.toDTO(category);
    }

    @Override
    public void deleteById(Long id) {
        // Kiểm tra xem category có tồn tại không
       if (!categoryRepository.existsById(id)){
               throw new NotFoundException("Category not found with id: " + id);}
        // Xóa category nếu tồn tại
        categoryRepository.deleteById(id);
    }

}
