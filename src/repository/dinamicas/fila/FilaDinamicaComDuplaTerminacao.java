package repository.dinamicas.fila;

import repository.DuplamenteEnfileiravel;
import repository.OverflowException;
import repository.UnderflowException;
import repository.dinamicas.NoDuplo;

/**
 * Extension da FilaDinamica para suporte completo a uma Fila de Dupla Terminacao (Deque).
 * Herda a estrutura basica e sobrescreve/implementa operacoes de manipulação no fim e no inicio.
 */
public class FilaDinamicaComDuplaTerminacao extends FilaDinamica implements DuplamenteEnfileiravel {

    /**
     * Construtor que define a capacidade maxima do Deque.
     * @param tamanhoMaximo Capacidade limite de elementos.
     */
    public FilaDinamicaComDuplaTerminacao(int tamanhoMaximo) {
        if (tamanhoMaximo <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que zero.");
        }
        this.ponteiroInicio = null;
        this.ponteiroFim = null;
        this.tamanhoMaximo = tamanhoMaximo;
        this.quantidade = 0;
    }

    /**
     * Construtor padrao com capacidade inicial de 10 elementos.
     */
    public FilaDinamicaComDuplaTerminacao() {
        this(10);
    }

    /**
     * Insere um elemento na frente (inicio) do Deque.
     * @param objeto Elemento a ser adicionado.
     */
    @Override
    public void enfileirarInicio(Object objeto) {
        if (estaCheia()) {
            throw new OverflowException("Fila cheia");
        }

        NoDuplo novoNo = new NoDuplo(objeto);

        if (estaVazia()) {
            ponteiroInicio = ponteiroFim = novoNo;
        } else {
            novoNo.setProximo(ponteiroInicio);
            ponteiroInicio.setAnterior(novoNo);
            ponteiroInicio = novoNo;
        }
        quantidade++;
    }

    /**
     * Consulta o elemento localizado no fim do Deque sem remove-lo.
     * @return O ultimo elemento da estrutura.
     */
    @Override
    public Object tras() {
        if (estaVazia()) {
            throw new UnderflowException("Fila vazia");
        }
        return ponteiroFim.getDado();
    }

    /**
     * Remove e retorna o elemento localizado no fim do Deque.
     * @return O elemento removido do final.
     */
    @Override
    public Object desenfileirarFim() {
        if (estaVazia()) {
            throw new UnderflowException("Fila vazia");
        }

        Object dadoFim = ponteiroFim.getDado();
        ponteiroFim = ponteiroFim.getAnterior();

        if (ponteiroFim != null) {
            ponteiroFim.setProximo(null);
        } else {
            ponteiroInicio = null;
        }

        quantidade--;
        return dadoFim;
    }

    /**
     * Retorna uma String com os elementos na ordem inversa (do Fim para o Inicio).
     * @return String formatada no padrao [ elem1, elem2 ].
     */
    @Override
    public String imprimirTrasPraFrente() {
        String resultado = "";
        NoDuplo ponteiroAuxiliar = ponteiroFim;

        for (int i = 0; i < quantidade; i++) {
            resultado += ponteiroAuxiliar.getDado();

            if (i != quantidade - 1) {
                resultado += ", ";
            }
            ponteiroAuxiliar = ponteiroAuxiliar.getAnterior();
        }

        return "[ " + resultado + " ]";
    }
}