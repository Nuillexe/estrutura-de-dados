package dao;

import model.Livro;
import repository.Enfileiravel;
import repository.estaticas.fila.FilaEstatica;
import repository.OverflowException;
import repository.UnderflowException;

import java.time.LocalDate;

public class LivroDAOFilaEstatica implements LivroDAO {

    private Enfileiravel filaDeLivros;

    /**
     * Construtor padrão com capacidade fixa de 100 elementos.
     */
    public LivroDAOFilaEstatica() {
        this(100);
    }

    /**
     * Construtor com capacidade flexível para o DAO.
     * @param capacidade Capacidade máxima da fila estática.
     */
    public LivroDAOFilaEstatica(int capacidade) {
        this.filaDeLivros = new FilaEstatica(capacidade);
    }

    // =========================================================================
    // OPERAÇÕES BÁSICAS CRUD
    // =========================================================================

    @Override
    public void addLivro(Livro newLivro) {
        if (newLivro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo.");
        }
        filaDeLivros.enfileirar(newLivro);
    }

    @Override
    public Livro getLivroPorId(long id) {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        Livro livroRetorno = null;

        while (!filaDeLivros.estaVazia()) {
            Livro livro = (Livro) filaDeLivros.desenfileirar();
            if (livro.getId() == id) {
                livroRetorno = livro;
            }
            filaAuxiliar.enfileirar(livro);
        }

        filaDeLivros = filaAuxiliar;
        return livroRetorno;
    }

    @Override
    public Livro[] getAllLivros() {
        int total = getTotalLivros();
        Livro[] arrayDeLivros = new Livro[total];
        Enfileiravel filaAuxiliar = new FilaEstatica(100);

        for (int i = 0; i < total; i++) {
            Livro livro = (Livro) filaDeLivros.desenfileirar();
            arrayDeLivros[i] = livro;
            filaAuxiliar.enfileirar(livro);
        }

        filaDeLivros = filaAuxiliar;
        return arrayDeLivros;
    }

    @Override
    public void updateLivro(Livro newLivro) {
        if (newLivro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo.");
        }

        Enfileiravel filaAuxiliar = new FilaEstatica(100);

        while (!filaDeLivros.estaVazia()) {
            Livro livroDaFrente = (Livro) filaDeLivros.desenfileirar();
            if (livroDaFrente.getId() == newLivro.getId()) {
                filaAuxiliar.enfileirar(newLivro);
            } else {
                filaAuxiliar.enfileirar(livroDaFrente);
            }
        }

        filaDeLivros = filaAuxiliar;
    }

    @Override
    public Livro deleteLivro(long id) {
        Enfileiravel filaAux = new FilaEstatica(100);
        Livro livroRetorno = null;

        while (!filaDeLivros.estaVazia()) {
            Livro livro = (Livro) filaDeLivros.desenfileirar();
            if (livro.getId() == id) {
                livroRetorno = livro;
            } else {
                filaAux.enfileirar(livro);
            }
        }

        filaDeLivros = filaAux;
        return livroRetorno;
    }

    // =========================================================================
    // OPERAÇÕES DE CONSULTA ESPECÍFICAS
    // =========================================================================

    @Override
    public Livro[] getLivrosPorAutor(String autor) {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        Enfileiravel filaRetorno = new FilaEstatica(100);
        int quantidade = 0;

        while (!filaDeLivros.estaVazia()) {
            Livro livro = (Livro) filaDeLivros.desenfileirar();
            if (autor != null && livro.getAutor().equalsIgnoreCase(autor)) {
                filaRetorno.enfileirar(livro);
                quantidade++;
            }
            filaAuxiliar.enfileirar(livro);
        }

        filaDeLivros = filaAuxiliar;

        Livro[] arrayRetorno = new Livro[quantidade];
        for (int i = 0; i < quantidade; i++) {
            arrayRetorno[i] = (Livro) filaRetorno.desenfileirar();
        }

        return arrayRetorno;
    }

    @Override
    public Livro[] getLivrosPorDataPublicacao(LocalDate dataPublicacao) {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        Enfileiravel filaRetorno = new FilaEstatica(100);
        int quantidade = 0;

        while (!filaDeLivros.estaVazia()) {
            Livro livro = (Livro) filaDeLivros.desenfileirar();
            if (dataPublicacao != null && dataPublicacao.equals(livro.getDataPublicacao())) {
                filaRetorno.enfileirar(livro);
                quantidade++;
            }
            filaAuxiliar.enfileirar(livro);
        }

        filaDeLivros = filaAuxiliar;

        Livro[] arrayRetorno = new Livro[quantidade];
        for (int i = 0; i < quantidade; i++) {
            arrayRetorno[i] = (Livro) filaRetorno.desenfileirar();
        }

        return arrayRetorno;
    }

    @Override
    public Livro[] getLivrosPorTitulo(String titulo) {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        Enfileiravel filaRetorno = new FilaEstatica(100);
        int quantidade = 0;

        while (!filaDeLivros.estaVazia()) {
            Livro livro = (Livro) filaDeLivros.desenfileirar();
            if (titulo != null && livro.getTitulo().equalsIgnoreCase(titulo)) {
                filaRetorno.enfileirar(livro);
                quantidade++;
            }
            filaAuxiliar.enfileirar(livro);
        }

        filaDeLivros = filaAuxiliar;

        Livro[] arrayRetorno = new Livro[quantidade];
        for (int i = 0; i < quantidade; i++) {
            arrayRetorno[i] = (Livro) filaRetorno.desenfileirar();
        }

        return arrayRetorno;
    }

    @Override
    public Livro[] deleteLivrosPorTitulo(String titulo) {
        Enfileiravel filaAux = new FilaEstatica(100);
        Enfileiravel filaRemovidos = new FilaEstatica(100);
        int quantidade = 0;

        while (!filaDeLivros.estaVazia()) {
            Livro atual = (Livro) filaDeLivros.desenfileirar();
            if (titulo != null && atual.getTitulo().equalsIgnoreCase(titulo)) {
                filaRemovidos.enfileirar(atual);
                quantidade++;
            } else {
                filaAux.enfileirar(atual);
            }
        }

        filaDeLivros = filaAux;

        Livro[] arrayRetorno = new Livro[quantidade];
        for (int i = 0; i < quantidade; i++) {
            arrayRetorno[i] = (Livro) filaRemovidos.desenfileirar();
        }

        return arrayRetorno;
    }

    @Override
    public Livro getLivroPorIsbn(String isbn) {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        Livro livroRetorno = null;

        while (!filaDeLivros.estaVazia()) {
            Livro livro = (Livro) filaDeLivros.desenfileirar();
            if (isbn != null && isbn.equalsIgnoreCase(livro.getIsbn())) {
                livroRetorno = livro;
            }
            filaAuxiliar.enfileirar(livro);
        }

        filaDeLivros = filaAuxiliar;
        return livroRetorno;
    }

    @Override
    public Livro[] getLivrosPorPrecoRange(double minPreco, double maxPreco) {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        Enfileiravel filaRetorno = new FilaEstatica(100);
        int quantidade = 0;

        while (!filaDeLivros.estaVazia()) {
            Livro livro = (Livro) filaDeLivros.desenfileirar();
            if (livro.getPreco() >= minPreco && livro.getPreco() <= maxPreco) {
                filaRetorno.enfileirar(livro);
                quantidade++;
            }
            filaAuxiliar.enfileirar(livro);
        }

        filaDeLivros = filaAuxiliar;

        Livro[] arrayRetorno = new Livro[quantidade];
        for (int i = 0; i < quantidade; i++) {
            arrayRetorno[i] = (Livro) filaRetorno.desenfileirar();
        }

        return arrayRetorno;
    }

    @Override
    public Livro[] getLivrosPorDataRange(LocalDate minDate, LocalDate maxDate) {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        Enfileiravel filaRetorno = new FilaEstatica(100);
        int quantidade = 0;

        while (!filaDeLivros.estaVazia()) {
            Livro livro = (Livro) filaDeLivros.desenfileirar();
            LocalDate data = livro.getDataPublicacao();

            boolean noIntervalo = (data != null) &&
                    (!data.isBefore(minDate)) &&
                    (!data.isAfter(maxDate));

            if (noIntervalo) {
                filaRetorno.enfileirar(livro);
                quantidade++;
            }
            filaAuxiliar.enfileirar(livro);
        }

        filaDeLivros = filaAuxiliar;

        Livro[] arrayRetorno = new Livro[quantidade];
        for (int i = 0; i < quantidade; i++) {
            arrayRetorno[i] = (Livro) filaRetorno.desenfileirar();
        }

        return arrayRetorno;
    }

    // =========================================================================
    // OPERAÇÕES DE ANÁLISE E ESTATÍSTICAS
    // =========================================================================

    @Override
    public Livro getLivroMaisCaro() {
        if (filaDeLivros.estaVazia()) {
            return null;
        }

        Enfileiravel filaAux = new FilaEstatica(100);
        Livro maisCaro = (Livro) filaDeLivros.frente();

        while (!filaDeLivros.estaVazia()) {
            Livro atual = (Livro) filaDeLivros.desenfileirar();
            if (atual.getPreco() > maisCaro.getPreco()) {
                maisCaro = atual;
            }
            filaAux.enfileirar(atual);
        }

        filaDeLivros = filaAux;
        return maisCaro;
    }

    @Override
    public Livro getLivroMaisBarato() {
        if (filaDeLivros.estaVazia()) {
            return null;
        }

        Enfileiravel filaAux = new FilaEstatica(100);
        Livro maisBarato = (Livro) filaDeLivros.frente();

        while (!filaDeLivros.estaVazia()) {
            Livro atual = (Livro) filaDeLivros.desenfileirar();
            if (atual.getPreco() < maisBarato.getPreco()) {
                maisBarato = atual;
            }
            filaAux.enfileirar(atual);
        }

        filaDeLivros = filaAux;
        return maisBarato;
    }

    @Override
    public Livro getLivroMaisNovo() {
        if (filaDeLivros.estaVazia()) {
            return null;
        }

        Enfileiravel filaAux = new FilaEstatica(100);
        Livro maisNovo = (Livro) filaDeLivros.frente();

        while (!filaDeLivros.estaVazia()) {
            Livro atual = (Livro) filaDeLivros.desenfileirar();
            if (atual.getDataPublicacao() != null && maisNovo.getDataPublicacao() != null) {
                if (atual.getDataPublicacao().isAfter(maisNovo.getDataPublicacao())) {
                    maisNovo = atual;
                }
            }
            filaAux.enfileirar(atual);
        }

        filaDeLivros = filaAux;
        return maisNovo;
    }

    @Override
    public Livro getLivroMaisAntigo() {
        if (filaDeLivros.estaVazia()) {
            return null;
        }

        Enfileiravel filaAux = new FilaEstatica(100);
        Livro maisAntigo = (Livro) filaDeLivros.frente();

        while (!filaDeLivros.estaVazia()) {
            Livro atual = (Livro) filaDeLivros.desenfileirar();
            if (atual.getDataPublicacao() != null && maisAntigo.getDataPublicacao() != null) {
                if (atual.getDataPublicacao().isBefore(maisAntigo.getDataPublicacao())) {
                    maisAntigo = atual;
                }
            }
            filaAux.enfileirar(atual);
        }

        filaDeLivros = filaAux;
        return maisAntigo;
    }

    // =========================================================================
    // OPERAÇÕES DE RELATÓRIO E ESTATÍSTICAS
    // =========================================================================

    @Override
    public String printLivros() {
        return filaDeLivros.imprimir();
    }

    @Override
    public int getTotalLivros() {
        Enfileiravel filaAuxiliar = new FilaEstatica(100);
        int contador = 0;

        while (!filaDeLivros.estaVazia()) {
            filaAuxiliar.enfileirar(filaDeLivros.desenfileirar());
            contador++;
        }

        filaDeLivros = filaAuxiliar;
        return contador;
    }

    @Override
    public double getPrecoMedio() {
        if (filaDeLivros.estaVazia()) {
            return 0.0;
        }

        Enfileiravel filaAux = new FilaEstatica(100);
        double somaPrecos = 0.0;
        int contador = 0;

        while (!filaDeLivros.estaVazia()) {
            Livro livro = (Livro) filaDeLivros.desenfileirar();
            somaPrecos += livro.getPreco();
            contador++;
            filaAux.enfileirar(livro);
        }

        filaDeLivros = filaAux;
        return somaPrecos / contador;
    }

    // =========================================================================
    // OPERAÇÕES DE GERENCIAMENTO E BACKUP
    // =========================================================================

    @Override
    public boolean isLivroDisponivel(long id) {
        return getLivroPorId(id) != null;
    }

    @Override
    public void clearAllLivros() {
        filaDeLivros.limpar();
    }
}