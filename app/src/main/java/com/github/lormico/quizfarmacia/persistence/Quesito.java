package com.github.lormico.quizfarmacia.persistence;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "quesiti", primaryKeys = {"id_domanda", "materia"})
public class Quesito {

    public Quesito(int idDomanda, @NonNull String materia, @NonNull String domanda,
                   @NonNull String rispostaA, @NonNull String rispostaB, @NonNull String rispostaC,
                   @NonNull String rispostaD, @NonNull String rispostaE, @NonNull String soluzione) {
        this.idDomanda = idDomanda;
        this.materia = materia;
        this.domanda = domanda;
        this.rispostaA = rispostaA;
        this.rispostaB = rispostaB;
        this.rispostaC = rispostaC;
        this.rispostaD = rispostaD;
        this.rispostaE = rispostaE;
        this.soluzione = soluzione;
    }

    @ColumnInfo(name = "id_domanda")
    @NonNull
    private int idDomanda;

    @ColumnInfo
    @NonNull
    private String materia;

    @ColumnInfo
    @NonNull
    private String domanda;

    @ColumnInfo(name = "risposta_a")
    @NonNull
    private String rispostaA;

    @ColumnInfo(name = "risposta_b")
    @NonNull
    private String rispostaB;

    @ColumnInfo(name = "risposta_c")
    @NonNull
    private String rispostaC;

    @ColumnInfo(name = "risposta_d")
    @NonNull
    private String rispostaD;

    @ColumnInfo(name = "risposta_e")
    @NonNull
    private String rispostaE;

    @ColumnInfo(name = "risposta_esatta")
    @NonNull
    private String soluzione;

    public int getIdDomanda() {
        return idDomanda;
    }

    @NonNull
    public String getMateria() {
        return materia;
    }

    @NonNull
    public String getDomanda() {
        return domanda;
    }

    public String getRispostaA() {
        return rispostaA;
    }

    public String getRispostaB() {
        return rispostaB;
    }

    public String getRispostaC() {
        return rispostaC;
    }

    public String getRispostaD() {
        return rispostaD;
    }

    public String getRispostaE() {
        return rispostaE;
    }

    public String getSoluzione() {
        return soluzione;
    }
}
