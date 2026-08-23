package repository;

/**
 * Interface que define o contrato para estruturas de dados do tipo Fila (FIFO).
 */
public interface Enfileiravel {

    // Métodos Principais (CRUD)

    /**
     * Insere um elemento no final da fila (Create / Enqueue).
     * @param dado Objeto a ser enfileirado.
     */
    void enfileirar(Object dado);

    /**
     * Consulta o elemento localizado na frente da fila sem removê-lo (Read / Front).
     * @return O primeiro objeto da fila.
     */
    Object frente();

    /**
     * Atualiza o valor do elemento localizado no início da fila (Update).
     * @param novoDado Novo objeto a ser colocado na frente.
     */
    void atualizarInicio(Object novoDado);

    /**
     * Atualiza o valor do elemento localizado no final da fila (Update).
     * @param novoDado Novo objeto a ser colocado no fim.
     */
    void atualizarFim(Object novoDado);

    /**
     * Remove e retorna o primeiro elemento da fila (Delete / Dequeue).
     * @return O elemento removido da frente.
     */
    Object desenfileirar();

    // Métodos Auxiliares

    /**
     * Verifica se a fila não possui elementos.
     * @return true se estiver vazia, false caso contrário.
     */
    boolean estaVazia();

    /**
     * Verifica se a fila atingiu sua capacidade máxima.
     * @return true se estiver cheia, false caso contrário.
     */
    boolean estaCheia();

    /**
     * Retorna uma representação em String com todos os elementos da fila.
     * @return String formatada contendo os elementos.
     */
    String imprimir();

    /**
     * Reseta a estrutura e remove todos os elementos armazenados.
     */
    void limpar();
}

/* ============================================================================
   VERSÃO GENÉRICA COM PARAMETRIZAÇÃO DE TIPO <T>
   ============================================================================

public interface EnfileiravelGenerica<T> {

    void enfileirar(T dado);

    T frente();

    void atualizarInicio(T novoDado);

    void atualizarFim(T novoDado);

    T desenfileirar();

    boolean estaVazia();

    boolean estaCheia();

    String imprimir();

    void limpar();
}
*/