package tp.edu.sg.musicstreamv10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class notif extends AppCompatActivity {

    private Switch AllSwitch;
    private Switch switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);


        switchButton = (Switch) findViewById(R.id.switchButton);
        AllSwitch    = (Switch) findViewById(R.id.switch1);


        switchButton.setChecked(false);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    System.out.println("On");
                } else {
                    System.out.println("Off");
                }

            }
        });

        masterSwitch();

    }

    private void masterSwitch(){
        AllSwitch.setChecked(false);

        AllSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    switchButton.setChecked(false);
                } else {
                    System.out.println("Off");
                }

            }
        });

    }

}
