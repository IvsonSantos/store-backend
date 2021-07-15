package com.store.dto;

import com.store.entity.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProductCategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Field is required")
    private String categoryName;

    public ProductCategoryDTO(ProductCategory dto) {
        this.id = dto.getId();
        this.categoryName = dto.getCategoryName();
    }

}
