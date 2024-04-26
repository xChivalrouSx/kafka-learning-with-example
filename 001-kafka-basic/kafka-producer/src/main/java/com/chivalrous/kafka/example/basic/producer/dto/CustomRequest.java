package com.chivalrous.kafka.example.basic.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomRequest {

	private int id;
	private String message;

}
