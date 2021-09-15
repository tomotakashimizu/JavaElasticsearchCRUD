package elasticsearch.sample.create;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

// Map 型のデータを Elasticsearch に送る class
public class InsertMapData {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // Map 型のサンプルデータを作成
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("keyOne", 10);
        map.put("keyTwo", 30);
        map.put("KeyThree", 20);

        // Map 型のデータを Elasticsearch に送る
        IndexRequest indexRequest = new IndexRequest("sampleindex");
        indexRequest.id("002");
        indexRequest.source(map);
        IndexResponse indexResponse = client.index(indexRequest,
                RequestOptions.DEFAULT);
        // 正常に処理されたか確認
        System.out.println("response id: " + indexResponse.getId());
        System.out.println("response name: " + indexResponse.getResult().name());

        // クライアントを閉じる
        client.close();
    }
}
