package elasticsearch.sample;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class JavaElasticDelete {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // // 特定の index id のデータを削除
        // DeleteRequest deleteRequest = new DeleteRequest("employeeindex", "002");
        // DeleteResponse deleteResponse = client.delete(deleteRequest,
        // RequestOptions.DEFAULT);
        // // 削除した id を確認
        // System.out.println("response id: " + deleteResponse.getId());

        // Delete the index
        // 特定の index ごと削除
        DeleteIndexRequest request = new DeleteIndexRequest("employeeindex");
        client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println("Delete Done ");
    }
}
