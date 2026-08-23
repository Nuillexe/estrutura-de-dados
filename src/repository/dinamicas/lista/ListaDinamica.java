package repository.dinamicas.lista;

import repository.Listavel;
import repository.OverflowException;
import repository.UnderflowException;
import repository.dinamicas.NoDuplo;
import java.util.NoSuchElementException;

/**
 * Implementação de uma Lista Encadeada Dupla com tamanho limite configurável.
 * Mantém referências tanto para o início quanto para o fim da estrutura.
 */
public class ListaDinamica implements Listavel {

    private NoDuplo ponteiroInicio;
    private NoDuplo ponteiroFim;
    private int quantidade;
    private int tamanhoMaximo;

    /**
     * Construtor padrão que define a capacidade máxima para 100 elementos.
     */
    public ListaDinamica() {
        this(100);
    }

    /**
     * Construtor que permite definir uma capacidade máxima personalizada.
     * @param tamanhoMaximo Capacidade limite da lista.
     */
    public ListaDinamica(int tamanhoMaximo) {
        this.tamanhoMaximo = tamanhoMaximo;
        this.ponteiroInicio = null;
        this.ponteiroFim = null;
        this.quantidade = 0;
    }

    /**
     * Verifica se a lista não possui nenhum elemento.
     * @return true se a quantidade for 0, false caso contrário.
     */
    public boolean estaVazia() {
        return quantidade == 0;
    }

    /**
     * Verifica se a lista atingiu sua capacidade máxima.
     * @return true se quantidade for igual ao tamanho máximo.
     */
    public boolean estaCheia() {
        return quantidade == tamanhoMaximo;
    }

    /**
     * Retorna uma representação em String de todos os elementos da lista,
     * separados por vírgula.
     */
    public String imprimir() {
        String resultado = "";
        NoDuplo ponteiroAuxiliar = ponteiroInicio;

        for (int i = 0; i < quantidade; i++) {
            resultado += ponteiroAuxiliar.getDado();
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();

            if (i != quantidade - 1) {
                resultado += ", ";
            }
        }

        return resultado;
    }

    /**
     * Imprime os elementos desde o início até a posição especificada (inclusive).
     * @param posicao Índice limite de parada (0 até quantidade-1).
     */
    public String imprimirAte(int posicao) {
        if (posicao < 0 || posicao >= quantidade) {
            throw new NoSuchElementException("Posição inválida");
        }

        String resultado = "";
        NoDuplo ponteiroAuxiliar = ponteiroInicio;

        for (int i = 0; i <= posicao; i++) {
            resultado += ponteiroAuxiliar.getDado();
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();

            if (i != posicao) {
                resultado += ", ";
            }
        }

        return resultado;
    }

    /**
     * Imprime os elementos da posição especificada até o final da lista.
     * @param posicao Índice de início da impressão (0 até quantidade-1).
     */
    public String imprimirAPartir(int posicao) {
        if (posicao < 0 || posicao >= quantidade) {
            throw new NoSuchElementException("Posição inválida");
        }

        String resultado = "";
        NoDuplo ponteiroAuxiliar = ponteiroInicio;

        // Avança até a posição inicial desejada
        for (int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }

        // Percorre do ponto de partida até o fim da lista
        for (int i = posicao; i < quantidade; i++) {
            resultado += ponteiroAuxiliar.getDado();
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();

            if (i != quantidade - 1) {
                resultado += ", ";
            }
        }

        return resultado;
    }

    /**
     * Adiciona um elemento ao final da lista (Operação Append).
     * @param novoDado Objeto a ser inserido.
     */
    public void anexar(Object novoDado) {
        if (estaCheia()) {
            throw new OverflowException("Lista Cheia");
        }

        NoDuplo novoNo = new NoDuplo(novoDado);
        novoNo.setAnterior(ponteiroFim);

        if (!estaVazia()) {
            ponteiroFim.setProximo(novoNo);
        } else {
            ponteiroInicio = novoNo;
        }

        ponteiroFim = novoNo;
        quantidade++;
    }

    /**
     * Copia todos os dados da lista para um Array de Objetos simples.
     * @return Array contendo os elementos na ordem em que estão na lista.
     */
    public Object[] selecionarTodos() {
        if (estaVazia()) {
            throw new UnderflowException("Lista Vazia");
        }

        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        Object[] arrayRetorno = new Object[quantidade];

        for (int i = 0; i < quantidade; i++) {
            arrayRetorno[i] = ponteiroAuxiliar.getDado();
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo(); // Corrigido: avança a referência do nó
        }

        return arrayRetorno;
    }

    /**
     * Busca o dado de uma posição específica. Otimiza a busca decidindo
     * se é mais rápido começar pelo início ou pelo fim.
     * @param posicao Índice do elemento desejado.
     * @return Dado armazenado no nó da posição.
     */
    public Object selecionar(int posicao) {
        if (posicao < 0 || posicao >= quantidade) {
            throw new NoSuchElementException("Posição inválida");
        }

        // Otimização: se a posição estiver na primeira metade, busca pelo início; senão, pelo fim.
        if (posicao <= quantidade / 2) {
            return selecionarPeloInicio(posicao);
        } else {
            return selecionarPeloFinal(posicao);
        }
    }

    /**
     * Método auxiliar para percorrer a lista a partir da cabeça (Início).
     */
    public Object selecionarPeloInicio(int pos) {
        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < pos; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }
        return ponteiroAuxiliar.getDado();
    }

    /**
     * Método auxiliar para percorrer a lista a partir da cauda (Fim).
     */
    public Object selecionarPeloFinal(int pos) {
        NoDuplo ponteiroAuxiliar = ponteiroFim;

        // Corrigido: começa do último elemento (quantidade - 1) e retrocede até pos
        for (int i = quantidade - 1; i > pos; i--) {
            ponteiroAuxiliar = ponteiroAuxiliar.getAnterior();
        }
        return ponteiroAuxiliar.getDado();
    }

    /**
     * Substitui o valor contido em uma determinada posição da lista.
     * @param objeto Novo dado a ser gravado.
     * @param posicao Índice a ser atualizado.
     */
    public void atualizar(Object objeto, int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("Lista Vazia");
        }

        if (posicao < 0 || posicao >= quantidade) {
            throw new NoSuchElementException("Posição Inválida");
        }

        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }

        ponteiroAuxiliar.setDado(objeto);
    }

    /**
     * Remove o nó de uma posição específica e reajusta as conexões dos nós vizinhos.
     * @param posicao Índice do nó que será removido.
     * @return O dado do nó removido.
     */
    public Object apagar(int posicao) {
        if (estaVazia()) {
            throw new UnderflowException("A lista está vazia");
        }

        if (posicao < 0 || posicao >= quantidade) {
            throw new NoSuchElementException("Posição Inválida");
        }

        NoDuplo ponteiroAuxiliar = ponteiroInicio;

        for (int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }

        Object retorno = ponteiroAuxiliar.getDado();

        NoDuplo ant = ponteiroAuxiliar.getAnterior();
        NoDuplo prox = ponteiroAuxiliar.getProximo();

        // Ajusta ponteiro do nó anterior
        if (ant != null) {
            ant.setProximo(prox);
        } else {
            ponteiroInicio = ponteiroInicio.getProximo(); // Era o primeiro nó
        }

        // Ajusta ponteiro do nó seguinte
        if (prox != null) {
            prox.setAnterior(ant);
        } else {
            ponteiroFim = ponteiroFim.getAnterior(); // Era o último nó
        }

        quantidade--;

        return retorno;
    }

    /**
     * Insere um elemento em qualquer posição válida da lista (0 até quantidade).
     * @param novoDado Elemento a ser adicionado.
     * @param posicao Posição onde o novo dado ficará alocado.
     */
    public void inserir(Object novoDado, int posicao) {
        if (estaCheia()) {
            throw new OverflowException("Lista Cheia");
        }

        if (posicao < 0 || posicao > quantidade) {
            throw new NoSuchElementException("Posição Inválida");
        }

        NoDuplo ponteiroAuxiliar = ponteiroInicio;
        for (int i = 0; i < posicao; i++) {
            ponteiroAuxiliar = ponteiroAuxiliar.getProximo();
        }

        // Se ponteiroAuxiliar for nulo, significa inserção no final da lista
        NoDuplo ant = (ponteiroAuxiliar != null) ? ponteiroAuxiliar.getAnterior() : ponteiroFim;
        NoDuplo prox = ponteiroAuxiliar;

        NoDuplo novoNo = new NoDuplo(novoDado);
        novoNo.setProximo(prox);
        novoNo.setAnterior(ant);

        // Atualiza a ligação do nó sucessor
        if (prox != null) {
            prox.setAnterior(novoNo);
        } else {
            ponteiroFim = novoNo;
        }

        // Atualiza a ligação do nó antecessor
        if (ant != null) {
            ant.setProximo(novoNo);
        } else {
            ponteiroInicio = novoNo;
        }

        quantidade++;
    }

    /**
     * Reseta a lista removendo todas as referências dos ponteiros principais.
     */
    public void limpar() {
        ponteiroInicio = null;
        ponteiroFim = null;
        quantidade = 0;
    }

    /**
     * Retorna a quantidade de elementos armazenados na lista.
     * @return Tamanho atual da lista.
     */
    public int tamanho() {
        return quantidade;
    }
}