package com.penguin.penguinmall.mq.constants;

public interface RabbitmqConstants {

    String SSC_OLDER_EXCHANGE = "ssc_older_routing_exchange";
    String SSC_OLDER_ROUTING_KEY = "ssc_older_routing_key";
    String SSC_OLDER_QUEUE ="ssc_older_routing_queue";

    String SSC_OLDER_WS_ROUTING_KEY = "ssc_older_ws_routing_key";
    String SSC_OLDER_WS_QUEUE ="ssc_older_ws_routing_queue";

    /*
    购物相关mq常量
     */
    String SSC_SC_EXCHANGE = "ssc_sc_routing_exchange";
    String SSC_SC_ROUTING_KEY = "ssc_sc_routing_key";
    String SSC_SC_QUEUE ="ssc_sc_queue";

    /*
      canal的mq常量
     */
    String CANAL_TOPIC_EXCHANGE = "canal.topic.exchange";
    String CANAL_TOPIC_QUEUE = "canal.topic.queue";
    String CANAL_TOPIC_ROUTING_KEY = "canal.topic.routingkey";

    /*
      dead letter的常量
     */
    String SSC_NORMAL_EXCHANGE="sc.normal.exchange";
    String SSC_DLX_EXCHANGE="sc.dlx.exchange";

    String SSC_NORMAL_QUEUE = "sc.normal.queue";
    String SSC_DLX_QUEUE = "sc.dlx.queue";

    String SSC_NORMAL_ROUTING_KEY="sc.normal.routingkey";
    String SSC_DLX_ROUTING_KEY="sc.dlx.routingkey";

    String SSC_DELAY_PLUGIN_QUEUE = "sc.dlx.plugin.queue";
    String DELAY_PLUGIN_ROUTING_KEY = "sc.dlx.plugin.routingkey";
    String DELAY_PLUGIN_EXCHANGE = "sc.dlx.plugin.exchange";

}
