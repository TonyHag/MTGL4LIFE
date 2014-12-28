package com.springapp.mvc.service;

/**
 * Created by eirikskogland on 28.12.14.
 */
public class RatingService {

    public double expectedScore(double ra, double rb) {
        return (1/(1+Math.pow(10, ((rb - ra) / 400))));

    }

    /**
     * Returnerer ny rating for spiller.
     * @param ra Rating til spiller A
     * @param rb Rating til spiller B
     * @param score 1 for win, 0.5 for draw og 0 for loss.
     * @return ny rating
     */
    public int updateRating(int ra, int rb, double score) {
        double expected = expectedScore(ra, rb);
        double k = 32;
        return (int) (ra + k*(score-expected));
    }

}
