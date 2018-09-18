package com.example.pangling.project4.model;

import java.io.Serializable;

public class Items implements Serializable {
    String id;
    String posterPath;
    String popularity;
    String title;
    String adult;
    String overview;
    String rilisDate;
    String voteAccount;
    String average;

    public Items(String id, String posterPath, String popularity, String title, String adult, String overview, String rilisDate, String voteAccount, String average) {
        this.id = id;
        this.posterPath = posterPath;
        this.popularity = popularity;
        this.title = title;
        this.adult = adult;
        this.overview = overview;
        this.rilisDate = rilisDate;
        this.voteAccount = voteAccount;
        this.average = average;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRilisDate() {
        return rilisDate;
    }

    public void setRilisDate(String rilisDate) {
        this.rilisDate = rilisDate;
    }

    public String getVoteAccount() {
        return voteAccount;
    }

    public void setVoteAccount(String voteAccount) {
        this.voteAccount = voteAccount;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
