/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.streamnative.pulsar.handlers.mqtt;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.streamnative.pulsar.handlers.mqtt.support.DefaultProtocolMethodProcessorImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
/**
 * MQTT in bound handler.
 */
@Sharable
@Slf4j
public class MQTTInboundHandler extends MQTTCommonInboundHandler {

    @Getter
    private final MQTTService mqttService;

    public MQTTInboundHandler(MQTTService mqttService) {
        this.mqttService = mqttService;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        processor = new DefaultProtocolMethodProcessorImpl(mqttService, ctx);
    }
}
