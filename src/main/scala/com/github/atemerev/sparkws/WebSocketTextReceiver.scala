package com.github.atemerev.sparkws
import java.net.URL

import org.apache.spark.storage.StorageLevel
import org.jfarcand.wcs.{TextListener, WebSocket}

class WebSocketTextReceiver(endpoint: URL, storageLevel: StorageLevel = StorageLevel.OFF_HEAP)
  extends WebSocketReceiver[String](endpoint, storageLevel) {

  override def initWebSocket(): WebSocket = WebSocket().open(endpoint.toString).listener(new TextListener() {
    override def onMessage(message: String): Unit = store(message)
  })
}
