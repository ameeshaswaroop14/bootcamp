package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.Category;
import com.commerceApp.commerceApp.Models.Product;
import com.commerceApp.commerceApp.Models.ProductVariation;
import com.commerceApp.commerceApp.Models.Seller;
import com.commerceApp.commerceApp.dtos.ProductSellerDto;
import com.commerceApp.commerceApp.dtos.ProductVariationDto;
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

    public Product toProduct(ProductSellerDto productSellerDto) {
        if (productSellerDto == null)
            return null;
        return modelMapper.map(productSellerDto, Product.class);
    }

    public ProductSellerDto toProductSellerDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductSellerDto.class);
    }

    public String validateProducts(String email, ProductSellerDto productSellerDto) {
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
        if (savedProduct != null) {
            if (savedProduct.getCategory().getId().equals(productSellerDto.getCategoryId())) {
                if (savedProduct.getBrand().equalsIgnoreCase(productSellerDto.getBrand())) {
                    if (savedProduct.getSeller().getEmail().equalsIgnoreCase(email)) {
                        message = "Product with similar details already exists.";
                        return message;
                    }
                }
            }
        }
        message = "success";
        return message;
    }

    public ResponseEntity<String> addProduct(String email, ProductSellerDto productSellerDto) {
        String message = validateProducts(email, productSellerDto);
        if (!message.equalsIgnoreCase("Successful"))
            return new ResponseEntity<>("Validation Failed", HttpStatus.BAD_REQUEST);
        Category category = categoryRepository.findById(productSellerDto.getCategoryId()).get();
        Seller seller = sellerRepository.findByEmail(email);
        Product product = toProduct(productSellerDto);
        product.setCategory(category);
        product.setSeller(seller);
        sendProductCreationMail(email, product);
        productRepository.save(product);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    private void sendProductCreationMail(String email, Product product) {
        String subject = "Product created";
        String content = "A product with following details has been created - \n" +
                "name - " + product.getName() + "\n" +
                "category - " + product.getCategory().getName() + "\n" +
                "brand - " + product.getBrand() + "\n" +
                "description - " + product.getDescription();
        mailService.sendEmail(email, subject, content);
    }

    public ProductVariation toProductVariation(ProductVariationDto variationDto) {
        if (variationDto == null)
            return null;
        return modelMapper.map(variationDto, ProductVariation.class);
    }

    public ProductVariationDto toProductVariationSellerDto(ProductVariation variation) {
        if (variation == null)
            return null;
        return modelMapper.map(variation, ProductVariationDto.class);
    }

    public String validateProductVariation(ProductVariationDto productVariationDto) {
        Optional<Product> savedProduct = productRepository.findById(productVariationDto.getProductid());

        if (!savedProduct.isPresent()) {
            String message = "invalid product Id";
        }
        return null;
        //idk
    }

    public ResponseEntity<String> activateProductById(Long id) {
        Optional<Product> savedproduct = productRepository.findById(id);
        if (!savedproduct.isPresent())
            return new ResponseEntity<>("Product not present", HttpStatus.NOT_FOUND);
        Product product = savedproduct.get();
        if (product.isActive())
            return new ResponseEntity<>("Product already activated", HttpStatus.BAD_REQUEST);
        product.setActive(true);
        String email=product.getSeller().getEmail();
        sendProductActivationMail(email);
        productRepository.save(product);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public void sendProductActivationMail(String email){

        mailService.sendEmail(email, "Product Activation", "Product activation successfully done");
    }
    public  void sendProductDeactivationMail(String email,Product product){
        String subject="product deactivation";
        String content = "The product has been deleted. product details are as follows - \n" +
                "name - " + product.getName() + "\n" +
                "category - " + product.getCategory().getName() + "\n" +
                "brand - " + product.getBrand() + "\n" +
                "description - " + product.getDescription();
        mailService.sendEmail(email,subject,content);
    }

    public ResponseEntity<String> deactivateproductById(Long id){
        Optional<Product> savedProduct=productRepository.findById(id);
        if(!savedProduct.isPresent())
            return new ResponseEntity<>("Invalid operation",HttpStatus.BAD_REQUEST);
        Product product=savedProduct.get();
        if(!product.isActive())
            return new ResponseEntity<>("Already inactive",HttpStatus.BAD_REQUEST);
        product.setActive(false);
        String email=product.getSeller().getEmail();
        sendProductDeactivationMail(email,product);
        productRepository.save(product);
        return new ResponseEntity<>("Success",HttpStatus.OK);

    }
}
