package encora.micro.cliente.service;

import encora.micro.cliente.shared.dto.request.ClientRequestDto;
import encora.micro.cliente.shared.dto.response.ResponseGeneralDto;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    ResponseEntity<ResponseGeneralDto> getClients();
    ResponseEntity<ResponseGeneralDto> saveClient(ClientRequestDto request);
    ResponseEntity<ResponseGeneralDto> udpateClient(Integer id, ClientRequestDto request);
}
