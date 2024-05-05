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
                    hotel.notifyAll();
                }
                System.out.println(nome + " está atendendo " + hospede.getNome());
            }
        }
    }

}