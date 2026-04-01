package com.dev.blogging.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	private int id;
	@NotEmpty(message = "username must be a 4 character")
	@Size(min = 4)
	private String name;	
	@Email(message = "email is not valid!!")
	private String email;
	@NotEmpty()
	@Size(min = 4 , max = 16 , message = "password must be more than 4 char and less than 16 char")
	private String password;
	@NotEmpty(message = "about field is not be  empty")
	private String about;
}
