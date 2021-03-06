/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.rpc.cluster.router;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.configcenter.DynamicConfiguration;
import org.apache.dubbo.rpc.cluster.Router;
import org.apache.dubbo.rpc.cluster.RouterChain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * TODO Extract more code to here if necessary
 */
public abstract class AbstractRouter implements Router {
    protected int priority;
    protected boolean force = false;
    protected boolean enabled = true;
    protected List<RouterChain> routerChains = new CopyOnWriteArrayList<>();
    protected URL url;

    protected DynamicConfiguration configuration;

    public AbstractRouter(DynamicConfiguration configuration, URL url) {
        this.configuration = configuration;
        this.url = url;
    }

    public AbstractRouter() {
    }

    @Override
    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public void addRouterChain(RouterChain routerChain) {
        this.routerChains.add(routerChain);
    }


    public void setConfiguration(DynamicConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean isRuntime() {
        return true;
    }

    @Override
    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    @Override
    public int compareTo(Router o) {
        return (this.getPriority() >= o.getPriority()) ? 1 : -1;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

}
