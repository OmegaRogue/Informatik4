package omegaRogue;

import javafx.geometry.Point2D;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class VelChart extends LineChart<Number, Number> {

	public static VelChart velChart = create();

	public VelChart(Axis<Number> numberAxis, Axis<Number> numberAxis2) {
		super(numberAxis, numberAxis2);

		XYChart.Data zero = new XYChart.Data(0, 0);
		XYChart.Data velocity = new XYChart.Data(1, 1);
		XYChart.Series series = new XYChart.Series();
		series.getData().add(zero);
		series.getData().add(velocity);
		getData().add(series);
		autosize();
		setAnimated(false);
	}

	public static void Update(Point2D velocity) {

		XYChart.Series series = new XYChart.Series();
		series.getData().add(new XYChart.Data(0, 0));
		series.getData().add(new XYChart.Data(velocity.getX(), velocity.getY()));
		velChart.getData().clear();
		velChart.getData().add(series);

	}

	public static VelChart create() {
		final NumberAxis xAxis = new NumberAxis("X", -10, 10, 1);
		final NumberAxis yAxis = new NumberAxis("Y", -10, 10, 1);
		return new VelChart(xAxis, yAxis);
	}
}
