package repository;

/**
 * Interface que define o contrato para estruturas de dados do tipo Lista Indexada (Listable).
 * Permite inserções por posição, anexação ao fim, consultas e remoções por índice.
 */
public interface Listavel {

    // Métodos Principais (CRUD)

    /**
     * Insere um elemento em uma posição específica da lista, deslocando os demais (Insert).
     * @param objeto Objeto a ser inserido.
     * @param posicao Índice onde o elemento será inserido.
     */
    void inserir(Object objeto, int posicao);

    /**
     * Adiciona um elemento ao final da lista (Append).
     * @param objeto Objeto a ser anexado.
     */
    void anexar(Object objeto);

    /**
     * Retorna o elemento presente em uma determinada posição da lista (Select).
     * @param posicao Índice do elemento a ser retornado.
     * @return O objeto presente na posição indicada.
     */
    Object selecionar(int posicao);

    /**
     * Retorna um vetor contendo todos os elementos armazenados na lista (SelectAll).
     * @return Array com todos os objetos da estrutura.
     */
    Object[] selecionarTodos();

    /**
     * Atualiza o valor do elemento armazenado em uma determinada posição (Update).
     * @param objeto Novo valor a ser armazenado.
     * @param posicao Índice do elemento que será substituído.
     */
    void atualizar(Object objeto, int posicao);

    /**
     * Remove e retorna o elemento armazenado na posição especificada (Delete).
     * @param posicao Índice do elemento a ser removido.
     * @return O objeto que foi removido.
     */
    Object apagar(int posicao);

    /**
     * Esvazia completamente a lista (Clear).
     */
    void limpar();

    // Métodos Auxiliares

    /**
     * Retorna a quantidade atual de elementos armazenados.
     * @return Tamanho da lista.
     */
    int tamanho();

    /**
     * Verifica se a lista não possui elementos.
     * @return true se estiver vazia, false caso contrário.
     */
    boolean estaVazia();

    /**
     * Verifica se a lista atingiu a sua capacidade máxima.
     * @return true se estiver cheia, false caso contrário.
     */
    boolean estaCheia();

    /**
     * Retorna uma representação em String com todos os elementos da lista.
     * @return String formatada com os elementos da estrutura.
     */
    String imprimir();
}

/* ============================================================================
   VERSÃO GENÉRICA COM PARAMETRIZAÇÃO DE TIPO <T>
   ============================================================================

public interface ListavelGenerica<T> {

    void inserir(T objeto, int posicao);

    void anexar(T objeto);

    T selecionar(int posicao);

    T[] selecionarTodos();

    void atualizar(T objeto, int posicao);

    T apagar(int posicao);

    void limpar();

    int tamanho();

    boolean estaVazia();

    boolean estaCheia();

    String imprimir();
}
*/