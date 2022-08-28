package com.correa.workShop2.servicos.excecoes;

//classe que se extende a runtime (excecao padrao do java) onde não é exigido tratamento
public class ObjetoNaoEncotradoExcecao extends RuntimeException {
	private static final long serialVersionUID = 1L;

	//sobrepor construtor basico que recebe string como argumento
	public ObjetoNaoEncotradoExcecao(String msg) {
		//repassar chamada para super classe
		super(msg);
	}
}
