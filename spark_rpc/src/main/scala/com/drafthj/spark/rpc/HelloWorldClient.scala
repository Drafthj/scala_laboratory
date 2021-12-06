package com.drafthj.spark.rpc

import net.neoremind.kraps.RpcConf
import net.neoremind.kraps.rpc.{RpcAddress, RpcEnvClientConfig}
import net.neoremind.kraps.rpc.netty.NettyRpcEnvFactory

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

object HelloWorldClient {
  def main(args: Array[String]): Unit = {
    val rpcConf = new RpcConf()
    val config = RpcEnvClientConfig(rpcConf, "hello-client")
    val rpcEnv = NettyRpcEnvFactory.create(config)
    val endPointRef = rpcEnv.setupEndpointRef(RpcAddress("localhost", 52345), "hello-service")
//    val future = endPointRef.ask[String](SayHi("neo"))
//    future.onComplete {
//      case Success(value) => println(s"Got The result = $value")
//      case Failure(exception) => println(s"Got error: $exception")
//    }
//    Await.result(future, Duration.apply("30s"))
    val msg = endPointRef.askWithRetry[String](SayHi("neo"))
    println(msg)
  }
}
