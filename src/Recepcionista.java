class Recepcionista extends Thread {
    private String nome;
    private Hotel hotel;

    public Recepcionista(String nome, Hotel hotel) {
        this.nome = nome;
        this.hotel = hotel;
    }

    @Override
    public void run() {
        while (true) {
            Hospede hospede = hotel.proximoDaFila();
            if (hospede != null) {
                synchronized (hotel) {
                    hotel.notifyAll(); // Notifica as camareiras de que um quarto pode estar disponível
                }
                System.out.println(nome + " está atendendo " + hospede.nome);
            }
        }