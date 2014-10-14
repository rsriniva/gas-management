# ガス圧遠隔監視システムの使い方

クライアントから電文をソケット通信で定期的に送信し、サーバはDBに書き込みます。
また、定期的にDBからデータを取得し、WebSocketでブラウザに表示します。

サーバ、クライアントともに、Apache Camel使用しています。より詳細なアーキテクチャは、ガス圧遠隔監視システム.pdfを参照してください。


## インストール

    cd gas-management
    mvn install

## サーバ起動

    cd war
    mvn jetty:run

を実行すると、Jettyが立ち上がり、WARファイルをデプロイします。  
デフォルトでは、Jettyはポート番号8080で起動します。8080が使用されていて起動時にエラーが出る場合は、

    mvn jetty:run -Djetty.port=8888

のように、ポート番号を指定してください。

## 監視画面の表示

WebSocketをサポートしている、Google Chromeなどの最新のブラウザで、<http://localhost:9998> にアクセスしてください。

## クライアントの起動

クライアントを起動して、サーバに電文を送信します。クライアントは5秒間隔で電文を送信します。

    cd ../client/target
    java -jar gasmanagement-client-1.0-SNAPSHOT.jar

電文が、監視画面に表示されることを確認してください。