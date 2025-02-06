#  Kafka Spring Boot Retry Example
For learning kafka Retry for Spring Boot

---

## Working Steps

---

#### 1. Run docker compose in this path

```shell
docker-compose up
```

---

#### 2. Add topics

Windows: 
```shell
bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --topic test-topic
```
```shell
bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --topic test-topic.custom-retry-0
```
```shell
bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --topic test-topic.dlt
```

Linux:
```shell
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --topic test-topic
```
```shell
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --topic test-topic.custom-retry-0
```
```shell
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --topic test-topic.dlt
```
---

#### 3. Run the java application in this path

---