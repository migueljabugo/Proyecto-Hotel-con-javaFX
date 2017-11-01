package hotel.vista;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import hotel.Miapp;
import hotel.modelo.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;

public class OcupacionPorMesControlador {
	private Miapp miapp;
	@FXML
	private Label etiquetaAnno;
	private int anno;
	
	@FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    private ObservableList<String> nombreMes = FXCollections.observableArrayList();

	@FXML
	private ProgressIndicator doble;
	@FXML
	private ProgressIndicator dobleIndividual;
	@FXML
	private ProgressIndicator juniorSuite;
	@FXML
	private ProgressIndicator suite;
	
	
	public void setMiapp(Miapp miapp) {
		this.miapp=miapp;
	}
	
	@FXML
    private void initialize(){
		anno=LocalDate.now().getYear();
		this.etiquetaAnno.setText(anno+"");
		
		String[] meses = {"Ene.","Feb.","Mar.","Abr.","May.","Jun.","Jul.","Ago.","Sep.","Oct.","Nov.","Dic."};
        nombreMes.addAll(Arrays.asList(meses));
        xAxis.setCategories(this.nombreMes);
	}
	
	public void calcularProgressIndicators() {
		int cantDoble=0;
		int cantDobleIndividual=0;
		int cantSuite=0;
		int cantJuniorSuite=0;
		float total=0.f;
		
		
		for(Reserva r:this.miapp.getDatosReserva()){
			if(r.getLlegada().getYear()==anno/* || r.getSalida().getYear()==anno*/){
				switch (r.getTipoHabitacion()){
				case DOBLE:
					cantDoble+=1;
					break;
				case DOBLE_INDIVIDUAL:
					cantDobleIndividual+=1;
					break;
				case SUITE:
					cantSuite+=1;
					break;
				case JUNIOR_SUITE:
					cantJuniorSuite+=1;
					break;
				}
			}
		}
		total=cantDoble+cantDobleIndividual+cantSuite+cantJuniorSuite;
		
		
		this.doble.setProgress((float)cantDoble/total);
		this.dobleIndividual.setProgress((float)cantDobleIndividual/total);
		this.juniorSuite.setProgress((float)cantSuite/total);
		this.suite.setProgress((float)cantJuniorSuite/total);
		
		
		
		
		
		
		
		
		
	}

	public void grafica(){
		for(int i=0;i<barChart.getData().size();i++){
			barChart.getData().remove(i);
		}
		int[] monthCounter = new int[12];
        for (Reserva p : miapp.getDatosReserva()) {
        	if(p.getLlegada().getYear()==anno){
        		int month =p.getLlegada().getMonthValue()-1;
        		monthCounter[month]++;
        	}
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(nombreMes.get(i), monthCounter[i]));
        }
        
        barChart.getData().add(series);
	}
	
	
	@FXML
	private void sumarAnno(){
		anno+=1;
		this.etiquetaAnno.setText(anno+"");
		System.out.println("Año: "+anno);
		grafica();
		calcularProgressIndicators();
	}
	
	@FXML
	private void restarAnno(){
		anno-=1;
		this.etiquetaAnno.setText(anno+"");
		System.out.println("Año: "+anno);
		grafica();
		calcularProgressIndicators();
	}
	
	
	@FXML
	public void pulsarVolverInicio(){
		try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Miapp.class.getResource("vista/inicio.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            miapp.getRootLayout().setCenter(personOverview);
            InicioControlador controlador=loader.getController();
            controlador.setMiapp(this.miapp);
           
            

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	

}
