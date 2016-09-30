package com.github.atemerev.sparkws

import org.apache.spark.storage.StorageLevel
import org.jfarcand.wcs.{BinaryListener, WebSocket}

class WebSocketBinaryReceiver(endpoint: String, outgoingMessage: Array[Byte] = Array.emptyByteArray, storageLevel: StorageLevel = StorageLevel.OFF_HEAP)
  extends WebSocketReceiver[Array[Byte]](endpoint, storageLevel) {

  override def onStart(): Unit = {
    super.onStart()
    if (outgoingMessage.nonEmpty) {
      websocket.get.send(outgoingMessage)
    }
  }

  override def initWebSocket(endpoint: String): WebSocket = WebSocket().open(endpoint.toString).listener(new BinaryListener() {
      override def onMessage(message: Array[Byte]): Unit = store(message)
    })
}
