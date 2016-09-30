package com.github.atemerev.sparkws

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.jfarcand.wcs.WebSocket

abstract class WebSocketReceiver[T](endpoint: String,
                                    storageLevel: StorageLevel = StorageLevel.OFF_HEAP) extends Receiver[T](storageLevel) {

  var websocket: Option[WebSocket] = None

  override def onStart(): Unit = this.websocket = Some(initWebSocket(endpoint))

  override def onStop(): Unit = for (ws <- websocket) {
    ws.close
    this.websocket = None
  }

  def initWebSocket(endpoint: String): WebSocket
}
