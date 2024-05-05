public class Quarto {

    private int numero;
    private boolean ocupado;

    public Quarto() {

    }
    public Quarto(int numero) {
        this.numero = numero;
        this.ocupado = false;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public synchronized boolean estaOcupado() {
        return ocupado;
    }

    public synchronized void ocupar() {
        ocupado = true;
    }

    public synchronized void liberar() {
        ocupado = false;
    }

    public int getNumero() {
        return numero;
    }

}
