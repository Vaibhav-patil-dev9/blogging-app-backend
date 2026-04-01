package com.dev.blogging.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {
	int categoryId;
	@NotEmpty(message = "category title not be empty")
	String categoryTitle;
	@NotEmpty(message = "category Description not be empty")
	String categoryDescription;
}
