package iics.casino.dominicmichael.healthy_foodie_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    Button posttest_game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        posttest_game = (Button)findViewById(R.id.postestbutt_game);

        posttest_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoPos = new Intent(getApplicationContext(), posttest_page.class);
                startActivity(gotoPos);
            }
        });
    }
}
