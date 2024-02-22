package encora.micro.cliente.web;

import encora.micro.cliente.service.ClientService;
import encora.micro.cliente.shared.dto.request.ClientRequestDto;
import encora.micro.cliente.shared.dto.response.ResponseGeneralDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RequestMapping(path = "/api/encora-micro-cliente/v1/clientes")
@RestController
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseGeneralDto> getClient() {
        return clientService.getClients();
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseGeneralDto> saveClient(@Validated @RequestBody ClientRequestDto request) {
        return clientService.saveClient(request);
    }
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseGeneralDto> updateClient(@PathVariable("id") Integer id, @Validated @RequestBody ClientRequestDto request) {
        return clientService.udpateClient(id, request);
    }
}
