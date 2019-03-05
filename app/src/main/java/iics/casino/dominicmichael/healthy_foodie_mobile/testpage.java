package iics.casino.dominicmichael.healthy_foodie_mobile;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class testpage extends AppCompatActivity {

    private boolean isFABOpen = false;
    FloatingActionButton fab1, fab2, fab3, fab4, fab5;
    Button preee, pooost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testpage);

        preee = (Button) findViewById(R.id.pretestbutt);
        pooost = (Button) findViewById(R.id.postestbutt);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        fab5 = (FloatingActionButton) findViewById(R.id.fab5);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToHomePage = new Intent(getApplicationContext(), homepage.class);
                startActivity(goToHomePage);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTestPage = new Intent(getApplicationContext(), testpage.class);
                startActivity(goToTestPage);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAb = new Intent(getApplicationContext(), about_uspage.class);
                startActivity(goToAb);
            }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoAccount = new Intent(getApplicationContext(), user_accountpage.class);
                startActivity(gotoAccount);
            }
        });
        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logOut = new Intent(getApplicationContext(), loginpage.class);
                startActivity(logOut);
            }
        });

        preee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoPre = new Intent(getApplicationContext(), pretest_page.class);
                startActivity(gotoPre);
            }
        });
        pooost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoPos = new Intent(getApplicationContext(), posttest_page.class);
                startActivity(gotoPos);
            }
        });
    }
    private void showFABMenu(){
        isFABOpen=true;
        fab5.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab4.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.fab4_dimen));
        fab1.animate().translationY(-getResources().getDimension(R.dimen.fab5_dimen));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
        fab3.animate().translationY(0);
        fab4.animate().translationY(0);
        fab5.animate().translationY(0);
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
            Intent logOut = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(logOut);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
