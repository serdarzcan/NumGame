package pro.onur.numgame;

/**
 * Created by kasirga on 3/28/2015.
 */
public class Game {

    /**
     * Player1 nickname
     */
    @com.google.gson.annotations.SerializedName("p1_nick")
    private String p1_nick;

    /**
     * Player2 nickname
     */
    @com.google.gson.annotations.SerializedName("p2_nick")
    private String p2_nick;

    /**
     * Player1's play time
     */
    @com.google.gson.annotations.SerializedName("p1_time")
    private int p1_time;

    /**
     * Player2's play time
     */
    @com.google.gson.annotations.SerializedName("p1_time")
    private int p2_time;

    /**
     * Player1's guess count
     */
    @com.google.gson.annotations.SerializedName("p1_guess")
    private int p1_guess;

    /**
     * Player2's guess count
     */
    @com.google.gson.annotations.SerializedName("p2_guess")
    private int p2_guess;

    /**
     * Winner of the game
     */
    @com.google.gson.annotations.SerializedName("winner")
    private String winner;

    public Game() {}

    public Game(String p1, String p2, int t1, int t2, int g1, int g2, String winner) {
        this.p1_nick = p1;
        this.p2_nick = p2;
        this.p1_time = t1;
        this.p2_time = t2;
        this.p1_guess = g1;
        this.p2_guess = g2;
        this.winner = winner;
    }

}
