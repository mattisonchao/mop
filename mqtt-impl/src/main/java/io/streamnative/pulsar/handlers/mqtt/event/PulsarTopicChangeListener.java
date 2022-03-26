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
package io.streamnative.pulsar.handlers.mqtt.event;

import io.streamnative.pulsar.handlers.mqtt.utils.EventParserUtils;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.pulsar.common.naming.TopicName;


public interface PulsarTopicChangeListener extends PulsarEventListener {
    String MANAGED_LEDGER_PATH = "/managed-ledgers";
    String REGEX = MANAGED_LEDGER_PATH + "/" + ".*" + "/persistent/";
    Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    default boolean matchPath(String path) {
        String listenNamespace = getListenNamespace();
        if (!StringUtils.isEmpty(listenNamespace)){
            return path.startsWith(MANAGED_LEDGER_PATH + "/" + listenNamespace + "/persistent/");
        } else {
            return PATTERN.matcher(path).find();
        }
    }

    default String getListenNamespace() {
        return "";
    }

    @Override
    default void onNodeCreated(String path) {
        TopicName topicName = EventParserUtils.parseFromManagedLedgerEvent(path);
        onTopicLoad(topicName);
    }

    @Override
    default void onNodeDeleted(String path) {
        TopicName topicName = EventParserUtils.parseFromManagedLedgerEvent(path);
        onTopicUnload(topicName);
    };

    void onTopicLoad(TopicName topicName);

    void onTopicUnload(TopicName topicName);
}
