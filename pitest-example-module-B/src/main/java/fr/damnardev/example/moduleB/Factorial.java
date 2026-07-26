package fr.damnardev.example.moduleB;

public final class Factorial {

	private Factorial() {
		throw new IllegalStateException("Utility class - no instances allowed");
	}

	public static int calculate(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Factorial is not defined for negative numbers");
		}
		if (n > 12) {
			throw new IllegalArgumentException("Factorial is not supported for numbers greater than 12");
		}
		int result = 1;
		for (int i = 2; i <= n; i++) {
			result *= i;
		}
		return result;
	}

}
