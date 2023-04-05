package com.itpearls.agritomarketplace.screen.person;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Person;

@UiController("Person.browse")
@UiDescriptor("person-browse.xml")
@LookupComponent("personsTable")
public class PersonBrowse extends StandardLookup<Person> {
}