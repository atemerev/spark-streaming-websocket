package com.github.atemerev.sparkws

import java.net.URL
import java.util.concurrent.{Executors, Future}

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.jfarcand.wcs.WebSocket

abstract class WebSocketReceiver[T](val endpoint: URL,
                        storageLevel: StorageLevel = StorageLevel.OFF_HEAP) extends Receiver[T](storageLevel) {

  var websocket: Option[WebSocket] = None

  override def onStart(): Unit = this.websocket = Some(initWebSocket())

  override def onStop(): Unit = for (ws <- websocket) ws.close; this.websocket = None

  def initWebSocket(): WebSocket
}
