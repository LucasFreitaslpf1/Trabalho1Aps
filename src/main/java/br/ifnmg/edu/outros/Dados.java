package br.ifnmg.edu.outros;

import br.ifnmg.edu.dominio.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Dados {

    public static void cargaDados() {
        Campus c = new Campus("IFNMG MOC", "Rua 2", 123, "Jaraagua", "Montes Claros");
        campi.add(c);
        Predio p = new Predio("Predio 1", c);
        predios.add(p);
        SalaReuniao s = new SalaReuniao(1, 100, p);
        salas.add(s);
        Funcionario f = new Funcionario("Joao", "Professor", "123");
        funcionarios.add(f);
        Equipamento e = new Equipamento("Giz", 12);
        equipamentos.add(e);
        Reserva r = new Reserva(LocalDate.of(2020, 2, 18), LocalTime.of(15, 0),
                LocalTime.of(16, 0), "Limpeza", new ArrayList<>(List.of(e)), s, f);
        reservas.add(r);
    }

    private static List<Equipamento> equipamentos = new ArrayList<>();
    private static List<Campus> campi = new ArrayList<>();
    private static List<Predio> predios = new ArrayList<>();
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static List<SalaReuniao> salas = new ArrayList<>();
    private static List<Reserva> reservas = new ArrayList<>();

    public static boolean salvarEquipamento(Equipamento e) {
        try {
            equipamentos.add(e);
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    public static boolean salvarCampus(Campus c) {
        try {
            campi.add(c);
        } catch (Exception exception) {
            return false;
        }
        return true;
    }

    public static boolean salvarFuncionario(Funcionario f) {
        try {
            funcionarios.add(f);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static boolean salvarPredio(Predio p) {
        try {
            predios.add(p);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean salvarSala(SalaReuniao s) {
        try {
            salas.add(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean salvarReserva(Reserva r) {

        for (Reserva reserva : reservas) {
            if (reserva.getSala().equals(r.getSala()) && r.getDataAlocacao().isEqual(reserva.getDataAlocacao())) {
                if (!r.getHoraFim().isBefore(reserva.getHoraInicio()) &&
                        !r.getHoraFim().isBefore(reserva.getHoraInicio())) {
                    System.out.println("Conflito de horário dessa sala!");
                    return false;
                }
            }
        }

        try {
            reservas.add(r);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static List<String> getEquipamentos() {
        List<String> equips = new ArrayList<>();
        equipamentos.forEach(equipamento -> equips.add(equipamento.toString()));

        return equips;
    }

    public static List<String> getCampi() {
        List<String> todosCampi = new ArrayList<>();
        campi.forEach(campus -> todosCampi.add(campus.toString()));

        return todosCampi;
    }

    public static List<String> getPredios() {
        List<String> todosPredios = new ArrayList<>();
        predios.forEach(predio -> todosPredios.add(predio.toString()));

        return todosPredios;
    }

    public static List<String> getFuncionarios() {
        List<String> todosFuncionarios = new ArrayList<>();
        funcionarios.forEach(funcionario -> todosFuncionarios.add(funcionario.toString()));

        return todosFuncionarios;
    }

    public static List<String> getSalas() {
        List<String> todasSalas = new ArrayList<>();
        salas.forEach(sala -> todasSalas.add(sala.toString()));

        return todasSalas;
    }

    public static List<String> getReservas() {
        List<String> todasReservas = new ArrayList<>();
        reservas.forEach(reserva -> todasReservas.add(reserva.toString()));

        return todasReservas;
    }

    public static Equipamento getEquipamento(Integer idx) {
        return equipamentos.get(idx);
    }

    public static List<String> getSalasPeriodo(LocalDate inicio, LocalDate fim) {
        List<String> salasLivres = new ArrayList<>();
        for (SalaReuniao sala : salas) {
            for (Reserva reserva : reservas) {
                if (reserva.getSala().equals(sala)) {
                    if (reserva.getDataAlocacao().isAfter(inicio) ||
                            reserva.getDataAlocacao().isEqual(inicio) &&
                                    reserva.getDataAlocacao().isBefore(fim)) {
                        salasLivres.add("Sala " + sala.getNumero() + " ocupada na data " + reserva.getDataAlocacao()
                                + " entre às " + reserva.getHoraInicio() + " e " + reserva.getHoraFim());
                    }
                }
            }
        }
        return salasLivres;
    }

    public static List<String> listaSalasOrdemCronologica(int escolha) {
        List<String> salasOrdem = new ArrayList<>();
        List<Reserva> reservasOrdem = new ArrayList<Reserva>(reservas);
        reservasOrdem.sort(
                new Comparator<Reserva>() {
                    @Override
                    public int compare(Reserva o1, Reserva o2) {
                        if (o1.getDataAlocacao().isBefore(o2.getDataAlocacao())) {
                            return -1;
                        }
                        if (o1.getDataAlocacao().isEqual(o2.getDataAlocacao())) {
                            return 0;
                        }

                        return 1;
                    }
                }
        );

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
                        System.out.println(reserva.getDataAlocacao().getMonth());
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

    public static List<String> getSalasLivres(LocalDate dataDesejada, LocalTime horaInicio, LocalTime horaFim) {
        List<String> salasLivres = new ArrayList<>();


        for (int i = 0; i < salas.size(); i++) {
            for (Reserva reserva : reservas) {
                if (salas.get(i).equals(reserva.getSala())) {
                    if (reserva.getDataAlocacao().isEqual(dataDesejada)) {
                        if(horaInicio.isBefore(reserva.getHoraFim()) &&
                        horaFim.isBefore(reserva.getHoraInicio()) ){
                            salasLivres.add(i + " - sala: " + salas.get(i).getNumero());
                        }
                    } else{
                        salasLivres.add(i + " - sala: " + salas.get(i).getNumero());
                    }
                }
            }
        }
        return salasLivres;
    }

    public static Campus getCampus(Integer idx) {
        return campi.get(idx);
    }

    public static Predio getPredio(Integer idx) {
        return predios.get(idx);
    }

    public static SalaReuniao getSala(Integer idx) {
        return salas.get(idx);
    }

    public static Funcionario getFuncionario(Integer idx) {
        return funcionarios.get(idx);
    }
}
