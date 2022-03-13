package view;

import java.text.DecimalFormat;
import controller.*;
import eduni.distributions.ParameterException;
import framework.Trace;
import framework.Trace.Level;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.*;

/**
 * SimulaattorinGUI-luokka toimii kayttoliittymana. Kayttajan syottaman tiedot
 * siirtyvat kontrollerille.
 * 
 * @author Svyatoslav Beletskiy
 * @author Laura Immonen
 *
 */
public class SimulaattorinGUI extends Application implements ISimulaattorinUI {

	private IKontrolleriVtoM kontrolleri;

	private TextField aika;
	private TextField viive;
	private TextField lkm;
	private TextField mista;
	private TextField mihin;

	private Label aikaLabel;
	private Label viiveLabel;
	private Label tyontekijaLabel;
	private Label palveluPisteLabel;
	private Label jakauma;
	private Label tulokset;
	private Label tulos1;
	private Label tulos1Label;
	private Label tulos2;
	private Label tulos2Label;
	private Label tulos3;
	private Label tulos3Label;
	private Label tulos4;
	private Label tulos4Label;

	private Button kaynnistaButton;
	private Button hidastaButton;
	private Button nopeutaButton;
	private Button ohjeet;

	private IVisualisointi naytto;
	private ComboBox<String> palveluPisteJakauma;

	/**
	 * Maaritetaan kontrolleri.
	 */
	@Override
	public void init() {
		Trace.setTraceLevel(Level.INFO);

		kontrolleri = new Kontrolleri(this);
	}

	/**
	 * Kayttoliittyman rakentamista.
	 */
	@Override
	public void start(Stage primaryStage) {
		// Käyttöliittymän rakentaminen
		try {

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					Platform.exit();
					System.exit(0);
				}
			});

			primaryStage.setTitle("Simulaattori");

			kaynnistaButton = new Button();
			kaynnistaButton.setText("KÄYNNISTÄ SIMULOINTI");
			kaynnistaButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						if (Integer.parseInt(mista.getText()) < 0 || Integer.parseInt(mihin.getText()) < 0) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Arvot puuttuvat");
							alert.setHeaderText("Jakauman arvot puuttuvat!");
							alert.setContentText("Kentissä täytyy olla positiiviset arvot.");
							alert.showAndWait();
						} else {

							kontrolleri.jakaumaArvot();
							kontrolleri.kaynnistaSimulointi();
							kaynnistaButton.setDisable(true);
						}
					} catch (ParameterException e) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Arvot puuttuvat");
						alert.setHeaderText("Arvot puuttuvat!");
						alert.setContentText("Kentissä täytyy olla positiiviset arvot.");
						alert.showAndWait();
					} catch (NumberFormatException e) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Arvot puuttuvat");
						alert.setHeaderText("Arvot puuttuvat!");
						alert.setContentText("Kentissä täytyy olla positiiviset arvot.");
						alert.showAndWait();
					}

				}
			});
			
			Alert kayttoohje = new Alert(AlertType.INFORMATION);
			kayttoohje.setTitle("SIMULAATTORIN KÄYTTÖOHJE");
			kayttoohje.setHeaderText(null);
			kayttoohje.getDialogPane().setPrefSize(500, 270);
			kayttoohje.setContentText("1. SYÖTÄ HALUTTU SIMULOINTIAIKA.\n"
					+ "\n2. SYÖTÄ HALUTTU VIIVE SIMULAATTORISSA.\n"
					+ "\n3. SYÖTÄ TYÖNTEKIJÖIDEN MÄÄRÄ VASTAANOTOLLA.\n"
					+ "\n4. VALITSE PALVELUPISTE, JOSSA JAKAUMA-ARVOT ASETETAAN.\n"
					+ "\n5. SYÖTÄ KAKSI JAKAUMA-ARVOA (SIMULAATTORI NOUDATTAA NORMAALIJAKAUMAA).\n"
					+ "\n6. KÄYNNISTÄ SIMULAATTORI.");
			
			ohjeet = new Button();
			ohjeet.setText("SIMULATTORIN KÄYTTÖOHJE");
			ohjeet.setAlignment(Pos.BOTTOM_LEFT);
			ohjeet.setOnAction(e -> kayttoohje.showAndWait());


			hidastaButton = new Button();
			hidastaButton.setText("HIDASTA");
			hidastaButton.setOnAction(e -> kontrolleri.hidasta());

			nopeutaButton = new Button();
			nopeutaButton.setText("NOPEUTA");
			nopeutaButton.setOnAction(e -> kontrolleri.nopeuta());

			aikaLabel = new Label("Simulointiaika:");
			aikaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			aika = new TextField();
			aika.setPromptText("Syötä aika");
			aika.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			aika.setPrefWidth(100);

			viiveLabel = new Label("Viive:");
			viiveLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			viive = new TextField();
			viive.setPromptText("Syötä viive");
			viive.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			viive.setPrefWidth(100);

			tyontekijaLabel = new Label("Työntekijöiden lkm:");
			tyontekijaLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			lkm = new TextField();
			lkm.setPromptText("Syötä työntekijöiden lkm (1-8)");
			lkm.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			lkm.setPrefWidth(100);

			palveluPisteLabel = new Label("Palvelupiste:");
			palveluPisteLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

			palveluPisteJakauma = new ComboBox<String>();
			palveluPisteJakauma.getItems().addAll("Vastaanotto", "Työntekijät", "Hyllytys", "Kerays", "Postitus");

			palveluPisteJakauma.setPrefWidth(400);
			palveluPisteJakauma.setPromptText("Valitse jokin palvelupiste");

			jakauma = new Label("Jakauma:");
			jakauma.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			mista = new TextField();
			mista.setPromptText("Mistä");
			mista.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			mista.setPrefWidth(100);
			mihin = new TextField();
			mihin.setPromptText("Mihin");
			mihin.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			mihin.setPrefWidth(100);

			tulokset = new Label("=== TULOKSET ===");
			tulokset.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			
			tulos1Label = new Label("Kokonaisaika:");
			tulos1Label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			tulos1 = new Label();
			tulos1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			tulos1.setPrefWidth(100);
			
			tulos2Label = new Label("Pakettien määrä (saapuneet):");
			tulos2Label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			tulos2 = new Label();
			tulos2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			tulos2.setPrefWidth(100);
			
			tulos3Label = new Label("Pakettien määrä (lähteneet):");
			tulos3Label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			tulos3 = new Label();
			tulos3.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			tulos3.setPrefWidth(100);
			
			tulos4Label = new Label("Paketit viipyi (keskimäärin):");
			tulos4Label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			tulos4 = new Label();
			tulos4.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			tulos4.setPrefWidth(100);

			HBox hBox = new HBox();
			hBox.setPadding(new Insets(12, 12, 12, 12)); // marginaalit ylä, oikea, ala, vasen
			hBox.setSpacing(10); // noodien välimatka 10 pikseliä

			GridPane grid = new GridPane();
			grid.getColumnConstraints().add(new ColumnConstraints(300));
			grid.getColumnConstraints().add(new ColumnConstraints(200));
			grid.getColumnConstraints().add(new ColumnConstraints(200));
			grid.setAlignment(Pos.CENTER);
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setVgap(15);
			grid.setHgap(5);

			grid.add(aikaLabel, 0, 0, 1, 1); // sarake, rivi, sarakkeen koko, rivin koko
			grid.add(aika, 1, 0, 2, 1);
			grid.add(viiveLabel, 0, 1, 1, 1);
			grid.add(viive, 1, 1, 2, 1);
			grid.add(tyontekijaLabel, 0, 2, 1, 1);
			grid.add(lkm, 1, 2, 2, 1);
			grid.add(palveluPisteLabel, 0, 3, 1, 1);
			grid.add(palveluPisteJakauma, 1, 3, 2, 1);
			grid.add(jakauma, 0, 4, 1, 1);
			grid.add(mista, 1, 4, 1, 1);
			grid.add(mihin, 2, 4, 1, 1);
			grid.add(kaynnistaButton, 0, 6, 1, 1);
			grid.add(nopeutaButton, 1, 6, 1, 1);
			grid.add(hidastaButton, 2, 6, 1, 1);
			grid.add(tulokset, 0, 9, 1, 1);
			grid.add(tulos1Label, 0, 10, 1, 1);
			grid.add(tulos1, 1, 10, 1, 1);
			grid.add(tulos2Label, 0, 11, 1, 1);
			grid.add(tulos2, 1, 11, 1, 1);
			grid.add(tulos3Label, 0, 12, 1, 1);
			grid.add(tulos3, 1, 12, 1, 1);
			grid.add(tulos4Label, 0, 13, 1, 1);
			grid.add(tulos4, 1, 13, 1, 1);
			grid.add(ohjeet, 0, 17, 1, 1);

			naytto = new Visualisointi(620, 620);

			// Täytetään boxi:
			hBox.getChildren().addAll(grid, (Canvas) naytto);

			Scene scene = new Scene(hBox);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rajapinnasta kaytettava metodi, joka hakee kayttajan syottaman ajan.
	 */
	@Override
	public double getAika() {
		return Double.parseDouble(aika.getText());
	}

	/**
	 * Rajapinnasta kaytettava metodi, joka hakee kayttajan syottaman viiveen.
	 */
	@Override
	public long getViive() {
		return Long.parseLong(viive.getText());
	}

	/**
	 * Rajapinnasta kaytettava metodi, joka asettaa simuloinnin lopetus ajan.
	 */
	@Override
	public void setLoppuaika(double aika1) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos1.setText(formatter.format(aika1));
	}
	
	@Override
	public void setPakettienMaaraSaapuneet(int kpl1) {
		this.tulos2.setText(String.format("%d", kpl1));
	}
	
	@Override
	public void setPakettienMaaraLahteneet(int kpl2) {
		this.tulos3.setText(String.format("%d", kpl2));
	}
	
	@Override
	public void setPakettienAikaViipyi(double aika2) {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		this.tulos4.setText(formatter.format(aika2));
	}


	/**
	 * Rajapinnasta kaytettava metodi, joka toteuttaa visualisoinnin.
	 */
	@Override
	public IVisualisointi getVisualisointi() {
		return naytto;
	}

	/**
	 * Käynnistetään
	 * @param args args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Rajapinnasta kaytettava metodi, joka hakee kayttajan syottaman
	 * tyontekijamaaran.
	 */
	@Override
	public int getTyontekijoidenLKM() {
		return Integer.parseInt(lkm.getText());
	}

	/**
	 * Rajapinnasta kaytettava metodi, joka hakee kayttajan syottaman jakauma-arvon
	 * 1.
	 */
	@Override
	public int getJakaumaArvo1() {
		return Integer.parseInt(mista.getText());
	}

	/**
	 * Rajapinnasta kaytettava metodi, joka hakee kayttajan syottaman jakauma-arvon
	 * 2.
	 */
	@Override
	public int getJakaumaArvo2() {
		return Integer.parseInt(mihin.getText());
	}

	/**
	 * Rajapinnasta kaytettava metodi, joka hakee kayttajan valitseman
	 * palvelupisteen.
	 */
	@Override
	public int getPalvelupiste() {
		int p = 0;
		if (palveluPisteJakauma != null) {
			return palveluPisteJakauma.getSelectionModel().getSelectedIndex();
		} else {
			return 0;
		}

	}

}
