package com.liwenwen.protocol;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class HttpServer {
    /**
     * 启动网络组件 netty tomcat
     * @param hostname 主机名
     * @param port 端口
     */
    public void start(String hostname,Integer port)  {
        Tomcat tomcat = new Tomcat();
        Server server =  tomcat.getServer();
        Service service = server.findService("Tomcat");
        Connector connector = new Connector();
        connector.setPort(port);
        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);
        Host host = new StandardHost();
        host.setName(hostname);
        String contextPath ="";
        Context myCtx =new StandardContext();
        myCtx.setPath(contextPath);
        myCtx.addLifecycleListener( new Tomcat.FixContextListener());
        host.addChild(myCtx);
        engine.addChild(host);
        service.setContainer(engine);
        service.addConnector(connector);
        /*
         * https://www.cnblogs.com/ChenD/p/10061008.html
         */

        /*
         * true时：相关classes | jar 修改时，会重新加载资源，不过资源消耗很大
         * autoDeploy 与这个很相似，tomcat自带的热部署不是特别可靠，效率也不高。生产环境不建议开启。
         * 相关文档：
         * http://www.blogjava.net/wangxinsh55/archive/2011/05/31/351449.html
         */
//        myCtx.setReloadable(false);
//        // 上下文监听器
//        myCtx.addLifecycleListener(new AprLifecycleListener());

        /*String webAppMount = System.getProperty("user.dir") + File.separator + TomcatStart.CLASS_PATH;
        WebResourceRoot root = new StandardRoot(myCtx);
        root.addPreResources(
            new DirResourceSet(root, TomcatStart.WEBINF_CLASSES, webAppMount, TomcatStart.INTERNAL_PATH));*/

        // 注册servlet
        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet());
        // servlet mapping
        myCtx.addServletMappingDecoded("/*", "dispatcher");
        try{
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }


    }
}
