package com.dk.digitalstore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class FiltersResponse {

    Set<String> filters;

}
