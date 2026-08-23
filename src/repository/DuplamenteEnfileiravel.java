package repository;

/**
 * Interface que define o contrato para filas de dupla terminação (Deque).
 * Permite inserções, remoções e consultas em ambas as extremidades da estrutura.
 */
public interface DuplamenteEnfileiravel {

    /**
     * Insere um elemento no início da fila.
     * @param elemento Objeto a ser inserido na frente.
     */
    void enfileirarInicio(Object elemento);

    // O método desenfileirarInicio() equivale ao desenfileirar() da interface Enfileiravel.

    /**
     * Remove e retorna o elemento localizado no fim da fila.
     * @return Elemento removido do final.
     */
    Object desenfileirarFim();

    /**
     * Retorna o elemento localizado no fim da fila sem removê-lo.
     * @return O elemento do final.
     */
    Object tras();

    /**
     * Retorna uma representação em String com os elementos percorridos do fim para o início.
     * @return String formatada contendo os elementos em ordem inversa.
     */
    String imprimirTrasPraFrente();
}

/* ============================================================================
   VERSÃO GENERICA COM PARAMETRIZAÇÃO DE TIPO <T>
   ============================================================================

public interface DuplamenteEnfileiravelGenerica<T> {

    void enfileirarInicio(T elemento);

    T desenfileirarFim();

    T tras();

    String imprimirTrasPraFrente();
}
*/