package com.chivalrous.kafka.example.service;

import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import com.chivalrous.kafka.example.dto.CustomDto;

@Component
public class StreamsService {

	@Value("${com.chivalrous.kafka.streams-topic}")
	private String kafkaStreamsTopic;

	@Value("${com.chivalrous.kafka.result-topic-not-a-number}")
	private String kafkaStreamsResultNotNumberTopic;

	@Value("${com.chivalrous.kafka.result-topic-positive-number}")
	private String kafkaStreamsResultPositiveTopic;

	@Value("${com.chivalrous.kafka.result-topic-negative-number}")
	private String kafkaStreamsResultNegativeTopic;

	@Autowired
	void kafkaStreamTopology(StreamsBuilder streamsBuilder) {
		JsonSerde<CustomDto> customDtoJsonSerde = new JsonSerde<>(CustomDto.class);

		Map<String, KStream<String, CustomDto>> numericOrNotBranches = streamsBuilder
				.stream(kafkaStreamsTopic, Consumed.with(Serdes.String(), customDtoJsonSerde))
				.split(Named.as("branch-"))
				.branch((key, value) -> NumberUtils.isParsable(value.getMagicalMessage()), Branched.as("numeric"))
				.defaultBranch(Branched.as("nonnumeric"));
		numericOrNotBranches.get("branch-nonnumeric").to(kafkaStreamsResultNotNumberTopic);

		Map<String, KStream<String, CustomDto>> positiveOrNotBranches = numericOrNotBranches.get("branch-numeric").split(Named.as("number-"))
				.branch((key, value) -> Integer.parseInt(value.getMagicalMessage()) > 0, Branched.as("positive"))
				.defaultBranch(Branched.as("negative"));

		positiveOrNotBranches.get("number-positive").to(kafkaStreamsResultPositiveTopic);
		positiveOrNotBranches.get("number-negative").to(kafkaStreamsResultNegativeTopic);
	}

}
