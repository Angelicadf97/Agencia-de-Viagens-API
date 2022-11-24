package br.org.com.recode.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.org.com.recode.controller.dto.UserDTO;
import br.org.com.recode.controller.form.UserForm;
import br.org.com.recode.model.User;
import br.org.com.recode.repository.UserRepository;

@Controller
@ResponseBody
@RequestMapping("/adm")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public List<UserDTO> lista() {
		List<User> users = userRepository.findAll();
		return UserDTO.converter(users);
	}

	@GetMapping("/{id}")
	public UserDTO detalhar(@PathVariable Long id) {
		User user = userRepository.getReferenceById(id);
		return new UserDTO(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {

		userRepository.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/")
	public ResponseEntity<UserDTO> cadastrar(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {

		Optional<User> users = userRepository.findByEmail(form.getEmail());

		if (users.isPresent()) {
			return ResponseEntity.badRequest().build();
		} else {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			User user = form.converter(encoder);
			userRepository.save(user);
			URI uri = uriBuilder.path("/adm/{id}").buildAndExpand(user.getId()).toUri();
			return ResponseEntity.created(uri).body(new UserDTO(user));
		}

	}
}
