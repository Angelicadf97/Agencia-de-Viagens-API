package br.org.com.recode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.com.recode.model.Aeroporto;
import br.org.com.recode.repository.AeroportoRepository;

@Controller
@ResponseBody
@RequestMapping("/adm")
public class AeroportoController {
	
	@Autowired
	private AeroportoRepository aerRepository;

	// get all clients
	@GetMapping("/aeroportos/")
	public List<Aeroporto> lista() {
		return aerRepository.findAll();
	}

	// create client rest api
	@PostMapping("/aeroportos/")
	public Aeroporto createAeroporto(@RequestBody Aeroporto aer) {
		return aerRepository.save(aer);
	}

	// get client by id rest api
	@GetMapping("/aeroportos/{id}")
	public Aeroporto detalhar(@PathVariable Long id) {
		return aerRepository.findById(id).get();
	}

	// update client rest api

	@PutMapping("/aeroportos/{id}")
	public Aeroporto updateAeroporto(@PathVariable Long id, @RequestBody Aeroporto aerDetails) {
		Aeroporto aer = aerRepository.findById(id).get();

		aer.setNome(aerDetails.getNome());
		aer.setCnpj(aerDetails.getCnpj());
		aer.setCidade(aerDetails.getCidade());

		return aerRepository.save(aer);

	}

	// delete client rest api
	@DeleteMapping("/aeroportos/{id}")
	public void deleteAeroporto(@PathVariable Long id) {
		aerRepository.deleteById(id);
	}
}
