package br.org.com.recode.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.org.com.recode.model.Cliente;
import br.org.com.recode.model.Hospedagem;
import br.org.com.recode.repository.HospedagemRepository;

@Controller
@ResponseBody
@RequestMapping("/adm")
public class HospedagemController {
	
	@Autowired
	private HospedagemRepository hosRepository;

	// get all clients
	@GetMapping("/hospedagens/")
	public List<Hospedagem> lista() {
		return hosRepository.findAll();
	}
	
//	@GetMapping("/imagem/{id}")
//	@ResponseBody
//	public byte[] exibirImagen(@PathVariable("id") Long id) {
//		Hospedagem hospedagem = this.hosRepository.getOne(id);
//		return hospedagem.getImagem();
//	}
	

	// create client rest api
	@PostMapping("/hospedagens/")
	public Hospedagem createHospedagem(@RequestBody Hospedagem hos) {
//		, @RequestParam("fileCliente") MultipartFile file
//		try {
//			hos.setImagem(file.getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return hosRepository.save(hos);
	}

	// get client by id rest api
	@GetMapping("/hospedagens/{id}")
	public Hospedagem detalhar(@PathVariable Long id) {
		return hosRepository.findById(id).get();
	}

	// update client rest api

	@PutMapping("/hospedagens/{id}")
	public Hospedagem updateHospedagem(@PathVariable Long id, @RequestBody Hospedagem hosDetails) {
		Hospedagem hos = hosRepository.findById(id).get();

		hos.setNome(hosDetails.getNome());
		hos.setCnpj(hosDetails.getCnpj());
		hos.setTipo(hosDetails.getTipo());
		hos.setCidade(hosDetails.getCidade());
		hos.setPreco_dia(hosDetails.getPreco_dia());
		hos.setDesconto(hosDetails.getDesconto());

		return hosRepository.save(hos);

	}

	// delete client rest api
	@DeleteMapping("/hospedagens/{id}")
	public void deleteHospedagem(@PathVariable Long id) {
		hosRepository.deleteById(id);
	}
	
	

	
}
