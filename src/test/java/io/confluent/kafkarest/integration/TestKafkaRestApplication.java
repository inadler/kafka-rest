/**
 * Copyright 2015 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package io.confluent.kafkarest.integration;

import org.I0Itec.zkclient.ZkClient;

import javax.ws.rs.core.Configurable;

import io.confluent.kafkarest.ConsumerManager;
import io.confluent.kafkarest.KafkaRestApplication;
import io.confluent.kafkarest.KafkaRestConfig;
import io.confluent.kafkarest.MetadataObserver;
import io.confluent.kafkarest.ProducerPool;

/**
 * Test version of KakfaRestApplication that allows for dependency injection so components and be
 * tweaked for tests.
 */
public class TestKafkaRestApplication extends KafkaRestApplication {

  ZkClient zkClientInjected;
  MetadataObserver mdObserverInjected;
  ProducerPool producerPoolInjected;
  ConsumerManager consumerManagerInjected;

  public TestKafkaRestApplication(KafkaRestConfig config, ZkClient zkClient,
                                  MetadataObserver mdObserver, ProducerPool producerPool,
                                  ConsumerManager consumerManager) {
    super(config);
    zkClientInjected = zkClient;
    mdObserverInjected = mdObserver;
    producerPoolInjected = producerPool;
    consumerManagerInjected = consumerManager;
  }

  @Override
  public void setupResources(Configurable<?> config, KafkaRestConfig appConfig) {
    setupInjectedResources(config, appConfig, zkClientInjected, mdObserverInjected,
                           producerPoolInjected, consumerManagerInjected);
  }

}
