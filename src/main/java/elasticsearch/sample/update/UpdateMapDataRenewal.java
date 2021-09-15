package elasticsearch.sample.update;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

// Elasticsearch を Map 型でデータ更新（元からデータがあるidの場合は一新される）
public class UpdateMapDataRenewal {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // update way2
        // Elasticsearch を Map 型でデータ更新（元からデータがあるidの場合は一新される）
        // Map 作成
        Map<String, Object> updateMap = new HashMap<String, Object>();
        updateMap.put("firstname", "Sundar");
        updateMap.put("lastname", "Pichai");
        updateMap.put("company", "Google");
        updateMap.put("sector", "IT");
        // Elasticsearch を Map 型でデータ更新
        IndexRequest request2 = new IndexRequest("employeeindex");
        request2.id("002");
        request2.source(updateMap);
        IndexResponse indexResponseUpdate = client.index(request2,
                RequestOptions.DEFAULT);
        // 更新されたか id の確認
        System.out.println("response id: " + indexResponseUpdate.getId());
        System.out.println(indexResponseUpdate.getResult().name());

        // クライアントを閉じる
        client.close();
    }
}
