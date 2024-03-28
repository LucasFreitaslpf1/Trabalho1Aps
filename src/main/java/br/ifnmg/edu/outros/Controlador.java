package br.ifnmg.edu.outros;

import br.ifnmg.edu.dominio.*;
import com.sun.source.tree.TryTree;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Controlador {

    public Controlador() {
    }

    public void criaEquipamento(String nome, Integer patrimonio) {
        Equipamento e = new Reserva().criaEquipamento();
        e.setNome(nome);
        e.setPatrimonio(patrimonio);
        if(Dados.salvarEquipamento(e)){
            System.out.println("Equipamento criado com sucesso.");
        }
    }

    public List<String> getEquipamentos() {
        return Dados.getEquipamentos();
    }

    public void criaCampus(String nome, String rua, Integer numero, String bairro, String cidade) {
        Campus c = new Reserva().criaFuncionario().criaCampus();
        c.setNome(nome);
        c.setRua(rua);
        c.setNumero(numero);
        c.setBairro(bairro);
        c.setCidade(cidade);
        if(Dados.salvarCampus(c)){
            System.out.println("Campus criado com sucesso.");
        }
    }


    public List<String> listarCampi() {
        return Dados.getCampi();

    }

    public void criaFuncionario(String nome, String cargo, String ramal) {
        Funcionario f = new Reserva().criaFuncionario();
        f.setNome(nome);
        f.setRamal(ramal);
        f.setCargo(cargo);
        if(Dados.salvarFuncionario(f)){
            System.out.println("Funcionário criado com sucesso.");
        }
    }

    public List<String> getFuncionarios() {
        return Dados.getFuncionarios();
    }

    public Boolean temCampus() {
        return !Dados.getCampi().isEmpty();
    }

    public Boolean temPredio() {
        return !Dados.getPredios().isEmpty();
    }

    public void criaPredio(String nome, Integer numeroCampus) {
        Predio p = new Reserva().criaFuncionario().criaCampus().criaPredio();
        p.setNome(nome);
        p.setCampus(Dados.getCampus(numeroCampus));
        if(Dados.salvarPredio(p)){
            System.out.println("Prédio criado com sucesso.");
        }
    }

    public List<String> getPredios() {
        return Dados.getPredios();
    }

    public void criarSala(Integer numero, Integer qtdLugares, Integer numPredio) {
        SalaReuniao s = new Reserva().criaSala();
        s.setQtdLugares(qtdLugares);
        s.setNumero(numero);
        s.setPredio(Dados.getPredio(numPredio));
        if(Dados.salvarSala(s)){
            System.out.println("Sala criada com sucesso.");
        }
    }

    public List<String> getSalas() {
        return Dados.getSalas();
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

        if(Dados.salvarReserva(r)){
            System.out.println("Reserva criada com sucesso.");
        }
    }

    public List<String> getReservas() {
        return Dados.getReservas();
    }

    public List<String> getSalasOcupadasPeriodo(String dataInicio, String dataFim) {
        LocalDate inicio;
        LocalDate fim;

        try {
            inicio = LocalDate.parse(dataInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            fim = LocalDate.parse(dataFim, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            System.out.println("Data inválida");
            return new ArrayList<>();
        }

        return Dados.getSalasPeriodo(inicio, fim);
    }

    public List<String> getSalasOrdemCronologica(Integer escolha) {
        return Dados.listaSalasOrdemCronologica(escolha);
    }

    public List<String> getSalasLivres(String data, String horaInicio, String horaFim) {

        LocalDate dataDesejada;
        LocalTime inicio;
        LocalTime fim;

        try {
            dataDesejada = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            inicio = LocalTime.parse(horaInicio);
            fim = LocalTime.parse(horaFim);
        } catch (Exception e) {
            System.out.println("Data/Hora inválidos");
            return new ArrayList<>();
        }

        return Dados.getSalasLivres(dataDesejada, inicio, fim);
    }

    public boolean temFuncionario(){
        return !Dados.getFuncionarios().isEmpty();
    }
}
