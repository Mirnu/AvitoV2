package ru.mirn.avitov2.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mirn.avitov2.models.Image;
import ru.mirn.avitov2.models.Product;
import ru.mirn.avitov2.repository.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        setImage(file1, product, true);
        setImage(file2, product, false);
        setImage(file3, product, false);

        log.info("Saving new Product. Title: {}; Author: {}", product.getTitle(), product.getAuthor());
        product.setPreviewImageId(product.getImages().get(0).getId());
        productRepository.save(product);
    }

    private void setImage(MultipartFile file, Product product, boolean isPreview) throws IOException {
        Image image = toImageEntity(file);
        image.setPreviewImage(isPreview);
        product.addImageToProduct(image);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
