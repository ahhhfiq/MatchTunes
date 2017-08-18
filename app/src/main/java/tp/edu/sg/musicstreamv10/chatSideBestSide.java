package tp.edu.sg.musicstreamv10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class chatSideBestSide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_side_best_side);
    }

    public void backButton(View view) {
        Intent intent = new Intent(this, chat.class);
        startActivity(intent);

        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
