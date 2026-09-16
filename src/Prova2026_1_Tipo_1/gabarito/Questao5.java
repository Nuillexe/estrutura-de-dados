package Prova2026_1_Tipo_1.gabarito;

public class Questao5 {

    // Tipo 1
    //1. Instanciar a lista de alunos.
    //2. Criar o array de retorno.
    //3. Percorrer a lista selecionando os alunos.
    //4. Verificar se o curso do aluno é diferente de null.
    //5. Comparar corretamente o curso do aluno.
    //6. Armazenar o aluno no array de retorno, caso o curso seja igual aoinformado.
    //7. Atualizou o índice do array de retorno.
    //8. Retornou o array de alunos.
    private Listavel listaAlunos = new ListaEstatica(20);
    public Aluno[] getAlunosPorCurso(String curso) {
        Aluno[] alunosRetorno = new Aluno[listaAlunos.tamanho()];
        int indice = 0;
        for (int i = 0; i < listaAlunos.tamanho(); i++) {
            Aluno aluno = (Aluno) listaAlunos.selecionar(i);
            if (aluno.getCurso() != null &&
                    aluno.getCurso().equalsIgnoreCase(curso)) {
                alunosRetorno[indice] = aluno;
                indice++;
            }
        }
        return Arrays.copyOf(alunosRetorno, indice);
    }

}
