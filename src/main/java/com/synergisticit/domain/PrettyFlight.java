package com.synergisticit.domain;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class PrettyFlight {

	private String eta;
	private String depart;
	private String arrive;
	private String date;
}
