package Provas_unidade_1.Espelho_da_prova2026;

public class FilaDeCarro {
    private Enfileiravel filaCarros = new FilaEstatica(20);

    // Questão 1
    public Carro apagaCarro(String placa) {
        Enfileiravel filaAux = new FilaEstatica(20);
        Carro carroApagado = null;

        while (!filaCarros.estaVazia()) {
            Carro carro = (Carro) filaCarros.desenfileirar();
            if (carro.getPlaca().equalsIgnoreCase(placa)) {
                carroApagado = carro;
            } else {
                filaAux.enfileirar(carro);
            }
        }

        filaCarros = filaAux;
        return carroApagado;
    }

    // Questão 2
    public Carro[] getCarrosPorModelo(String modelo) {
        Enfileiravel filaDoModelo = new FilaEstatica(20);
        Enfileiravel filaAux = new FilaEstatica(20);
        int quantidade = 0;

        while (!filaCarros.estaVazia()) {
            Carro atual = (Carro) filaCarros.desenfileirar();
            if (atual.getModelo().equalsIgnoreCase(modelo)) {
                filaDoModelo.enfileirar(atual);
                quantidade++;
            }
            filaAux.enfileirar(atual);
        }

        filaCarros = filaAux;
        Carro[] arrayDeRetorno = new Carro[quantidade];

        for (int i = 0; i < quantidade; i++) {
            arrayDeRetorno[i] = (Carro) filaDoModelo.desenfileirar();
        }

        return arrayDeRetorno;
    }
}