package com.store.dto;

import com.store.entity.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Field is required")
    private String first_name;

    @NotEmpty(message = "Field is required")
    private String last_name;

    @Email(message = "Email invalid")
    @NotEmpty(message = "Field is required")
    private String email;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.email = client.getEmail();
        this.first_name = client.getFirstName();
        this.last_name = client.getLastName();
    }

}
