package org.apache.dubbo.demo.consumer;

import com.comsince.github.PushService;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.config.*;
import org.apache.dubbo.monitor.MonitorFactory;
import org.apache.dubbo.rpc.Filter;

/**
 * @author comsicne
 * Copyright (c) [2019] [Meizu.inc]
 * @Time 19-5-10 上午9:37
 **/
public class PushApplication {
    public static void main(String[] args){
        ReferenceConfig<PushService> referenceConfig = new ReferenceConfig<PushService>();
        referenceConfig.setApplication(new ApplicationConfig("push-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://zk-test-master1.meizu.mz:2181"));
        referenceConfig.setInterface(PushService.class);
        MetricsConfig metricsConfig = new MetricsConfig();
        metricsConfig.setPort("20887");
        metricsConfig.setProtocol("dubbo");
        //metadata
        MetadataReportConfig metadataReportConfig = new MetadataReportConfig();
        metadataReportConfig.setAddress("zookeeper://zk-test-master1.meizu.mz:2181");
        referenceConfig.setMetadataReportConfig(metadataReportConfig);
        referenceConfig.setMetrics(metricsConfig);
        for (;;){
            PushService pushService = referenceConfig.get();
            pushService.pushAll("push from test");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void testMonitorFactory(){
        Filter filter = ExtensionLoader.getExtensionLoader(Filter.class).getExtension("monitor");
        System.out.println(filter);
        MonitorFactory monitorFactory = ExtensionLoader.getExtensionLoader(MonitorFactory.class).getExtension("dubbo");
        System.out.println(monitorFactory);
    }
}
