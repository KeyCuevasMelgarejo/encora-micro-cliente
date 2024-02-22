package encora.micro.cliente.shared.dto.response;

import encora.micro.cliente.domain.ClienteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {

    private Integer id;
    private String name;

    public static List<ClientResponseDto> fromEntityList(List<ClienteEntity> entities) {
        return entities.stream()
                .map(ClientResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public static ClientResponseDto fromEntity(ClienteEntity entity) {
        return ClientResponseDto.builder()
                .id(entity.getId())
                .name(entity.getFullName())
                .build();
    }
}
