package com.liang.config;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @author Liang
 * com.liang.config LoadBalancerRule  2021/1/3 0003  Liang
 */
public class LoadBalancerRule implements IRule {

    private ILoadBalancer mILoadBalancer;

    @Override
    public Server choose(Object o) {
        List<Server> allServers = mILoadBalancer.getAllServers();
        for (Server server : allServers) {
            System.out.println(server.getHostPort());
        }
        return allServers.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.mILoadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return mILoadBalancer;
    }
}
