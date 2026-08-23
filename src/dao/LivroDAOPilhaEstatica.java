package dao;
import model.Livro;
import repository.Empilhavel;
import repository.estaticas.pilha.PilhaEstatica;
import java.time.LocalDate;

public class LivroDAOPilhaEstatica implements LivroDAO {
    
    private Empilhavel pilhaLivros = new PilhaEstatica(100);
        
    @Override
    public void addLivro(Livro livro) {
        pilhaLivros.empilhar(livro);
    }
    
    @Override
    public Livro getLivroPorId(long id) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Livro livroRetorno = null;
        
        // Desempilhar até encontrar
        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo.getId()==id) {
                livroRetorno = livroTopo;
                break;
            }
        }
        
        // Reempilhar na ordem original
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }   

        return livroRetorno;
    }
    
    @Override
    public Livro[] getAllLivros() {

        Livro[] arrayLivrosRetorno = new Livro[contarLivros()];
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        
        // Desempilhar e coletar em array
        int indice = 0;
        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            arrayLivrosRetorno[indice++] = livroTopo;
            pilhaAuxiliar.empilhar(livroTopo);        }
        
        // Reempilhar na ordem original
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }
        
        return arrayLivrosRetorno;
    }
    
    @Override
    public Livro deleteLivro(long id) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Livro livroRetorno = null;
        
        // Desempilhar e remover se encontrar
        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            if (livroTopo.getId().equals(id)) {
                livroRetorno = livroTopo;
                // Não empilhar o removido
            } else {
                pilhaAuxiliar.empilhar(livroTopo);
            }
        }

        // Reempilhar na ordem original
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }
        
        return livroRetorno;
    }
    
    @Override
    public Livro[] getLivrosPorAutor(String autor) {
        
        Empilhavel pilhaAuxiliar = new PilhaEstatica(100);
        Empilhavel pilhaRetorno = new PilhaEstatica(100);
     
        // Desempilhar e filtrar
        while (!pilhaLivros.estaVazia()) {
            Livro livroTopo = (Livro) pilhaLivros.desempilhar();
            pilhaAuxiliar.empilhar(livroTopo);
            if (livroTopo.getAutor().equalsIgnoreCase(autor)) {
                pilhaRetorno.empilhar(livroTopo);
            }
        }
        
        // Reempilhar na ordem original
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }


        return pilhaParaArray(pilhaRetorno);
    }


    private Livro[] pilhaParaArray( Empilhavel pilha){
        Empilhavel pilhaAuxiliar= new PilhaEstatica(100);
        Livro[] arrayLivrosRetorno= new Livro[contarLivros()];
        int indiciceArray=0;
        while(!pilha.estaVazia()){
            pilhaAuxiliar.empilhar(pilha.desempilhar());
        }

        while(!pilhaAuxiliar.estaVazia()){
            arrayLivrosRetorno[indiciceArray]=(Livro)pilhaAuxiliar.desempilhar();
            indiciceArray++;
        }

        return arrayLivrosRetorno;
    }

    private int contarLivros(){
        Empilhavel pilhaAuxiliar= new PilhaEstatica(100);
        int numeroDeLivros=0;

        while(!pilhaLivros.estaVazia()){
            pilhaAuxiliar.empilhar(pilhaLivros.desempilhar());
            numeroDeLivros++;
        }

        while(!pilhaAuxiliar.estaVazia()){
            pilhaLivros.empilhar(pilhaAuxiliar.desempilhar());
        }

        return numeroDeLivros;
    }

    //Atualizando Livro do topo
    public void updateLivro(Livro newLivro){
        Empilhavel auxiliar= new PilhaEstatica(100);
        Livro livroTopo;
        while(!pilhaLivros.estaVazia()){
            livroTopo=(Livro) pilhaLivros.espiar();

            if(newLivro.getId()==livroTopo.getId()){
                pilhaLivros.atualizar(newLivro);
                break;
            }

            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while (!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

    }

    public Livro[] getLivrosPorDataPublicacao(LocalDate dataPublicacao){
        Empilhavel auxiliar= new PilhaEstatica(100);
        Empilhavel pilhaRetorno= new PilhaEstatica(100);
        Livro livro;
        while(!pilhaLivros.estaVazia()){
            livro=(Livro) pilhaLivros.espiar();
            if(livro.getDataPublicacao().equals(dataPublicacao))
                pilhaRetorno.empilhar(livro);

            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    public Livro[] getLivrosPorTitulo(String titulo){
        Empilhavel auxiliar= new PilhaEstatica(100);
        Empilhavel pilhaRetorno= new PilhaEstatica(100);
        Livro livro;
        while(!pilhaLivros.estaVazia()){
            livro=(Livro) pilhaLivros.espiar();
            if(livro.getTitulo().equalsIgnoreCase(titulo))
                pilhaRetorno.empilhar(livro);

            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    public Livro[] deleteLivrosPorTitulo(String titulo){
        Empilhavel auxiliar= new PilhaEstatica(100);
        Empilhavel pilhaRetorno= new PilhaEstatica(100);
        Livro livro;
        while(!pilhaLivros.estaVazia()) {
            livro = (Livro) pilhaLivros.espiar();
            if (livro.getTitulo().equalsIgnoreCase(titulo))
                pilhaRetorno.empilhar(pilhaLivros.desempilhar());
            else
                auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    public Livro getLivroPorIsbn(String isbn){
        Empilhavel auxiliar= new PilhaEstatica(100);
        Livro livro;

        while(!pilhaLivros.estaVazia()){
            livro=(Livro) pilhaLivros.espiar();
            if(livro.getIsbn().equalsIgnoreCase(isbn)) {
                while (!auxiliar.estaVazia()) {
                    pilhaLivros.empilhar(auxiliar.desempilhar());
                }

                return livro;
            }

            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return null;
    }

    public Livro[] getLivrosPorPrecoRange(double minPreco, double maxPreco){
        Empilhavel auxiliar= new PilhaEstatica(100);
        Empilhavel pilhaRetorno= new PilhaEstatica(100);
        Livro livro;

        while(!pilhaLivros.estaVazia()){
            livro=(Livro) pilhaLivros.espiar();
            if(livro.getPreco()>=minPreco && livro.getPreco()<maxPreco)
                pilhaRetorno.empilhar(livro);

            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    public Livro[] getLivrosPorDataRange(LocalDate minDate, LocalDate maxDate){
        Empilhavel auxiliar= new PilhaEstatica(100);
        Empilhavel pilhaRetorno= new PilhaEstatica(100);
        Livro livro;

        while(!pilhaLivros.estaVazia()){
            livro=(Livro) pilhaLivros.espiar();
            if( livro.getDataPublicacao().isAfter(minDate) && livro.getDataPublicacao().isBefore(maxDate) )
                pilhaRetorno.empilhar(livro);

            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return pilhaParaArray(pilhaRetorno);
    }

    public Livro getLivroMaisCaro(){
        Empilhavel auxiliar= new PilhaEstatica(100);
        Livro livro;
        Livro livroMaisCaro = (Livro)pilhaLivros.espiar();
        while(!pilhaLivros.estaVazia()){
            livro=(Livro) pilhaLivros.espiar();

            if(livro.getPreco()>livroMaisCaro.getPreco())
                livroMaisCaro=livro;

            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return livroMaisCaro;
    }


    public Livro getLivroMaisBarato(){
        Empilhavel auxiliar= new PilhaEstatica(100);
        Livro livro;
        Livro livroMaisBarato = (Livro)pilhaLivros.espiar();
        while(!pilhaLivros.estaVazia()){
            livro=(Livro) pilhaLivros.espiar();

            if(livro.getPreco()<livroMaisBarato.getPreco())
                livroMaisBarato=livro;

            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return livroMaisBarato;

    }

    public Livro getLivroMaisNovo(){
        Empilhavel auxiliar= new PilhaEstatica(100);
        Livro livro;
        Livro livroMaisNovo = (Livro)pilhaLivros.espiar();
        while(!pilhaLivros.estaVazia()){
            livro=(Livro) pilhaLivros.espiar();

            if(livro.getDataPublicacao().isAfter( livroMaisNovo.getDataPublicacao()))
                livroMaisNovo=livro;

            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return livroMaisNovo;
    }

    public Livro getLivroMaisAntigo(){
        Empilhavel auxiliar= new PilhaEstatica(100);
        Livro livro;
        Livro livroMaisAntigo = (Livro)pilhaLivros.espiar();
        while(!pilhaLivros.estaVazia()){
            livro=(Livro) pilhaLivros.espiar();

            if(livro.getDataPublicacao().isBefore( livroMaisAntigo.getDataPublicacao()))
                livroMaisAntigo=livro;

            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }

        return livroMaisAntigo;
    }

    public String printLivros(){
        return pilhaLivros.imprimir();
    }

    public int getTotalLivros(){
        return contarLivros();
    }

    public double getPrecoMedio(){
        if(getTotalLivros()==0)
            return 0;


        Empilhavel auxiliar= new PilhaEstatica(100);
        Livro livro;
        double somatorioDePrecos=0;

        while (!pilhaLivros.estaVazia()){
            livro=(Livro)pilhaLivros.espiar();
            somatorioDePrecos+=livro.getPreco();

            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }


        return somatorioDePrecos/getTotalLivros();
    }


    public boolean isLivroDisponivel(long id) {
        Empilhavel auxiliar= new PilhaEstatica(100);
        Livro livro;

        while (!pilhaLivros.estaVazia()){
            livro=(Livro)pilhaLivros.espiar();
            if(livro.getId()==id){
                while(!auxiliar.estaVazia()){
                    pilhaLivros.empilhar(auxiliar.desempilhar());
                }
                return true;
            }
            auxiliar.empilhar(pilhaLivros.desempilhar());
        }

        while(!auxiliar.estaVazia()){
            pilhaLivros.empilhar(auxiliar.desempilhar());
        }
        return false;
    }

    public void clearAllLivros(){
        while(!pilhaLivros.estaVazia()){
            pilhaLivros.desempilhar();
        }
    }
}
