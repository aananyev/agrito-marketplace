package com.itpearls.agritomarketplace.screen.person;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Person;

@UiController("Person.edit")
@UiDescriptor("person-edit.xml")
@EditedEntityContainer("personDc")
public class PersonEdit extends StandardEditor<Person> {
}