/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioarquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author LaNo
 */
public class ManipulaArq {
    
    //manipula arquivo binario
    private ArrayList<String> arqbin;
    //manipula arquivo texto
    private ArrayList<String> arqtmp;
    
    public ArrayList<String> lerArquivoBinario(String entradaFile){
        arqbin = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(new File(entradaFile));
            try (InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.ISO_8859_1)) {
                // read one byte at a time
                int ch; while ((ch = reader.read()) != -1) {
                //System.out.println(Integer.toBinaryString(ch));
                arqbin.add(Integer.toBinaryString(ch));
                }
                return arqbin;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean escreveArquivoBinario(ArrayList<String> bin, String saidaFile){
        saidaFile = (saidaFile==null) ? "hakai.bin" : saidaFile;
        try (FileOutputStream fos = new FileOutputStream(new File(saidaFile))) {
            for (String tmp : bin) {
                //fos.write(Integer.parseInt(tmp));
                fos.write(tmp.getBytes());
            }
            fos.flush();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    //completa os zeros a direita do byte
    public ArrayList<String> completaZerosADireitaByte(ArrayList<String> listBytes){
        int contador = 0;
        //for(String t: rom){
        for(String t: listBytes){
            String lbyte = "";
            int zeroADireita = 8-t.length();
            //System.out.println(zeroADireita);
            for(int i=0;i<zeroADireita;i++){
                lbyte+="0";
                //System.out.println(lbyte);
            }
            lbyte+=t;
            //rom.set(contador, lbyte);
            listBytes.set(contador, lbyte);
            contador++;
        }
        return listBytes;
    }
    //organiza o arq binario para o armaz. de controle
    public ArrayList<String> preArmazenamentoControle(ArrayList<String> bin){
        ArrayList<String> arc = new ArrayList<>();
        for (int i=0;i<bin.size();i+=8){
            arc.add(bin.get(i+4).substring(4)+bin.get(i+3)+bin.get(i+2)+bin.get(i+1)+bin.get(i));
            //System.out.println(arc.get(i/8));
        }
        return arc;
    }
    //ver microinstrucao do arquivo binario
    public void printMicroInstArquivo(ArrayList<String> bin){
        for (int i=0; i<bin.size(); i+=8){
            System.out.println(i/8);
            for (int j=i; j<(i+8); j++){
                System.out.print(bin.get(j));
            }
            System.out.println("");
        }
    }
    
    public ArrayList<String> lerArquivo(String nomeArq){
        arqtmp = new ArrayList<>();
        try(FileReader arq = new FileReader(nomeArq)) {
            BufferedReader lerArq = new BufferedReader(arq);    
            String linha = lerArq.readLine();
            while (linha != null) {
                //System.out.printf("%s\n", linha);
                arqtmp.add(linha);
                linha = lerArq.readLine();
            }
            return arqtmp;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
        }
        return null;
    }
    
    public void escreveArquivo(){
        
    }
    
    //corrige o bug no microprog.rom do professor, inversao dos bits sll8 e sra1
    public ArrayList<String> swapSll8Sra1(ArrayList<String> list){
        for(String t: list){
            String[] arr = t.split("");
            if (Integer.parseInt(arr[12]) != Integer.parseInt(arr[13])){
                String sll8v1 = arr[12];
                arr[12] = arr[13];
                arr[13] = sll8v1;
                String tmp = "";
                for(String f: arr) tmp+=f;
                list.set(list.indexOf(t), tmp);
            }
        }
        return list;
    }
}