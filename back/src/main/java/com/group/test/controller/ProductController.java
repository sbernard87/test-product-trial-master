package com.group.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.test.api.ProductsApi;
import com.group.test.model.ProductDTO;
import com.group.test.model.ProductSummaryDTO;
import com.group.test.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/products")
public class ProductController implements ProductsApi {

    @Autowired
    private ProductService productService;

    @Override
    @Operation(operationId = "findAllProducts", summary = "Get all products with pagination", responses = {
            @ApiResponse(responseCode = "200", description = "A list of products", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductSummaryDTO.class))) }) })
    @GetMapping(value = "/products", produces = { "application/json" })
    public ResponseEntity<List<ProductSummaryDTO>> findAllProducts(
            @RequestParam(value = "page", required = false) final Integer page,
            @RequestParam(value = "size", required = false) final Integer size) {

        return ResponseEntity.ok(productService.getAllProducts(page, size));
    }

    @Override
    @Operation(operationId = "findProductDetails", summary = "Get product details", responses = {
            @ApiResponse(responseCode = "200", description = "Product details", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }) })
    @GetMapping(value = "/products/{id}", produces = { "application/json" })
    public ResponseEntity<ProductDTO> findProductDetails(@PathVariable final Integer id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Override
    @Operation(operationId = "createProduct", summary = "Create a new product", responses = {
            @ApiResponse(responseCode = "201", description = "Product created") })
    @PostMapping(value = "/products", consumes = { "application/json" })
    public ResponseEntity<Void> createProduct(@RequestBody final ProductDTO productDTO) {
        productService.createProduct(productDTO);
        return ResponseEntity.status(201).build();
    }

    @Override
    @Operation(operationId = "updateProductDetails", summary = "Update product details", responses = {
            @ApiResponse(responseCode = "200", description = "Product updated") })
    @PatchMapping(value = "/products/{id}", consumes = { "application/json" })
    public ResponseEntity<Void> updateProductDetails(@PathVariable final Integer id,
            @RequestBody final ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    @Operation(operationId = "deleteProduct", summary = "Delete a product", responses = {
            @ApiResponse(responseCode = "204", description = "Product deleted") })
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}