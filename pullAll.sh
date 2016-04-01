#!/bin/sh

cd ..
git -C BtBase pull
git -C BtVertxBase pull
git -C vertx-util pull
git -C vertx-key-generator pull
git -C vertx-pojo-mapper pull
git -C NetRelay pull
git -C NetRelay-Controller pull
git -C NetRelay-PdfController pull
git -C NetRelay-DemoProject pull

cd Dev-Helper
git pull
