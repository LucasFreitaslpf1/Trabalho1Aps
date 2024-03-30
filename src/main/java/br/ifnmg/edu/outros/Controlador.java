package br.ifnmg.edu.outros;

import br.ifnmg.edu.dominio.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class Controlador {

    public Controlador() {
    }

    public void criaEquipamento(String nome, Integer patrimonio) {
        Equipamento e = new Reserva().criaEquipamento();
        e.setNome(nome);
        e.setPatrimonio(patrimonio);
        if (Dados.salvarEquipamento(e)) {
            System.out.println("Equipamento criado com sucesso.");
        }
    }

    public List<String> getEquipamentos() {
        List<String> equips = new ArrayList<>();
        Dados.getEquipamentos().forEach(equipamento -> equips.add(equipamento.toString()));

        return equips;
    }

    public void criaCampus(String nome, String rua, Integer numero, String bairro, String cidade) {
        Campus c = new Reserva().criaFuncionario().criaCampus();
        c.setNome(nome);
        c.setRua(rua);
        c.setNumero(numero);
        c.setBairro(bairro);
        c.setCidade(cidade);
        if (Dados.salvarCampus(c)) {
            System.out.println("Campus criado com sucesso.");
        }
    }


    public List<String> listarCampi() {
        List<String> campi = new ArrayList<>();

        Dados.getCampi().forEach(campus -> campi.add(campus.toString()));

        return campi;

    }

    public void criaFuncionario(String nome, String cargo, String ramal, Integer numCampus) {
        Funcionario f = new Reserva().criaFuncionario();
        f.setNome(nome);
        f.setRamal(ramal);
        f.setCargo(cargo);
        f.setCampus(Dados.getCampus(numCampus));
        if (Dados.salvarFuncionario(f)) {
            System.out.println("Funcionário criado com sucesso.");
        }
    }

    public List<String> getFuncionarios() {

        List<String> funcionarios = new ArrayList<>();
        Dados.getFuncionarios().forEach(funcionario -> funcionarios.add(funcionario.toString()));

        return funcionarios;
    }

    public Boolean temCampus() {
        return !Dados.getCampi().isEmpty();
    }

    public Boolean temPredio() {
        return !Dados.getPredios().isEmpty();
    }

    public void criaPredio(String nome, Integer numeroCampus) {
        Predio p = new Reserva().criaSala().criaPredio();
        p.setNome(nome);
        p.setCampus(Dados.getCampus(numeroCampus));
        if (Dados.salvarPredio(p)) {
            System.out.println("Prédio criado com sucesso.");
        }
    }

    public List<String> getPredios() {
        List<String> predios = new ArrayList<>();
        Dados.getPredios().forEach(predio -> predios.add(predio.toString()));
        return predios;
    }

    public void criarSala(Integer numero, Integer qtdLugares, Integer numPredio) {
        SalaReuniao s = new Reserva().criaSala();
        s.setQtdLugares(qtdLugares);
        s.setNumero(numero);
        s.setPredio(Dados.getPredio(numPredio));
        if (Dados.salvarSala(s)) {
            System.out.println("Sala criada com sucesso.");
        }
    }

    public List<String> getSalas() {
        List<String> salas = new ArrayList<>();
        Dados.getSalas().forEach(sala -> salas.add(sala.toString()));
        return salas;
    }

    public Boolean temSala() {
        return !Dados.getSalas().isEmpty();
    }

    public void criaReserva(String data, String horaInicio, String horaFim,
                            String assunto, ArrayList<Integer> equipamentos, Integer numSala,
                            Integer numFuncionario) {

        Reserva r = new Reserva();
        r.setAssunto(assunto);
        try {
            r.setDataAlocacao(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            r.setHoraInicio(LocalTime.parse(horaInicio));
            r.setHoraFim(LocalTime.parse(horaFim));
        } catch (Exception e) {
            System.out.println("Data/Hora inválidos");
            return;
        }
        r.setSala(Dados.getSala(numSala));
        for (Integer e : equipamentos) {
            r.adicionarEquipamento(Dados.getEquipamento(e));
        }
        r.setFuncionario(Dados.getFuncionario(numFuncionario));

        if (Dados.salvarReserva(r)) {
            System.out.println("Reserva criada com sucesso.");
        }
    }

    public List<String> getReservas() {

        List<String> reservas = new ArrayList<>();
        Dados.getReservas().forEach(reserva -> reservas.add(reserva.toString()));

        return reservas;
    }

    public List<String> getSalasOcupadasPeriodo(String dataInicio, String dataFim) {
        List<String> salasLivres = new ArrayList<>();
        LocalDate inicio;
        LocalDate fim;

        try {
            inicio = LocalDate.parse(dataInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            fim = LocalDate.parse(dataFim, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            System.out.println("Data inválida");
            return new ArrayList<>();
        }


        for (Reserva r : Dados.getReservasPeriodo(inicio, fim)) {
            salasLivres.add("Sala " + r.getSala().getNumero() + " ocupada na data " + r.getDataAlocacao()
                    + " entre às " + r.getHoraInicio() + " e " + r.getHoraFim());
        }

        return salasLivres;
    }

    public List<String> getSalasOrdemCronologica(Integer escolha) {
        List<Reserva> reservasOrdem = Dados.listaSalasOrdemCronologica(escolha);
        List<String> salasOrdem = new ArrayList<>();

        switch (escolha) {
            case 1: // DIA
                for (Reserva reserva : reservasOrdem) {
                    salasOrdem.add("Sala " + reserva.getSala().getNumero() + " no dia "
                            + reserva.getDataAlocacao());
                }
                break;
            case 2:
                var ultimoMes = reservasOrdem.get(0).getDataAlocacao().getMonth();
                salasOrdem.add(ultimoMes.toString());
                for (Reserva reserva : reservasOrdem) {
                    if (reserva.getDataAlocacao().getMonth() != ultimoMes) {
                        salasOrdem.add(reserva.getDataAlocacao().getMonth().toString());
                    }
                    salasOrdem.add("Sala " + reserva.getSala().getNumero() + " no dia "
                            + reserva.getDataAlocacao());
                    ultimoMes = reserva.getDataAlocacao().getMonth();
                }
                break;
            case 3:
                var data = reservasOrdem.get(0).getDataAlocacao();
                LocalDate inicioSemana = data.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate fimSemana = inicioSemana.plusDays(6L);

                salasOrdem.add("Entre: " + inicioSemana + " e " + fimSemana);
                for (Reserva reserva : reservasOrdem) {

                    if (!reserva.getDataAlocacao()
                            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                            .isEqual(inicioSemana)) {

                        inicioSemana = reserva.getDataAlocacao()
                                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                        fimSemana = inicioSemana.plusDays(6L);

                        salasOrdem.add("Entre: " + inicioSemana + " e " + fimSemana);
                    }

                    if (reserva.getDataAlocacao().isBefore(fimSemana) ||
                            reserva.getDataAlocacao().isEqual(fimSemana) &&
                                    reserva.getDataAlocacao().isAfter(inicioSemana) ||
                            reserva.getDataAlocacao().isEqual(inicioSemana)) {
                        salasOrdem.add("Sala " + reserva.getSala().getNumero() + " no dia "
                                + reserva.getDataAlocacao());
                    }

                }
                break;
        }
        return salasOrdem;
    }

    public List<String> getSalasLivres(String data, String horaInicio, String horaFim) {

        LocalDate dataDesejada;
        LocalTime inicio;
        LocalTime fim;
        List<String> salasLivres = new ArrayList<>();

        try {
            dataDesejada = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            inicio = LocalTime.parse(horaInicio);
            fim = LocalTime.parse(horaFim);
        } catch (Exception e) {
            System.out.println("Data/Hora inválidos");
            return new ArrayList<>();
        }
        var salas = Dados.getSalasLivres(dataDesejada, inicio, fim);


        for (int i = 0; i < salas.size(); i++) {
            salasLivres.add(i + " - sala: " + salas.get(i).getNumero());
        }

        return salasLivres;
    }

    public boolean temFuncionario() {
        return !Dados.getFuncionarios().isEmpty();
    }

    public Integer salaLivreToSala(int idx, String dataDesejada, String hInicio, String hFim) {
        LocalDate data;
        LocalTime ini;
        LocalTime fim;
        try{
            data = LocalDate.parse(dataDesejada,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            ini = LocalTime.parse(hInicio);
            fim = LocalTime.parse(hFim);
        } catch (Exception e)
        {
            System.out.println("Data/Hora inválidos");
            return 0;
        }
        SalaReuniao sala = Dados.getSalasLivres(data, ini, fim).get(idx);
        List<SalaReuniao> salas = Dados.getSalas();

        for (int i = 0; i < salas.size(); i++) {
            if(salas.get(i).equals(sala)){
                return i;
            }
        }
        return 0;
    }
}
