package dao;

import model.Livro;
import repository.Empilhavel;
import repository.estaticas.pilha.PilhaEstatica;

import java.time.LocalDate;

public class LivroDAOPilhaEstatica implements LivroDAO {

    private Empilhavel pilhaLivros;

    public LivroDAOPilhaEstatica() {
        this(100);
    }

    public LivroDAOPilhaEstatica(int capacidade) {
        this.pilhaLivros = new PilhaEstatica(capacidade);
    }

    // =========================================================================
    // MÉTODOS AUXILIARES PRIVADOS
    // =========================================================================

    private int contarLivrosDaPilha(Empilhavel pilha) {
        Empilhavel auxiliar = new PilhaEstatica(100);
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

    private Livro[] pilhaParaArray(Empilhavel pilha) {
        int tamanho = contarLivrosDaPilha(pilha);
        Empilhavel auxiliar = new PilhaEstatica(100);
        Livro[] arrayLivrosRetorno = new Livro[tamanho];

        // Inverte para desempilhar na ordem correta
        while (!pilha.estaVazia()) {
            auxiliar.empilhar(pilha.desempilhar());
        }

        int indice = 0;
        while (!auxiliar.estaVazia()) {
            arrayLivrosRetorno[indice++] = (Livro) auxiliar.desempilhar();
        }

        return arrayLivrosRetorno;
    }

    // =========================================================================
    // OPERAÇÕES BÁSICAS CRUD
    // =========================================================================

    @Override
    public void addLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo.");
        }
        pilhaLivros.empilhar(livro);
    }

    @Override
    public Livro getLivroPorId(long id) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Livro livroRetorno = null;

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo.getId() == id) {
                livroRetorno = livroTopo;
                break;
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return livroRetorno;
    }

    @Override
    public Livro[] getAllLivros() {
        return pilhaParaArray(pilhaLivros);
    }

    @Override
    public void updateLivro(Livro newLivro) {
        if (newLivro == null || pilhaLivros.estaVazia()) {
            return;
        }

        Empilhavel auxiliar = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            if (livroTopo.getId() == newLivro.getId()) {
                auxiliar.empilhar(newLivro);
            } else {
                auxiliar.empilhar(livroTopo);
            }
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }
    }

    @Override
    public Livro deleteLivro(long id) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Livro livroRetorno = null;

        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            if (livroTopo.getId() == id) {
                livroRetorno = livroTopo;
            } else {
                pilhaAuxiliar.empilhar(livroTopo);
            }
        }

        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return livroRetorno;
    }

    // =========================================================================
    // OPERAÇÕES DE CONSULTA ESPECÍFICAS
    // =========================================================================

    @Override
    public Livro[] getLivrosPorAutor(String autor) {
        Empilhavel auxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livro = (Livro) pilhaLivros.desempilhar();
            if (autor != null && livro.getAutor().equalsIgnoreCase(autor)) {
                pilhaRetorno.empilhar(livro);
            }
            auxiliar.empilhar(livro);
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    @Override
    public Livro[] getLivrosPorDataPublicacao(LocalDate dataPublicacao) {
        Empilhavel auxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livro = (Livro) pilhaLivros.desempilhar();
            if (dataPublicacao != null && dataPublicacao.equals(livro.getDataPublicacao())) {
                pilhaRetorno.empilhar(livro);
            }
            auxiliar.empilhar(livro);
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    @Override
    public Livro[] getLivrosPorTitulo(String titulo) {
        Empilhavel auxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livro = (Livro) pilhaLivros.desempilhar();
            if (titulo != null && livro.getTitulo().equalsIgnoreCase(titulo)) {
                pilhaRetorno.empilhar(livro);
            }
            auxiliar.empilhar(livro);
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    @Override
    public Livro[] deleteLivrosPorTitulo(String titulo) {
        Empilhavel auxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livro = (Livro) pilhaLivros.desempilhar();
            if (titulo != null && livro.getTitulo().equalsIgnoreCase(titulo)) {
                pilhaRetorno.empilhar(livro);
            } else {
                auxiliar.empilhar(livro);
            }
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    @Override
    public Livro getLivroPorIsbn(String isbn) {
        Empilhavel auxiliar = new PilhaEstatica(100);
        Livro livroRetorno = null;

        while (!pilhaLivros.estaVazia()) {
            Livro livro = (Livro) pilhaLivros.desempilhar();
            if (isbn != null && isbn.equalsIgnoreCase(livro.getIsbn())) {
                livroRetorno = livro;
            }
            auxiliar.empilhar(livro);
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return livroRetorno;
    }

    @Override
    public Livro[] getLivrosPorPrecoRange(double minPreco, double maxPreco) {
        Empilhavel auxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livro = (Livro) pilhaLivros.desempilhar();
            if (livro.getPreco() >= minPreco && livro.getPreco() <= maxPreco) {
                pilhaRetorno.empilhar(livro);
            }
            auxiliar.empilhar(livro);
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    @Override
    public Livro[] getLivrosPorDataRange(LocalDate minDate, LocalDate maxDate) {
        Empilhavel auxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);

        while (!pilhaLivros.estaVazia()) {
            Livro livro = (Livro) pilhaLivros.desempilhar();
            LocalDate data = livro.getDataPublicacao();

            boolean noIntervalo = (data != null) &&
                    (!data.isBefore(minDate)) &&
                    (!data.isAfter(maxDate));

            if (noIntervalo) {
                pilhaRetorno.empilhar(livro);
            }
            auxiliar.empilhar(livro);
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    // =========================================================================
    // OPERAÇÕES DE ANÁLISE E ESTATÍSTICAS
    // =========================================================================

    @Override
    public Livro getLivroMaisCaro() {
        if (pilhaLivros.estaVazia()) {
            return null;
        }

        Empilhavel auxiliar = new PilhaEstatica(100);
        Livro maisCaro = (Livro) pilhaLivros.espiar();

        while (!pilhaLivros.estaVazia()) {
            Livro atual = (Livro) pilhaLivros.desempilhar();
            if (atual.getPreco() > maisCaro.getPreco()) {
                maisCaro = atual;
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return maisCaro;
    }

    @Override
    public Livro getLivroMaisBarato() {
        if (pilhaLivros.estaVazia()) {
            return null;
        }

        Empilhavel auxiliar = new PilhaEstatica(100);
        Livro maisBarato = (Livro) pilhaLivros.espiar();

        while (!pilhaLivros.estaVazia()) {
            Livro atual = (Livro) pilhaLivros.desempilhar();
            if (atual.getPreco() < maisBarato.getPreco()) {
                maisBarato = atual;
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return maisBarato;
    }

    @Override
    public Livro getLivroMaisNovo() {
        if (pilhaLivros.estaVazia()) {
            return null;
        }

        Empilhavel auxiliar = new PilhaEstatica(100);
        Livro maisNovo = (Livro) pilhaLivros.espiar();

        while (!pilhaLivros.estaVazia()) {
            Livro atual = (Livro) pilhaLivros.desempilhar();
            if (atual.getDataPublicacao() != null && maisNovo.getDataPublicacao() != null) {
                if (atual.getDataPublicacao().isAfter(maisNovo.getDataPublicacao())) {
                    maisNovo = atual;
                }
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return maisNovo;
    }

    @Override
    public Livro getLivroMaisAntigo() {
        if (pilhaLivros.estaVazia()) {
            return null;
        }

        Empilhavel auxiliar = new PilhaEstatica(100);
        Livro maisAntigo = (Livro) pilhaLivros.espiar();

        while (!pilhaLivros.estaVazia()) {
            Livro atual = (Livro) pilhaLivros.desempilhar();
            if (atual.getDataPublicacao() != null && maisAntigo.getDataPublicacao() != null) {
                if (atual.getDataPublicacao().isBefore(maisAntigo.getDataPublicacao())) {
                    maisAntigo = atual;
                }
            }
            auxiliar.empilhar(atual);
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return maisAntigo;
    }

    // =========================================================================
    // OPERAÇÕES DE RELATÓRIO E GERENCIAMENTO
    // =========================================================================

    @Override
    public String printLivros() {
        return pilhaLivros.imprimir();
    }

    @Override
    public int getTotalLivros() {
        return contarLivrosDaPilha(pilhaLivros);
    }

    @Override
    public double getPrecoMedio() {
        int total = getTotalLivros();
        if (total == 0) {
            return 0.0;
        }

        Empilhavel auxiliar = new PilhaEstatica(100);
        double somaPrecos = 0.0;

        while (!pilhaLivros.estaVazia()) {
            Livro livro = (Livro) pilhaLivros.desempilhar();
            somaPrecos += livro.getPreco();
            auxiliar.empilhar(livro);
        }

        while (!auxiliar.estaVazia()) {
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return somaPrecos / total;
    }

    @Override
    public boolean isLivroDisponivel(long id) {
        return getLivroPorId(id) != null;
    }

    @Override
    public void clearAllLivros() {
        while (!pilhaLivros.estaVazia()) {
            pilhaLivros.desempilhar();
        }
    }
}