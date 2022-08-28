package com.correa.workShop2.dto;

import java.io.Serializable;

import com.correa.workShop2.dominio.User;

/*DTO (Data Transfer Object(Objeto de transferencia de dados)): 
 * 
 * é um objeto que tem o papel de carregar dados das entidades de forma simples,
 * podendo "projetar" apenas alguns dados da entidade original. 
 * 		Vantagens:
-				 Otimizar o tráfego (trafegando menos dados)
-				 Evitar que dados de interesse exclusivo do sistema fiquem sendo expostos 
-				 Customizar os objetos trafegados conforme a necessidade de cada requisição 
 */

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	public UserDTO() {
		super();
	}

	//construtor reposavel por instanciar userdto apartir do user(cópia dos dados do usuario para dto)
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

}
