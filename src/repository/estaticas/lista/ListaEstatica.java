package repository.estaticas.lista;

import repository.Listavel;
import repository.OverflowException;
import repository.UnderflowException;

/**
 * Implementação de uma Lista Estática Circular baseada em Array.
 * Otimiza inserções e remoções deslocando elementos pelo lado mais próximo (início ou fim).
 */
public class ListaEstatica implements Listavel {

    protected int inicio, fim;
    protected Object[] dados;
    protected int quantidade;

    /**
     * Construtor padrão com capacidade para 10 elementos.
     */
    public ListaEstatica() {
        this(10);
    }

    /**
     * Construtor com capacidade personalizada.
     * @param tamanho Capacidade máxima do vetor.
     */
    public ListaEstatica(int tamanho) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho deve ser maior que zero.");
        }
        quantidade = 0;
        inicio = 0;
        fim = -1;
        dados = new Object[tamanho];
    }

    @Override
    public boolean estaCheia() {
        return quantidade == dados.length;
    }

    @Override
    public boolean estaVazia() {
        return quantidade == 0;
    }

    @Override
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

    @Override
    public void anexar(Object dado) {
        if (estaCheia()) {
            throw new OverflowException("Lista cheia");
        }
        fim = avancar(fim);
        dados[fim] = dado;
        quantidade++;
    }

    @Override
    public void atualizar(Object novoDado, int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista vazia");
        }
        if (posicao < 0 || posicao >= quantidade) {
            throw new IndexOutOfBoundsException("Índice inválido: " + posicao);
        }

        int pontoManipulacao = mapearPosicaoFisica(posicao);
        dados[pontoManipulacao] = novoDado;
    }

    @Override
    public Object selecionar(int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista vazia");
        }
        if (posicao < 0 || posicao >= quantidade) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        int posicaoFisica = mapearPosicaoFisica(posicao);
        return dados[posicaoFisica];
    }

    public Object[] selecionarTodos() {
        if (estaVazia()) {
            return new Object[0];
        }

        Object[] arrayRetorno = new Object[quantidade];
        int ponteiroTemporario = inicio;

        for (int i = 0; i < quantidade; i++) {
            arrayRetorno[i] = dados[ponteiroTemporario];
            ponteiroTemporario = avancar(ponteiroTemporario);
        }

        return arrayRetorno;
    }

    @Override
    public void inserir(Object objeto, int posicao) {
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

    public Object apagar(int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista vazia");
        }
        if (posicao < 0 || posicao >= quantidade) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        int posicaoFisica = mapearPosicaoFisica(posicao);
        Object dadoAux = dados[posicaoFisica];
        int x = posicaoFisica;

        if (posicao > quantidade / 2) { // Mais próximo do fim
            int y = avancar(x);
            for (int i = 0; i < quantidade - posicao - 1; i++) {
                dados[x] = dados[y];
                x = avancar(x);
                y = avancar(y);
            }
            fim = retroceder(fim);
        } else { // Mais próximo do início
            int y = retroceder(x);
            for (int i = 0; i < posicao; i++) {
                dados[x] = dados[y];
                x = retroceder(x);
                y = retroceder(y);
            }
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
        inicio = 0;
        fim = -1;
        quantidade = 0;
    }
}