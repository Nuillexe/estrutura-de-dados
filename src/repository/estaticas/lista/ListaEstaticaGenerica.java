package repository.estaticas.lista;

import repository.OverflowException;
import repository.UnderflowException;

/**
 * Implementação de uma Lista Estática Circular Genérica baseada em Array.
 *
 * @param <T> Tipo genérico dos elementos mantidos na lista.
 */
@SuppressWarnings("unchecked")
public class ListaEstaticaGenerica<T> {

    protected int inicio, fim;
    protected T[] dados;
    protected int quantidade;

    /**
     * Construtor padrão com capacidade inicial de 10 elementos.
     */
    public ListaEstaticaGenerica() {
        this(10);
    }

    /**
     * Construtor com capacidade personalizada.
     * @param tamanho Capacidade máxima do vetor.
     */
    public ListaEstaticaGenerica(int tamanho) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho deve ser maior que zero.");
        }
        quantidade = 0;
        inicio = 0;
        fim = -1;
        // Criação do array genérico via casting
        dados = (T[]) new Object[tamanho];
    }

    public boolean estaCheia() {
        return quantidade == dados.length;
    }

    public boolean estaVazia() {
        return quantidade == 0;
    }

    public String imprimir() {
        String resultado = "";
        int aux = inicio;

        for (int i = 0; i < quantidade; i++) {
            resultado += dados[aux];
            if (i != quantidade - 1) {
                resultado += ", ";
            }
            aux = avancar(aux);
        }

        return "[" + resultado + "]";
    }

    public void anexar(T dado) {
        if (estaCheia()) {
            throw new OverflowException("Lista cheia");
        }
        fim = avancar(fim);
        dados[fim] = dado;
        quantidade++;
    }

    public void atualizar(T novoDado, int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista vazia");
        }
        if (posicao < 0 || posicao >= quantidade) {
            throw new IndexOutOfBoundsException("Índice inválido: " + posicao);
        }

        int pontoManipulacao = mapearPosicaoFisica(posicao);
        dados[pontoManipulacao] = novoDado;
    }

    public T selecionar(int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista vazia");
        }
        if (posicao < 0 || posicao >= quantidade) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        int posicaoFisica = mapearPosicaoFisica(posicao);
        return dados[posicaoFisica];
    }

    public void inserir(T objeto, int posicao) {
        if (estaCheia()) {
            throw new OverflowException("Lista cheia");
        }
        if (posicao < 0 || posicao > quantidade) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        if (estaVazia()) {
            inicio = 0;
            fim = 0;
            dados[0] = objeto;
        } else {
            if (posicao <= quantidade / 2) {
                estrategiaInsercaoInicio(posicao);
            } else {
                estrategiaInsercaoFim(posicao);
            }
            dados[mapearPosicaoFisica(posicao)] = objeto;
        }

        quantidade++;
    }

    private void estrategiaInsercaoInicio(int posicao) {
        for (int i = 0; i < posicao; i++) {
            int depois = mapearPosicaoFisica(i);
            int antes = retroceder(depois);
            dados[antes] = dados[depois];
        }
        inicio = retroceder(inicio);
    }

    private void estrategiaInsercaoFim(int posicao) {
        for (int i = quantidade - 1; i >= posicao; i--) {
            int depois = mapearPosicaoFisica(i + 1);
            int antes = retroceder(depois);
            dados[depois] = dados[antes];
        }
        fim = avancar(fim);
    }

    public T apagar(int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista vazia");
        }
        if (posicao < 0 || posicao >= quantidade) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        int posicaoFisica = mapearPosicaoFisica(posicao);
        T dadoAux = dados[posicaoFisica];
        int x = posicaoFisica;

        if (posicao > quantidade / 2) { // Mais próximo do fim
            int y = avancar(x);
            for (int i = 0; i < quantidade - posicao - 1; i++) {
                dados[x] = dados[y];
                x = avancar(x);
                y = avancar(y);
            }
            dados[fim] = null; // Limpa a referência para o GC
            fim = retroceder(fim);
        } else { // Mais próximo do início
            int y = retroceder(x);
            for (int i = 0; i < posicao; i++) {
                dados[x] = dados[y];
                x = retroceder(x);
                y = retroceder(y);
            }
            dados[inicio] = null; // Limpa a referência para o GC
            inicio = avancar(inicio);
        }

        quantidade--;
        return dadoAux;
    }

    private int mapearPosicaoFisica(int posicao) {
        return (inicio + posicao) % dados.length;
    }

    private int avancar(int ponteiro) {
        return (ponteiro + 1) % dados.length;
    }

    private int retroceder(int ponteiro) {
        return (ponteiro - 1 + dados.length) % dados.length;
    }

    public int tamanho() {
        return quantidade;
    }

    public void limpar() {
        for (int i = 0; i < dados.length; i++) {
            dados[i] = null;
        }
        inicio = 0;
        fim = -1;
        quantidade = 0;
    }
}