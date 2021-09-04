package elasticsearch.sample;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

// String 型で Elasticsearch のデータ更新
public class UpdateStringData {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // String 型でデータ更新
        UpdateRequest updateRequest = new UpdateRequest("employeeindex", "002");
        updateRequest.doc("department", "Bussiness");
        UpdateResponse updateResponse = client.update(updateRequest,
                RequestOptions.DEFAULT);
        // 更新されたか id の確認
        System.out.println("updated response id: " + updateResponse.getId());
    }
}
