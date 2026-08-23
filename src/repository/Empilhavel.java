package repository;

/**
 * Interface que define o contrato para estruturas de dados do tipo Pilha (LIFO).
 */
public interface Empilhavel {

	// Métodos Principais (CRUD)

	/**
	 * Adiciona um novo elemento no topo da pilha (Create).
	 * @param dado Objeto a ser empilhado.
	 */
	void empilhar(Object dado);

	/**
	 * Consulta o elemento do topo da pilha sem removê-lo (Read).
	 * @return O objeto localizado no topo.
	 */
	Object espiar();

	/**
	 * Atualiza o valor do elemento localizado no topo da pilha (Update).
	 * @param dado Novo objeto a ser colocado no topo.
	 */
	void atualizar(Object dado);

	/**
	 * Remove e retorna o elemento localizado no topo da pilha (Delete).
	 * @return O objeto removido do topo.
	 */
	Object desempilhar();

	// Métodos Auxiliares

	/**
	 * Verifica se a pilha atingiu a capacidade máxima.
	 * @return true se estiver cheia, false caso contrário.
	 */
	boolean estaCheia();

	/**
	 * Verifica se a pilha não contém elementos.
	 * @return true se estiver vazia, false caso contrário.
	 */
	boolean estaVazia();

	/**
	 * Retorna uma representação em String com todos os elementos da pilha.
	 * @return String formatada com os elementos.
	 */
	String imprimir();
}

/* ============================================================================
   VERSÃO GENÉRICA COM PARAMETRIZAÇÃO DE TIPO <T>
   ============================================================================

public interface EmpilhavelGenerica<T> {

    void empilhar(T dado);

    T espiar();

    void atualizar(T dado);

    T desempilhar();

    boolean estaCheia();

    boolean estaVazia();

    String imprimir();
}
*/