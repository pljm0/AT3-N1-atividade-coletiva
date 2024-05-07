import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int numQuartos = random.nextInt(21) + 10;
        int numHospedes = random.nextInt(51) + 50;
        int numCamareiras = random.nextInt(11) + 10;
        int numRecepcionistas = random.nextInt(6) + 5;

        Hotel hotel = new Hotel(numQuartos, numHospedes, numCamareiras, numRecepcionistas);
        hotel.run();

        for (int i = 0; i < 20; i++) {
            hospedeChegando(hotel);
        }

        for (int i = 0; i < 3; i++) {
            Hospede[] grupo = new Hospede[5];
            for (int j = 0; j < 5; j++) {
                grupo[j] = new Hospede("Grupo " + (i + 1) + ", Membro " + (j + 1), hotel);
                hotel.adicionarHospede(grupo[j]);
            }
        }

        // Liberar alguns quartos depois que os hóspedes chegarem
        for (int i = 0; i < 5; i++) {
            Quarto quartoLiberado = hotel.getQuartos().get(i);
            quartoLiberado.liberar();
            hotel.entregarChave(quartoLiberado);
            System.out.println("Quarto " + quartoLiberado.getNumero() + " foi liberado.");
        }

        // Liberar alguns quartos quando os hóspedes saírem para passear
        for (int i = 0; i < 3; i++) {
            Quarto quartoLiberado = hotel.getQuartos().get(i);
            quartoLiberado.liberar();
            hotel.entregarChave(quartoLiberado);
            System.out.println("Quarto " + quartoLiberado.getNumero() + " foi liberado.");
        }

        for (Quarto quarto : hotel.getQuartos()) {
            while (!quarto.estaOcupado()) {
                Hospede hospede = hospedeChegando(hotel);
                quarto.ocupar();
                System.out.println(hospede.getNome() + " foi alocado ao quarto " + quarto.getNumero());
            }
        }

        System.out.println("\nStatus das chaves dos quartos:");
        for (Quarto quarto : hotel.getQuartos()) {
            if (quarto.estaOcupado()) {
                System.out.println("Chave do quarto " + quarto.getNumero() + " está com os hóspedes.");
            } else {
                if (hotel.chaveNaRecepcao(quarto)) {
                    System.out.println("Chave do quarto " + quarto.getNumero() + " está na recepção.");
                } else {
                    System.out.println("Chave do quarto " + quarto.getNumero() + " está perdida!");
                }
            }
        }

        // Output do status do hotel após simulação
        System.out.println("\nStatus do hotel após simulação:");
        System.out.println("Número de quartos: " + hotel.getQuartos().size());
        System.out.println("Número de hóspedes: " + hotel.getHospedes().size());
        System.out.println("Número de camareiras: " + hotel.getCamareiras().size());
        System.out.println("Número de recepcionistas: " + hotel.getRecepcionistas().size());
    }

    private static Hospede hospedeChegando(Hotel hotel) {
        Hospede hospede = new Hospede("Hóspede " + (hotel.getHospedes().size() + 1), hotel);
        hotel.adicionarHospede(hospede);
        System.out.println(hospede.getNome() + " chegou ao hotel.");
        return hospede;
    }
}
