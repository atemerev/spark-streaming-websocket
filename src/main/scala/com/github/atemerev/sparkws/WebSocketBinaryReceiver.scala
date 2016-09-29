package com.github.atemerev.sparkws

import java.net.URL

import org.apache.spark.storage.StorageLevel
import org.jfarcand.wcs.{BinaryListener, WebSocket}

class WebSocketBinaryReceiver(endpoint: URL, storageLevel: StorageLevel = StorageLevel.OFF_HEAP)
  extends WebSocketReceiver[Array[Byte]](endpoint, storageLevel) {

    override def initWebSocket(): WebSocket = WebSocket().open(endpoint.toString).listener(new BinaryListener() {
      override def onMessage(message: Array[Byte]): Unit = store(message)
    })
  }
