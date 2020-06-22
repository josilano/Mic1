/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ijvm;

import java.util.ArrayList;

/**
 *
 * @author LaNo
 */
public class AssemblerIJVM {
    
    ArrayList<String> assemblyijvm;
    ArrayList<String> opcodes;
    
    public AssemblerIJVM(){ 
        assemblyijvm = new ArrayList<>();
        opcodes = new ArrayList<>();
        assemblyijvm.add("nop");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("01", 16)));
        assemblyijvm.add("iadd");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("02", 16)));
        assemblyijvm.add("isub");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("05", 16)));
        assemblyijvm.add("iand");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("08", 16)));
        assemblyijvm.add("ior");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("0B", 16)));
        assemblyijvm.add("dup");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("0E", 16)));
        assemblyijvm.add("pop");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("10", 16)));
        assemblyijvm.add("swap");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("13", 16)));
        assemblyijvm.add("bipush");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("19", 16)));
        assemblyijvm.add("iload");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("1C", 16)));
        assemblyijvm.add("istore");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("22", 16)));
        assemblyijvm.add("wide");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("28", 16)));
        assemblyijvm.add("ldc_w");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("32", 16)));
        assemblyijvm.add("iinc");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("36", 16)));
        assemblyijvm.add("goto");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("3C", 16)));
        assemblyijvm.add("iflt");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("43", 16)));
        assemblyijvm.add("ifeq");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("47", 16)));
        assemblyijvm.add("if_icmpeq");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("4B", 16)));
        assemblyijvm.add("invokevirtual");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("55", 16)));
        assemblyijvm.add("ireturn");
        opcodes.add(Integer.toBinaryString(Integer.parseInt("6B", 16)));
    }
    
    public ArrayList<String> montagemInstrucoes(ArrayList<String> arqu){
        ArrayList<String> instrucoes = new ArrayList<>();
        ArrayList<String> marcadorLinha = new ArrayList<>();
        ArrayList<String> variaveis = new ArrayList<>();
        for (String t: arqu){
            String[] linha = t.trim().split(" ");
            int tam = linha.length;
            if (tam == 1) {
                if (assemblyijvm.contains(linha[0]))
                    instrucoes.add(opcodes.get(assemblyijvm.indexOf(linha[0])));
                else break;//possivel instrucao invalida
            }
            //ex bipush 12
            if (tam == 2) {
                //ex fim nop
                if (assemblyijvm.contains(linha[0])){
                    instrucoes.add(opcodes.get(assemblyijvm.indexOf(linha[0])));
                    if(linha[0].equalsIgnoreCase("if_icmpeq") || linha[0].equalsIgnoreCase("goto"))
                        instrucoes.add("00000000");
                }
                else {
                    marcadorLinha.add(linha[0]);
                    marcadorLinha.add(String.valueOf(instrucoes.size()+1));
                }//supoe q eh marcador d linha, mas pode ser instr. invalida
                if (assemblyijvm.contains(linha[1]))
                    instrucoes.add(opcodes.get(assemblyijvm.indexOf(linha[1])));
                else if(linha[1].matches("[0-9]*"))
                    instrucoes.add(Integer.toBinaryString(Integer.parseInt(linha[1])));
                else instrucoes.add(linha[1]);//add marcador de linha ou variavel
            }
            if (tam == 3){
                if (assemblyijvm.contains(linha[0])){
                    instrucoes.add(opcodes.get(assemblyijvm.indexOf(linha[0])));
                    if(linha[0].equalsIgnoreCase("if_icmpeq") || linha[0].equalsIgnoreCase("goto"))
                        instrucoes.add("00000000");
                }
                else {
                    marcadorLinha.add(linha[0]);
                    marcadorLinha.add(String.valueOf(instrucoes.size()+1));
                }//supoe q eh marcador d linha, mas pode ser instr. invalida
                if (assemblyijvm.contains(linha[1])){
                    instrucoes.add(opcodes.get(assemblyijvm.indexOf(linha[1])));
                    if(linha[0].equalsIgnoreCase("if_icmpeq") || linha[0].equalsIgnoreCase("goto"))
                        instrucoes.add("00000000");
                }
                if(linha[2].matches("[0-9]*"))
                    instrucoes.add(Integer.toBinaryString(Integer.parseInt(linha[2])));
                else instrucoes.add(linha[2]);//add marcador de linha ou variavel
            }
        }
        //troca identicador de linha e variavel
        for(String t: instrucoes){
            if (marcadorLinha.contains(t)) {
                String idLinha = Integer.toBinaryString(Integer.parseInt(marcadorLinha.get(marcadorLinha.indexOf(t)+1))-(instrucoes.indexOf(t)+1-2));
                if (idLinha.length()>16) idLinha = idLinha.substring(idLinha.length()-16);
                if(idLinha.length()>8) {
                    int indice = instrucoes.indexOf(t)-1;
                    instrucoes.set(instrucoes.indexOf(t), idLinha.substring(idLinha.length()-8));
                    instrucoes.set(indice, idLinha.substring(0, idLinha.length()-8));
                }
                else{
                    instrucoes.set(instrucoes.indexOf(t), idLinha);
                }
            }
            else if(t.toLowerCase().matches("[a-z]*")){
                if(variaveis.contains(t))
                    instrucoes.set(instrucoes.indexOf(t), Integer.toBinaryString(variaveis.indexOf(t)));
                else {
                    variaveis.add(t);
                    instrucoes.set(instrucoes.indexOf(t), Integer.toBinaryString(variaveis.indexOf(t)));
                }
            }
        }
        return instrucoes;
    }
    //insere os valores iniciais do mapeamento de memoria
    public ArrayList<String> inicializacaoInstrucoes(ArrayList<String> instr) {
        ArrayList<String> init = new ArrayList<>();
        init.add("00000000");
        init.add(Integer.toBinaryString(Integer.parseInt("73", 16)));
        init.add("00000000");
        init.add("00000000");
        //cpp=6
        init.add("00000110");
        init.add("00000000");
        init.add("00000000");
        init.add("00000000");
        //lv=4097
        init.add("00000001");
        init.add("00010000");//0x10 0001 0000
        init.add("00000000");
        init.add("00000000");
        //pc=1024
        init.add("00000000");
        init.add("00000100");//0x04 0000 0100
        init.add("00000000");
        init.add("00000000");
        //sp=4099
        init.add("00000011");//0x03
        init.add("00010000");//0x10
        init.add("00000000");
        init.add("00000000");
        //completa zeros ate o byte 1024
        for(int i=20;i<1025;i++){
            init.add("00000000");//1049, ex 5 bytes=40bits=4bytes+1byte,40mod32
        }
        instr.forEach((t) -> {
            init.add(t);
        });
        //completa o tam como mult d 32
        int tam=init.size();
        int completa=4-(tam%4);
        for(int i=0;i<completa;i++){
            //System.out.println("byte add "+i);
            init.add("00000000");
        }
        //System.out.println("tam bits "+init.size());
        return init;
    }
    
    public boolean isByte(ArrayList<String> list){
        //for(String t: list){
            //System.out.println(t);
        //}
        for(String t: list){
            //System.out.println(t);
            if(!t.matches("[0-9]*")) return false;
        }
        return true;
    }
    
    //insere os valores iniciais do mapeamento de memoria conforme aula 15 grrr
    public ArrayList<String> inicializacaoInstrucoesAula15(ArrayList<String> instr) {
        ArrayList<String> init = new ArrayList<>();
        //Q bytes 20+P
        String q = Integer.toBinaryString(instr.size()+20);
        if (q.length()<=8) {
            init.add(q);
            init.add("00000000");
            init.add("00000000");
            init.add("00000000");
        }
        else if (q.length()<=16){
            init.add(q.substring(8));
            init.add(q.substring(0, q.length()-8));
            init.add("00000000");
            init.add("00000000");
        }
        else if (q.length()<=24){
            init.add(q.substring(8));
            init.add(q.substring(8, 16));
            init.add(q.substring(0, q.length()-16));
            init.add("00000000");
        }
        else {
            init.add(q.substring(8));
            init.add(q.substring(8, 16));
            init.add(q.substring(16, 25));
            init.add(q.substring(0, q.length()-24));
        }
        //inicializacao 20 bytes
        init.add("00000000");
        init.add(Integer.toBinaryString(Integer.parseInt("73", 16)));
        init.add("00000000");
        init.add("00000000");
        //cpp=6
        init.add("00000110");
        init.add("00000000");
        init.add("00000000");
        init.add("00000000");
        //lv=4097
        init.add("00000001");
        init.add("00010000");//0x10 0001 0000
        init.add("00000000");
        init.add("00000000");
        //pc=1024
        init.add("00000000");
        init.add("00000100");//0x04 0000 0100
        init.add("00000000");
        init.add("00000000");
        //sp=4099
        init.add("00000011");//0x03
        init.add("00010000");//0x10
        init.add("00000000");
        init.add("00000000");
        //programa P bytes
        instr.forEach((t) -> {
            init.add(t);
        });
        
        return init;
    }
}