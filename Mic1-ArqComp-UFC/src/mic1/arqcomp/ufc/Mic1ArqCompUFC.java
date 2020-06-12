/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mic1.arqcomp.ufc;

import java.util.ArrayList;

/**
 *
 * @author LaNo
 */
public class Mic1ArqCompUFC {

    //registradores
    private int[] mar;
    private int[] mdr;
    private int[] pc;
    private int[] mbr;
    private int[] sp;
    private int[] lv;
    private int[] cpp;
    private int[] tos;
    private int[] opc;
    private int[] h;
    private final int TAMREG;
    private final int TAMMIR;
    private final int TAMMPC;
    //saida N e Z da ULA
    private int n;
    private int z;
    //memoria RAM
    public int[] ram;
    //armazenamento de controle
    private String[] armazDeControle;
    //registrador MIR e MPC
    private int[] mir;
    private int[] mpc;
    
    public Mic1ArqCompUFC(){
        /** array de [34]
         * [0] - enable write
         * [1] - enable read
         * [2] ao [34] - 32 bits de armazenamento
         */
        this.TAMREG = 34;
        this.TAMMIR = 36;
        this.TAMMPC = 9;
        this.mar = new int[this.TAMREG];
        this.mdr = new int[this.TAMREG];
        this.pc = new int[this.TAMREG];
        this.mbr = new int[this.TAMREG];
        this.sp = new int[this.TAMREG];
        this.lv = new int[this.TAMREG];
        this.cpp = new int[this.TAMREG];
        this.tos = new int[this.TAMREG];
        this.opc = new int[this.TAMREG];
        this.h = new int[this.TAMREG];
        this.n = 0;
        this.z = 0;
        this.carregaArmazDeControle(0, null);
        this.mir = new int[this.TAMMIR];
        this.mpc = new int[this.TAMMPC];
        carregaZeroNosReg();
    }
    
    public Mic1ArqCompUFC(int numaula, ArrayList<String> arqAC){
        /** array de [34]
         * [0] - enable write
         * [1] - enable read
         * [2] ao [34] - 32 bits de armazenamento
         */
        this.TAMREG = 34;
        this.TAMMIR = 36;
        this.TAMMPC = 9;
        this.mar = new int[this.TAMREG];
        this.mdr = new int[this.TAMREG];
        this.pc = new int[this.TAMREG];
        this.mbr = new int[this.TAMREG];
        this.sp = new int[this.TAMREG];
        this.lv = new int[this.TAMREG];
        this.cpp = new int[this.TAMREG];
        this.tos = new int[this.TAMREG];
        this.opc = new int[this.TAMREG];
        this.h = new int[this.TAMREG];
        this.n = 0;
        this.z = 0;
        this.carregaArmazDeControle(numaula, arqAC);
        this.mir = new int[this.TAMMIR];
        this.mpc = new int[this.TAMMPC];
        carregaZeroNosReg();
    }

    private int getN() {
        return n;
    }

    private void setN(int n) {
        this.n = n;
    }

    private int getZ() {
        return z;
    }

    private void setZ(int z) {
        this.z = z;
    }

    public String[] getArmazDeControle() {
        return armazDeControle;
    }
    
    private void setMir(int[] saidaControleInterno){
        this.mir = saidaControleInterno;
    }

    public int[] getMar() {
        return mar;
    }

    public int[] getMdr() {
        return mdr;
    }

    public int[] getPc() {
        return pc;
    }

    public int[] getMbr() {
        return mbr;
    }

    public int[] getSp() {
        return sp;
    }

    public int[] getLv() {
        return lv;
    }

    public int[] getCpp() {
        return cpp;
    }

    public int[] getTos() {
        return tos;
    }

    public int[] getOpc() {
        return opc;
    }

    public int[] getH() {
        return h;
    }
    
    
    private void carregaArmazDeControle(int numAula, ArrayList<String> arqAC){
        final int AULA9 = 0;
        final int AULA10 = 1;
        if(arqAC!=null){
            int cont = 0;
            this.armazDeControle = new String[arqAC.size()];
            for(String t: arqAC){
                this.armazDeControle[cont]=t;
                cont++;
            }
        }
        else if (numAula == AULA9){
            this.armazDeControle = new String[6];
            this.armazDeControle[0] = "000000000100001101010000001000010001";
            this.armazDeControle[1] = "000000010000001101010000001000010001";
            this.armazDeControle[2] = "000000011000000101001000000000000010";
            this.armazDeControle[3] = "000000100000001101010000001000010001";
            this.armazDeControle[4] = "000000101000000101000100000000000010";
            this.armazDeControle[5] = "000000000000001111000000000100000010";
        }
        else if (numAula == AULA10){
            this.armazDeControle = new String[270];
            this.armazDeControle[0] = "000000000100001101010000001000010001"; //PC <- PC + 1; fetch; GOTO MBR;
            //OPC = OPC + memory[end_word];
            this.armazDeControle[2] = "000000011000001101010000001000010001"; //PC <- PC + 1; fetch;
            this.armazDeControle[3] = "000000100000000101000000000010100010"; //MAR <- MBR; read;
            this.armazDeControle[4] = "000000101000000101001000000000000000"; //H <- MDR;
            this.armazDeControle[5] = "000000000000001111000100000000001000"; //OPC <- OPC + H; GOTO MAIN;

            //memory[end_word] = OPC;
            this.armazDeControle[6] = "000000111000001101010000001000010001"; //PC <- PC + 1; fetch;
            this.armazDeControle[7] = "000001000000000101000000000010000010"; //MAR <- MBR;
            this.armazDeControle[8] = "000000000000000101000000000101001000"; //MDR <- OPC; write; GOTO MAIN;

            //goto endereco_comando_programa;
            this.armazDeControle[9] = "000001010000001101010000001000010001"; //PC <- PC + 1; fetch;
            this.armazDeControle[10] = "000000000100000101000000001000010010"; //PC <- MBR; fetch; GOTO MBR;

            //if OPC = 0 goto endereco_comando_programa else goto proxima_linha;
            this.armazDeControle[11] = "000001100001000101000100000000001000"; //OPC <- OPC; IF ALU = 0 GOTO 268 (100001100) 								                ELSE GOTO 12 (000001100);
            this.armazDeControle[12] = "000000000000001101010000001000000001"; //PC <- PC + 1; GOTO MAIN;
            this.armazDeControle[268] = "100001101000001101010000001000010001"; //PC <- PC + 1; fetch;
            this.armazDeControle[269] = "000000000100000101000000001000010010"; //PC <- MBR; fetch; GOTO MBR;

            //OPC = OPC - memory[end_word];
            this.armazDeControle[13] = "000001110000001101010000001000010001"; //PC <- PC + 1; fetch;
            this.armazDeControle[14] = "000001111000000101000000000010100010"; //MAR <- MBR; read;
            this.armazDeControle[15] = "000010000000000101001000000000000000"; //H <- MDR;
            this.armazDeControle[16] = "000000000000001111110100000000001000";
        }
    }
    
    private void carregaZeroNosReg(){
        for (int i = 0; i < this.TAMREG; i++){
            this.mar[i] = 0;
            this.mdr[i] = 0;
            this.pc[i] = 0;
            this.mbr[i] = 0;
            this.sp[i] = 0;
            this.lv[i] = 0;
            this.cpp[i] = 0;
            this.tos[i] = 0;
            this.opc[i] = 0;
            this.h[i] = 0;
        }
        for (int i = 0; i < this.TAMMIR; i++){
            this.mir[i] = 0;
        }
        for (int i = 0; i < this.TAMMPC; i++){
            this.mpc[i] = 0;
        }
    }
    
    //write resultado do deslocador da ula nos registradores
    private void wULARegistrador(int[] saidaDeslocador){
        if (this.mar[0] == 1) {
            for (int i = 2; i < this.TAMREG; i++){
                this.mar[i] = saidaDeslocador[i-2];
            }
        }
        if (this.mdr[0] == 1) {
            for (int i = 2; i < this.TAMREG; i++){
                this.mdr[i] = saidaDeslocador[i-2];
            }
        }
        if (this.pc[0] == 1) {
            for (int i = 2; i < this.TAMREG; i++){
                this.pc[i] = saidaDeslocador[i-2];
            }
        }
        if (this.sp[0] == 1) {
            for (int i = 2; i < this.TAMREG; i++){
                this.sp[i] = saidaDeslocador[i-2];
            }
        }
        if (this.lv[0] == 1) {
            for (int i = 2; i < this.TAMREG; i++){
                this.lv[i] = saidaDeslocador[i-2];
            }
        }
        if (this.cpp[0] == 1) {
            for (int i = 2; i < this.TAMREG; i++){
                this.cpp[i] = saidaDeslocador[i-2];
            }
        }
        if (this.tos[0] == 1) {
            for (int i = 2; i < this.TAMREG; i++){
                this.tos[i] = saidaDeslocador[i-2];
            }
        }
        if (this.opc[0] == 1) {
            for (int i = 2; i < this.TAMREG; i++){
                this.opc[i] = saidaDeslocador[i-2];
            }
        }
        if (this.h[0] == 1) {
            for (int i = 2; i < this.TAMREG; i++){
                this.h[i] = saidaDeslocador[i-2];
            }
        }
    }
    
    //write tem q melhorar, na vdd sao mais metodos no w r f habilitados
    private void wrfRAMRegistrador(int w, int r, int f){
        final int bytePC = 8;
        final int palavraMAR = 32;
        if (w == 1){
            int[] arrmar = new int[palavraMAR];
            for (int i = 0; i < palavraMAR; i++){
                arrmar[i] = this.mar[i+2];
            }
            int indiceMAR = this.binToDec(arrmar);
            indiceMAR *= palavraMAR;
            /*for (int i = 0; i < palavraMAR; i++){
                this.ram[indiceMAR] = this.mdr[i+2];
                indiceMAR++;
            }*/
            int bitmdr=26;//26-33, 18-25, 10-17, 2-9
            for (int i=0;i<4;i++){
                for(int j=0;j<8;j++){
                    this.ram[indiceMAR] = this.mdr[bitmdr];
                    bitmdr++;
                    indiceMAR++;
                }
                bitmdr-=16;
            }
        }
        if (r == 1){
            int[] arrmar = new int[palavraMAR];
            for (int i = 0; i < palavraMAR; i++){
                arrmar[i] = this.mar[i+2];
            }
            int indiceMAR = this.binToDec(arrmar);
            indiceMAR *= palavraMAR;
            /*for (int i = 0; i < palavraMAR; i++){
                this.mdr[i+2] = this.ram[indiceMAR];
                indiceMAR++;
            }*/
            int bitmdr=26;//26-33, 18-25, 10-17, 2-9
            for (int i=0;i<4;i++){
                for(int j=0;j<8;j++){
                    this.mdr[bitmdr] = this.ram[indiceMAR];
                    bitmdr++;
                    indiceMAR++;
                }
                bitmdr-=16;
            }
        }
        if (f == 1){
            int[] arrpc = new int[palavraMAR];
            for (int i = 0; i < palavraMAR; i++){
                arrpc[i] = this.pc[i+2];
            }
            //for (int i: arrpc){
            //    System.out.println(i);
            //}
            int indicePC = this.binToDec(arrpc);
            //System.out.println("indicepc " +indicePC);
            indicePC *= bytePC;
            //System.out.println(indicePC);
            for (int i = 0; i < bytePC; i++){
                this.mbr[i+26] = this.ram[indicePC];
                indicePC++;
            }
        }
    }
    
    //decodificador interno da alu
    private int[] decodificador(int f0, int f1) {
        int[] enLine = new int[4];
        int f0negado = PortaLogica.not(f0);
        int f1negado = PortaLogica.not(f1);
        enLine[0] = PortaLogica.and(f0negado, f1negado);
        enLine[1] = PortaLogica.and(f0negado, f1);
        enLine[2] = PortaLogica.and(f0, f1negado);
        enLine[3] = PortaLogica.and(f0, f1);
        return enLine;
    }
	//somador completo interno da alu
    private int[][] somadorCompleto(int[] a, int[] b, int enl4, int vemUm) {
        int[][] saida = new int[2][32];//saida tem a ordem soma e vaiUm
        saida[0] = this.streamPortaANDSimples(enl4, this.streamPortaXORSimples(vemUm, this.streamPortaXOR(a, b)));
        saida[1] = this.streamPortaOR(this.streamPortaANDSimples(enl4, this.streamPortaAND(a, b)), this.streamPortaANDSimples(enl4, this.streamPortaANDSimples(vemUm, this.streamPortaXOR(a, b))));
        return saida;
    }
    private int[] somadorCompleto1bit(int a, int b, int enl4, int vemUm) {
        int[] saida = new int[2];//saida tem a ordem soma e vaiUm
        saida[0] = PortaLogica.and(enl4, PortaLogica.xOr(vemUm, PortaLogica.xOr(a, b)));
        saida[1] = PortaLogica.or(PortaLogica.and(enl4, PortaLogica.and(a, b)), PortaLogica.and(enl4, PortaLogica.and(vemUm, PortaLogica.xOr(a, b))));
        return saida;
    }
	//unidade logica interna da alu
    private int[][] unidadeLogica(int[] a, int[] b, int enl1, int enl2, int enl3) {
        int[][] saida = new int[3][32];
        //saida[0] = PortaLogica.and(enl1, PortaLogica.and(a, b));
        //saida[1] = PortaLogica.and(enl2, PortaLogica.or(a, b));
        //saida[2] = PortaLogica.and(enl3, PortaLogica.not(b));
        saida[0] = this.streamPortaANDSimples(enl1, this.streamPortaAND(a, b));
        saida[1] = this.streamPortaANDSimples(enl2, this.streamPortaOR(a, b));
        saida[2] = this.streamPortaANDSimples(enl3, this.streamPortaNOT(b));
        return saida;
    }
    private int[] unidadeLogica1bit(int a, int b, int enl1, int enl2, int enl3) {
        int[] saida = new int[3];
        saida[0] = PortaLogica.and(enl1, PortaLogica.and(a, b));
        saida[1] = PortaLogica.and(enl2, PortaLogica.or(a, b));
        saida[2] = PortaLogica.and(enl3, PortaLogica.not(b));
        return saida;
    }
    //porta and para entrada de 32 bits
    private int[] streamPortaAND(int[] a, int[] b){
        int[] result = new int[32];
        for(int i = 0; i<a.length; i++){
            result[i] = PortaLogica.and(a[i], b[i]);
        }
        return result;
    }
    //porta and para entrada de 32 bits com um int
    private int[] streamPortaANDSimples(int a, int[] b){
        int[] result = new int[32];
        for(int i = 0; i<b.length; i++){
            result[i] = PortaLogica.and(a, b[i]);
        }
        return result;
    }
    //porta or para entrada de 32 bits
    private int[] streamPortaOR(int[] a, int[] b){
        //int[] result = new int[32];
        int[] result = new int[a.length];
        for(int i = 0; i<a.length; i++){
            result[i] = PortaLogica.or(a[i], b[i]);
        }
        return result;
    }
    //porta not para entrada de 32 bits
    private int[] streamPortaNOT(int[] a){
        int[] result = new int[32];
        for(int i = 0; i<a.length; i++){
            result[i] = PortaLogica.not(a[i]);
        }
        return result;
    }
    
    private int[] streamPortaXOR(int[] a, int[] b){
        int[] result = new int[32];
        for(int i = 0; i<a.length; i++){
            result[i] = PortaLogica.xOr(a[i], b[i]);
        }
        return result;
    }
    
    private int[] streamPortaXORSimples(int a, int[] b){
        int[] result = new int[32];
        for(int i = 0; i<b.length; i++){
            result[i] = PortaLogica.xOr(a, b[i]);
        }
        return result;
    }
    //retorna na sequencia o resultado da alu no primeiro bit e vaium no segundo bit
    private int[][] alu(int[] a, int[] b, int f0, int f1, int ena, int enb, int inva, int vemUm) {
        int[] enLine = this.decodificador(f0, f1);
        //int entradaA = PortaLogica.xOr(PortaLogica.and(a, ena), inva);
        int[] entradaA = new int[32];
        for (int i = 0; i<a.length; i++){
            entradaA[i] = PortaLogica.and(a[i], ena);
        }
        for (int i = 0; i<entradaA.length; i++){
            entradaA[i] = PortaLogica.xOr(entradaA[i], inva);
        }
        //int[] entradaB = PortaLogica.and(b, enb);
        int[] entradaB = new int[32];
        for (int i = 0; i<b.length; i++){
            entradaB[i] = PortaLogica.and(b[i], enb);
        }
        int[][] sUnLog = this.unidadeLogica(entradaA, entradaB, enLine[0], enLine[1], enLine[2]);
        int[][] sSomCom = this.somadorCompleto(entradaA, entradaB, enLine[3], vemUm);
        int[][] saida = new int[2][32];
        //saida[0] = PortaLogica.or(sSomCom[0], PortaLogica.or(sUnLog[0], PortaLogica.or(sUnLog[1], sUnLog[2]) ) );
        saida[0] = this.streamPortaOR(sSomCom[0], this.streamPortaOR(sUnLog[0],this.streamPortaOR(sUnLog[1], sUnLog[2])));
        saida[1] = sSomCom[1];
        return saida;
    }
    
    private int[] alu1bit(int a, int b, int f0, int f1, int ena, int enb, int inva, int vemUm) {
        int[] enLine = this.decodificador(f0, f1);
        int entradaA = PortaLogica.xOr(PortaLogica.and(a, ena), inva);
        int entradaB = PortaLogica.and(b, enb);

        int[] saidaUnLog = this.unidadeLogica1bit(entradaA, entradaB, enLine[0], enLine[1], enLine[2]);
        int[] saidaSomCom = this.somadorCompleto1bit(entradaA, entradaB, enLine[3], vemUm);
        int[] saida = new int[2];
        saida[0] = PortaLogica.or(saidaSomCom[0], PortaLogica.or(saidaUnLog[0], PortaLogica.or(saidaUnLog[1], saidaUnLog[2])));
        saida[1] = saidaSomCom[1];
        return saida;
    }
    //decod para habilitar leitura dos registradores, reebe 4 inteiros do B do MIR
    private void decod4x16(int a, int b, int c, int d){
        this.mdr[1] = PortaLogica.and(PortaLogica.not(a), PortaLogica.and(PortaLogica.not(b), PortaLogica.and(PortaLogica.not(c), PortaLogica.not(d))));
        this.pc[1] = PortaLogica.and(PortaLogica.not(a), PortaLogica.and(PortaLogica.not(b), PortaLogica.and(PortaLogica.not(c), d)));
        //mbr[0] e mbr[1] sao habilitacoes de escrita Sem Sinal e Com Sinal, respectivamente
        this.mbr[0] = PortaLogica.and(PortaLogica.not(a), PortaLogica.and(PortaLogica.not(b), PortaLogica.and(c, PortaLogica.not(d))));
        this.mbr[1] = PortaLogica.and(PortaLogica.not(a), PortaLogica.and(PortaLogica.not(b), PortaLogica.and(c, d)));
        this.sp[1] = PortaLogica.and(PortaLogica.not(a), PortaLogica.and(b, PortaLogica.and(PortaLogica.not(c), PortaLogica.not(d))));
        this.lv[1] = PortaLogica.and(PortaLogica.not(a), PortaLogica.and(b, PortaLogica.and(PortaLogica.not(c), d)));
        this.cpp[1] = PortaLogica.and(PortaLogica.not(a), PortaLogica.and(b, PortaLogica.and(c, PortaLogica.not(d))));
        this.tos[1] = PortaLogica.and(PortaLogica.not(a), PortaLogica.and(b, PortaLogica.and(c, d)));
        this.opc[1] = PortaLogica.and(a, PortaLogica.and(PortaLogica.not(b), PortaLogica.and(PortaLogica.not(c), PortaLogica.not(d))));
    }
    
    private int[] deslocador(int sll8, int sra1, int[] saidaUla){
        if (sll8 == 1){
            for (int i = 0; i < saidaUla.length; i++){
                if (i < 24) saidaUla[i] = saidaUla[i+1];
                else saidaUla[i] = 0;
            }
            return saidaUla;
        }else if (sra1 == 1){
            for (int i = saidaUla.length - 1; i > 0; i--){
                saidaUla[i] = saidaUla[i-1];
            }
            return saidaUla;
        }
        else return saidaUla;
    }
    
    //habilita o(s) registrador(es) para escrita, recebe do mir
    private void enableWReg(int[] c){
        this.mar[0] = (c[8] == 1) ? 1 : 0;
        this.mdr[0] = (c[7] == 1) ? 1 : 0;
        this.pc[0] =  (c[6] == 1) ? 1 : 0;
        this.sp[0] =  (c[5] == 1) ? 1 : 0;
        this.lv[0] =  (c[4] == 1) ? 1 : 0;
        this.cpp[0] = (c[3] == 1) ? 1 : 0;
        this.tos[0] = (c[2] == 1) ? 1 : 0;
        this.opc[0] = (c[1] == 1) ? 1 : 0;
        this.h[0] =   (c[0] == 1) ? 1 : 0;
    }
    
    public int binToDec(int[] arr){
        int decimal = 0;
        for (int i = arr.length - 1; i >= 0; i--){
            if (arr[i] == 1) decimal += Math.pow(2, (arr.length-1)-i);
        }
        return decimal;
    }
    
    private int[] carregaBarramentoB(){
        int[] barramento = new int[32];
        if (this.mdr[1] == 1){
            for (int i = 0; i < barramento.length; i++){
                barramento[i] = this.mdr[i+2];
            }
            return barramento;
        }
        if (this.pc[1] == 1){
            for (int i = 0; i < barramento.length; i++){
                barramento[i] = this.pc[i+2];
            }
            return barramento;
        }
        //mbr sem sinal
        if (this.mbr[0] == 1){
            for (int i = 0; i < barramento.length; i++){
                if (i < 24) barramento[i] = 0;
                else barramento[i] = this.mbr[i+2];
            }
            return barramento;
        }
        //mbr com sinal
        if (this.mbr[1] == 1){
            for (int i = 0; i < barramento.length; i++){
                if (i < 24) barramento[i] = 1;
                else barramento[i] = this.mbr[i+2];
            }
            return barramento;
        }
        if (this.sp[1] == 1){
            for (int i = 0; i < barramento.length; i++){
                barramento[i] = this.sp[i+2];
            }
            return barramento;
        }
        if (this.lv[1] == 1){
            for (int i = 0; i < barramento.length; i++){
                barramento[i] = this.lv[i+2];
            }
            return barramento;
        }
        if (this.cpp[1] == 1){
            for (int i = 0; i < barramento.length; i++){
                barramento[i] = this.cpp[i+2];
            }
            return barramento;
        }
        if (this.tos[1] == 1){
            for (int i = 0; i < barramento.length; i++){
                barramento[i] = this.tos[i+2];
            }
            return barramento;
        }
        if (this.opc[1] == 1){
            for (int i = 0; i < barramento.length; i++){
                barramento[i] = this.opc[i+2];
            }
            return barramento;
        }
        //nenhum reg habilitado, barramento fica zerado
        for (int i = 0; i < barramento.length; i++){
            barramento[i] = 0;
        }
        return barramento;
    }
    
    public void printRegistros(){
        System.out.println("w r 32bits");
        System.out.println("mar");
        for (int i: this.mar){
            System.out.print(i);
        }
        System.out.println("");
        System.out.println("mdr");
        for (int i: this.mdr){
            System.out.print(i);
        }
        System.out.println("");
        System.out.println("pc");
        for (int i: this.pc){
            System.out.print(i);
        }
        System.out.println("");
        System.out.println("mbr");
        for (int i: this.mbr){
            System.out.print(i);
        }
        System.out.println("");
        System.out.println("sp");
        for (int i: this.sp){
            System.out.print(i);
        }
        System.out.println("");
        System.out.println("lv");
        for (int i: this.lv){
            System.out.print(i);
        }
        System.out.println("");
        System.out.println("cpp");
        for (int i: this.cpp){
            System.out.print(i);
        }
        System.out.println("");
        System.out.println("tos");
        for (int i: this.tos){
            System.out.print(i);
        }
        System.out.println("");
        System.out.println("opc");
        for (int i: this.opc){
            System.out.print(i);
        }
        System.out.println("");
        System.out.println("h");
        for (int i: this.h){
            System.out.print(i);
        }
        System.out.println("");
    }
    
    public boolean isWriteMemP(){
        return this.mir[29] == 1;
    }
    public void cicloDeClock(){
        //executar os metodos na sequencia correta
        //System.out.println("ciclo de clock");
        //1 passo, armazenamento de controle passa para o mir o endereco do mpc
        String microInstrucao = this.armazDeControle[this.binToDec(this.mpc)];
        //System.out.println("microinstrucao "+microInstrucao);
        //System.out.println(microInstrucao.split("")[0]);
        int[] mInst = new int[this.TAMMIR];
        int contador = 0;
        for (String i: microInstrucao.split("")){
            mInst[contador] = Integer.parseInt(i);
            contador++;
        }
        contador = 0;
        setMir(mInst);
        //2 passo, enable read reg (decod4x16)
        //B no MIR [32]-[35]
        this.decod4x16(this.mir[32], this.mir[33], this.mir[34], this.mir[35]);
        //3 passo, entrada do controle da ula
        /** ula no MIR [12]-[19]
         * [12] sll8
         * [13] sra1
         * [14] f0
         * [15] f1
         * [16] ena
         * [17] enb
         * [18] inva
         * [19] inc
         */
        final int Sll8 = 12;
        final int SRA1 = 13;
        final int F0 = 14;
        final int F1 = 15;
        final int ENA = 16;
        final int ENB = 17;
        final int INVA = 18;
        final int INC = 19;
        int[] barramentoB;
        barramentoB = this.carregaBarramentoB();
        int[] barramentoA = new int[32];
        for (int i = 0; i < barramentoA.length; i++){
            barramentoA[i] = this.h[i+2];
        }
        //int[][] saidaUla = this.alu(barramentoA, barramentoB, this.mir[F0], this.mir[F1], this.mir[ENA], this.mir[ENB], this.mir[INVA], this.mir[INC]);
        int[] saidaUla1bit = new int[32];
        int incrementoUla1bit = this.mir[INC];
        for (int i = saidaUla1bit.length-1; i >= 0; i--){
            int[] saidaVaiUm = this.alu1bit(barramentoA[i], barramentoB[i], this.mir[F0], this.mir[F1], this.mir[ENA], this.mir[ENB], this.mir[INVA], incrementoUla1bit);
            saidaUla1bit[i] = saidaVaiUm[0];
            incrementoUla1bit = saidaVaiUm[1];
        }
        //4 passo, enable write reg
        //C do mir [20]-[28]
        final int regH = 20;
        final int regOPC = 21;
        final int regTOS = 22;
        final int regCPP = 23;
        final int regLV = 24;
        final int regSP = 25;
        final int regPC = 26;
        final int regMDR = 27;
        final int regMAR = 28;
        int[] c = {this.mir[regH], this.mir[regOPC], this.mir[regTOS], this.mir[regCPP], this.mir[regLV], this.mir[regSP], this.mir[regPC], this.mir[regMDR], this.mir[regMAR]};
        this.enableWReg(c);
        //5 passo, executar deslocador pelo valor da saida da ula
        //int[] saidaDeslocador = this.deslocador(this.mir[Sll8], this.mir[SRA1], saidaUla[0]);
        int[] saidaDeslocador = this.deslocador(this.mir[Sll8], this.mir[SRA1], saidaUla1bit);
        //6 passo, escrever nos reg o valor da saida do deslocador
        this.wULARegistrador(saidaDeslocador);
        //7 passo, wrf na ram pelos reg
        //M do mir [29]-[31]
        final int WRITE = 29;
        final int READ = 30;
        final int FETCH = 31;
        this.wrfRAMRegistrador(this.mir[WRITE], this.mir[READ], this.mir[FETCH]);
        //8 passo, setar N e Z pelo valor da saida da ula
        int testeNZ = 0;
        for (int i: saidaUla1bit){
            testeNZ += i;
        }
        if (testeNZ == 0) {
            this.setZ(1);
            this.setN(0);
        }
        else {
            this.setN(1);
            this.setZ(0);
        }
        //9 passo, setar bit alto
        //jam do mir [9]-[11]
        final int JMPC = 9;
        final int JAMN = 10;
        final int JAMZ = 11;
        int bitAlto;
        bitAlto = PortaLogica.or(PortaLogica.and(this.getN(), this.mir[JAMN]), PortaLogica.and(this.getZ(), this.mir[JAMZ]));
        //10 passo, se jmpc é 1, fazer OR d mbr com addr (calc mpc8lsb), senao, passar addr para mpc
        //addr do mir [0]-[8]
        int[] addr = new int[this.TAMMPC - 1];
        int[] mbr8lsb = new int[this.TAMMPC - 1];
        for (int i = 0; i < this.TAMMPC - 1; i++){
            addr[i] = this.mir[i+1];
            mbr8lsb[i] = this.mbr[i+26];
        }
        if (this.mir[JMPC] == 1){
            int[] calcMpc = this.streamPortaOR(mbr8lsb, addr);
            for (int i: calcMpc){
                this.mpc[contador+1] = i;
                contador++;
            }
        }else {
            for (int i: addr){
                this.mpc[contador+1] = i;
                contador++;
            }
            this.mpc[0] = this.mir[0];
        }
        //11 passo, setar msb do mpc pelo bit alto
        this.mpc[0] = bitAlto;   
    }
}
