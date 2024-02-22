package encora.micro.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import encora.micro.cliente.service.ClientService;
import encora.micro.cliente.shared.dto.request.ClientRequestDto;
import encora.micro.cliente.shared.dto.response.ResponseGeneralDto;
import encora.micro.cliente.web.ClientController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EncoraMicroClienteApplicationTests {

	private MockMvc mockMvc;

	@Mock
	private ClientService clientService;

	@InjectMocks
	private ClientController clientController;


	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
	}

	@Test
	public void testGetClients() throws Exception {
		when(clientService.getClients()).thenReturn(ResponseEntity.ok(new ResponseGeneralDto()));

		mockMvc.perform(get("/api/encora-micro-cliente/v1/clientes")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testSaveClient() throws Exception {
		ClientRequestDto request = new ClientRequestDto();
		request.setName("Key");
		request.setPatSurname("Cuevas");
		request.setMatSurname("Melgarejo");

		when(clientService.saveClient(any(ClientRequestDto.class))).thenReturn(ResponseEntity.ok(new ResponseGeneralDto()));

		mockMvc.perform(post("/api/encora-micro-cliente/v1/clientes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(request)))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateClient() throws Exception {
		int id = 1;
		ClientRequestDto request = new ClientRequestDto();
		request.setName("Enrique");
		request.setPatSurname("Cuevas");
		request.setMatSurname("Melgarejo");

		when(clientService.udpateClient(id, request))
				.thenReturn(ResponseEntity.ok(new ResponseGeneralDto()));

		mockMvc.perform(put("/api/encora-micro-cliente/v1/clientes/{id}", id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(request)))
				.andExpect(status().isOk());
	}
}
