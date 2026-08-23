package Provas_unidade_1.Espelho_da_prova2026;

public class PilhaDeCarros {
    private Empilhavel pilhaCarros = new PilhaEstatica(20);

    // Questão 3
    public Carro getCarroPorPlaca(String placa) {
        Empilhavel pilhaAux = new PilhaEstatica(20);
        Carro carroRetorno = null;

        while (!pilhaCarros.estaVazia()) {
            Carro topo = (Carro) pilhaCarros.desempilhar();
            if (topo.getPlaca().equalsIgnoreCase(placa) && carroRetorno == null) {
                carroRetorno = topo;
            }
            pilhaAux.empilhar(topo);
        }

        while (!pilhaAux.estaVazia()) {
            pilhaCarros.empilhar(pilhaAux.desempilhar());
        }

        return carroRetorno;
    }

    // Questão 4
    public void apagaCarrosPorProprietario(String proprietario) {
        Empilhavel pilhaAux = new PilhaEstatica(20);

        while (!pilhaCarros.estaVazia()) {
            Carro topo = (Carro) pilhaCarros.desempilhar();
            if (!topo.getProprietario().equalsIgnoreCase(proprietario)) {
                pilhaAux.empilhar(topo);
            }
        }

        while (!pilhaAux.estaVazia()) {
            pilhaCarros.empilhar(pilhaAux.desempilhar());
        }
    }
}