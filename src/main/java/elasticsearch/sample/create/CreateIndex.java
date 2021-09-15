package elasticsearch.sample.create;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;

// Elasticsearch に新しい index を作成する class
public class CreateIndex {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // Elasticsearch に新しい index を作成
        CreateIndexRequest request = new CreateIndexRequest("sampleindex");
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 2));
        CreateIndexResponse createIndexResponse = client.indices().create(request,
                RequestOptions.DEFAULT);
        // index が作成されたか確認
        System.out.println("response id: " + createIndexResponse.index());

        // クライアントを閉じる
        client.close();
    }
}
