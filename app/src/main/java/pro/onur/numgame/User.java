package pro.onur.numgame;


/**
 * Created by kasirga on 3/28/2015.
 */
public class User {

    /**
     * Users nickname
     */
    @com.google.gson.annotations.SerializedName("nick")
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
    private int xp;

    /**
     * Users total play time
     */
    @com.google.gson.annotations.SerializedName("time")
    private int total_time;

    /**
     * Users total guess count
     */
    @com.google.gson.annotations.SerializedName("guess")
    private int total_guess;

    /**
     * Users total game count
     */
    @com.google.gson.annotations.SerializedName("game")
    private int total_game;

    /**
     * Users total won game
     */
    @com.google.gson.annotations.SerializedName("won")
    private int total_won;

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
    public User(String nick, String id) {
        this.setNickname(nick);
        this.setId(id);
        this.setLevel(0);
        this.setXp(0);
        this.setTotal_time(0);
        this.setTotal_guess(0);
        this.setTotal_game(0);
        this.setTotal_won(0);
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

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
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

    public int getTotal_game() {
        return total_game;
    }

    public void setTotal_game(int total_game) {
        this.total_game = total_game;
    }

    public int getTotal_won() {
        return total_won;
    }

    public void setTotal_won(int total_won) {
        this.total_won = total_won;
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
