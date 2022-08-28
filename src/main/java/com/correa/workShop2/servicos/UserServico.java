package com.correa.workShop2.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.correa.workShop2.dominio.User;
import com.correa.workShop2.dto.UserDTO;
import com.correa.workShop2.repositorios.UserRepositorio;
import com.correa.workShop2.servicos.excecoes.ObjetoNaoEncotradoExcecao;

@Service
public class UserServico {
	
	//para o serviço conversar com o repositorio
	//injeção de dependecncia automática do spring 
	//declaração de um objeto dentro de uma classe e fazendo a instanciação do objeto repo
	@Autowired
	private UserRepositorio repo;
		
	public List<User> findAll(){
		return repo.findAll();		
	}
	
	//método para retornar objeto do tipo usuario recebendo string id como argumento
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncotradoExcecao("Objeto não encontrado!"));
	}
	//métodos insert pra retornar o objeto inserido user obj como argumento
	public User insert(User obj) {
		//retorno
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	//implementação do método fromdto
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
