package Training.Streams;

public enum Grade {

	TWO(2),
	THREE(3),
	THREE_PLUS(3.5),
	FOUR(4),
	FOUR_PLUS(4.5),
	FIVE(5);

	final double value;
	Grade(double value) {
		this.value = value;
	}
}