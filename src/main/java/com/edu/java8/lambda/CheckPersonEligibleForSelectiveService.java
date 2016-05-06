package com.edu.java8.lambda;

public class CheckPersonEligibleForSelectiveService implements CheckPerson {

	public boolean test(Person p) {
		return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
	}
}