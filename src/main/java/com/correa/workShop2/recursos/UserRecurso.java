package com.correa.workShop2.recursos;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.correa.workShop2.dominio.Post;
import com.correa.workShop2.dominio.User;
import com.correa.workShop2.dto.UserDTO;
import com.correa.workShop2.servicos.UserServico;

@RestController
@RequestMapping(value = "/users") //boa pratica colocar o nome do recurso em plural
public class UserRecurso {
	
	/*Controlador rest acessa os serviços
	 * o serviço por sua vez acessa o repositorio
	 */
	
	//injeção do servico	
	@Autowired
	private UserServico service;
	
	@RequestMapping(method=RequestMethod.GET)
	//método que (findAll busca todos) retornara uma lista de objeto do tipo usuario
	public ResponseEntity<List<UserDTO>> findAll(){
		//busca no banco de dados os elementos do objeto usuario e armazena-los na lista 
		List<User> list = service.findAll();
		//conversao de cada objeto da lista original para dto
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());		
		//retorno da lista em forma dto
				return ResponseEntity.ok().body(listDto);
	}
	
	//método para tornar o usuario por id
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
 	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	//método entrepoint para inserir um novo usuário ao db
	@RequestMapping(method=RequestMethod.POST)
 	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		//código especifico do spring que pega o endereço do novo objeto inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		//creted retorna o codigo 201(codigo de resposta http quando se cria um novo recurso)
		//esse comando retorna uma resposta vazia com a localização do caminho criado no cabeçalho
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
 	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
 	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}/posts", method=RequestMethod.GET)
 	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
}
