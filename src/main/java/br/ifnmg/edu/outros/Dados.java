package br.ifnmg.edu.outros;

import br.ifnmg.edu.dominio.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
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
        Reserva r1 = new Reserva(LocalDate.of(2020, 3, 18), LocalTime.of(15, 0),
                LocalTime.of(16, 0), "Limpeza", new ArrayList<>(List.of(e)), s, f);
        reservas.add(r);
        reservas.add(r1);
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

    public static List<Equipamento> getEquipamentos() {

        return equipamentos;
    }

    public static List<Campus> getCampi() {

        return campi;
    }

    public static List<Predio> getPredios() {

        return predios;
    }

    public static List<Funcionario> getFuncionarios() {

        return funcionarios;
    }

    public static List<SalaReuniao> getSalas() {

        return salas;
    }

    public static List<Reserva> getReservas() {

        return reservas;
    }

    public static Equipamento getEquipamento(Integer idx) {
        return equipamentos.get(idx);
    }

    public static List<Reserva> getReservasPeriodo(LocalDate inicio, LocalDate fim) {
        List<Reserva> salasLivres = new ArrayList<>();
        for (SalaReuniao sala : salas) {
            for (Reserva reserva : reservas) {
                if (reserva.getSala().equals(sala)) {
                    if (reserva.getDataAlocacao().isAfter(inicio) ||
                            reserva.getDataAlocacao().isEqual(inicio) &&
                                    reserva.getDataAlocacao().isBefore(fim)) {
                        salasLivres.add(reserva);
                    }
                }
            }
        }
        return salasLivres;
    }

    public static List<Reserva> listaSalasOrdemCronologica(int escolha) {
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


        return reservasOrdem;
    }

    public static List<SalaReuniao> getSalasLivres(LocalDate dataDesejada, LocalTime horaInicio, LocalTime horaFim) {
        List<SalaReuniao> salasLivres = new ArrayList<>();


        for (SalaReuniao sala : salas) {
            for (Reserva reserva : reservas) {
                if (sala.equals(reserva.getSala())) {
                    if (reserva.getDataAlocacao().isEqual(dataDesejada)) {
                        if (horaInicio.isBefore(reserva.getHoraFim()) &&
                                horaFim.isBefore(reserva.getHoraInicio())) {
                            salasLivres.add(sala);
                        }
                    } else {
                        salasLivres.add(sala);
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
