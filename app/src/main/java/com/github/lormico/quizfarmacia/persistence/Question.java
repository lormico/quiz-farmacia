package com.github.lormico.quizfarmacia.persistence;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "question", primaryKeys = {"question_id", "subject"})
public class Question implements Parcelable {

    public Question(@NonNull int questionId, @NonNull String subject, @NonNull String question,
                    @NonNull String answerA, @NonNull String answerB, @NonNull String answerC,
                    @NonNull String answerD, @NonNull String answerE, @NonNull String solution) {
        this.questionId = questionId;
        this.subject = subject;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answerE = answerE;
        this.solution = solution;
    }

    @ColumnInfo(name = "question_id")
    @NonNull
    private int questionId;

    @ColumnInfo
    @NonNull
    private String subject;

    @ColumnInfo
    @NonNull
    private String question;

    @ColumnInfo(name = "answer_a")
    @NonNull
    private String answerA;

    @ColumnInfo(name = "answer_b")
    @NonNull
    private String answerB;

    @ColumnInfo(name = "answer_c")
    @NonNull
    private String answerC;

    @ColumnInfo(name = "answer_d")
    @NonNull
    private String answerD;

    @ColumnInfo(name = "answer_e")
    @NonNull
    private String answerE;

    @ColumnInfo
    @NonNull
    private String solution;

    /* property useful to the Archive section */
    @Ignore
    private boolean expanded;

    protected Question(Parcel in) {
        questionId = in.readInt();
        subject = in.readString();
        question = in.readString();
        answerA = in.readString();
        answerB = in.readString();
        answerC = in.readString();
        answerD = in.readString();
        answerE = in.readString();
        solution = in.readString();
        expanded = in.readByte() != 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @NonNull
    public int getQuestionId() {
        return questionId;
    }

    @NonNull
    public String getSubject() {
        return subject;
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public String getAnswerE() {
        return answerE;
    }

    public String[] getAnswers() {
        return new String[]{answerA, answerB, answerC, answerD, answerE};
    }

    public String getSolution() {
        return solution;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questionId);
        dest.writeString(subject);
        dest.writeString(question);
        dest.writeString(answerA);
        dest.writeString(answerB);
        dest.writeString(answerC);
        dest.writeString(answerD);
        dest.writeString(answerE);
        dest.writeString(solution);
        dest.writeByte((byte) (expanded ? 1 : 0));
    }
}
