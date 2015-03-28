package pro.onur.numgame;


/**
 * Created by kasirga on 3/28/2015.
 */
public class User {

    /**
     * Users nickname
     */
    @com.google.gson.annotations.SerializedName("nickname")
    private String nickname;

    /**
     * User Id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String id;

    /**
     * Users level
     */
    @com.google.gson.annotations.SerializedName("level")
    private int level;

    /**
     * Users experience
     */
    @com.google.gson.annotations.SerializedName("exp")
    private int exp;

    /**
     * Users total play time
     */
    @com.google.gson.annotations.SerializedName("total_time")
    private int total_time;

    /**
     * Users total guess count
     */
    @com.google.gson.annotations.SerializedName("total_guess")
    private int total_guess;

    /**
     * Users total game count
     */
    @com.google.gson.annotations.SerializedName("games_played")
    private int games_played;

    /**
     * Users total won game
     */
    @com.google.gson.annotations.SerializedName("won")
    private int won;

    /**
     * User constructor
     */
    public User() {

    }

    /**
     * Initializes a new User object
     *
     * @param nick
     *            The item text
     * @param id
     *            The item id
     */
    public User(String nick) {
        this.setNickname(nick);
        //this.setId(id);
        this.setLevel(0);
        this.setExp(0);
        this.setTotal_time(0);
        this.setTotal_guess(0);
        this.setGames_played(0);
        this.setWon(0);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getTotal_time() {
        return total_time;
    }

    public void setTotal_time(int total_time) {
        this.total_time = total_time;
    }

    public int getTotal_guess() {
        return total_guess;
    }

    public void setTotal_guess(int total_guess) {
        this.total_guess = total_guess;
    }

    public int getGames_played() {
        return games_played;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    /*public void addUser(View view) {
        if (mClient == null) {
            return;
        }

        // Create a new item
        final ToDoItem item = new ToDoItem();

        item.setText(mTextNewToDo.getText().toString());
        item.setComplete(false);

        // Insert the new item
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mToDoTable.insert(item).get();
                    if (!item.isComplete()) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                mAdapter.add(item);
                            }
                        });
                    }
                } catch (Exception exception) {
                    createAndShowDialog(exception, "Error");
                }
                return null;
            }
        }.execute();

        mTextNewToDo.setText("");
    }*/
}
