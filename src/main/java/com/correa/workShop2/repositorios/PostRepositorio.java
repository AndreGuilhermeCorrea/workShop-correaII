package com.correa.workShop2.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.correa.workShop2.dominio.Post;

@Repository
public interface PostRepositorio extends MongoRepository <Post, String> {
		
	//ref:
	//	https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
	//	https://www.mongodb.com/docs/manual/reference/operator/query/regex/
	
	//método query para gerar consultas + regex
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")	//expressão regular regex
	List<Post> searchTitle(String text);
	//findByTitleContainingIgnoreCase consulta ignorando maiusculas e minusculas
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	//método query para gerar consultas + regex
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }") //expressão regular regex
	List<Post> fullSearch(String text, Date minDate, Date maxDate);

}
