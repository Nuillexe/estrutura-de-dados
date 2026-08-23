package repository.estaticas.fila;

import repository.DuplamenteEnfileiravel;
import repository.OverflowException;
import repository.UnderflowException;

/**
 * Implementação de uma Fila Estática com Dupla Terminação (Deque) baseada em Array.
 * Herda o comportamento da FilaEstatica e permite inserções/remoções nas duas extremidades.
 */
public class FilaEstaticaComDuplaTerminação extends FilaEstatica implements DuplamenteEnfileiravel {

    /**
     * Construtor padrão com capacidade para 10 elementos.
     */
    public FilaEstaticaComDuplaTerminação() {
        super(10);
    }

    /**
     * Construtor com tamanho personalizado.
     * @param tamanho Capacidade máxima do vetor.
     */
    public FilaEstaticaComDuplaTerminação(int tamanho) {
        super(tamanho);
    }

    /**
     * Insere um elemento no início do Deque.
     * Mover o início para trás exige calcular a rotação circular negativa.
     * @param dado Objeto a ser inserido na frente.
     */
    @Override
    public void enfileirarInicio(Object dado) {
        if (estaCheia()) {
            throw new OverflowException("Fila cheia");
        }

        // Recua o ponteiro inicio de forma circular
        inicio = recuar(inicio);
        dados[inicio] = dado;
        quantidade++;
    }

    /**
     * Remove e retorna o elemento localizado no fim do Deque.
     * @return Elemento removido do final.
     */
    @Override
    public Object desenfileirarFim() {
        if (estaVazia()) {
            throw new UnderflowException("Fila vazia");
        }

        Object elementoFim = dados[fim];
        // Recua o ponteiro fim de forma circular
        fim = recuar(fim);
        quantidade--;

        return elementoFim;
    }

    /**
     * Consulta o elemento do fim do Deque sem removê-lo.
     * @return Dado armazenado no ponteiro fim.
     */
    @Override
    public Object tras() {
        if (estaVazia()) {
            throw new UnderflowException("Fila vazia");
        }
        return dados[fim];
    }

    /**
     * Imprime os elementos na ordem inversa (do Fim para o Início).
     * @return String formatada contendo os elementos.
     */
    @Override
    public String imprimirTrasPraFrente() {
        String resultado = "";
        int aux = fim;

        for (int i = 0; i < quantidade; i++) {
            resultado += dados[aux];

            if (i != quantidade - 1) {
                resultado += ", ";
            }
            aux = recuar(aux); // Recua a navegação respeitando a circularidade
        }

        return "[" + resultado + "]";
    }

    /**
     * Método auxiliar privado para recuar ponteiros circularmente no array.
     * Evita problemas com restos de divisão negativos em Java.
     * @param ponteiro Índice atual.
     * @return Próximo índice válido ao recuar.
     */
    private int recuar(int ponteiro) {
        return (ponteiro - 1 + dados.length) % dados.length;
    }
}