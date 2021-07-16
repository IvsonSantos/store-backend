package com.store.dto;

import com.store.entity.Product;
import com.store.service.ProductCategoryService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long category;

    @NotEmpty(message = "Field is required")
    private String name;

    @NotEmpty(message = "Field is required")
    private String description;

    @NotEmpty(message = "Field is required")
    private BigDecimal unitPrice;

    @NotEmpty(message = "Field is required")
    private String imageUrl;

    @NotEmpty(message = "Field is required")
    private boolean active;

    @NotEmpty(message = "Field is required")
    private int unitsInStock;

    private LocalDateTime dateCreated;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.category = product.getCategory().getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.unitPrice = product.getUnitPrice();
        this.imageUrl = product.getImageUrl();
        this.active = product.isActive();
        this.unitsInStock = product.getUnitsInStock();

    }

}
