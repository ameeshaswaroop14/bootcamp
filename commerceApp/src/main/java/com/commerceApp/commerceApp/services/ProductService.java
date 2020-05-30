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
import com.commerceApp.commerceApp.repositories.categoryRepos.CategoryRepository;
import com.commerceApp.commerceApp.repositories.productRepos.CustomProductRepo;
import com.commerceApp.commerceApp.repositories.productRepos.ProductRedisRepo;
import com.commerceApp.commerceApp.repositories.productRepos.ProductRepository;
import com.commerceApp.commerceApp.repositories.userRepos.SellerRepository;
import com.commerceApp.commerceApp.util.EntityDtoMapping;
import com.commerceApp.commerceApp.util.responseDtos.BaseDto;
import com.commerceApp.commerceApp.util.responseDtos.ErrorDto;
import com.commerceApp.commerceApp.util.responseDtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Autowired
    CustomProductRepo customProductRepo;
    @Autowired
    ProductRedisRepo productRedisRepo;

    public String validateNewProduct(String email, ProductSellerDto productDto) {
        BaseDto response;
        String message;

        Optional<Category> savedCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!savedCategory.isPresent()) {
            message = "Category does not exist.";
            return message;
        }
        Category category = savedCategory.get();
        if (!(category.getSubCategories() == null || category.getSubCategories().isEmpty())) {
            message = "Category is not a leaf category.";
            return message;
        }

        Product savedProduct = productRepository.findByName(productDto.getName());
        if (savedProduct != null) {
            if (savedProduct.getCategory().getId().equals(productDto.getCategoryId())) {
                if (savedProduct.getBrand().equalsIgnoreCase(productDto.getBrand())) {
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

    public BaseDto saveNewProduct(String email, ProductSellerDto productDto) {
        BaseDto response;
        String message = validateNewProduct(email, productDto);
        if (!message.equalsIgnoreCase("success")) {
            response = new ErrorDto("Validation failed", message);
            return response;

        }

        Category category = categoryRepository.findById(productDto.getCategoryId()).get();

        Product product = toProduct(productDto);
        Seller seller = sellerRepository.findByEmail(email);
        product.setCategory(category);
        product.setSeller(seller);
        productRepository.save(product);

        mailService.sendProductCreationMail(email, product);

        response = new ResponseDto<>(null, "Success");
        return response;
    }


    public BaseDto activateProductById(Long id) {
        Optional<Product> savedproduct = productRepository.findById(id);
        if (!savedproduct.isPresent())
            return new ErrorDto("not found","Product with the given id does not exist");
        Product product = savedproduct.get();
        if (product.isActive())
            return new ErrorDto("","Product already active");
        product.setActive(true);
        String email = product.getSeller().getEmail();
        mailService.sendProductActivationMail(email);
        productRepository.save(product);
        return new ResponseDto<>("activated",null);

    }

    public BaseDto deactivateproductById(Long id) {
        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent())
            return new ErrorDto("Not found","Product does not exist");
        Product product = savedProduct.get();
        if (!product.isActive())
            return new ErrorDto("Bad Request","Product already inactive");
        product.setActive(false);
        String email = product.getSeller().getEmail();
        mailService.sendProductDeactivationMail(email, product);
        productRepository.save(product);
        return new ResponseDto<>("Deactivated",null);

    }

    public BaseDto getProductByIdForSeller(Long id, String email) {
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            throw new ProductAlreadyExists("Product does not exists");
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
        BaseDto response=new ResponseDto<>(null,productSellerDto);
        return response;
    }

    //cache
    @Cacheable(value = "productCache")

    public List getAllProductsForSeller(String offset, String size, String sortByField, String order, Long categoryId, String brand) {

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
        return productDtos;
    }

    public BaseDto deleteProductById(Long id, String email) {
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            return new ErrorDto("Not found",null);
        }
        Product product = savedProduct.get();
        if (!product.getSeller().getEmail().equalsIgnoreCase(email)) {
            return new ErrorDto("Unauthorized",null);
        }
        if (product.isDeleted()) {
            return new ErrorDto("product is already deleted",null);
        }


        customProductRepo.deleteByProductId(id);
        return new ResponseDto<>("Deleted",null);
    }

    public BaseDto getProductByIdForCustomer(Long id) {
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            throw new ProductDoesNotExists("Product does not exist");
        }
        Product product = savedProduct.get();

        if (product.isDeleted()) {
            throw new ProductDoesNotExists("Product does not exist");

        }
        if (!product.isActive()) {
            throw new ProductNotActive("Product not active");
        }
        ProductCustomerDto productCustomerDto = toproductCustomerDto(product);
        productCustomerDto.setCategoryDto(toCategoryDto(product.getCategory()));
        return new ResponseDto<>(null,productCustomerDto);
    }

    public BaseDto getProductByIdForAdmin(Long id) {
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            throw new ProductDoesNotExists("Product does not exist");
        }
        Product product = savedProduct.get();

        if (product.isDeleted()) {
            throw new ProductDoesNotExists("Product does not exist");

        }
        if (!product.isActive()) {
            throw new ProductNotActive("Product not active");
        }
        ProductAdminDto productAdminDto = toProductAdminDto(product);
        productAdminDto.setCategoryDto(EntityDtoMapping.toCategoryDto(product.getCategory()));
        return new ResponseDto<>(null,productAdminDto);
    }
    @Cacheable(value = "productsAdminCache")
    public List getAllProductsForAdmin(Long categoryId, String offset, String size, String sortByField, String order, String brand) {

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
        return productDtos;
    }

    public ResponseEntity<BaseDto> validateProductUpdate(Long id, String email, ProductUpdateDto productDto) {
        BaseDto response;
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            message = "Product with id " + id + " not found";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.NOT_FOUND);
        }
        Product product = savedProduct.get();
        if (!product.getSeller().getEmail().equalsIgnoreCase(email)) {
            message = "Product with id does not belong to you.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }
        if (product.isDeleted()) {
            message = "Product does not exist.";
            response = new ErrorDto("Validation failed", message);
            return new ResponseEntity<BaseDto>(response, HttpStatus.BAD_REQUEST);
        }

        if (productDto.getName() != null) {
            Product duplicateProduct = productRepository.findByName(productDto.getName());
            if (duplicateProduct != null) {
                if (duplicateProduct.getCategory().getId().equals(product.getCategory().getId())) {
                    if (duplicateProduct.getBrand().equalsIgnoreCase(product.getBrand())) {
                        if (duplicateProduct.getSeller().getEmail().equalsIgnoreCase(email)) {
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

    public BaseDto updateProductByProductId(Long id, String email, ProductUpdateDto productDto) {
        BaseDto response;
        String message;

        ResponseEntity<BaseDto> validationResult = validateProductUpdate(id, email, productDto);
        if (validationResult != null) {
            return new ResponseDto<>(null,validationResult);

        }

        Product product = productRepository.findById(id).get();
        applyProductUpdateDtoToProduct(product, productDto);
        productRepository.save(product);

        response = new ResponseDto<>("success", null);
        return response;
    }

    public void applyProductUpdateDtoToProduct(Product product, ProductUpdateDto productDto) {

        if (productDto.getName() != null)
            product.setName(productDto.getName());

        if (productDto.getDescription() != null)
            product.setDescription(productDto.getDescription());

        if (productDto.getReturnable() != null)
            product.setReturnable(productDto.getReturnable());

        if (productDto.getCancelleable() != null)
            product.setCancelleable(productDto.getCancelleable());

    }
    public List getAllProducts(Optional<String>offset,Optional<String >size, Optional<String>sortByField,Optional<String> order) {
        String getOffset=offset.get();
        String getSize=size.get();
        String getSortBy=sortByField.get();
        String getOrder=order.get();
        Integer pageNo = Integer.parseInt(getOffset);
        Integer pageSize = Integer.parseInt(getSize);

        if (getOrder.equalsIgnoreCase("Des")) {

            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.desc(getSortBy)));
            List<Product> products =productRepository.findAll(pageable);
            return products;

        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc(getSortBy)));
        List<Product> products =productRepository.findAll(pageable);
        return products;

    }

    public List getAllProducts(Optional<String> searchType, Optional<String> search) {

        String type=searchType.get();
        String searchParam=search.get();
        if (type.equalsIgnoreCase("name"))
            return customProductRepo.findByName(searchParam);

        else if (type.equalsIgnoreCase("brand"))
            return customProductRepo.findByBrand(searchParam);
        else if (type.equalsIgnoreCase("description"))
            return customProductRepo.findByDescription(searchParam);
        else
            return customProductRepo.findById(Long.valueOf(searchParam));

    }
    public List getAllProducts(){

        return productRepository.findAll();
    }




}



