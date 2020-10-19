package com.indracompany.treinamento.controller.rest;

	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.service.ClienteService;


@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("rest/clientes")
public class ClienteRest extends GenericCrudRest<Cliente, Long, ClienteService>{

	@Autowired
	private ClienteService clienteService; 
	
	@RequestMapping(value = "/buscar-por-cpf/{cpf}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Cliente> buscarClientePorCpf(final @PathVariable String cpf) {
		Cliente retorno = clienteService.buscarClientePorCpf(cpf);
		return new ResponseEntity<>(retorno, HttpStatus.OK);
	}
	
	// a busca aqui é por uma lista
	@RequestMapping(value = "/buscar-por-ativo/{ativo}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<List<Cliente>> buscarClientePorAtivo(final @PathVariable boolean ativo) {
		List<Cliente> retornoAtivo = clienteService.buscarClientePorAtivo(ativo);
		return new ResponseEntity<List<Cliente>>(retornoAtivo, HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/deletar-por-cpf/{cpf}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody void deletarClientePorCpf(final @PathVariable String cpf) {
		clienteService.deletarClientePorCpf(cpf);
	}

}
