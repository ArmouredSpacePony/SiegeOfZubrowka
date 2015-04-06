package nl.voorbeeld.SoZ;

import nl.saxion.act.playground.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;


//Poging tot het veranderen van de menu titel in het 1e scherm. 
public class Titlebar extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final boolean customTitleSupported = 
                requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.main);
        if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.menuscherm);
        }
        final TextView myTitleText = (TextView) findViewById(R.id.view);
        if (myTitleText != null) {
            myTitleText.setText("SiegeOfZubrowka");
        }
    }
}