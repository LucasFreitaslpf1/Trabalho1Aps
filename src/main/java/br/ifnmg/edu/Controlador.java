package br.ifnmg.edu;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Controlador {

    public Controlador() {
        cargaDados();
    }

    private void cargaDados() {
        Campus c = new Campus("IFNMG MOC", "Rua 2", 123, "Jaraagua", "Montes Claros");
        campi.add(c);
        Predio p = new Predio("Predio 1", c);
        predios.add(p);
        SalaReuniao s = new SalaReuniao(1, 100, p);
        salas.add(s);
    }

    private List<Equipamento> equipamentos = new ArrayList<>();
    private List<Campus> campi = new ArrayList<>();
    private List<Predio> predios = new ArrayList<>();
    private List<Funcionario> funcionarios = new ArrayList<>();
    private List<SalaReuniao> salas = new ArrayList<>();

    private List<Reserva> reservas = new ArrayList<>();

    public void criaEquipamento(String nome, Integer patrimonio) {
        Equipamento e = new Reserva().criaEquipamento();
        e.setNome(nome);
        e.setPatrimonio(patrimonio);
        equipamentos.add(e);
    }

    public void listarEquipamento() {
        System.out.println("Equipamentos: ");
        for (int i = 0; i < equipamentos.size(); i++) {
            System.out.println(i + " - " + equipamentos.get(i));
        }
    }

    public void criaCampus(String nome, String rua, Integer numero, String bairro, String cidade) {
        Campus c = new Reserva().criaFuncionario().criaCampus();
        c.setNome(nome);
        c.setRua(rua);
        c.setNumero(numero);
        c.setBairro(bairro);
        c.setCidade(cidade);
        campi.add(c);
    }


    public void listarCampi() {
        System.out.println("Campi: ");
        for (int i = 0; i < campi.size(); i++) {
            System.out.println(i + " - " + campi.get(i));
        }
    }

    public void criaFuncionario(String nome, String cargo, String ramal) {
        Funcionario f = new Reserva().criaFuncionario();
        f.setNome(nome);
        f.setRamal(ramal);
        f.setCargo(cargo);
        funcionarios.add(f);
    }

    public void listarFuncionarios() {
        System.out.println("Funcionários: ");
        for (Funcionario f : funcionarios) {
            System.out.println(f);
        }
    }

    public Boolean temCampus() {
        return !campi.isEmpty();
    }

    public Boolean temPredio() {
        return !predios.isEmpty();
    }

    public void criaPredio(String nome, Integer numeroCampus) {
        Predio p = new Reserva().criaFuncionario().criaCampus().criaPredio();
        p.setNome(nome);
        p.setCampus(campi.get(numeroCampus));
        predios.add(p);
    }

    public void listarPredios() {
        for (int i = 0; i < predios.size(); i++) {
            System.out.println(i + " - " + predios.get(i));
        }
    }

    public void criarSala(Integer numero, Integer qtdLugares, Integer numPredio) {
        SalaReuniao s = new Reserva().criaSala();
        s.setQtdLugares(qtdLugares);
        s.setNumero(numero);
        s.setPredio(predios.get(numPredio));
        salas.add(s);
    }

    public void listaSalas() {
        for (int i = 0; i < salas.size(); i++) {
            System.out.println(i + " - " + salas.get(i));
        }
    }

    public Boolean temSala() {
        return !salas.isEmpty();
    }

    public void criaReserva(String data, String horaInicio, String horaFim,
                            String assunto, ArrayList<Integer> equipamentos, Integer numSala) {

        Reserva r = new Reserva();
        r.setAssunto(assunto);
        r.setDataAlocacao(LocalDate.parse(data));

        r.setHoraInicio(LocalTime.parse(horaInicio));
        r.setHoraFim(LocalTime.parse(horaFim));
        r.setSala(salas.get(numSala));
        for (Integer e : equipamentos) {
            r.adicionarEquipamento(this.equipamentos.get(e));
        }

        for (Reserva reserva : reservas) {
            if (reserva.getSala().equals(r.getSala()) && r.getDataAlocacao().isEqual(reserva.getDataAlocacao())) {
                if (r.getHoraInicio().isAfter(reserva.getHoraInicio()) &&
                        r.getHoraInicio().isBefore(reserva.getHoraFim()) ||
                        r.getHoraFim().isAfter(reserva.getHoraInicio()) &&
                                r.getHoraFim().isBefore(reserva.getHoraFim())

                ) {
                    System.out.println("Conflito de horário dessa sala!");
                    return;
                }
            }
        }

        reservas.add(r);
    }

    public void listarReservas() {
        for (Reserva r : reservas) {
            System.out.println(r);
        }
    }

}
