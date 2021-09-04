package elasticsearch.sample.update;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

// Elasticsearch を Map 型でデータ更新, 追加
public class UpdateMapData {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // Map を作成
        Map<String, Object> updateMap = new HashMap<String, Object>();
        updateMap.put("firstname", "Sundar");
        updateMap.put("lastname", "Pichai");
        updateMap.put("company", "Google");
        updateMap.put("sector", "IT");
        // Elasticsearch を Map 型でデータ更新, 追加
        UpdateRequest request = new UpdateRequest("employeeindex",
                "002").doc(updateMap);
        UpdateResponse updateResponse = client.update(request,
                RequestOptions.DEFAULT);
        // 更新されたか id の確認
        System.out.println("updated response id: " + updateResponse.getId());
    }
}
