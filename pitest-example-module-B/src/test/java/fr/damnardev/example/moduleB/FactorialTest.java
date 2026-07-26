package fr.damnardev.example.moduleB;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FactorialTest {

	@Test
	@DisplayName("constructor should be private")
	void constructor_shouldBePrivate() throws Exception {
		Constructor<Factorial> ctor = Factorial.class.getDeclaredConstructor();
		assertThat(Modifier.isPrivate(ctor.getModifiers())).isTrue();
	}

	@Test
	@DisplayName("constructor should throw IllegalStateException when instantiated")
	void constructor_shouldThrowIllegalStateException_whenInstantiated() throws Exception {
		Constructor<Factorial> ctor = Factorial.class.getDeclaredConstructor();
		ctor.setAccessible(true);

		assertThatThrownBy(ctor::newInstance)
				.cause()
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Utility class - no instances allowed");
	}

	@Test
	@DisplayName("calculate should return correct factorial for valid inputs")
	void calculate_shouldReturnFactorial_whenValidInputProvided() {
		// Given
		int input = 2;
		int expectedResult = 2;

		// When
		int result = Factorial.calculate(input);

		// Then
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	@DisplayName("calculate should throw IllegalArgumentException when input is negative")
	void calculate_shouldThrowIllegalArgumentException_whenInputIsNegative() {
		// Given
		int input = -1;
		String expectedMessage = "Factorial is not defined for negative numbers";

		// When & Then
		assertThatThrownBy(() -> Factorial.calculate(input))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(expectedMessage);
	}

	@Test
	@DisplayName("calculate should throw IllegalArgumentException when input exceeds max limit (12)")
	void calculate_shouldThrowIllegalArgumentException_whenInputExceedsMaxLimit() {
		// Given
		int input = 13;
		String expectedMessage = "Factorial is not supported for numbers greater than 12";

		// When & Then
		assertThatThrownBy(() -> Factorial.calculate(input))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage(expectedMessage);
	}

}
