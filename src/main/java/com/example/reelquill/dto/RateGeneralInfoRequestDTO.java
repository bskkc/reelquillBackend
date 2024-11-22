package com.example.reelquill.dto;

public class RateGeneralInfoRequestDTO {
    private int movieId;
    private double rate;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
