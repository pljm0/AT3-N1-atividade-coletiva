import java.util.List;

class Camareira extends Thread {
    private String nome;
    private Hotel hotel;

    public Camareira(String nome, Hotel hotel) {
        this.nome = nome;
        this.hotel = hotel;
    }

    @Override
    public void run() {
        while (true) {
            List<Quarto> quartos = hotel.getQuartos();
            for (Quarto quarto : quartos) {
                if (!quarto.estaOcupado()) {
                    synchronized (hotel) {
                        try {
                            hotel.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                quarto.liberar();
                System.out.println(nome + " limpou o quarto " + quarto.getNumero());
            }
        }
    }
}