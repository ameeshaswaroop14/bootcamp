package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.Models.Category;
import com.commerceApp.commerceApp.Models.Product;
import com.commerceApp.commerceApp.Models.ProductVariation;
import com.commerceApp.commerceApp.Models.Seller;
import com.commerceApp.commerceApp.dtos.ProductAdminDto;
import com.commerceApp.commerceApp.dtos.ProductCustomerDto;
import com.commerceApp.commerceApp.dtos.ProductSellerDto;
import com.commerceApp.commerceApp.dtos.ProductVariationDto;
import com.commerceApp.commerceApp.exceptions.ProductAlreadyExists;
import com.commerceApp.commerceApp.exceptions.ProductDoesNotExists;
import com.commerceApp.commerceApp.exceptions.ProductNotActive;
import com.commerceApp.commerceApp.repositories.CategoryRepository;
import com.commerceApp.commerceApp.repositories.ProductRepository;
import com.commerceApp.commerceApp.repositories.SellerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    CategoryService categoryService;

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
    public Product toProduct(ProductCustomerDto productCustomerDto) {
        if (productCustomerDto == null)
            return null;
        return modelMapper.map(productCustomerDto, Product.class);
    }
    public ProductCustomerDto toproductCustomerDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductCustomerDto.class);
    }
    public Product toProduct(ProductAdminDto productAdminDto) {
        if (productAdminDto == null)
            return null;
        return modelMapper.map(productAdminDto, Product.class);
    }
    public ProductAdminDto toProductAdminDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductAdminDto.class);
    }

    public String validateProducts(String email, ProductSellerDto productSellerDto) {
        String message;
        Optional<Category> savedCategory = categoryRepository.findById(productSellerDto.getCategoryId());
        if (!savedCategory.isPresent()) {
            message = "Category does not exist";
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
        String email = product.getSeller().getEmail();
        sendProductActivationMail(email);
        productRepository.save(product);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public void sendProductActivationMail(String email) {

        mailService.sendEmail(email, "Product Activation", "Product activation successfully done");
    }

    public void sendProductDeactivationMail(String email, Product product) {
        String subject = "product deactivation";
        String content = "The product has been deleted. product details are as follows - \n" +
                "name - " + product.getName() + "\n" +
                "category - " + product.getCategory().getName() + "\n" +
                "brand - " + product.getBrand() + "\n" +
                "description - " + product.getDescription();
        mailService.sendEmail(email, subject, content);
    }

    public ResponseEntity<String> deactivateproductById(Long id) {
        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent())
            return new ResponseEntity<>("Invalid operation", HttpStatus.BAD_REQUEST);
        Product product = savedProduct.get();
        if (!product.isActive())
            return new ResponseEntity<>("Already inactive", HttpStatus.BAD_REQUEST);
        product.setActive(false);
        String email = product.getSeller().getEmail();
        sendProductDeactivationMail(email, product);
        productRepository.save(product);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public ProductSellerDto getProductByIdForSeller(Long id, String email) {
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            throw new ProductAlreadyExists("Product already exists");
        }
        Product product = savedProduct.get();
        if (!product.getSeller().getEmail().equalsIgnoreCase(email)) {
            throw new ProductDoesNotExists("Product does not exist");
        }
        if (product.isDeleted()) {
            throw new ProductDoesNotExists("product has already been deleted");
        }

        ProductSellerDto productSellerDto = toProductSellerDto(product);
        productSellerDto.setCategoryDto(categoryService.toCategoryDto(product.getCategory()));
        return productSellerDto;
    }

    public ResponseEntity<List> getAllProductsForSeller(String offset, String size, String sortByField, String order, Long categoryId, String brand) {

        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortByField).ascending());
        List<Product> products;
        if (categoryId != null && brand != null) {
            products = productRepository.findByBrandAndCategoryId(brand, categoryId, pageable);
        } else if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId, pageable);
        } else if (brand != null) {
            products = productRepository.findByBrand(brand, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }
        List<ProductSellerDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            ProductSellerDto productSellerDto = toProductSellerDto(product);
            productSellerDto.setCategoryDto(categoryService.toCategoryDto(product.getCategory()));
            productDtos.add(productSellerDto);
        });
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
    public ResponseEntity<String> deleteProductById(Long id, String email) {
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if(!savedProduct.isPresent()){
            return new ResponseEntity<>("Product with the given id not found", HttpStatus.NOT_FOUND);
        }
        Product product = savedProduct.get();
        if(!product.getSeller().getEmail().equalsIgnoreCase(email)){
            return new ResponseEntity<>("Unauthorized", HttpStatus.BAD_REQUEST);
        }
        if(product.isDeleted()){
            return new ResponseEntity<>("Product does not exist", HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }
    public ResponseEntity<ProductCustomerDto> getProductByIdForCustomer(Long id){
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if(!savedProduct.isPresent()) {
            throw new ProductDoesNotExists("Product does not exist");
        }
        Product product = savedProduct.get();

        if(product.isDeleted()){
            throw new ProductDoesNotExists("Product does not exist");

        }
        if(!product.isActive()) {
            throw new ProductNotActive("Product not active");
        }
        ProductCustomerDto productCustomerDto = toproductCustomerDto(product);
        productCustomerDto.setCategoryDto(categoryService.toCategoryDto(product.getCategory()));
        return new ResponseEntity<>(productCustomerDto,HttpStatus.OK);
    }
    public ResponseEntity<ProductAdminDto> getProductByIdForAdmin(Long id){
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if(!savedProduct.isPresent()) {
            throw new ProductDoesNotExists("Product does not exist");
        }
        Product product = savedProduct.get();

        if(product.isDeleted()){
            throw new ProductDoesNotExists("Product does not exist");

        }
        if(!product.isActive()) {
            throw new ProductNotActive("Product not active");
        }
        ProductAdminDto productAdminDto= toProductAdminDto(product);
        productAdminDto.setCategoryDto(categoryService.toCategoryDto(product.getCategory()));
        return new ResponseEntity<>(productAdminDto,HttpStatus.OK);
    }
    public ResponseEntity<List> getAllProductsForAdmin(Long categoryId, String offset, String size, String sortByField, String order, String brand) {

        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortByField).ascending());
        List<Product> products;
        if (categoryId != null && brand != null) {
            products = productRepository.findByBrandAndCategoryId(brand, categoryId, pageable);
        } else if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId, pageable);
        } else if (brand != null) {
            products = productRepository.findByBrand(brand, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }
        List<ProductAdminDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            ProductAdminDto productAdminDto = toProductAdminDto(product);
            productAdminDto.setCategoryDto(categoryService.toCategoryDto(product.getCategory()));
            productDtos.add(productAdminDto);
        });
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

}



