package repository.dinamicas;

/**
 * Representa um Nó em uma estrutura de dados duplamente encadeada.
 * Armazena o dado e as referências para o nó anterior e o próximo nó.
 *
 * @param <T> Tipo genérico do elemento armazenado.
 */
public class NoDuplo<T> {

    private NoDuplo<T> anterior;
    private T dado;
    private NoDuplo<T> proximo;

    /**
     * Construtor que inicializa o nó com um dado.
     * @param dado Objeto a ser armazenado no nó.
     */
    public NoDuplo(T dado) {
        this.dado = dado;
        this.anterior = null;
        this.proximo = null;
    }

    /**
     * Retorna a referência para o nó anterior.
     * @return O nó anterior.
     */
    public NoDuplo<T> getAnterior() {
        return anterior;
    }

    /**
     * Define a referência para o nó anterior.
     * @param anterior Nó que antecede o nó atual.
     */
    public void setAnterior(NoDuplo<T> anterior) {
        this.anterior = anterior;
    }

    /**
     * Retorna o dado armazenado no nó.
     * @return O elemento do tipo T.
     */
    public T getDado() {
        return dado;
    }

    /**
     * Atualiza o valor do dado armazenado no nó.
     * @param dado Novo objeto a ser armazenado.
     */
    public void setDado(T dado) {
        this.dado = dado;
    }

    /**
     * Retorna a referência para o próximo nó.
     * @return O próximo nó.
     */
    public NoDuplo<T> getProximo() {
        return proximo;
    }

    /**
     * Define a referência para o próximo nó.
     * @param proximo Nó que sucede o nó atual.
     */
    public void setProximo(NoDuplo<T> proximo) {
        this.proximo = proximo;
    }
}

/* ============================================================================
   VERSÃO ALTERNATIVA (SEM GENERICS / UTILIZANDO APENAS OBJECT)

   Esta versão é comum em disciplinas introdutórias de Estrutura de Dados.
   Em vez de parâmetros de tipo flexíveis (<T>), ela utiliza a classe base
   java.lang.Object para armazenar qualquer tipo de dado.
   ============================================================================

public class NoDuplo {

    private NoDuplo anterior;
    private Object dado;
    private NoDuplo proximo;

    public NoDuplo(Object dado) {
        this.dado = dado;
        this.anterior = null;
        this.proximo = null;
    }

    public NoDuplo getAnterior() {
        return anterior;
    }

    public void setAnterior(NoDuplo anterior) {
        this.anterior = anterior;
    }

    public Object getDado() {
        return dado;
    }

    public void setDado(Object dado) {
        this.dado = dado;
    }

    public NoDuplo getProximo() {
        return proximo;
    }

    public void setProximo(NoDuplo proximo) {
        this.proximo = proximo;
    }
}
*/