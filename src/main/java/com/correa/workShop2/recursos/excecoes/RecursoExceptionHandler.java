package com.correa.workShop2.recursos.excecoes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.correa.workShop2.servicos.excecoes.ObjetoNaoEncotradoExcecao;

//classe manipuladora de excecoes na camada de recursos
//tratar possíveis erros nas requisicoes
@ControllerAdvice
public class RecursoExceptionHandler {
		
	@ExceptionHandler(ObjetoNaoEncotradoExcecao.class)
	public ResponseEntity<ErroPadrao> objetoNaoEncontrado(ObjetoNaoEncotradoExcecao e,HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroPadrao err = new ErroPadrao(System.currentTimeMillis(),status.value(), "Não encontrado!", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
