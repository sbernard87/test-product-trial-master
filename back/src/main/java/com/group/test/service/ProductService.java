package com.group.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.test.model.Product;
import com.group.test.model.ProductDTO;
import com.group.test.model.ProductSummaryDTO;
import com.group.test.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	public List<ProductSummaryDTO> getAllProducts(final Integer page, final Integer size) {

		Page<Product> products = productRepository.findAll(PageRequest.of(page, size));
		objectMapper.convertValue(products, ProductSummaryDTO.class);
		return null;
	}

	public ProductDTO getProductById(final Integer id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			ProductDTO product = objectMapper.convertValue(optionalProduct.get(), ProductDTO.class);
			return product;
		} else {
			throw new RuntimeException("Product not found");
		}
	}

	public void createProduct(final ProductDTO productDTO) {
		Product product = objectMapper.convertValue(productDTO, Product.class);
		productRepository.save(product);
	}

	public void updateProduct(final Integer id, final ProductDTO productDTO) {

		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = objectMapper.convertValue(productDTO, Product.class);
			productRepository.saveAndFlush(product);
		} else {
			throw new RuntimeException("Product not found");
		}
	}

	public void deleteProduct(final Integer id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			productRepository.delete(optionalProduct.get());
		} else {
			throw new RuntimeException("Product not found");
		}
	}
}