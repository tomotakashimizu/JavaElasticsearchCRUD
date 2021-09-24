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

- [Create Index API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-create-index.html)

### Read

- [Search API](https://www.elastic.co/guide//en/elasticsearch/client/java-rest/master/java-rest-high-search.html)

### Update

### Delete
