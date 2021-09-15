package elasticsearch.sample.delete;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

// 特定の index ごと削除
public class DeleteIndex {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // Delete the index
        // 特定の index ごと削除
        DeleteIndexRequest request = new DeleteIndexRequest("employeeindex");
        client.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println("Delete Done ");

        // クライアントを閉じる
        client.close();
    }
}
