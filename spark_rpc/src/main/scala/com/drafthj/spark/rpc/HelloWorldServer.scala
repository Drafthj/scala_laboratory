package com.drafthj.spark.rpc

import net.neoremind.kraps.RpcConf
import net.neoremind.kraps.rpc.RpcEnvServerConfig
import net.neoremind.kraps.rpc.netty.NettyRpcEnvFactory


object HelloWorldServer {
  def main(args: Array[String]): Unit = {
    //rpc服务器的配置
    val config = RpcEnvServerConfig(new RpcConf(), "hello-server", "localhost", 52345)
    //netty启动
    val rpcEnv = NettyRpcEnvFactory.create(config);

    val helloPoint = new HelloPoint(rpcEnv)
    rpcEnv.setupEndpoint("hello-service", helloPoint)
    rpcEnv.awaitTermination()
  }
}
