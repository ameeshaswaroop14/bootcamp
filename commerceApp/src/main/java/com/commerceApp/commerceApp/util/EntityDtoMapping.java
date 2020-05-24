package com.commerceApp.commerceApp.util;

import com.commerceApp.commerceApp.dtos.categoryDtos.CategoryDto;
import com.commerceApp.commerceApp.dtos.productDto.ProductAdminDto;
import com.commerceApp.commerceApp.dtos.productDto.ProductCustomerDto;
import com.commerceApp.commerceApp.dtos.productDto.ProductSellerDto;
import com.commerceApp.commerceApp.dtos.productDto.ProductvariationSellerDto;
import com.commerceApp.commerceApp.dtos.profileDtos.CustomerViewProfileDto;
import com.commerceApp.commerceApp.dtos.registrationDtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.registrationDtos.SellerRegistrationDto;
import com.commerceApp.commerceApp.models.*;
import com.commerceApp.commerceApp.models.category.Category;
import com.commerceApp.commerceApp.dtos.*;
import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.models.product.ProductReview;
import com.commerceApp.commerceApp.models.product.ProductVariation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;
import java.util.Optional;

public class EntityDtoMapping {

    static ModelMapper modelMapper=new ModelMapper();
    public static Address toAddress(AddressDto addressDto){
        if(addressDto != null)
            return modelMapper.map(addressDto, Address.class);
        return null;
    }

    public static AddressDto toAddressDto(Address address){
        if(address != null)
            return modelMapper.map(address, AddressDto.class);
        return null;
    }
    public static CategoryDto toCategoryDto(Category category){
        if(category == null)
            return null;
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        CategoryDto parentDto = toCategoryDto(category.getParentCategory());
        categoryDto.setParent(parentDto);
        return categoryDto;
    }
    public static Customer toCustomer(CustomerRegistrationDto customerRegistrationDto) {
        Customer customer = modelMapper.map(customerRegistrationDto, Customer.class);
        return customer;
    }

    public static AdminCustomerDto toCustomerDto(Customer customer) {
        AdminCustomerDto adminCustomerDto = modelMapper.map(customer, AdminCustomerDto.class);
        adminCustomerDto.setFirstName(customer.getFirstName());
        adminCustomerDto.setMiddleName(customer.getMiddleName());
        adminCustomerDto.setLastName(customer.getLastName());
        return adminCustomerDto;
    }

    public static Customer toCustomer(CustomerViewProfileDto customerViewProfileDto) {
        Customer customer = modelMapper.map(customerViewProfileDto, Customer.class);
        return customer;
    }

    public static CustomerViewProfileDto tocustomerViewProfileDto(Customer customer) {
        CustomerViewProfileDto customerViewProfileDto = modelMapper.map(customer, CustomerViewProfileDto.class);
        return customerViewProfileDto;
    }

    public static Product toProduct(ProductSellerDto productSellerDto) {
        if (productSellerDto == null)
            return null;
        return modelMapper.map(productSellerDto, Product.class);
    }

    public static ProductSellerDto toProductSellerDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductSellerDto.class);
    }
    public static Product toProduct(ProductCustomerDto productCustomerDto) {
        if (productCustomerDto == null)
            return null;
        return modelMapper.map(productCustomerDto, Product.class);
    }
    public static ProductCustomerDto toproductCustomerDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductCustomerDto.class);
    }
    public static Product toProduct(ProductAdminDto productAdminDto) {
        if (productAdminDto == null)
            return null;
        return modelMapper.map(productAdminDto, Product.class);
    }
    public static ProductAdminDto toProductAdminDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductAdminDto.class);
    }

    public static Seller toSeller(SellerRegistrationDto sellerRegistrationDto) {
        Seller seller = modelMapper.map(sellerRegistrationDto, Seller.class);
        return seller;
    }
    public static AdminSellerDto toadminSellerDto(Seller seller){
        AdminSellerDto adminSellerDto=modelMapper.map(seller,AdminSellerDto.class);
        return adminSellerDto;
    }

    public static ProductVariation toProductVariation(ProductvariationSellerDto variationDto) {
        if (variationDto == null)
            return null;
        return modelMapper.map(variationDto, ProductVariation.class);
    }

    public static ProductvariationSellerDto
    toProductVariationSellerDto(ProductVariation variation) {
        if (variation == null)
            return null;
        return modelMapper.map(variation, ProductvariationSellerDto.class);
    }
    public static ReviewDto toReviewDto(ProductReview productReview){
        return modelMapper.map(productReview,ReviewDto.class);
    }
    public static ProductReview toProductReview(ReviewDto reviewDto){
        return modelMapper.map(reviewDto,ProductReview.class);
    }
    public static CartDto toCartDto(Cart cart){
        return modelMapper.map(cart,CartDto.class);
    }
    public static Cart toCart(CartDto cartDto){
        return modelMapper.map(cartDto,Cart.class);
    }
    public static ResponseCartDto toResponseCartDto(Cart cart){
        return modelMapper.map(cart,ResponseCartDto.class);
    }












}
