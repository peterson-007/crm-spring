package org.ultimacrm.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ultimacrm.dtos.validations.Cpf;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ClientInputDTO {

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @Cpf
    private String cpf;
}
