import java.util.Random;

class Hospede extends Thread {
    private String nome;
    private Hotel hotel;

    public Hospede() {

    }
    public Hospede(String nome, Hotel hotel) {
        this.nome = nome;
        this.hotel = hotel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void run() {
        Random random = new Random();
        Quarto quarto = hotel.getQuartoDisponivel();
        if (quarto != null) {
            quarto.ocupar();
            System.out.println(nome + " entrou no quarto " + quarto.getNumero());
            hotel.adicionarHospede(this);

            try {
                // Simula o tempo que o hóspede permanece no quarto
                Thread.sleep(random.nextInt(5000) + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hotel.removerHospede(this);
            hotel.liberarQuarto(quarto);
            hotel.entregarChave(quarto); // Entregar a chave na recepção
            System.out.println(nome + " saiu do quarto " + quarto.getNumero());
        } else {
            System.out.println(nome + " não encontrou quartos disponíveis. Entrando na fila de espera.");
            hotel.entrarNaFila(this);
            try {
                while (quarto == null) {
                    Thread.sleep(random.nextInt(3000) + 1000); // Espera um tempo e tenta novamente
                    quarto = hotel.getQuartoDisponivel();
                }
                hotel.sairDaFila(this);
                run(); // Tentar alugar novamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}