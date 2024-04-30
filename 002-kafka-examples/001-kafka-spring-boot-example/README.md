#  Kafka Spring Boot Example
For learning kafka for Spring Boot

---

## Working Steps

#### 1. Run the java application in this path

---

#### 2. Run zookeeper

Windows:
```shell
bin/windows/zookeeper-server-start.bat config/zookeeper.properties
```

Linux:
```shell
bin/zookeeper-server-start.sh config/zookeeper.properties
```

---

#### 3. Run kafka

Windows:
```shell
bin/windows/kafka-server-start.bat config/server.properties
```

Linux:
```shell
bin/kafka-server-start.sh config/server.properties
```

---

#### 4. Add topics

Windows: 
```shell
bin/windows/kafka-topics.bat --create --topic not-a-number --bootstrap-server localhost:9092
```
```shell
bin/windows/kafka-topics.bat --create --topic negative-number --bootstrap-server localhost:9092
```
```shell
bin/windows/kafka-topics.bat --create --topic positive-number --bootstrap-server localhost:9092
```

Linux:
```shell
bin/kafka-topics.sh --create --topic not-a-number --bootstrap-server localhost:9092
```
```shell
bin/kafka-topics.sh --create --topic negative-number --bootstrap-server localhost:9092
```
```shell
bin/kafka-topics.sh --create --topic positive-number --bootstrap-server localhost:9092
```
