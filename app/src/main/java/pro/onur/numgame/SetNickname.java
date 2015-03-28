package pro.onur.numgame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import pro.onur.myviews.MyButton;
import pro.onur.myviews.MyEditText;
import pro.onur.myviews.MyTextView;


public class SetNickname extends ActionBarActivity {

    private boolean canGoNext = false;
    private Animation shakeAnim;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_nickname);

        shakeAnim = AnimationUtils.loadAnimation(this,R.anim.shake);

        MyTextView inUse = (MyTextView) findViewById(R.id.text_InUse);
        inUse.setVisibility(View.INVISIBLE);

        final MyTextView setNick = (MyTextView) findViewById(R.id.text_SetNickname);

        MyEditText editNick = (MyEditText) findViewById(R.id.editText_setNickname);
        editNick.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                canGoNext = checkNicknameAvailablity(s.toString());
            }
        });

        MyButton nextButton_SN = (MyButton) findViewById(R.id.button_sN_Next);
        nextButton_SN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canGoNext) {
                    user = new User(setNick.getText().toString());
                    addUser(user);
                    Intent i = new Intent(SetNickname.this, pro.onur.numgame.Menu.class);
                    startActivity(i);
                } else {
                    setNick.startAnimation(shakeAnim);
                }
            }
        });

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

    private boolean checkNicknameAvailablity(String nick) {

        return false;
    }

    private void addUser(User user) {

    }

}
