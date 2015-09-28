package org.pablo.filterSortMapExamples.functions;

import org.pablo.filterSortMapExamples.dto.PersonDTO;
import org.pablo.filterSortMapExamples.model.Person;

public class PersonToPersonDtoConverter implements SuperConverter<Person, PersonDTO> {
	@Override
	public PersonDTO apply(Person person) {
		return new PersonDTO(person.getId(), person.getAge(), person.getGender(), person.getFirstName(), person.getLastName());
	}
}
