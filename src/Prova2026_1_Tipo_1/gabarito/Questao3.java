package Prova2026_1_Tipo_1.gabarito;
public class Questao3 {
    //Tipo 1
    //1. Instanciar a pilha de alunos.
    //2. Criar pilha auxiliar.
    //3. Percorrer a pilha desempilhando elementos.
    //4. Verificar se o curso do aluno é diferente de null.
    //5. Comparar corretamente o curso do aluno.
    //6. Não copiar o aluno para a pilha auxiliar, caso o curso seja diferente do informado.
    //7. Restaurar a pilha original usando a pilha auxiliar.
    private Empilhavel pilhaAlunos = new PilhaEstatica(20);
    public void apagaAlunosPorCurso(String curso) {
        Empilhavel pilhaAuxiliar = new PilhaEstatica(20);
        while (!pilhaAlunos.estaVazia()) {
            Aluno aluno = (Aluno) pilhaAlunos.desempilhar();
            if (aluno.getCurso() != null &&
                    !aluno.getCurso().equalsIgnoreCase(curso)) {
                pilhaAuxiliar.empilhar(aluno);
            }
        }
        while (!pilhaAuxiliar.estaVazia()) {
            pilhaAlunos.empilhar(pilhaAuxiliar.desempilhar());
        }
    }