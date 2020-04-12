package com.github.lormico.quizfarmacia.ui.quiz;

import android.os.Parcel;
import android.os.Parcelable;

public class AnsweredQuestion implements Parcelable {

    private String subject;
    private int questionId;
    private int answer;

    public AnsweredQuestion(String subject, int questionId, int answer) {
        this.subject = subject;
        this.questionId = questionId;
        this.answer = answer;
    }

    protected AnsweredQuestion(Parcel in) {
        subject = in.readString();
        questionId = in.readInt();
        answer = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subject);
        dest.writeInt(questionId);
        dest.writeInt(answer);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public static final Creator<AnsweredQuestion> CREATOR = new Creator<AnsweredQuestion>() {
        @Override
        public AnsweredQuestion createFromParcel(Parcel in) {
            return new AnsweredQuestion(in);
        }

        @Override
        public AnsweredQuestion[] newArray(int size) {
            return new AnsweredQuestion[size];
        }
    };

    public String getSubject() {
        return subject;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}