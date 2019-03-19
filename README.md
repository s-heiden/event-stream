## event-stream

(c) 2019-03-19 Stefan Heiden

#### Setup for Demo:


##### Start Kafka Infrastructure
###### Terminal
```
cd ~/Dropbox/Technikum/SWF/Assignments/2_Integration/kafka_2.11-2.1.0/
./bin/kafka-server-stop.sh
./bin/zookeeper-server-stop.sh
./bin/zookeeper-server-start.sh ./config/zookeeper.properties &

./bin/kafka-server-start.sh ./config/server.properties &

./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic customer
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic transaction &

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic transaction
```

###### IntelliJ IDEA
- open IntelliJ project `event-stream`
- start run-configurations: `banking-core`, `customer-service`, `transaction-service`
- open [customer](http://localhost:8080/customer) and [transaction](localhost:8081/transaction)

