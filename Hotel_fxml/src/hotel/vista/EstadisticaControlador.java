package hotel.vista;

import java.io.IOException;
import java.time.LocalDate;

import hotel.Miapp;
import hotel.modelo.Reserva;
import hotel.modelo.TipoHabitacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class EstadisticaControlador {
	
	private Miapp miapp;
	@FXML
    private BarChart<String, Integer> barChart;
	@FXML
    //private CategoryAxis xAxis;
	private ObservableList<String> habitaciones=FXCollections.observableArrayList();
	private LocalDate inicio,fin;
	
	@FXML
	private DatePicker fechaInicio=new DatePicker(LocalDate.now());
	@FXML
	private DatePicker fechaFin=new DatePicker(LocalDate.now());
	@FXML
	private PieChart graficoCircular;
	@FXML
	private Label numOcupacion;
	
	public void setMiapp(Miapp miapp) {
		this.miapp=miapp;
	}
	
	@FXML
    private void initialize(){
		this.inicio=LocalDate.now();
		this.fin=LocalDate.now().plusWeeks(2);
		
		
		String[] habitaciones={"Doble Individual", "Doble", "Junior Suite", "Suite"};
		this.habitaciones.addAll(habitaciones);
		//xAxis.setCategories(this.habitaciones);
		
		
	}
	/*
	 * 
	 * 
	 * Hay que comprobar este metodo
	 * 
	 * 
	 * */
	@FXML
	public void ocupacion(){
		
		
		
//		for(int i=0;i<barChart.getData().size();i++){
//			barChart.getData().remove(i);
//		}
		if(fechaInicio.getValue() !=null || fechaFin.getValue() !=null){
			inicio=fechaInicio.getValue();
			fin=fechaFin.getValue();
			System.out.println("Coge valores DatePiker");
			//System.out.println("Inicio: "+fechaInicio.getValue()+"\nFin: "+fechaFin.getValue());
		}
		
		int[] contadorHabitaciones={0,0,0,0};
		System.out.println("Numero de reservas analizadas: "+miapp.getDatosReserva().size());
		for(Reserva r: this.miapp.getDatosReserva()){
			//System.out.println(r.getLlegada().isAfter(inicio));
			//System.out.println(r.getSalida().isBefore(fin));
			if(r.getLlegada().isAfter(inicio) && r.getSalida().isBefore(fin)){
				
				
				
				
				TipoHabitacion habitacion=r.getTipoHabitacion();
				//System.out.println("Reserva encontrada: "+r.getDni()+"\nLlegada: "+r.getLlegada()+"\nSalida: "+r.getSalida());
				switch (habitacion){
					case DOBLE_INDIVIDUAL:
						contadorHabitaciones[0]+=1;
						break;
					case DOBLE:
						contadorHabitaciones[1]+=1;
						break;
					case JUNIOR_SUITE:
						contadorHabitaciones[2]+=1;
						break;
					case SUITE:
						contadorHabitaciones[3]+=1;
						break;
				}
//				for(int j=0;j<contadorHabitaciones.length;j++){
//					System.out.println(contadorHabitaciones[j]);
//				}
			}
			
		}
//		XYChart.Series<String, Integer> series = new XYChart.Series<>();
//		for(int i=0;i<contadorHabitaciones.length;i++){
//			series.getData().add(new XYChart.Data<>(this.habitaciones.get(i),contadorHabitaciones[i]));
//		}
		
		///////////////////////////////////////////////////////////////////
		ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Doble", contadorHabitaciones[1]),
                new PieChart.Data("Doble individual", contadorHabitaciones[0]),
                new PieChart.Data("Suite", contadorHabitaciones[2]),
                new PieChart.Data("Junior Suite", contadorHabitaciones[3]));
        graficoCircular.setData(pieChartData);
        
        numOcupacion.setText("Doble: "+contadorHabitaciones[1]+
        					"\nDoble individual: "+contadorHabitaciones[0]+
        					"\nSuite: "+contadorHabitaciones[2]+
        					"\nJunior Suite: "+contadorHabitaciones[3]);
		//Solucionar Esto
		
		//barChart.getData().add(series);
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
