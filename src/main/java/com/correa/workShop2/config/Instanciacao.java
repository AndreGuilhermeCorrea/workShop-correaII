package com.correa.workShop2.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.correa.workShop2.dominio.Post;
import com.correa.workShop2.dominio.User;
import com.correa.workShop2.dto.AutorDTO;
import com.correa.workShop2.dto.ComentarioDTO;
import com.correa.workShop2.repositorios.PostRepositorio;
import com.correa.workShop2.repositorios.UserRepositorio;


//classe de ##configuração## que implementa a interface comandlinerunner
@Configuration
public class Instanciacao implements CommandLineRunner {


		//injecao de dependencia
		@Autowired
		private UserRepositorio userRepositorio;
		
		@Autowired
		private PostRepositorio postRepositorio;

		//implementação padrao dos contrato da interface comandlinerunner
		@Override
		public void run(String... arg0) throws Exception {
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

			//implementação dos métodos
			
			//chamada da instanciação reponsavel por limpar a coleção ora criada no mongodb
			//zerar a coleção
			userRepositorio.deleteAll();
			postRepositorio.deleteAll();

			//instanciação dos 3 objetos do tipo user
			User maria = new User(null, "Maria Brown", "maria@gmail.com");
			User alex = new User(null, "Alex Green", "alex@gmail.com");
			User bob = new User(null, "Bob Grey", "bob@gmail.com");
			
			//chamada da instanciação reponsavel por salvar a nova coleção criada no userRepositorio
			userRepositorio.saveAll(Arrays.asList(maria, alex, bob));

			//instanciação dos posts
			Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AutorDTO(maria));
			Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AutorDTO(maria));		
			
			//instanciação dos comentarios nos posts
			ComentarioDTO c1 = new ComentarioDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AutorDTO(alex));
			ComentarioDTO c2 = new ComentarioDTO("Aproveite", sdf.parse("22/03/2018"), new AutorDTO(bob));
			ComentarioDTO c3 = new ComentarioDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AutorDTO(alex));

			//asociações post com comentarios
			post1.getComentarios().addAll(Arrays.asList(c1, c2));
			post2.getComentarios().addAll(Arrays.asList(c3));
			
			//chamada da instanciação reponsavel por salvar os posts
			postRepositorio.saveAll(Arrays.asList(post1, post2));
			
			maria.getPosts().addAll(Arrays.asList(post1, post2));
			userRepositorio.save(maria);
		}

}
