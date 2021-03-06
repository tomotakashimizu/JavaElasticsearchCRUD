# JavaElasticsearchCRUD

JavaElasticsearchCRUD は [Java REST Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/index.html) を用いて [Elasticsearch](https://www.elastic.co/products/elasticsearch) とデータのやりとり(CRUD) を行うためのサンプルコードです。Elasticsearch に保存されたデータは [Kibana](https://www.elastic.co/products/kibana) で可視化して確認できます。

## Elasticsearch setup

### 起動

```
$ elasticsearch
```

### 起動の確認

```
$ curl http://localhost:9200/
```

## Kibana setup

### 起動

```
$ kibana
```

## Document [Java REST Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/index.html)

### Create

- [Create Index API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-create-index.html)：新しい index を作成
- [Index API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-document-index.html)：index にデータを作成

### Read

- [Search API](https://www.elastic.co/guide//en/elasticsearch/client/java-rest/master/java-rest-high-search.html)：データを取得, 検索

### Update

- [Update API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-document-update.html)：データ更新, 追加
- [Index API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-document-index.html)：データ更新（元からデータがある id の場合は一新される）

### Delete

- [Delete Index API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-delete-index.html)：特定の index ごと削除
- [Delete API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-document-delete.html)：特定の index id のデータを削除
