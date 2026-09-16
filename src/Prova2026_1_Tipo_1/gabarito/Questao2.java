package Prova2026_1_Tipo_1.gabarito;

public class Questao2 {
    //Tipo 1
    //1. Instanciar a pilha de alunos.
    //2. Criar variável para armazenar o aluno encontrado.
    //3. Criou pilha auxiliar.
    //4. Percorrer a pilha desempilhando elementos.
    //5. Armazenar temporariamente os elementos na pilha auxiliar.
    //6. Comparar a matrícula corretamente.
    //7. Armazenar o aluno encontrado, caso a matrícula seja igual a informada.
    //8. Interromper a busca ao encontrar o aluno.
    //9. Restaurar a pilha original usando a pilha auxiliar.
    //10. Retornar o aluno encontrado ou null caso não exista.
    private Empilhavel pilhaAlunos = new PilhaEstatica(20);

    public Aluno getAluno(int matricula) {
        Aluno alunoRetorno = null;
        Empilhavel pilhaAuxiliar = new PilhaEstatica(20);
        while (!pilhaAlunos.estaVazia()) {
            Aluno aluno = (Aluno) pilhaAlunos.desempilhar();
            pilhaAuxiliar.empilhar(aluno);
            if (aluno.getMatricula() == matricula) {
                alunoRetorno = aluno;
                break;
            }
        }
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaAlunos.empilhar(pilhaAuxiliar.desempilhar());
        }
        return alunoRetorno;
    }
}