package elasticsearch.sample.update;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

// String 型で Elasticsearch のデータ更新（元からデータがあるidの場合は一新される）
public class UpdateStringDataRenewal {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // update way2 String 型で Elasticsearch のデータ更新（元からデータがあるidの場合は一新される）
        IndexRequest request = new IndexRequest("employeeindex");
        request.id("001");
        request.source("company", "SpaceX");
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        // 更新されたか id の確認
        System.out.println("response id: " + indexResponse.getId());
        System.out.println(indexResponse.getResult().name());
    }
}
