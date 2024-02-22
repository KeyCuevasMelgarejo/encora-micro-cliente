package encora.micro.cliente.common.component;

import encora.micro.cliente.domain.ClienteEntity;
import encora.micro.cliente.shared.dto.request.ClientRequestDto;
import encora.micro.cliente.shared.dto.response.ResponseGeneralDto;
import org.springframework.stereotype.Component;

@Component
public class ClienteAdapter {
    public static ResponseGeneralDto responseGeneral(String code, Integer status, String message, Object data) {
        return ResponseGeneralDto.builder()
                .status(status)
                .code(code)
                .comment(message)
                .data(data)
                .build();
    }

    public static ResponseGeneralDto responseException(String code, Integer status, String message){
        return ResponseGeneralDto.builder()
                .status(status)
                .code(code)
                .comment(message)
                .build();
    }

    public static ClienteEntity save (ClientRequestDto request){
        return ClienteEntity.builder()
                .name(request.getName())
                .patSurname(request.getPatSurname())
                .matSurname(request.getMatSurname())
                .status(request.getStatus())
                .build();
    }

    public static ClienteEntity update (Integer id, ClientRequestDto request){
        return ClienteEntity.builder()
                .id(id)
                .name(request.getName())
                .patSurname(request.getPatSurname())
                .matSurname(request.getMatSurname())
                .status(request.getStatus())
                .build();
    }
}
