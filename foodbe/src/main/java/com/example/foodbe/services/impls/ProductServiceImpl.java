package com.example.foodbe.services.impls;

import com.example.foodbe.request.product.CreateProductDTO;
import com.example.foodbe.response.product.ProductResponseDTO;
import com.example.foodbe.request.product.UpdateProductDTO;
import com.example.foodbe.exception_handler.NotFoundException;
import com.example.foodbe.mapper.ProductMapper;
import com.example.foodbe.models.Category;
import com.example.foodbe.models.Product;
import com.example.foodbe.repositories.CategoryRepository;
import com.example.foodbe.repositories.ProductRepository;
import com.example.foodbe.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponseDTO> findAll() {
         List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            return List.of();
        }
        return  products.stream()
                .map(product->productMapper.toDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        Product product= productRepository.findById(id)
                .orElseThrow(() ->new NotFoundException("product not found with id: "+id));
        return productMapper.toDto(product);
    }

    @Override
    public ProductResponseDTO create(CreateProductDTO createProductDTO) {
        // Log giá trị imgProduct
        System.out.println("===> imgProduct received: " + createProductDTO.getImgProduct());
        Category category= categoryRepository.findById(createProductDTO.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + createProductDTO.getCategoryId()));
        Product product= productMapper.toEntity(createProductDTO,category);

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO updateById(Long id,UpdateProductDTO updateProductDTO) {
       Product product= productRepository.findById(id)
               .orElseThrow(()-> new NotFoundException("product  notfound with id: "+id));
       productMapper.UpdateEntityFromDto(updateProductDTO,product);
       productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void deleteById(Long id) {
       if(!productRepository.existsById(id))
                throw new NotFoundException("product  notfound with id: "+id);
        productRepository.deleteById(id);
    }
}
