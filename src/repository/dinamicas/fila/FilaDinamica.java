package repository.dinamicas.fila;

import repository.Enfileiravel;
import repository.OverflowException;
import repository.UnderflowException;
import repository.dinamicas.NoDuplo;

import java.util.NoSuchElementException;

/**
 * Implementação de uma Fila Dinâmica / Deque (Fila de Dupla Extremidade)
 * utilizando nós duplamente encadeados.
 * Permite inserções e remoções tanto no início quanto no fim (FIFO/LIFO).
 */
public class FilaDinamica implements Enfileiravel {

    protected NoDuplo ponteiroInicio;
    protected NoDuplo ponteiroFim;
    protected int quantidade;
    protected int tamanhoMaximo;

    /**
     * Construtor com capacidade personalizada.
     * @param tamanhoMaximo Capacidade máxima de elementos da fila.
     */
    public FilaDinamica(int tamanhoMaximo) {
        if (tamanhoMaximo <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que 0");
        }
        this.tamanhoMaximo = tamanhoMaximo;
        this.ponteiroInicio = null;
        this.ponteiroFim = null;
        this.quantidade = 0;
    }

    /**
     * Construtor padrão com limite inicial de 100 elementos.
     */
    public FilaDinamica() {
        this(100);
    }

    /**
     * Construtor completo para inicializações avançadas.
     */
    public FilaDinamica(NoDuplo ponteiroInicio, NoDuplo ponteiroFim, int quantidade, int tamanhoMaximo) {
        if (tamanhoMaximo <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que 0");
        }
        this.ponteiroInicio = ponteiroInicio;
        this.ponteiroFim = ponteiroFim;
        this.quantidade = quantidade;
        this.tamanhoMaximo = tamanhoMaximo;
    }

    /**
     * Verifica se a fila está vazia.
     * @return true se não houver elementos, false caso contrário.
     */
    @Override
    public boolean estaVazia() {
        return quantidade == 0;
    }

    /**
     * Verifica se a fila atingiu sua capacidade máxima.
     * @return true se o número de elementos for igual ao tamanho máximo.
     */
    @Override
    public boolean estaCheia() {
        return quantidade == tamanhoMaximo;
    }

    /**
     * Retorna o elemento localizado no início da fila sem removê-lo.
     * @return Dado do primeiro elemento.
     */
    public Object frente() {
        if (estaVazia()) {
            throw new UnderflowException("A fila está vazia");
        }
        return ponteiroInicio.getDado();
    }

    /**
     * Substitui o valor do elemento no início da fila.
     * @param novoDado Novo valor a ser inserido no primeiro nó.
     */
    public void atualizarInicio(Object novoDado) {
        if (estaVazia()) {
            throw new UnderflowException("Fila Vazia");
        }
        ponteiroInicio.setDado(novoDado);
    }

    /**
     * Substitui o valor do elemento no fim da fila.
     * @param novoDado Novo valor a ser inserido no último nó.
     */
    @Override
    public void atualizarFim(Object novoDado) {
        if (estaVazia()) {
            throw new UnderflowException("Fila Vazia");
        }
        ponteiroFim.setDado(novoDado);
    }

    /**
     * Insere um elemento no final da fila (comportamento padrão FIFO).
     * @param dado Objeto a ser inserido.
     */
    @Override
    public void enfileirar(Object dado) {
        if (estaCheia()) {
            throw new OverflowException("A fila está cheia");
        }

        NoDuplo novoNo = new NoDuplo(dado);

        if (!estaVazia()) {
            ponteiroFim.setProximo(novoNo);
        } else {
            ponteiroInicio = novoNo;
        }

        novoNo.setAnterior(ponteiroFim);
        ponteiroFim = novoNo;
        quantidade++;
    }

    /**
     * Remove e retorna o elemento do início da fila (comportamento padrão FIFO).
     * @return O elemento removido do início.
     */
    @Override
    public Object desenfileirar() {
        if (estaVazia()) {
            throw new NoSuchElementException("Fila vazia");
        }
        Object dadoInicio = ponteiroInicio.getDado();
        ponteiroInicio = ponteiroInicio.getProximo();
        if (ponteiroInicio == null) {
            ponteiroFim = null;
        }
        quantidade--;
        return dadoInicio;
    }


    /**
     * Reseta a fila removendo todas as referências dos nós e zerando a quantidade.
     */
    @Override
    public void limpar() {
        quantidade = 0;
        ponteiroInicio = null;
        ponteiroFim = null;
    }

    /**
     * Retorna uma representação em String de todos os elementos da fila.
     * @return String formatada no padrão [elem1, elem2].
     */
    public String imprimir() {
        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        String resultado = "";

        for (int i = 0; i < quantidade; i++) {
            resultado += ponteiroAuxiliar.getDado();

            if (i != quantidade - 1) {
                resultado += ", ";
            }
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }

        return "[" + resultado + "]";
    }

}