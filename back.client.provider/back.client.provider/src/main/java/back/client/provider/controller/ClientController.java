package back.client.provider.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DuplicateKeyException;

import back.client.provider.model.Client;
import back.client.provider.repository.ClientRepository;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@GetMapping("/clients")
	public ResponseEntity<List<Client>> getAllClientes(@RequestParam(required = false) String name) {
		try {
			List<Client> clientes = new ArrayList<Client>();

			if (name == null) {
				clientRepository.findAll().forEach(clientes::add);
			} else {
				clientRepository.findByName(name).forEach(clientes::add);
			}

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/clientes/id/{id}")
	public ResponseEntity<Client> getClienteById(@PathVariable("id") String id) {
		Optional<Client> clienteData = clientRepository.findById(id);

		if (clienteData.isPresent()) {
			return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/clientes/name/{name}")
	public ResponseEntity<Client> getClientByName(@PathVariable("name") String name) {
		Client aux = clientRepository.findByName(name).get(0);
		Optional<Client> clienteData = Optional.of(aux);

		if (clienteData.isPresent()) {
			return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/clients/email/{email}")
	public ResponseEntity<Client> getClientByEmail(@PathVariable("email") String email) {
		Client aux = clientRepository.findByEmail(email).get(0);
		Optional<Client> clienteData = Optional.of(aux);

		if (clienteData.isPresent()) {
			return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/clients/identification/{identification}")
	public ResponseEntity<Client> getClientByIdentification(@PathVariable("identification") Long identification) {
		
		List<Client> clients = clientRepository.findByIdentification(identification);

	    if (!clients.isEmpty()) {
	        Client client = clients.get(0);
	        return new ResponseEntity<>(client, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
		
	}

	
	@PostMapping("/clients")
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		try {
			Client _cliente = clientRepository.save(new Client(client.getIdentification(), client.getAddress(),
					client.getEmail(), client.getName(), client.getPhone()));
			return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<>(null, HttpStatus.IM_USED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// RECOMENDACIÓN, ENVIAR JSON SIN ID PERO EL SI ES OBLIGATORIO EN LA URL
	@PutMapping("/clients/id/{id}")
	public ResponseEntity<Client> updateClientById(@PathVariable("id") String id, @RequestBody Client client) {
		Optional<Client> clienteData = clientRepository.findById(id);

		if (clienteData.isPresent()) {
			Client _cliente = clienteData.get();
			_cliente.setIdentification(client.getIdentification());
			_cliente.setAddress(client.getAddress());
			_cliente.setEmail(client.getEmail());
			_cliente.setName(client.getName());
			_cliente.setPhone(client.getPhone());

			return new ResponseEntity<>(clientRepository.save(_cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/clients/identification/{identification}")
	public ResponseEntity<Client> updateClientByIdentification(@PathVariable("identification") long identification,
			@RequestBody Client client) {
		Client aux = clientRepository.findByIdentification(identification).get(0);
		Optional<Client> clienteData = Optional.of(aux);

		if (clienteData.isPresent()) {
			Client _cliente = clienteData.get();
			_cliente.setIdentification(client.getIdentification());
			_cliente.setAddress(client.getAddress());
			_cliente.setEmail(client.getEmail());
			_cliente.setName(client.getName());
			_cliente.setPhone(client.getPhone());

			return new ResponseEntity<>(clientRepository.save(_cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/clients/id/{id}")
	public ResponseEntity<HttpStatus> deleteClientesById(@PathVariable("id") String id) {
		try {
			clientRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/clients/identification/{identification}")
	public ResponseEntity<HttpStatus> deleteClientsByIdentification(
			@PathVariable("identification") long identification) {
		try {
			clientRepository.deleteByIdentification(identification);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/clients/name/{name}")
	public ResponseEntity<HttpStatus> deleteClientsByName(@PathVariable("name") String name) {
		try {
			clientRepository.deleteByName(name);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/clients/email/{email}")
	public ResponseEntity<HttpStatus> deleteClientsByEmail(@PathVariable("email") String email) {
		try {
			clientRepository.deleteByEmail(email);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/clients")
	public ResponseEntity<HttpStatus> deleteAllClientess() {
		try {
			clientRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/clients/{name}")
	public ResponseEntity<List<Client>> findByName(@PathVariable("name") String name) {
		try {
			List<Client> clientes = clientRepository.findByName(name);

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
