package repository.estaticas.fila;

import repository.Enfileiravel;
import repository.OverflowException;
import repository.UnderflowException;

/**
 * Implementação de uma Fila Estática Circular baseada em Array.
 * Utiliza o operador de módulo (%) para reaproveitar os espaços liberados no vetor.
 */
public class FilaEstatica implements Enfileiravel {

    protected int inicio;
    protected int fim;
    protected Object[] dados;
    protected int quantidade;

    /**
     * Construtor padrão com capacidade para 10 elementos.
     */
    public FilaEstatica() {
        this(10);
    }

    /**
     * Construtor com tamanho personalizado.
     * @param tamanho Capacidade máxima do vetor.
     */
    public FilaEstatica(int tamanho) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho deve ser maior que 0");
        }
        inicio = 0;
        fim = -1;
        quantidade = 0;
        dados = new Object[tamanho];
    }

    /**
     * Insere um novo elemento no final da fila circular.
     * @param dado Objeto a ser enfileirado.
     */
    @Override
    public void enfileirar(Object dado) {
        if (estaCheia()) {
            throw new OverflowException("Fila cheia");
        }
        fim = avancar(fim);
        dados[fim] = dado;
        quantidade++;
    }

    /**
     * Remove e retorna o primeiro elemento da fila.
     * @return Elemento removido do início.
     */
    @Override
    public Object desenfileirar() {
        if (estaVazia()) {
            throw new UnderflowException("Fila vazia");
        }
        Object elementoInicio = dados[inicio];
        inicio = avancar(inicio); // Avança o ponteiro após salvar o valor
        quantidade--;
        return elementoInicio;
    }

    /**
     * Retorna o elemento da frente da fila sem removê-lo.
     * @return Elemento do início.
     */
    public Object frente() {
        if (estaVazia()) {
            throw new UnderflowException("Fila vazia");
        }
        return dados[inicio];
    }

    /**
     * Verifica se a fila atingiu a capacidade máxima.
     * @return true se estiver cheia, false caso contrário.
     */
    @Override
    public boolean estaCheia() {
        return quantidade == dados.length;
    }

    /**
     * Verifica se a fila não possui elementos.
     * @return true se estiver vazia, false caso contrário.
     */
    @Override
    public boolean estaVazia() {
        return quantidade == 0;
    }

    /**
     * Substitui o elemento no início da fila.
     * @param novoDado Novo objeto a ser colocado na frente.
     */
    public void atualizarInicio(Object novoDado) {
        if (estaVazia()) {
            throw new UnderflowException("Fila vazia");
        }
        dados[inicio] = novoDado;
    }

    /**
     * Substitui o elemento no final da fila.
     * @param novoDado Novo objeto a ser colocado no fim.
     */
    public void atualizarFim(Object novoDado) {
        if (estaVazia()) {
            throw new UnderflowException("Fila vazia");
        }
        dados[fim] = novoDado;
    }

    /**
     * Retorna uma String com todos os elementos formatados na ordem correta da fila.
     * Trata o comportamento circular percorrendo os elementos via ponteiro auxiliar.
     * @return String formatada no padrão [elem1, elem2].
     */
    public String imprimir() {
        String resultado = "";
        int aux = inicio;

        for (int i = 0; i < quantidade; i++) {
            resultado += dados[aux];
            if (i != quantidade - 1) {
                resultado += ", ";
            }
            aux = avancar(aux); // Navega no vetor respeitando a circularidade
        }

        return "[" + resultado + "]";
    }

    /**
     * Calcula a próxima posição do índice usando o resto da divisão (Módulo).
     * @param ponteiro Índice atual.
     * @return Próximo índice dentro do limite do vetor.
     */
    private int avancar(int ponteiro) {
        return (ponteiro + 1) % dados.length;
    }

    /**
     * Reseta os ponteiros e a quantidade de elementos da fila.
     */
    public void limpar() {
        inicio = 0;
        fim = -1;
        quantidade = 0;
    }

    /**
     * Retorna a quantidade de elementos armazenados.
     * @return Tamanho atual.
     */
    public int tamanho() {
        return quantidade;
    }
}