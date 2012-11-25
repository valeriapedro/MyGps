package br.ufpr.mygps;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.widget.Button;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class MainActivity extends Activity {
	//atributos da tela - elementos visuais do app
	Button btnGps;
	TextView txtLatitude;
	TextView txtLongitude;

	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        
        //Recupera as informa��es definidas no layout XML
        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);        
        btnGps = (Button) findViewById(R.id.btnGps);
        
        //Registra o listener do bot�o 'Onde Estou'
        btnGps.setOnClickListener(new Button.OnClickListener() {
	        public void onClick(View v){
	        	IniciarServico();
        }});
    }
    
    //Implementa a a��o do bot�o 'Onde Estou', servi�o de GPS
    public void IniciarServico()
    {
    	//Agora come�amos a brincar com o servi�o de GPS do Android, que � invocado atrav�s de um objeto Location Manager, 
    	//que carrega o servi�o de localiza��o do dispositivo
    	LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    	
    	LocationListener locationListener = new LocationListener() {
    	    public void onLocationChanged(Location location) {
    	      Atualizar(location);
    	    }

    	    public void onStatusChanged(String provider, int status, Bundle extras) {}

    	    public void onProviderEnabled(String provider) {}

    	    public void onProviderDisabled(String provider) {}
    	  };

    	  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
    
    //Pega o objeto Location enviado pelo servi�o de GPS e jogar suas informa��es nos TextViews 
    //correspondentes � latitude e longitude
    public void Atualizar(Location location)
    {
    	Double latPoint = location.getLatitude();
    	Double lngPoint = location.getLongitude();
        
        txtLatitude.setText(latPoint.toString());
        txtLongitude.setText(lngPoint.toString());
    }
}

