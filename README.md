docker compose up --build

# Consumer Legacy

```
docker logs -f rethinking-kafka-consumption-consumer-legacy-1
docker-compose up -d --scale consumer-legacy=2
```

```
2026-03-16 16:52:00.547 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Consumed key=205 value=205 from my-topic-2 @ offset 65
2026-03-16 16:52:00.548 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Committing polled records...
2026-03-16 16:52:01.555 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Consumed key=206 value=206 from my-topic-0 @ offset 82
2026-03-16 16:52:01.556 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Committing polled records...
2026-03-16 16:52:02.554 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Consumed key=207 value=207 from my-topic-0 @ offset 83
2026-03-16 16:52:02.554 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Committing polled records...
2026-03-16 16:52:03.531 INFO  [main] o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=consumer-legacy, groupId=legacy-consumer-group] Request joining group due to: group is already rebalancing
2026-03-16 16:52:03.533 INFO  [main] o.a.k.c.c.i.ConsumerRebalanceListenerInvoker - [Consumer clientId=consumer-legacy, groupId=legacy-consumer-group] Revoke previously assigned partitions [my-topic-0, my-topic-1, my-topic-2]
2026-03-16 16:52:03.534 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Partitions revoked: [my-topic-0, my-topic-1, my-topic-2]
2026-03-16 16:52:03.535 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Offsets committed before rebalance.
2026-03-16 16:52:03.535 INFO  [main] o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=consumer-legacy, groupId=legacy-consumer-group] (Re-)joining group
2026-03-16 16:52:03.539 INFO  [main] o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=consumer-legacy, groupId=legacy-consumer-group] Successfully joined group with generation Generation{generationId=2, memberId='consumer-legacy-936f0bfd-f1ce-46ab-b896-b39ad77244a0', protocol='range'}
2026-03-16 16:52:03.540 INFO  [main] o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=consumer-legacy, groupId=legacy-consumer-group] Finished assignment for group at generation 2: {consumer-legacy-936f0bfd-f1ce-46ab-b896-b39ad77244a0=Assignment(partitions=[my-topic-2]), consumer-legacy-5485febc-752b-4881-b106-5829a1889014=Assignment(partitions=[my-topic-0, my-topic-1])}
2026-03-16 16:52:03.542 INFO  [main] o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=consumer-legacy, groupId=legacy-consumer-group] Successfully synced group in generation Generation{generationId=2, memberId='consumer-legacy-936f0bfd-f1ce-46ab-b896-b39ad77244a0', protocol='range'}
2026-03-16 16:52:03.542 INFO  [main] o.a.k.c.c.i.ConsumerCoordinator - [Consumer clientId=consumer-legacy, groupId=legacy-consumer-group] Notifying assignor about the new Assignment(partitions=[my-topic-2])
2026-03-16 16:52:03.542 INFO  [main] o.a.k.c.c.i.ConsumerRebalanceListenerInvoker - [Consumer clientId=consumer-legacy, groupId=legacy-consumer-group] Adding newly assigned partitions: [my-topic-2]
2026-03-16 16:52:03.542 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Partitions assigned: [my-topic-2]
2026-03-16 16:52:03.544 INFO  [main] o.a.k.c.c.internals.ConsumerUtils - Setting offset for partition my-topic-2 to the committed offset FetchPosition{offset=66, offsetEpoch=Optional[0], currentLeader=LeaderAndEpoch{leader=Optional[kafka:29092 (id: 1 rack: null isFenced: false)], epoch=0}}
2026-03-16 16:52:07.570 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Consumed key=212 value=212 from my-topic-2 @ offset 66
2026-03-16 16:52:07.570 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Committing polled records...
2026-03-16 16:52:08.573 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Consumed key=213 value=213 from my-topic-2 @ offset 67
2026-03-16 16:52:08.574 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Committing polled records...
2026-03-16 16:52:09.586 INFO  [main] c.r.k.consumer.MyKafkaConsumerLegacy - Consumed key=214 value=214 from my-topic-2 @ offset 68
```

# Consumer Next gen

```
docker logs rethinking-kafka-consumption-consumer-nextgen-1
docker-compose up -d --scale consumer-nextgen=2
docker logs rethinking-kafka-consumption-consumer-nextgen-2
```

```
2026-03-16 16:54:47.466 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=371 value=371 from my-topic-0 @ offset 147
2026-03-16 16:54:47.466 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:54:48.474 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=372 value=372 from my-topic-2 @ offset 116
2026-03-16 16:54:48.475 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:54:49.484 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=373 value=373 from my-topic-0 @ offset 148
2026-03-16 16:54:49.484 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:54:50.495 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=374 value=374 from my-topic-2 @ offset 117
2026-03-16 16:54:50.495 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:54:51.463 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=375 value=375 from my-topic-1 @ offset 108
2026-03-16 16:54:51.463 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:54:51.473 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Member tKQVSQuHR9aBocp13XT9nw with epoch 1 transitioned from STABLE to RECONCILING.
2026-03-16 16:54:51.475 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Reconciling assignment with local epoch 1
	Member:                                    tKQVSQuHR9aBocp13XT9nw
	Assigned partitions:                       [my-topic-0, my-topic-1]
	Current owned partitions:                  [my-topic-0, my-topic-1, my-topic-2]
	Added partitions (assigned - owned):       []
	Revoked partitions (owned - assigned):     [my-topic-2]

2026-03-16 16:54:51.475 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Revoking previously assigned partitions [my-topic-2]
2026-03-16 16:54:51.481 INFO  [main] o.a.k.c.c.i.ConsumerRebalanceListenerInvoker - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Revoke previously assigned partitions [my-topic-2]
2026-03-16 16:54:51.482 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Partitions revoked: [my-topic-2]
2026-03-16 16:54:51.484 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Offsets committed before rebalance.
2026-03-16 16:54:52.494 INFO  [main] o.a.k.c.c.i.ConsumerRebalanceListenerInvoker - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Adding newly assigned partitions: []
2026-03-16 16:54:52.494 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Partitions assigned: []
2026-03-16 16:54:52.495 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Member tKQVSQuHR9aBocp13XT9nw with epoch 1 transitioned from RECONCILING to ACKNOWLEDGING.
2026-03-16 16:54:52.496 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Member tKQVSQuHR9aBocp13XT9nw with epoch 1 transitioned from ACKNOWLEDGING to STABLE.
2026-03-16 16:54:53.505 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=377 value=377 from my-topic-1 @ offset 109
2026-03-16 16:54:53.505 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:54:54.523 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=378 value=378 from my-topic-1 @ offset 110
2026-03-16 16:54:54.523 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:54:55.533 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=379 value=379 from my-topic-1 @ offset 111
2026-03-16 16:54:55.534 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:54:56.547 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=380 value=380 from my-topic-1 @ offset 112
2026-03-16 16:54:56.547 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:54:59.562 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=383 value=383 from my-topic-1 @ offset 113
2026-03-16 16:54:59.562 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:55:00.571 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=384 value=384 from my-topic-1 @ offset 114

```

```
2026-03-16 16:54:46.905 INFO  [main] o.a.k.c.t.i.KafkaMetricsCollector - initializing Kafka metrics collector
2026-03-16 16:54:46.982 INFO  [main] o.a.kafka.common.utils.AppInfoParser - Kafka version: 8.1.1-ce
2026-03-16 16:54:46.983 INFO  [main] o.a.kafka.common.utils.AppInfoParser - Kafka commitId: 1936d6674741736d
2026-03-16 16:54:46.983 INFO  [main] o.a.kafka.common.utils.AppInfoParser - Kafka startTimeMs: 1773680086980
2026-03-16 16:54:46.992 INFO  [main] o.a.k.c.c.i.AsyncKafkaConsumer - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Subscribed to topic(s): my-topic
2026-03-16 16:54:47.027 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Subscribed to topic my-topic with group.id=nextgen-consumer-group using next-gen group protocol (KIP-848).
2026-03-16 16:54:47.162 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Member oCpRdkCgQGWK7Rq-kJ2BVw with epoch 0 transitioned from UNSUBSCRIBED to JOINING.
2026-03-16 16:54:47.202 INFO  [consumer_background_thread] org.apache.kafka.clients.Metadata - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Cluster ID: MkU3OEVBNTcwNTJENDM2Qk
2026-03-16 16:54:47.206 INFO  [consumer_background_thread] o.a.k.c.c.i.CoordinatorRequestManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Discovered group coordinator Coordinator(key='nextgen-consumer-group', nodeId=1, host='kafka', port=29092, errorCode=0, errorMessage='')
2026-03-16 16:54:47.214 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Member oCpRdkCgQGWK7Rq-kJ2BVw with epoch 2 transitioned from JOINING to RECONCILING.
2026-03-16 16:54:47.215 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Reconciling assignment with local epoch 0
	Member:                                    oCpRdkCgQGWK7Rq-kJ2BVw
	Assigned partitions:                       []
	Current owned partitions:                  []
	Added partitions (assigned - owned):       []
	Revoked partitions (owned - assigned):     []

2026-03-16 16:54:47.216 INFO  [main] o.a.k.c.c.i.ConsumerRebalanceListenerInvoker - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Adding newly assigned partitions: []
2026-03-16 16:54:47.217 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Partitions assigned: []
2026-03-16 16:54:47.217 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Member oCpRdkCgQGWK7Rq-kJ2BVw with epoch 2 transitioned from RECONCILING to ACKNOWLEDGING.
2026-03-16 16:54:47.217 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Member oCpRdkCgQGWK7Rq-kJ2BVw with epoch 2 transitioned from ACKNOWLEDGING to STABLE.
2026-03-16 16:54:57.224 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Member oCpRdkCgQGWK7Rq-kJ2BVw with epoch 2 transitioned from STABLE to RECONCILING.
2026-03-16 16:54:57.227 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Reconciling assignment with local epoch 1
	Member:                                    oCpRdkCgQGWK7Rq-kJ2BVw
	Assigned partitions:                       [my-topic-2]
	Current owned partitions:                  []
	Added partitions (assigned - owned):       [my-topic-2]
	Revoked partitions (owned - assigned):     []

2026-03-16 16:54:58.084 INFO  [main] o.a.k.c.c.i.ConsumerRebalanceListenerInvoker - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Adding newly assigned partitions: [my-topic-2]
2026-03-16 16:54:58.084 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Partitions assigned: [my-topic-2]
2026-03-16 16:54:58.085 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Member oCpRdkCgQGWK7Rq-kJ2BVw with epoch 2 transitioned from RECONCILING to ACKNOWLEDGING.
2026-03-16 16:54:58.112 INFO  [consumer_background_thread] o.a.k.c.c.i.ConsumerMembershipManager - [Consumer clientId=consumer-nextgen, groupId=nextgen-consumer-group] Member oCpRdkCgQGWK7Rq-kJ2BVw with epoch 2 transitioned from ACKNOWLEDGING to STABLE.
2026-03-16 16:54:58.117 INFO  [consumer_background_thread] o.a.k.c.c.internals.ConsumerUtils - Setting offset for partition my-topic-2 to the committed offset FetchPosition{offset=118, offsetEpoch=Optional[0], currentLeader=LeaderAndEpoch{leader=Optional[kafka:29092 (id: 1 rack: null isFenced: false)], epoch=0}}
2026-03-16 16:54:58.448 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=376 value=376 from my-topic-2 @ offset 118
2026-03-16 16:54:58.448 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=381 value=381 from my-topic-2 @ offset 119
2026-03-16 16:54:58.448 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=382 value=382 from my-topic-2 @ offset 120
2026-03-16 16:54:58.448 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:55:01.467 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=385 value=385 from my-topic-2 @ offset 121
2026-03-16 16:55:01.467 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:55:02.486 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=386 value=386 from my-topic-2 @ offset 122
2026-03-16 16:55:02.486 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:55:04.514 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=388 value=388 from my-topic-2 @ offset 123
2026-03-16 16:55:04.515 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:55:06.539 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=390 value=390 from my-topic-2 @ offset 124
2026-03-16 16:55:06.539 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:55:09.563 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=393 value=393 from my-topic-2 @ offset 125
2026-03-16 16:55:09.563 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:55:15.609 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=399 value=399 from my-topic-2 @ offset 126
2026-03-16 16:55:15.610 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:55:16.627 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=400 value=400 from my-topic-2 @ offset 127
2026-03-16 16:55:16.628 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
2026-03-16 16:55:17.648 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Consumed key=401 value=401 from my-topic-2 @ offset 128
2026-03-16 16:55:17.649 INFO  [main] c.r.k.c.MyKafkaConsumerNextgen - Committing polled records...
202
```

# Consumer Queue

```
docker logs rethinking-kafka-consumption-consumer-queue-1
docker-compose up -d --scale consumer-queue=5
docker logs rethinking-kafka-consumption-consumer-queue-3
docker logs rethinking-kafka-consumption-consumer-queue-5
```

```
2026-03-16 16:48:36.276 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Reconciling assignment with local epoch 0
	Member:                                    fYsHM9_zTjidvgKO2ThIaQ
	Assigned partitions:                       []
	Current owned partitions:                  []
	Added partitions (assigned - owned):       []
	Revoked partitions (owned - assigned):     []

2026-03-16 16:48:36.280 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member fYsHM9_zTjidvgKO2ThIaQ with epoch 1 transitioned from RECONCILING to ACKNOWLEDGING.
2026-03-16 16:48:36.525 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member fYsHM9_zTjidvgKO2ThIaQ with epoch 1 transitioned from ACKNOWLEDGING to STABLE.
2026-03-16 16:48:41.541 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member fYsHM9_zTjidvgKO2ThIaQ with epoch 2 transitioned from STABLE to RECONCILING.
2026-03-16 16:48:41.544 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Reconciling assignment with local epoch 1
	Member:                                    fYsHM9_zTjidvgKO2ThIaQ
	Assigned partitions:                       [my-topic-0, my-topic-1, my-topic-2]
	Current owned partitions:                  []
	Added partitions (assigned - owned):       [my-topic-0, my-topic-1, my-topic-2]
	Revoked partitions (owned - assigned):     []

2026-03-16 16:48:41.545 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member fYsHM9_zTjidvgKO2ThIaQ with epoch 2 transitioned from RECONCILING to ACKNOWLEDGING.
2026-03-16 16:48:41.548 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member fYsHM9_zTjidvgKO2ThIaQ with epoch 2 transitioned from ACKNOWLEDGING to STABLE.
2026-03-16 16:48:41.917 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=7 value=7 from my-topic-0 @ offset 2 (delivery #1)
2026-03-16 16:48:41.918 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=7 with reject.
2026-03-16 16:48:42.917 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=8 value=8 from my-topic-0 @ offset 3 (delivery #1)
2026-03-16 16:48:42.917 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=8 with release.
2026-03-16 16:48:42.926 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=8 value=8 from my-topic-0 @ offset 3 (delivery #2)
2026-03-16 16:48:42.926 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=8 with accept.
2026-03-16 16:48:43.906 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=9 value=9 from my-topic-2 @ offset 3 (delivery #1)
```

```
2026-03-16 17:03:00.452 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=863 with accept.
2026-03-16 17:03:01.452 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=864 value=864 from my-topic-0 @ offset 316 (delivery #1)
2026-03-16 17:03:01.452 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=864 with release.
2026-03-16 17:03:01.463 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=864 value=864 from my-topic-0 @ offset 316 (delivery #2)
2026-03-16 17:03:01.463 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=864 with accept.
2026-03-16 17:03:01.981 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member fYsHM9_zTjidvgKO2ThIaQ with epoch 6 transitioned from STABLE to RECONCILING.
2026-03-16 17:03:01.982 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Reconciling assignment with local epoch 2
	Member:                                    fYsHM9_zTjidvgKO2ThIaQ
	Assigned partitions:                       [my-topic-2]
	Current owned partitions:                  [my-topic-0, my-topic-1, my-topic-2]
	Added partitions (assigned - owned):       []
	Revoked partitions (owned - assigned):     [my-topic-0, my-topic-1]

2026-03-16 17:03:01.983 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Revoking previously assigned partitions [my-topic-0, my-topic-1]
2026-03-16 17:03:01.986 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member fYsHM9_zTjidvgKO2ThIaQ with epoch 6 transitioned from RECONCILING to ACKNOWLEDGING.
2026-03-16 17:03:02.454 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=865 value=865 from my-topic-0 @ offset 317 (delivery #1)
2026-03-16 17:03:02.454 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member fYsHM9_zTjidvgKO2ThIaQ with epoch 6 transitioned from ACKNOWLEDGING to STABLE.
2026-03-16 17:03:02.454 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=865 with accept.
2026-03-16 17:03:04.460 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=867 value=867 from my-topic-2 @ offset 269 (delivery #1)
2026-03-16 17:03:04.461 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=867 with accept.
2026-03-16 17:03:06.459 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=869 value=869 from my-topic-2 @ offset 270 (delivery #1)
2026-03-16 17:03:06.459 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=869 with accept.
2026-03-16 17:03:10.467 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=873 value=873 from my-topic-2 @ offset 271 (delivery #1)
2026-03-16 17:03:10.467 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=873 with reject.
2026-03-16 17:03:11.471 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=874 value=874 from my-topic-2 @ offset 272 (delivery #1)
2026-03-16 17:03:11.472 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=874 with reject.
2026-03-16 17:03:13.475 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=876 value=876 from my-topic-2 @ offset 273 (delivery #1)
2026-03-16 17:03:13.475 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=876 with accept.
```

```
2026-03-16 17:02:57.206 WARN  [main] o.a.k.c.c.i.ShareConsumerDelegateCreator - Share groups and KafkaShareConsumer are part of a preview feature introduced by KIP-932, and are not recommended for use in production.
2026-03-16 17:02:57.283 INFO  [main] o.a.k.c.t.i.KafkaMetricsCollector - initializing Kafka metrics collector
2026-03-16 17:02:57.377 INFO  [main] o.a.kafka.common.utils.AppInfoParser - Kafka version: 8.1.1-ce
2026-03-16 17:02:57.378 INFO  [main] o.a.kafka.common.utils.AppInfoParser - Kafka commitId: 1936d6674741736d
2026-03-16 17:02:57.378 INFO  [main] o.a.kafka.common.utils.AppInfoParser - Kafka startTimeMs: 1773680577376
2026-03-16 17:02:57.418 INFO  [main] o.a.k.c.c.i.ShareConsumerImpl - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Subscribed to topics: my-topic
2026-03-16 17:02:57.425 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Subscribed to topic my-topic with share group queue-consumer-group (KIP-932).
2026-03-16 17:02:57.627 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member jX7NREtZTTSBpaAXPL57oQ with epoch 0 transitioned from UNSUBSCRIBED to JOINING.
2026-03-16 17:02:57.699 INFO  [consumer_background_thread] org.apache.kafka.clients.Metadata - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Cluster ID: MkU3OEVBNTcwNTJENDM2Qk
2026-03-16 17:02:57.705 INFO  [consumer_background_thread] o.a.k.c.c.i.CoordinatorRequestManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Discovered group coordinator Coordinator(key='queue-consumer-group', nodeId=1, host='kafka', port=29092, errorCode=0, errorMessage='')
2026-03-16 17:02:57.715 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member jX7NREtZTTSBpaAXPL57oQ with epoch 4 transitioned from JOINING to RECONCILING.
2026-03-16 17:02:57.718 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Reconciling assignment with local epoch 0
	Member:                                    jX7NREtZTTSBpaAXPL57oQ
	Assigned partitions:                       [my-topic-1]
	Current owned partitions:                  []
	Added partitions (assigned - owned):       [my-topic-1]
	Revoked partitions (owned - assigned):     []

2026-03-16 17:02:57.721 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member jX7NREtZTTSBpaAXPL57oQ with epoch 4 transitioned from RECONCILING to ACKNOWLEDGING.
2026-03-16 17:02:57.730 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member jX7NREtZTTSBpaAXPL57oQ with epoch 4 transitioned from ACKNOWLEDGING to STABLE.
2026-03-16 17:03:02.733 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member jX7NREtZTTSBpaAXPL57oQ with epoch 6 transitioned from STABLE to RECONCILING.
2026-03-16 17:03:02.733 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Reconciling assignment with local epoch 1
	Member:                                    jX7NREtZTTSBpaAXPL57oQ
	Assigned partitions:                       [my-topic-2]
	Current owned partitions:                  [my-topic-1]
	Added partitions (assigned - owned):       [my-topic-2]
	Revoked partitions (owned - assigned):     [my-topic-1]

2026-03-16 17:03:02.733 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Revoking previously assigned partitions [my-topic-1]
2026-03-16 17:03:02.735 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member jX7NREtZTTSBpaAXPL57oQ with epoch 6 transitioned from RECONCILING to ACKNOWLEDGING.
2026-03-16 17:03:02.787 INFO  [consumer_background_thread] o.a.k.c.c.i.ShareMembershipManager - [ShareConsumer clientId=consumer-share, groupId=queue-consumer-group] Member jX7NREtZTTSBpaAXPL57oQ with epoch 6 transitioned from ACKNOWLEDGING to STABLE.
2026-03-16 17:03:19.510 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Consumed key=882 value=882 from my-topic-2 @ offset 275 (delivery #1)
2026-03-16 17:03:19.510 INFO  [main] c.r.k.consumer.MyKafkaConsumerShare - Acknowledging key=882 with accept.
```
