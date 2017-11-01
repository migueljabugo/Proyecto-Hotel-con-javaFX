package hotel.vista;


import hotel.Miapp;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;

public class HabitacionesControlador {

	private Miapp miapp;
	@FXML
	private ImageView imagen1;
	@FXML
	private ImageView imagen2;
	@FXML
	private Label nombreHabitacion;
	@FXML
	private Label descripcion;
	@FXML
	private WebView webview;
	
	private Image[] arrayImagenes;
	private String[] arrayDescripcion;
	private String[] arrayTitulo={"Doble","Doble individual", "Junior suite", "Suite"};
	private int cache;
	private int cacheImagen;
	
	
	public void setMiapp(Miapp miapp) {
		this.miapp=miapp;
	}
	
	@FXML
    private void initialize(){
		inicializarImagenes();
		inicializarDescripciones();
		cache=0;
		cargarHabitacion(cache);
		
		/*
        Image image = new Image(this.getClass().getResource("../../imagenes/doble.jpg").toExternalForm(),false);
        imagen1.setImage(image);
        descripcion.setText("Descripcion de la habitacion");
        nombreHabitacion.setText("Doble");
        */
	}


	private void cargarHabitacion(int i) {
		imagen1.setImage(arrayImagenes[cacheImagen++]);
        imagen2.setImage(arrayImagenes[cacheImagen--]);
        imagen2.setFitWidth(242);
        imagen2.setFitHeight(217);
        imagen1.setFitWidth(289);
        imagen1.setFitHeight(198);
        descripcion.setText(arrayDescripcion[i]);
        nombreHabitacion.setText(arrayTitulo[i]);
        nombreHabitacion.setAlignment(Pos.CENTER);
        cargarVideo();
	}

	private void cargarVideo() {
	    webview.getEngine().load("https://www.youtube.com/embed/C_djYRqCEQo");
	}

	private void inicializarDescripciones() {
		arrayDescripcion=new String[4];
		for(int i=0;i<4;i++){
			if(i==0 || i==2)
				arrayDescripcion[i]="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
			else
			arrayDescripcion[i]="Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
		}
		
	}

	private void inicializarImagenes() {
		Image imagen1=new Image(this.getClass().getResource("../../imagenes/doble.jpg").toExternalForm(),false);
		Image imagen2=new Image(this.getClass().getResource("../../imagenes/doble2.jpg").toExternalForm(),false);
		Image imagen3=new Image(this.getClass().getResource("../../imagenes/doble-individual.jpg").toExternalForm(),false);
		Image imagen4=new Image(this.getClass().getResource("../../imagenes/doble-individual2.jpg").toExternalForm(),false);
		Image imagen5=new Image(this.getClass().getResource("../../imagenes/junior-suite.jpg").toExternalForm(),false);
		Image imagen6=new Image(this.getClass().getResource("../../imagenes/junior-Suite2.jpg").toExternalForm(),false);
		Image imagen7=new Image(this.getClass().getResource("../../imagenes/suite.jpg").toExternalForm(),false);
		Image imagen8=new Image(this.getClass().getResource("../../imagenes/suite2.jpg").toExternalForm(),false);
		arrayImagenes=new Image[8];
		arrayImagenes[0]=imagen1;
		arrayImagenes[1]=imagen2;
		arrayImagenes[2]=imagen3;
		arrayImagenes[3]=imagen4;
		arrayImagenes[4]=imagen5;
		arrayImagenes[5]=imagen6;
		arrayImagenes[6]=imagen7;
		arrayImagenes[7]=imagen8;
		
	}
	
	@FXML
	public void sumarCache(){
		if(cache==3){
			cache=0;
			cacheImagen=0;
		}
		else{
			this.cache++;
			this.cacheImagen+=2;
		}
		cargarHabitacion(cache);
	}
	@FXML
	public void restarCache(){
		if(cache==0){
			cache=3;
			cacheImagen=6;
		}
		else{
			this.cache--;
			this.cacheImagen-=2;
		}
		cargarHabitacion(cache);
	}

	public Miapp getMiapp() {
		return miapp;
	}
	

}
