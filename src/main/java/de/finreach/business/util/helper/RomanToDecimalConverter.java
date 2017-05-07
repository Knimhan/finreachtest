package de.finreach.business.util.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class RomanToDecimalConverter {

	private Map<Character, Integer> map;
	String regex;

	public RomanToDecimalConverter() {
		map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		regex = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
	}

	public int getRomanEquivalentNumeralValue(char ch) {
		if (map.containsKey(ch)) {
			return map.get(ch);
		} else {
			throw new RuntimeException(
					"Roman numeral string contains invalid characters " + ch);
		}
	}

	public int convert(String romanNumeral) {
		romanNumeral = romanNumeral.toUpperCase();
		if (romanNumeral.equals("") || romanNumeral.isEmpty()) {
			throw new RuntimeException(
					"Roman numeral string is either null or empty");
		} else if (!romanNumeral.matches(regex)) {
			throw new RuntimeException(
					"Roman numeral string is not a valid Roman Rule ");
		} else {
			int index = romanNumeral.length() - 1;
			int result = getRomanEquivalentNumeralValue(romanNumeral.charAt(index));

			for (int i = index - 1; i >= 0; i--) {
				if (getRomanEquivalentNumeralValue(romanNumeral.charAt(i)) >= getRomanEquivalentNumeralValue(romanNumeral
						.charAt(i + 1))) {
					result = result
							+ getRomanEquivalentNumeralValue(romanNumeral.charAt(i));
				} else {
					result = result
							- getRomanEquivalentNumeralValue(romanNumeral.charAt(i));
				}
			}

			return result;
		}
	}
}
