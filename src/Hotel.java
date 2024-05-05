import java.util.ArrayList;

public class Hotel {
    private ArrayList<Quarto> quartos = new ArrayList<>();
    private ArrayList<Hospede> hospedes = new ArrayList<>();
    private ArrayList<Camareira> camareiras = new ArrayList<>();
    private ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    private ArrayList<Hospede> filaEspera = new ArrayList<>();

    public Hotel() {

    }


    public ArrayList<Hospede> getHospedes() {
        return hospedes;
    }


    public ArrayList<Camareira> getCamareiras() {
        return camareiras;
    }


    public ArrayList<Recepcionista> getRecepcionistas() {
        return recepcionistas;
    }

    public ArrayList<Hospede> getFilaEspera() {
        return filaEspera;
    }

    public synchronized ArrayList<Quarto> getQuartos() {
        return quartos;
    }

    public synchronized Quarto getQuartoDisponivel() {
        for (Quarto quarto : quartos) {
            if (!quarto.estaOcupado()) {
                return quarto;
            }
        }
        return null;
    }

    public synchronized void liberarQuarto(Quarto quarto) {
        quarto.liberar();
        notifyAll();
    }

    public synchronized void adicionarHospede(Hospede hospede) {
        hospedes.add(hospede);
    }

    public synchronized void removerHospede(Hospede hospede) {
        hospedes.remove(hospede);
    }

    public synchronized void entrarNaFila(Hospede hospede) {
        filaEspera.add(hospede);
    }

    public synchronized void sairDaFila(Hospede hospede) {
        filaEspera.remove(hospede);
    }

    public synchronized Hospede proximoDaFila() {
        if (!filaEspera.isEmpty()) {
            return filaEspera.remove(0);
        }
        return null;
    }

    public void run() {

    }
}