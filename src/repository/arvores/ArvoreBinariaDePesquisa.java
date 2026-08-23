package repository.arvores;

import java.util.Stack;

public class ArvoreBinariaDePesquisa {
    private NoTriplo raiz;

    public ArvoreBinariaDePesquisa() {
        this.raiz = null;
    }

    public NoTriplo getRaiz() {
        return raiz;
    }

    public void setRaiz(NoTriplo raiz) {
        this.raiz = raiz;
    }

    // =========================================================================
    // INSERÇÃO, PESQUISA E BUSCA DE EXTREMOS
    // =========================================================================

    public void inserir(int elemento) {
        NoTriplo novo = new NoTriplo(elemento);

        if (raiz == null) {
            raiz = novo;
            return;
        }

        NoTriplo atual = raiz;
        NoTriplo pai = null;

        while (atual != null) {
            pai = atual;
            if (elemento < atual.getElemento()) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        novo.setPai(pai);

        if (elemento < pai.getElemento()) {
            pai.setEsquerda(novo);
        } else {
            pai.setDireita(novo);
        }
    }

    public NoTriplo pesquisar(int elemento) {
        NoTriplo atual = raiz;

        while (atual != null) {
            if (elemento == atual.getElemento()) {
                return atual;
            }
            if (elemento < atual.getElemento()) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }
        return null;
    }

    public boolean contem(int elemento) {
        return pesquisar(elemento) != null;
    }

    public NoTriplo menor() {
        if (raiz == null) return null;

        NoTriplo atual = raiz;
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

    public NoTriplo maior() {
        if (raiz == null) return null;

        NoTriplo atual = raiz;
        while (atual.getDireita() != null) {
            atual = atual.getDireita();
        }
        return atual;
    }

    // =========================================================================
    // MÉTODOS DE BUSCA DE ANCESTRAIS / SUCESSORES
    // =========================================================================

    public NoTriplo encontrarMaiorAEsquerda(NoTriplo nodo) {
        if (nodo == null || nodo.getEsquerda() == null) {
            return null;
        }

        NoTriplo atual = nodo.getEsquerda();
        while (atual.getDireita() != null) {
            atual = atual.getDireita();
        }
        return atual;
    }

    public NoTriplo encontrarMenorADireita(NoTriplo nodo) {
        if (nodo == null || nodo.getDireita() == null) {
            return null;
        }

        NoTriplo atual = nodo.getDireita();
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

    public int calculaAltura(NoTriplo nodo) {
        if (nodo == null) {
            return -1; // Convenção de altura de árvore vazia
        }
        int altEsquerda = calculaAltura(nodo.getEsquerda());
        int altDireita = calculaAltura(nodo.getDireita());

        return 1 + Math.max(altEsquerda, altDireita);
    }

    // =========================================================================
    // REMOÇÕES
    // =========================================================================

    public void apagarSemFilhos(NoTriplo nodo) {
        if (nodo == null) return;

        if (nodo == raiz) {
            raiz = null;
            return;
        }

        NoTriplo pai = nodo.getPai();
        if (pai != null) {
            if (pai.getEsquerda() == nodo) {
                pai.setEsquerda(null);
            } else {
                pai.setDireita(null);
            }
            nodo.setPai(null);
        }
    }

    public void apagarComUmFilho(NoTriplo nodo) {
        if (nodo == null) return;

        NoTriplo filho = (nodo.getEsquerda() != null) ? nodo.getEsquerda() : nodo.getDireita();

        if (nodo == raiz) {
            raiz = filho;
            if (filho != null) {
                filho.setPai(null);
            }
            return;
        }

        NoTriplo pai = nodo.getPai();
        if (filho != null) {
            filho.setPai(pai);
        }

        if (pai.getEsquerda() == nodo) {
            pai.setEsquerda(filho);
        } else {
            pai.setDireita(filho);
        }

        nodo.setPai(null);
        nodo.setEsquerda(null);
        nodo.setDireita(null);
    }

    public void apagarComDoisFilhos(NoTriplo nodo) {
        if (nodo == null) return;

        // Substituto usando o maior elemento da subárvore esquerda
        NoTriplo substituto = encontrarMaiorAEsquerda(nodo);

        // Copia o valor
        nodo.setElemento(substituto.getElemento());

        // Apaga o nó substituto da sua posição original
        if (substituto.getEsquerda() == null && substituto.getDireita() == null) {
            apagarSemFilhos(substituto);
        } else {
            apagarComUmFilho(substituto);
        }
    }

    // =========================================================================
    // PERCURSOS RECURSIVOS
    // =========================================================================

    public void preOrdem(NoTriplo no) {
        if (no != null) {
            System.out.print(no.getElemento() + " ");
            preOrdem(no.getEsquerda());
            preOrdem(no.getDireita());
        }
    }

    public void emOrdem(NoTriplo no) {
        if (no != null) {
            emOrdem(no.getEsquerda());
            System.out.print(no.getElemento() + " ");
            emOrdem(no.getDireita());
        }
    }

    public void posOrdem(NoTriplo no) {
        if (no != null) {
            posOrdem(no.getEsquerda());
            posOrdem(no.getDireita());
            System.out.print(no.getElemento() + " ");
        }
    }

    // =========================================================================
    // PERCURSOS ITERATIVOS (USANDO PILHA)
    // =========================================================================

    public void preOrdemIterativo() {
        if (raiz == null) return;

        Stack<NoTriplo> pilha = new Stack<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            NoTriplo atual = pilha.pop();
            System.out.print(atual.getElemento() + " ");

            // Empilha a direita primeiro para que a esquerda seja processada antes
            if (atual.getDireita() != null) {
                pilha.push(atual.getDireita());
            }
            if (atual.getEsquerda() != null) {
                pilha.push(atual.getEsquerda());
            }
        }
        System.out.println();
    }

    public void emOrdemIterativo() {
        if (raiz == null) return;

        Stack<NoTriplo> pilha = new Stack<>();
        NoTriplo atual = raiz;

        while (atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual);
                atual = atual.getEsquerda();
            }

            atual = pilha.pop();
            System.out.print(atual.getElemento() + " ");

            atual = atual.getDireita();
        }
        System.out.println();
    }

    public void posOrdemIterativo() {
        if (raiz == null) return;

        Stack<NoTriplo> pilha1 = new Stack<>();
        Stack<NoTriplo> pilha2 = new Stack<>();

        pilha1.push(raiz);

        while (!pilha1.isEmpty()) {
            NoTriplo atual = pilha1.pop();
            pilha2.push(atual);

            if (atual.getEsquerda() != null) {
                pilha1.push(atual.getEsquerda());
            }
            if (atual.getDireita() != null) {
                pilha1.push(atual.getDireita());
            }
        }

        while (!pilha2.isEmpty()) {
            System.out.print(pilha2.pop().getElemento() + " ");
        }
        System.out.println();
    }

    // =========================================================================
    // ROTAÇÕES PARA ÁRBORES BALANCEADAS (AVL)
    // =========================================================================

    public NoTriplo rotacaoSimplesEsquerda(NoTriplo nodo) {
        if (nodo == null || nodo.getDireita() == null) return nodo;

        NoTriplo filhoDireito = nodo.getDireita();

        // Subárvore esquerda de 'filhoDireito' passa para a direita de 'nodo'
        nodo.setDireita(filhoDireito.getEsquerda());
        if (filhoDireito.getEsquerda() != null) {
            filhoDireito.getEsquerda().setPai(nodo);
        }

        // 'nodo' vira filho esquerdo de 'filhoDireito'
        filhoDireito.setEsquerda(nodo);

        // Atualização de pais
        filhoDireito.setPai(nodo.getPai());

        if (nodo.getPai() != null) {
            if (nodo.getPai().getEsquerda() == nodo) {
                nodo.getPai().setEsquerda(filhoDireito);
            } else {
                nodo.getPai().setDireita(filhoDireito);
            }
        } else {
            raiz = filhoDireito;
        }

        nodo.setPai(filhoDireito);

        return filhoDireito;
    }

    public NoTriplo rotacaoSimplesDireita(NoTriplo nodo) {
        if (nodo == null || nodo.getEsquerda() == null) return nodo;

        NoTriplo filhoEsquerdo = nodo.getEsquerda();

        // Subárvore direita de 'filhoEsquerdo' passa para a esquerda de 'nodo'
        nodo.setEsquerda(filhoEsquerdo.getDireita());
        if (filhoEsquerdo.getDireita() != null) {
            filhoEsquerdo.getDireita().setPai(nodo);
        }

        // 'nodo' vira filho direito de 'filhoEsquerdo'
        filhoEsquerdo.setDireita(nodo);

        // Atualização de pais
        filhoEsquerdo.setPai(nodo.getPai());

        if (nodo.getPai() != null) {
            if (nodo.getPai().getEsquerda() == nodo) {
                nodo.getPai().setEsquerda(filhoEsquerdo);
            } else {
                nodo.getPai().setDireita(filhoEsquerdo);
            }
        } else {
            raiz = filhoEsquerdo;
        }

        nodo.setPai(filhoEsquerdo);

        return filhoEsquerdo;
    }

    // =========================================================================
    // ESTATÍSTICAS
    // =========================================================================

    public int quantidadeNos(NoTriplo no) {
        if (no == null) return 0;
        return 1 + quantidadeNos(no.getEsquerda()) + quantidadeNos(no.getDireita());
    }

    public int quantidadeFolhas(NoTriplo no) {
        if (no == null) return 0;
        if (no.getEsquerda() == null && no.getDireita() == null) {
            return 1;
        }
        return quantidadeFolhas(no.getEsquerda()) + quantidadeFolhas(no.getDireita());
    }
}