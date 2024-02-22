package encora.micro.cliente.service.implement;

import encora.micro.cliente.common.component.ClienteAdapter;
import encora.micro.cliente.common.util.Constants;
import encora.micro.cliente.domain.ClienteEntity;
import encora.micro.cliente.repository.ClientRepository;
import encora.micro.cliente.service.ClientService;
import encora.micro.cliente.shared.dto.request.ClientRequestDto;
import encora.micro.cliente.shared.dto.response.ClientResponseDto;
import encora.micro.cliente.shared.dto.response.ResponseGeneralDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<ResponseGeneralDto> getClients() {
        List<ClienteEntity> clientsFound = clientRepository.findAll();

        List<ClientResponseDto> clientsDto = ClientResponseDto.fromEntityList(clientsFound);

        return new ResponseEntity<>(ClienteAdapter.responseGeneral(
                Constants.HTTP_200, HttpStatus.OK.value(), Constants.messageProcessOK, clientsDto
        ), HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<ResponseGeneralDto> saveClient(ClientRequestDto request) {
        ClienteEntity response = clientRepository.save(ClienteAdapter.save(request));

        return new ResponseEntity<>(ClienteAdapter.responseGeneral(
                Constants.HTTP_200, HttpStatus.OK.value(), Constants.messageProcessOK, response
        ),HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<ResponseGeneralDto> udpateClient(Integer id, ClientRequestDto request) {
        Optional<ClienteEntity> entity = Optional.ofNullable(clientRepository.findById(id).orElse(null));
        if (entity.isPresent()) {
            clientRepository.save(ClienteAdapter.update(id, request));

            return new ResponseEntity<>(ClienteAdapter.responseGeneral(
                    Constants.HTTP_200, HttpStatus.OK.value(), Constants.messageProcessOK, entity
            ),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ClienteAdapter.responseException(
                    Constants.HTTP_400, HttpStatus.NOT_FOUND.value(), Constants.messageNotFound
            ),HttpStatus.NOT_FOUND);
        }
    }
}
