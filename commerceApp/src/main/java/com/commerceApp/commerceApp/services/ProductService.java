package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.Category;
import com.commerceApp.commerceApp.Models.Product;
import com.commerceApp.commerceApp.Models.Seller;
import com.commerceApp.commerceApp.dtos.ProductSellerDto;
import com.commerceApp.commerceApp.repositories.CategoryRepository;
import com.commerceApp.commerceApp.repositories.ProductRepository;
import com.commerceApp.commerceApp.repositories.SellerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    MailService mailService;

    public Product toProduct(ProductSellerDto productSellerDto){
        if(productSellerDto == null)
            return null;
        return modelMapper.map(productSellerDto, Product.class);
    }

    public ProductSellerDto toProductSellerDto(Product product){
        if(product == null)
            return null;
        return modelMapper.map(product, ProductSellerDto.class);
    }
    public String validateProducts(String email,ProductSellerDto productSellerDto) {
        String message;
        Optional<Category> savedCategory = categoryRepository.findById(productSellerDto.getCategoryId());
        if (!savedCategory.isPresent()) {
            message = "Category doers not exist";
            return message;
        }
        Category category = savedCategory.get();
        if (!(category.getSubCategories() == null)) {
            message = "Not a leaf node";
            return message;
        }

        Product savedProduct = productRepository.findByName(productSellerDto.getName());
        if(savedProduct!=null){
            if(savedProduct.getCategory().getId().equals(productSellerDto.getCategoryId())){
                if(savedProduct.getBrand().equalsIgnoreCase(productSellerDto.getBrand())){
                    if(savedProduct.getSeller().getEmail().equalsIgnoreCase(email)){
                        message = "Product with similar details already exists.";
                        return message;
                    }
                }
            }
        }
        message = "success";
        return message;
    }
    public ResponseEntity<String> addProduct(String email,ProductSellerDto productSellerDto){
        String message=validateProducts(email,productSellerDto);
        if(!message.equalsIgnoreCase("Successful"))
         return new ResponseEntity<>("Validation Failed",HttpStatus.BAD_REQUEST);
        Category category = categoryRepository.findById(productSellerDto.getCategoryId()).get();
        Seller seller=sellerRepository.findByEmail(email);
        Product product=toProduct(productSellerDto);
        product.setCategory(category);
        product.setSeller(seller);
        productRepository.save(product);
        return new ResponseEntity<>("Success",HttpStatus.OK);

    }
    private void sendProductCreationMail(String email, Product product) {
        String subject = "Product created";
        String content = "A product with following details has been created - \n" +
                "name - "+product.getName()+"\n" +
                "category - "+product.getCategory().getName()+"\n" +
                "brand - "+product.getBrand()+"\n" +
                "description - "+product.getDescription();
        mailService.sendEmail(email, subject, content);
    }
}
