package com.example.Lofeback.service;

import com.example.Lofeback.dto.ProductDTO;
import com.example.Lofeback.entity.Product;
import com.example.Lofeback.entity.Team;
import com.example.Lofeback.repository.ProductRepository;
import com.example.Lofeback.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final TeamRepository teamRepository;
    private ProductRepository productRepository;
    private final FeedbackService feedbackService;

    @Transactional
    public void save(ProductDTO productDTO) {
        productRepository.save(toEntity(productDTO));
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElse(null);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        productRepository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setTeam(teamRepository.findById(productDTO.getTeamId()).orElse(null));
        return product;
    }

    private ProductDTO toDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setTeamId(product.getTeam().getId());
        productDTO.setFeedbacks(product.getFeedbacks().stream().map(feedbackService::toDto).collect(Collectors.toList()));
        return productDTO;
    }
}
