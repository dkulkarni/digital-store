package com.dk.digitalstore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Data
@NoArgsConstructor
public class FiltersResponse {

    Set<String> filters = newHashSet();

}
