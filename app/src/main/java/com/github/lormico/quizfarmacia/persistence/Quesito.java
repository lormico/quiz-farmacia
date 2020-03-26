package com.github.lormico.quizfarmacia.persistence;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Quesiti", primaryKeys = {"id_domanda", "materia"})
public class Quesito {
    @ColumnInfo(name = "id_domanda")
    @NonNull
    public int idDomanda;

    @ColumnInfo
    @NonNull
    public String materia;

    @ColumnInfo
    public String domanda;

    @ColumnInfo(name = "risposta_a")
    public String rispostaA;

    @ColumnInfo(name = "risposta_b")
    public String rispostaB;

    @ColumnInfo(name = "risposta_c")
    public String rispostaC;

    @ColumnInfo(name = "risposta_d")
    public String rispostaD;

    @ColumnInfo(name = "risposta_e")
    public String rispostaE;

    @ColumnInfo(name = "soluzione")
    public String soluzione;
}
