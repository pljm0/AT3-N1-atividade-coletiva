import java.util.ArrayList;

public class Hotel {
    private ArrayList<Quarto> quartos = new ArrayList<>();
    private ArrayList<Hospede> hospedes = new ArrayList<>();
    private ArrayList<Camareira> camareiras = new ArrayList<>();
    private ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    private ArrayList<Hospede> filaEspera = new ArrayList<>();

    public Hotel() {

    }

    public Hotel(int numQuartos, int numHospedes, int numCamareiras, int numRecepcionistas) {
        inicializarQuartos(numQuartos);
        inicializarHospedes(numHospedes);
        inicializarCamareiras(numCamareiras);
        inicializarRecepcionistas(numRecepcionistas);
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
    

    private void inicializarQuartos(int numQuartos) {
        for (int i = 0; i < numQuartos; i++) {
            quartos.add(new Quarto(i + 1));
        }
    }

    private void inicializarHospedes(int numHospedes) {
        for (int i = 0; i < numHospedes; i++) {
            hospedes.add(new Hospede("Hóspede " + (i + 1), this));
        }
    }

    private void inicializarCamareiras(int numCamareiras) {
        for (int i = 0; i < numCamareiras; i++) {
            camareiras.add(new Camareira("Camareira " + (i + 1), this));
        }
    }

    private void inicializarRecepcionistas(int numRecepcionistas) {
        for (int i = 0; i < numRecepcionistas; i++) {
            recepcionistas.add(new Recepcionista("Recepcionista " + (i + 1), this));
        }
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