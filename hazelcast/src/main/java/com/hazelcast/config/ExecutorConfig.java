/*
 * Copyright (c) 2008-2012, Hazel Bilisim Ltd. All Rights Reserved.
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
 */

package com.hazelcast.config;

import com.hazelcast.nio.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ExecutorConfig implements DataSerializable {

    public final static int DEFAULT_CORE_POOL_SIZE = 40;
    public final static int DEFAULT_MAX_POOL_SIZE = 40;
    public final static int DEFAULT_CAPACITY = Integer.MAX_VALUE;
    public final static int DEFAULT_KEEP_ALIVE_SECONDS = 300;
    public final static ExecutorType DEFAULT_EXECUTOR_TYPE = ExecutorType.Parallel;

    private String name = "default";

    private int corePoolSize = DEFAULT_CORE_POOL_SIZE;

    private int maxPoolSize = DEFAULT_MAX_POOL_SIZE;

    private int capacity = DEFAULT_CAPACITY;

    private int keepAliveSeconds = DEFAULT_KEEP_ALIVE_SECONDS;

    private ExecutorType executorType = DEFAULT_EXECUTOR_TYPE;


    public enum ExecutorType {
        BlockingParallel, Parallel, Queued
    }

    public ExecutorConfig() {
    }

    public ExecutorConfig(String name) {
        this.name = name;
    }

    public ExecutorConfig(String name, int corePoolSize, int maxPoolSize, int keepAliveSeconds) {
        this.name = name;
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.keepAliveSeconds = keepAliveSeconds;
    }

    public String getName() {
        return name;
    }

    public ExecutorConfig setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return the corePoolSize
     */
    public int getCorePoolSize() {
        return corePoolSize;
    }

    /**
     * @param corePoolSize the corePoolSize to set
     */
    public ExecutorConfig setCorePoolSize(final int corePoolSize) {
        if (corePoolSize < 0) {
            throw new IllegalArgumentException("corePoolSize must be positive");
        }
        this.corePoolSize = corePoolSize;
        return this;
    }

    /**
     * @return the maxPoolSize
     */
    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    /**
     * @param maxPoolSize the maxPoolSize to set
     */
    public ExecutorConfig setMaxPoolSize(final int maxPoolSize) {
        if (maxPoolSize <= 0) {
            throw new IllegalArgumentException("maxPoolSize must be positive");
        }
        this.maxPoolSize = maxPoolSize;
        return this;
    }


    /**
     * @return the size of the work queue
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the size of the work queue
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the keepAliveSeconds
     */
    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    /**
     * @param keepAliveSeconds the keepAliveSeconds to set
     */
    public ExecutorConfig setKeepAliveSeconds(final int keepAliveSeconds) {
        if (keepAliveSeconds <= 0) {
            throw new IllegalArgumentException("keepAlice seconds must be positive");
        }
        this.keepAliveSeconds = keepAliveSeconds;
        return this;
    }

    /**
     * @return the executor type
     */
    public ExecutorType getExecutorType() {
        return executorType;
    }

    /**
     * @param executorType set the type of executor to use
     */
    public void setExecutorType(ExecutorType executorType) {
        this.executorType = executorType;
    }

    public void writeData(DataOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(corePoolSize);
        out.writeInt(maxPoolSize);
        out.writeInt(keepAliveSeconds);
        out.writeInt(capacity);
        out.writeUTF(executorType.name());
    }

    public void readData(DataInput in) throws IOException {
        name = in.readUTF();
        corePoolSize = in.readInt();
        maxPoolSize = in.readInt();
        keepAliveSeconds = in.readInt();
        capacity = in.readInt();
        executorType = ExecutorType.valueOf(in.readUTF());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ExecutorConfig");
        sb.append("{name='").append(name).append('\'');
        sb.append(", corePoolSize=").append(corePoolSize);
        sb.append(", maxPoolSize=").append(maxPoolSize);
        sb.append(", keepAliveSeconds=").append(keepAliveSeconds);
        sb.append('}');
        return sb.toString();
    }
}
