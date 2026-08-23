package dao;

import model.Carro;
import repository.Empilhavel;
import repository.estaticas.pilha.PilhaEstatica;

import java.time.Duration;
import java.time.LocalDateTime;

public class CarroDAOPilhaEstatica implements CarroDAO {

    private final int capacidadeMaxima;
    private Empilhavel pilhaCarros;

    public CarroDAOPilhaEstatica() {
        this(100);
    }

    public CarroDAOPilhaEstatica(int capacidade) {
        this.capacidadeMaxima = capacidade;
        this.pilhaCarros = new PilhaEstatica(capacidade);
    }

    // =========================================================================
    // MÉTODOS AUXILIARES PRIVADOS
    // =========================================================================

    private int contarCarrosDaPilha(Empilhavel pilha) {
        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        int contador = 0;

        while (!pilha.estaVazia()) {
            auxiliar.empilhar(pilha.desempilhar());
            contador++;
        }

        while (!auxiliar.estaVazia()) {
            pilha.empilhar(auxiliar.desempilhar());
        }

        return contador;
    }

    private Carro[] pilhaParaArray(Empilhavel pilha) {
        int tamanho = contarCarrosDaPilha(pilha);
        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        Carro[] arrayRetorno = new Carro[tamanho];

        while (!pilha.estaVazia()) {
            auxiliar.empilhar(pilha.desempilhar());
        }

        int indice = 0;
        while (!auxiliar.estaVazia()) {
            arrayRetorno[indice++] = (Carro) auxiliar.desempilhar();
        }

        return arrayRetorno;
    }

    // =========================================================================
    // OPERAÇÕES BÁSICAS CRUD E REMOÇÕES
    // =========================================================================

    @Override
    public void addCarro(Carro carro) {
        if (carro == null) {
            throw new IllegalArgumentException("Carro não pode ser nulo.");
        }
        pilhaCarros.empilhar(carro);
    }

    @Override
    public Carro getCarroPorPlaca(String placa) {
        if (placa == null || pilhaCarros.estaVazia()) {
            return null;
        }

        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        Carro carroEncontrado = null;

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            auxiliar.empilhar(atual);
            if (atual.getPlaca() != null && atual.getPlaca().equalsIgnoreCase(placa)) {
                carroEncontrado = atual;
                break;
            }
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }

        return carroEncontrado;
    }

    @Override
    public Carro[] getAllCarros() {
        return pilhaParaArray(pilhaCarros);
    }

    @Override
    public void updateCarro(Carro novoCarro) {
        if (novoCarro == null || pilhaCarros.estaVazia()) {
            return;
        }

        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            if (atual.getPlaca() != null && atual.getPlaca().equalsIgnoreCase(novoCarro.getPlaca())) {
                auxiliar.empilhar(novoCarro);
            } else {
                auxiliar.empilhar(atual);
            }
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }
    }

    @Override
    public Carro deleteCarro(String placa) {
        if (placa == null || pilhaCarros.estaVazia()) {
            return null;
        }

        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        Carro removido = null;

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            if (atual.getPlaca() != null && atual.getPlaca().equalsIgnoreCase(placa)) {
                removido = atual;
            } else {
                auxiliar.empilhar(atual);
            }
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }

        return removido;
    }

    @Override
    public void removeCarrosPorProprietario(String proprietario) {
        if (proprietario == null || pilhaCarros.estaVazia()) {
            return;
        }

        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            if (atual.getNomeProprietario() == null || !atual.getNomeProprietario().equalsIgnoreCase(proprietario)) {
                auxiliar.empilhar(atual);
            }
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }
    }

    @Override
    public void removeCarrosMaisAntigosQue(LocalDateTime data) {
        if (data == null || pilhaCarros.estaVazia()) {
            return;
        }

        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            if (atual.getChegada() != null && !atual.getChegada().isBefore(data)) {
                auxiliar.empilhar(atual);
            }
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }
    }

    // =========================================================================
    // OPERAÇÕES DE CONSULTA ESPECÍFICAS
    // =========================================================================

    @Override
    public Carro getCarByLicensePlate(String licensePlate) {
        return getCarroPorPlaca(licensePlate);
    }

    @Override
    public Carro[] getCarrosPorMarca(String marca) {
        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        Empilhavel retorno = new PilhaEstatica(capacidadeMaxima);

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            if (marca != null && atual.getMarca() != null && atual.getMarca().equalsIgnoreCase(marca)) {
                retorno.empilhar(atual);
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(retorno);
    }

    @Override
    public Carro[] getCarrosPorModelo(String modelo) {
        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        Empilhavel retorno = new PilhaEstatica(capacidadeMaxima);

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            if (modelo != null && atual.getModelo() != null && atual.getModelo().equalsIgnoreCase(modelo)) {
                retorno.empilhar(atual);
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(retorno);
    }

    @Override
    public Carro[] getCarrosPorCor(String cor) {
        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        Empilhavel retorno = new PilhaEstatica(capacidadeMaxima);

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            if (cor != null && atual.getCor() != null && atual.getCor().equalsIgnoreCase(cor)) {
                retorno.empilhar(atual);
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(retorno);
    }

    @Override
    public Carro[] getCarrosPorProprietario(String proprietario) {
        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        Empilhavel retorno = new PilhaEstatica(capacidadeMaxima);

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            if (proprietario != null && atual.getNomeProprietario() != null && atual.getNomeProprietario().equalsIgnoreCase(proprietario)) {
                retorno.empilhar(atual);
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(retorno);
    }

    @Override
    public Carro[] getCarrosPorMomentoChegada(LocalDateTime inicialMomento, LocalDateTime finalMomento) {
        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        Empilhavel retorno = new PilhaEstatica(capacidadeMaxima);

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            LocalDateTime momento = atual.getChegada();

            boolean noIntervalo = (momento != null) &&
                    (!momento.isBefore(inicialMomento)) &&
                    (!momento.isAfter(finalMomento));

            if (noIntervalo) {
                retorno.empilhar(atual);
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(retorno);
    }

    @Override
    public Carro[] getCarrosComEstacionamentoLongo(long limiteHoras) {
        return getCarrosPorTempoEstacionamento(limiteHoras, Long.MAX_VALUE);
    }

    @Override
    public long getTempoMedioChegada() {
        int total = getTotalCarros();
        if (total == 0) {
            return 0;
        }

        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        long somaHoras = 0;

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            somaHoras += getTempoEstacionamento(atual.getPlaca());
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }

        return somaHoras / total;
    }

    // =========================================================================
    // ANÁLISE E ESTATÍSTICAS
    // =========================================================================

    @Override
    public Carro getCarroMaisNovo() {
        if (pilhaCarros.estaVazia()) {
            return null;
        }

        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        Carro maisNovo = (Carro) pilhaCarros.espiar();

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            if (atual.getChegada() != null && maisNovo.getChegada() != null) {
                if (atual.getChegada().isAfter(maisNovo.getChegada())) {
                    maisNovo = atual;
                }
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }

        return maisNovo;
    }

    @Override
    public Carro getCarroMaisAntigo() {
        if (pilhaCarros.estaVazia()) {
            return null;
        }

        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        Carro maisAntigo = (Carro) pilhaCarros.espiar();

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            if (atual.getChegada() != null && maisAntigo.getChegada() != null) {
                if (atual.getChegada().isBefore(maisAntigo.getChegada())) {
                    maisAntigo = atual;
                }
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }

        return maisAntigo;
    }

    // =========================================================================
    // RELATÓRIOS E POPULARIDADE
    // =========================================================================

    @Override
    public String printCarros() {
        return pilhaCarros.imprimir();
    }

    @Override
    public int getTotalCarros() {
        return contarCarrosDaPilha(pilhaCarros);
    }

    @Override
    public String getMarcaMaisPopular() {
        Carro[] todos = getAllCarros();
        if (todos.length == 0) return null;

        String maisPopular = null;
        int maxCount = 0;

        for (int i = 0; i < todos.length; i++) {
            String marca = todos[i].getMarca();
            int count = 0;
            for (int j = 0; j < todos.length; j++) {
                if (todos[j].getMarca() != null && todos[j].getMarca().equalsIgnoreCase(marca)) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                maisPopular = marca;
            }
        }
        return maisPopular;
    }

    @Override
    public String getModeloMaisPopular() {
        Carro[] todos = getAllCarros();
        if (todos.length == 0) return null;

        String maisPopular = null;
        int maxCount = 0;

        for (int i = 0; i < todos.length; i++) {
            String modelo = todos[i].getModelo();
            int count = 0;
            for (int j = 0; j < todos.length; j++) {
                if (todos[j].getModelo() != null && todos[j].getModelo().equalsIgnoreCase(modelo)) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                maisPopular = modelo;
            }
        }
        return maisPopular;
    }

    @Override
    public String getCorMaisPopular() {
        Carro[] todos = getAllCarros();
        if (todos.length == 0) return null;

        String maisPopular = null;
        int maxCount = 0;

        for (int i = 0; i < todos.length; i++) {
            String cor = todos[i].getCor();
            int count = 0;
            for (int j = 0; j < todos.length; j++) {
                if (todos[j].getCor() != null && todos[j].getCor().equalsIgnoreCase(cor)) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                maisPopular = cor;
            }
        }
        return maisPopular;
    }

    @Override
    public long getTempoEstacionamento(String placa) {
        Carro carro = getCarroPorPlaca(placa);
        if (carro == null || carro.getChegada() == null) {
            return 0;
        }
        return Duration.between(carro.getChegada(), LocalDateTime.now()).toHours();
    }

    @Override
    public Carro[] getCarrosPorTempoEstacionamento(long minHoras, long maxHoras) {
        Empilhavel auxiliar = new PilhaEstatica(capacidadeMaxima);
        Empilhavel retorno = new PilhaEstatica(capacidadeMaxima);

        while (!pilhaCarros.estaVazia()) {
            Carro atual = (Carro) pilhaCarros.desempilhar();
            long horas = getTempoEstacionamento(atual.getPlaca());

            if (horas >= minHoras && horas <= maxHoras) {
                retorno.empilhar(atual);
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaCarros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(retorno);
    }

    // =========================================================================
    // GERENCIAMENTO E CAPACIDADE DO ESTACIONAMENTO
    // =========================================================================

    @Override
    public boolean isCarroEstacionado(String placa) {
        return getCarroPorPlaca(placa) != null;
    }

    @Override
    public void clearAllCarros() {
        while (!pilhaCarros.estaVazia()) {
            pilhaCarros.desempilhar();
        }
    }

    @Override
    public int getEspacosDisponiveis() {
        return capacidadeMaxima - getTotalCarros();
    }

    @Override
    public int getOcupacao() {
        return getTotalCarros();
    }

    @Override
    public boolean isEstacionamentoCheio() {
        return getTotalCarros() >= capacidadeMaxima;
    }

    @Override
    public boolean isEstacionamentoVazio() {
        return pilhaCarros.estaVazia();
    }

    @Override
    public int getMaximaCapacidade() {
        return capacidadeMaxima;
    }
}