package com.correa.workShop2.servicos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.correa.workShop2.dominio.Post;
import com.correa.workShop2.repositorios.PostRepositorio;
import com.correa.workShop2.servicos.excecoes.ObjetoNaoEncotradoExcecao;

@Service
public class PostServico {
	
	@Autowired
	private PostRepositorio repo;

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncotradoExcecao("Objeto não encontrado"));
	}


	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
