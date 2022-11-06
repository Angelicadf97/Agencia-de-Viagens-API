package br.org.com.recode.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.org.com.recode.controller.dto.ClienteDTO;
import br.org.com.recode.controller.form.ClienteForm;
import br.org.com.recode.model.Cliente;
import br.org.com.recode.repository.ClienteRepository;

@Controller
@ResponseBody
@RequestMapping("/")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/")
	  public List<ClienteDTO> lista() {
	    List<Cliente> cli = clienteRepository.findAll();
	    return ClienteDTO.converter(cli);
	  }

	  @GetMapping("/{id}")
	  public ClienteDTO detalhar(@PathVariable Long id) {
	    Cliente cliente = clienteRepository.getReferenceById(id);
	    return new ClienteDTO(cliente);
	  }

	  @DeleteMapping("/{id}")
	  public ResponseEntity<?> remove(@PathVariable Long id) {

	    clienteRepository.deleteById(id);

	    return ResponseEntity.ok().build();
	  }

	  @PostMapping("/")
	  public ResponseEntity<ClienteDTO> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {

	    Optional<Cliente> cli = clienteRepository.findByEmail(form.getEmail());

	    if (cli.isPresent()) {
	      return ResponseEntity.badRequest().build();
	    } else {

	      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	      Cliente cliente = form.converter(encoder);
	      clienteRepository.save(cliente);
	      URI uri = uriBuilder.path("/{id}").buildAndExpand(cliente.getId()).toUri();
	      return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
	    }

	  }
}
