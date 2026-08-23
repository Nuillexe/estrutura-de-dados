package repository.dinamicas.pilha;

import java.util.NoSuchElementException;

import repository.OverflowException;
import repository.UnderflowException;
import repository.dinamicas.NoDuplo;
import repository.Empilhavel;

/**
 * Implementação de uma Pilha Dinâmica (LIFO - Last In, First Out)
 * utilizando nós duplamente encadeados.
 */
public class PilhaDinamica implements Empilhavel {

    private int tamanhoMaximo;
    private int quantidade;
    private NoDuplo ponteiroTopo;

    /**
     * Construtor que define a capacidade máxima da pilha.
     * @param tamanhoMaximo Capacidade limite da pilha.
     */
    public PilhaDinamica(int tamanhoMaximo) {
        if (tamanhoMaximo <= 0) {
            throw new IllegalArgumentException("Tamanho máximo deve ser maior que 0");
        }
        this.tamanhoMaximo = tamanhoMaximo;
        this.quantidade = 0;
        this.ponteiroTopo = null;
    }

    /**
     * Construtor padrão com capacidade máxima padrão de 10 elementos.
     */
    public PilhaDinamica() {
        this(10);
    }

    /**
     * Verifica se a pilha está vazia.
     * @return true se a quantidade de elementos for 0, false caso contrário.
     */
    @Override
    public boolean estaVazia() {
        return quantidade == 0;
    }

    /**
     * Verifica se a pilha atingiu a sua capacidade máxima.
     * @return true se a quantidade atual for igual ao tamanho máximo.
     */
    @Override
    public boolean estaCheia() {
        return quantidade == tamanhoMaximo;
    }

    /**
     * Insere um novo elemento no topo da pilha (Push).
     * @param dado Objeto a ser empilhado.
     */
    @Override
    public void empilhar(Object dado) {
        if (estaCheia()) {
            throw new OverflowException("Pilha Cheia");
        }

        NoDuplo novoNo = new NoDuplo(dado);

        if (ponteiroTopo == null) {
            ponteiroTopo = novoNo;
        } else {
            novoNo.setAnterior(ponteiroTopo); // O novo nó aponta para o antigo topo
            ponteiroTopo.setProximo(novoNo);  // O antigo topo aponta para o novo nó
            ponteiroTopo = novoNo;            // Atualiza o ponteiro de topo
        }
        quantidade++;
    }

    /**
     * Remove e retorna o elemento localizado no topo da pilha (Pop).
     * @return O elemento removido do topo.
     */
    public Object desempilhar() {
        if (estaVazia()) {
            throw new UnderflowException("A pilha está vazia");
        }

        Object dadoTopo = ponteiroTopo.getDado();
        ponteiroTopo = ponteiroTopo.getAnterior(); // Desloca o topo para o nó anterior

        if (ponteiroTopo != null) {
            ponteiroTopo.setProximo(null); // Desconecta o ponteiro para o nó removido
        }

        quantidade--;
        return dadoTopo;
    }

    /**
     * Consulta o elemento do topo sem removê-lo (Peek).
     * @return O elemento do topo.
     */
    public Object espiar() {
        if (estaVazia()) {
            throw new UnderflowException("Pilha Vazia!");
        }
        return ponteiroTopo.getDado();
    }

    /**
     * Substitui o valor contido no elemento do topo.
     * @param novoDado Novo valor a ser armazenado.
     */
    @Override
    public void atualizar(Object novoDado) {
        if (estaVazia()) {
            throw new UnderflowException("Pilha Vazia!");
        }

        ponteiroTopo.setDado(novoDado);
    }

    /**
     * Imprime os elementos da pilha da ordem do Topo em direção à Base.
     * @return String formatada contendo os elementos.
     */
    public String imprimir() {
        NoDuplo ponteiroAuxiliar = ponteiroTopo;
        String resultado = "";

        for (int i = 0; i < quantidade; i++) {
            resultado += ponteiroAuxiliar.getDado();

            if (i != quantidade - 1) {
                resultado += ", ";
            }
            ponteiroAuxiliar = ponteiroAuxiliar.getAnterior(); // Corrigido: avança usando a variável auxiliar
        }

        return "[" + resultado + "]";
    }

    /**
     * Imprime os elementos da pilha da ordem da Base em direção ao Topo.
     * @return String formatada contendo os elementos.
     */
    public String imprimirBaseATopo() {
        String resultado = "";
        NoDuplo ponteiroAuxiliar = ponteiroTopo;

        for (int i = 0; i < quantidade; i++) {
            Object dado = ponteiroAuxiliar.getDado(); // Removido o cast rígido para String

            if (resultado.isEmpty()) {
                resultado = String.valueOf(dado);
            } else {
                resultado = dado + ", " + resultado;
            }

            ponteiroAuxiliar = ponteiroAuxiliar.getAnterior();
        }

        return "[" + resultado + "]";
    }

    public int tamanho() {
        return quantidade;
    }
}