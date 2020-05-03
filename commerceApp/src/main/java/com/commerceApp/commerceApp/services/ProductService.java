package com.commerceApp.commerceApp.services;

import com.commerceApp.commerceApp.dtos.productDto.ProductUpdateDto;
import com.commerceApp.commerceApp.models.category.Category;
import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.models.Seller;
import com.commerceApp.commerceApp.dtos.productDto.ProductAdminDto;
import com.commerceApp.commerceApp.dtos.productDto.ProductCustomerDto;
import com.commerceApp.commerceApp.dtos.productDto.ProductSellerDto;
import com.commerceApp.commerceApp.exceptions.ProductAlreadyExists;
import com.commerceApp.commerceApp.exceptions.ProductDoesNotExists;
import com.commerceApp.commerceApp.exceptions.ProductNotActive;
import com.commerceApp.commerceApp.repositories.CategoryRepository;
import com.commerceApp.commerceApp.repositories.ProductRepository;
import com.commerceApp.commerceApp.repositories.SellerRepository;
import com.commerceApp.commerceApp.util.EntityDtoMapping;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.commerceApp.commerceApp.util.EntityDtoMapping.*;

@Service
public class ProductService {

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
        if (!message.equalsIgnoreCase("Success"))
            return new ResponseEntity<>("Validation Failed", HttpStatus.BAD_REQUEST);
        Category category = categoryRepository.findById(productSellerDto.getCategoryId()).get();
        Seller seller = sellerRepository.findByEmail(email);
        Product product = toProduct(productSellerDto);
        product.setCategory(category);
        product.setSeller(seller);
        mailService.sendProductCreationMail(email, product);
        productRepository.save(product);
        return new ResponseEntity<>("Success", HttpStatus.OK);

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
        mailService.sendProductActivationMail(email);
        productRepository.save(product);
        return new ResponseEntity<>("Success", HttpStatus.OK);

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
        mailService.sendProductDeactivationMail(email, product);
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
        productSellerDto.setCategoryDto(EntityDtoMapping.toCategoryDto(product.getCategory()));
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
            productSellerDto.setCategoryDto(EntityDtoMapping.toCategoryDto(product.getCategory()));
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
        productCustomerDto.setCategoryDto(toCategoryDto(product.getCategory()));
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
        productAdminDto.setCategoryDto(EntityDtoMapping.toCategoryDto(product.getCategory()));
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
            productAdminDto.setCategoryDto(EntityDtoMapping.toCategoryDto(product.getCategory()));
            productDtos.add(productAdminDto);
        });
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
    public ResponseEntity<BaseDto> validateProductUpdate(Long id, String email, ProductUpdateDto productDto){
        BaseDto response;
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if(!savedProduct.isPresent()){
            message = "Product with id "+id+ " not found";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }
        Product product = savedProduct.get();
        if(!product.getSeller().getEmail().equalsIgnoreCase(email)){
            message = "Product with id does not belong to you.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }
        if(product.isDeleted()){
            message = "Product does not exist.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        if(productDto.getName() != null){
            Product duplicateProduct = productRepository.findByName(productDto.getName());
            if(duplicateProduct!=null){
                if(duplicateProduct.getCategory().getId().equals(product.getCategory().getId())){
                    if(duplicateProduct.getBrand().equalsIgnoreCase(product.getBrand())){
                        if(duplicateProduct.getSeller().getEmail().equalsIgnoreCase(email)){
                            message = "Product with similar details already exists.";
                            response = new ErrorDto("Validation failed", message);
                            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
                        }
                    }
                }
            }
        }
        return null;
    }
    public ResponseEntity<BaseDto> updateProductByProductId(Long id, String email, ProductUpdateDto productDto) {
        BaseDto response;
        String message;

        ResponseEntity<BaseDto> validationResult = validateProductUpdate(id, email, productDto);
        if(validationResult != null){
            return validationResult;
        }

        Product product = productRepository.findById(id).get();
        applyProductUpdateDtoToProduct(product, productDto);
        productRepository.save(product);

        response = new ResponseDto<>( "success", null);
        return new ResponseEntity<BaseDto>(response, HttpStatus.OK);
    }

    public void applyProductUpdateDtoToProduct(Product product, ProductUpdateDto productDto) {

        if(productDto.getName() != null)
            product.setName(productDto.getName());

        if(productDto.getDescription() != null)
            product.setDescription(productDto.getDescription());

        if(productDto.getReturnable() != null)
            product.setReturnable(productDto.getReturnable());

        if(productDto.getCancelleable() != null)
            product.setCancelleable(productDto.getCancelleable());

    }



}



