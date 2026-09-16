package Prova2026_1_Tipo_1.gabarito;

//Tipo 1
//1. Instanciou a lista de alunos.
//2. Criou variável para armazenar o aluno encontrado.
//3. Percorreu a lista selecionando os alunos.
//4. Verificou se a matrícula do aluno é igual à matrícula passada como parâmetro.
//5. Armazenou o aluno encontrado, caso a matrícula seja igual a informada.
//6. Apagou o aluno da lista, caso a matrícula seja igual a informada.
//7. Retornou o aluno encontrado ou null caso não exista.


public class questao4{
    private Listavel listaAlunos = new ListaEstatica(20);

    public Aluno apagaAluno(int matricula) {
    Aluno alunoRetorno = null;
        for (int i = 0; i < listaAlunos.tamanho(); i++) {
            Aluno aluno = (Aluno) listaAlunos.selecionar(i);
            if (aluno.getMatricula() == matricula) {
                alunoRetorno = aluno;
                listaAlunos.apagar(i);
                break;
            }
        }
    return alunoRetorno;
    }
}

