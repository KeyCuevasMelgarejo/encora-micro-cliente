package encora.micro.cliente.shared.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRequestDto {

    @NotNull(message = "El campo nombre no puede ser nulo")
    @NotBlank(message = "El campo nombre no puede ser vacío")
    @NotEmpty(message = "El campo nombre no puede ser vacío")
    private String name;

    @NotNull(message = "El campo apellido paterno no puede ser nulo")
    @NotBlank(message = "El campo apellido paterno no puede ser vacío")
    @NotEmpty(message = "El campo apellido paterno no puede ser vacío")
    private String patSurname;

    @NotNull(message = "El campo apellido materno no puede ser nulo")
    private String matSurname;

    private Boolean status;
}
