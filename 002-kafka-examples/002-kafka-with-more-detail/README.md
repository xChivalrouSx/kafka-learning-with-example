#  Kafka With More Detail
For learning kafka with more detail

---

## Working Steps

#### 1. Run zookeeper

Windows:
```shell
bin/windows/zookeeper-server-start.bat config/zookeeper.properties
```

Linux:
```shell
bin/zookeeper-server-start.sh config/zookeeper.properties
```

---

#### 2. Run kafka

Windows:
```shell
bin/windows/kafka-server-start.bat config/server.properties
```

Linux:
```shell
bin/kafka-server-start.sh config/server.properties
```

---

#### 3. Add topics

Windows: 
```shell
bin/windows/kafka-topics.bat --create --topic kafka-demo --bootstrap-server localhost:9092
```


Linux:
```shell
bin/kafka-topics.sh --create --topic kafka-demo --bootstrap-server localhost:9092
```

---

#### 4. Run kafka consumer

Windows:
```shell
bin/windows/kafka-console-consumer.bat --topic kafka-demo --from-beginning --bootstrap-server localhost:9092
```

Linux:
```shell
bin/kafka-console-consumer.sh --topic kafka-demo --from-beginning --bootstrap-server localhost:9092
```

---

#### 5. Run the java application in this path