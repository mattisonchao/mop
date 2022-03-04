# MOP test

## Broker Standalone

## Stability test

### Relate software

- Apache Pulsar 2.9.1
- StreamNative MOP 2.9.2.6 (pulsar 2.9.1 compatible version)
- Emqtt Bench 0.4.4 Released

### Resources

| Service provider | Instance Type | Architecture  | Memory(GB) | vCPUs | Service | amount  |
|:----------------:|:-------------:|:-------------:|:----------:|:-----:|:-------:|:-------:|
|Amazon Web Services|  t3.2xlarge   |    x86_64     |     32     |   8   | Broker  |    1    |
|Amazon Web Services|   t3.xlarge   |    x86_64     |     16     |   4   | Client  |    1    |

### Result

| Producer amount | Consumer amount | Topic | message size (byte) | send interval (ms) | running time (minutes) | publish rate (sec) | receive rate (sec) | Memory Avail (MiB) |
|--------------|-----------------|-------|---------------------|--------------------|------------------------|---------------------------|---------------------------|--------------------|
| 25k          | 25k             | 25k   | 256                 | 1000               | 40.30                  | 24985                     | 25005                     | 20822.8            |
| 25k          | 25k             | 25k   | 512                 | 1000               | 45.31                  | 25003                     | 25128                     | 20014.0            |
| 25k          | 25k             | 25k   | 1024                | 1000               | 36.23                  | 25060                     | 24957                     | 18747.3            |


#### Problem

- [Get NPE when the producer/consumer process was killed.](https://github.com/streamnative/mop/issues/484)

## Performance test

### Relate software

- Apache Pulsar 2.9.1
- StreamNative MOP 2.9.2.6 (pulsar 2.9.1 compatible version)
- Emqtt Bench 0.4.4 Released

### Resources

| Service provider | Instance Type | Architecture  | Memory(GB) | vCPUs | Service | amount  |
|:----------------:|:-------------:|:-------------:|:----------:|:-----:|:-------:|:-------:|
|Amazon Web Services|  t3.2xlarge   |    x86_64     |     32     |   8   | Broker  |    1    |
|Amazon Web Services|   t3.xlarge   |    x86_64     |     16     |   4   | Client  |    1    |

### Result

|          | number | rate | publish interval | connection interval (ms) |
|----------|:------:|:----:|------------------|--------------------------|
| Producer |   |      |                  |                          |
| Consumer |        |      |                  |                          |


## Broker Standalone with Proxy (Blocked)

### Stability test

### Relate software

- Apache Pulsar 2.9.1
- StreamNative MOP 2.9.2.6 (pulsar 2.9.1 compatible version)
- Emqtt Bench 0.4.4 Released

### Resources

| Service provider | Instance Type | Architecture  | Memory(GB) | vCPUs | Service | amount  |
|:----------------:|:-------------:|:-------------:|:----------:|:-----:|:-------:|:-------:|
|Amazon Web Services|  t3.2xlarge   |    x86_64     |     32     |   8   | Broker  |    1    |
|Amazon Web Services|   t3.xlarge   |    x86_64     |     16     |   4   | Client  |    1    |

### Result

Found many problem with Proxy, we need to fix it and then to re-test.

- [[Proxy] Lookup fail when many producer to publish message.](https://github.com/streamnative/mop/issues/497)
- [[Proxy] Publish message fail when proxy disconnect from the broker.](https://github.com/streamnative/mop/issues/498)
- [[Proxy] Lookup fail when many request to subscribe.](https://github.com/streamnative/mop/issues/499)
