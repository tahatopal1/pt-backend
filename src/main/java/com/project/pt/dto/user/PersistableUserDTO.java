package com.project.pt.dto.user;

import com.project.pt.annotation.PasswordValueMatch;
import com.project.pt.annotation.ValidPassword;
import com.project.pt.dto.AddressDTO;
import com.project.pt.dto.BaseEntityDTO;
import com.project.pt.model.constant.Gender;
import com.project.pt.model.constant.Type;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        )
})
@SuperBuilder
public class PersistableUserDTO extends BaseEntityDTO {

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 80 characters")
    private String name;

    @NotNull(message = "Surname cannot be null")
    @Size(min = 3, max = 40, message = "Surname must be between 3 and 20 characters")
    private String surname;

    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 20, message = "Username must be between 8 and 20 characters")
    @Email
    private String username;

    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;

    @NotNull(message = "Birth date cannot be null")
    private Date birthDate;

    @Valid
    private AddressDTO address;

    @ValidPassword
    private String password;

    @ValidPassword
    private String confirmPassword;

    @Builder.Default
    private Gender gender = Gender.MALE;

    @Builder.Default
    private Type type = Type.CUSTOMER;

}
