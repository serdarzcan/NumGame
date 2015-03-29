package pro.onur.numgame;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;

import pro.onur.myviews.MyButton;
import pro.onur.myviews.MyEditText;
import pro.onur.myviews.MyTextView;


public class SetNickname extends ActionBarActivity {

    public boolean canGoNext = false;
    public MyTextView inUse;

    private Animation shakeAnim;

    /**
     * Mobile Service Client reference
     */
    private MobileServiceClient mClient;

    /**
     * Mobile Service Table used to access data
     */
    private MobileServiceTable<User> mUserTable;

    /**
     * Progress spinner to use for table operations
     */
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_nickname);

        shakeAnim = AnimationUtils.loadAnimation(this,R.anim.shake);

        inUse = (MyTextView) findViewById(R.id.text_InUse);
        inUse.setVisibility(View.GONE);

        mProgressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);
        mProgressBar.setVisibility(View.GONE);

        final MyTextView setNick = (MyTextView) findViewById(R.id.text_SetNickname);
        final MyEditText editNick = (MyEditText) findViewById(R.id.editText_setNickname);

        final MyButton nextButton_SN = (MyButton) findViewById(R.id.button_sN_Next);
        nextButton_SN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canGoNext) {
                    addUser(editNick.getText().toString());

                } else {
                    setNick.startAnimation(shakeAnim);
                }
            }
        });

        editNick.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /*inUse.setVisibility(View.GONE);
                System.out.println(count);
                if (s.length() < 3) {
                    nextButton_SN.setClickable(false);
                } else {
                    nextButton_SN.setClickable(true);
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 3) {
                    checkNicknameAvailability(s.toString());
                    if (!canGoNext) {
                        inUse.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        try {
            // Create the Mobile Service Client instance, using the provided
            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://numgame.azure-mobile.net/",
                    "wZNxxbOuSHDVuSDDlYlzjdvzGvuQcK88",
                    this).withFilter(new ProgressFilter());

            // Get the Mobile Service Table instance to use
            mUserTable = mClient.getTable(User.class);

            //Log.d("database","database:"+mUserTable.toString());

            //mTextNewToDo = (EditText) findViewById(R.id.textNewToDo);

            // Create an adapter to bind the items with the view
            /*mAdapter = new ToDoItemAdapter(this, R.layout.row_list_to_do);
            ListView listViewToDo = (ListView) findViewById(R.id.listViewToDo);
            listViewToDo.setAdapter(mAdapter);*/

            // Load the items from the Mobile Service
            //refreshItemsFromTable();

        } catch (MalformedURLException e) {
            //createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkNicknameAvailability(final String nick) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final MobileServiceList<User> result = mUserTable.where().field("nickname").eq(nick).execute().get();
                    System.out.println("result size: " + result.size());
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (result.size() == 0) {
                                canGoNext = true;
                            } else {
                                canGoNext = false;
                                inUse.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                } catch (Exception e) {
                    //createAndShowDialog(exception, "Error");
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    private void addUser(String nick) {
        if (mClient == null) {
            return;
        }

        // Create a new user
        final User user = new User(nick);

        // Insert the new item
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mUserTable.insert(user).get();
                    if (!user.getNickname().isEmpty()) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                //mAdapter.add(item);
                                Intent i = new Intent(SetNickname.this, pro.onur.numgame.Menu.class);
                                startActivity(i);
                            }
                        });
                    }
                } catch (Exception e) {
                    //createAndShowDialog(exception, "Error");
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

    }

    /**
     * Creates a dialog and shows it
     *
     * @param exception
     *            The exception to show in the dialog
     * @param title
     *            The dialog title
     */
    private void createAndShowDialog(Exception exception, String title) {
        Throwable ex = exception;
        if(exception.getCause() != null){
            ex = exception.getCause();
        }
        createAndShowDialog(ex.getMessage(), title);
    }

    /**
     * Creates a dialog and shows it
     *
     * @param message
     *            The dialog message
     * @param title
     *            The dialog title
     */
    private void createAndShowDialog(String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);
        builder.setTitle(title);
        builder.create().show();
    }

    private class ProgressFilter implements ServiceFilter {

        @Override
        public ListenableFuture<ServiceFilterResponse> handleRequest(
                ServiceFilterRequest request, NextServiceFilterCallback next) {

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mProgressBar != null) {
                        inUse.setVisibility(View.GONE);
                        mProgressBar.setVisibility(ProgressBar.VISIBLE);
                    }
                }
            });

            SettableFuture<ServiceFilterResponse> result = SettableFuture.create();
            try {
                ServiceFilterResponse response = next.onNext(request).get();
                result.set(response);
            } catch (Exception exc) {
                result.setException(exc);
            }

            dismissProgressBar();
            return result;
        }

        private void dismissProgressBar() {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mProgressBar != null) mProgressBar.setVisibility(ProgressBar.GONE);
                }
            });
        }
    }

}
