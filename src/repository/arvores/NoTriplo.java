package repository.arvores;

public class NoTriplo {

    private int elemento;
    private NoTriplo esquerda;
    private NoTriplo direita;
    private NoTriplo pai;

    public NoTriplo(int elemento){
        this.elemento=elemento;
    }

    public NoTriplo getDireita() {
        return direita;
    }

    public void setDireita(NoTriplo direita) {
        this.direita = direita;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public NoTriplo getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoTriplo esquerda) {
        this.esquerda = esquerda;
    }

    public NoTriplo getPai() {
        return pai;
    }

    public void setPai(NoTriplo pai) {
        this.pai = pai;
    }
}
