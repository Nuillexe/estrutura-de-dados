package repository.estaticas.pilha;

import repository.Empilhavel;
import repository.OverflowException;
import repository.UnderflowException;

/**
 * Implementação de uma Pilha Estática baseada em Array (LIFO).
 */
public class PilhaEstatica implements Empilhavel {

	protected int ponteiroTopo;
	protected Object[] dados;

	/**
	 * Construtor padrão com capacidade inicial para 10 elementos.
	 */
	public PilhaEstatica() {
		this(10);
	}

	/**
	 * Construtor com capacidade personalizada.
	 * @param tamanho Capacidade máxima do vetor.
	 */
	public PilhaEstatica(int tamanho) {
		if (tamanho <= 0) {
			throw new IllegalArgumentException("O tamanho deve ser maior que zero.");
		}
		ponteiroTopo = -1;
		dados = new Object[tamanho];
	}

	@Override
	public void empilhar(Object dado) {
		if (estaCheia()) {
			throw new OverflowException("Pilha Cheia!");
		}
		ponteiroTopo++;
		dados[ponteiroTopo] = dado;
	}

	@Override
	public void atualizar(Object dado) {
		if (estaVazia()) {
			throw new UnderflowException("Pilha Vazia!");
		}
		dados[ponteiroTopo] = dado;
	}

	@Override
	public Object desempilhar() {
		if (estaVazia()) {
			throw new UnderflowException("Pilha Vazia!");
		}
		Object dadoTopo = dados[ponteiroTopo];
		dados[ponteiroTopo] = null; // Limpa a referência do objeto
		ponteiroTopo--;
		return dadoTopo;
	}

	@Override
	public Object espiar() {
		if (estaVazia()) {
			throw new UnderflowException("Pilha Vazia!");
		}
		return dados[ponteiroTopo];
	}

	@Override
	public boolean estaCheia() {
		return (ponteiroTopo == dados.length - 1);
	}

	@Override
	public boolean estaVazia() {
		return (ponteiroTopo == -1);
	}

	@Override
	public String imprimir() {
		String resultado = "";
		for (int i = ponteiroTopo; i >= 0; i--) {
			resultado += dados[i];
			if (i != 0) {
				resultado += ", ";
			}
		}
		return "[" + resultado + "]";
	}

	/**
	 * Retorna a quantidade de elementos armazenados na pilha.
	 * @return Tamanho atual.
	 */
	public int tamanho() {
		return ponteiroTopo + 1;
	}

	/**
	 * Reseta a pilha para o estado inicial.
	 */
	public void limpar() {
		for (int i = 0; i <= ponteiroTopo; i++) {
			dados[i] = null;
		}
		ponteiroTopo = -1;
	}
}