package Prova2026_1_Tipo_1.Monitoria16_do_9_26;

/*
3. Implemente void apagaAlunosPorCurso(String curso), que remove todos os alunos de pilhaAlunos, cujo curso coincide
com o valor informado. Utilizando uma Lista Estática Circular (ListaEstatica.java) que implementa a interface
Listavel.java, implemente os métodos solicitados a seguir. Considere que os alunos estejam armazenados em: Listavel
listaAlunos = new ListaEstatica(20);
 *
 */



public class Questao3 {
    class Aluno(){
        private String curso;

        public String getCurso(){
            return curso;
        }
    }

    ListaEstatica listaAlunos = new ListaEstatica(20);

    public void apagaAlunosPorCurso(String curso){
        for(int i=0; i<listaAlunos.tamanho(); i++){
            Aluno a = listaAlunos.selecionar(i);
            if(a.getCurso().equalIgnoreCase(curso)){
                listaAlunos.apagar(i);
            }
        }
    }
}
