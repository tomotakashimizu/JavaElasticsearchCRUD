package elasticsearch.sample.create;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import elasticsearch.sample.EmployeePojo;

// POJO を JSON 形式にして データを Elasticsearch に送る class
public class InsertPOJOMappingsData {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // POJO クラスをインスタンス化
        EmployeePojo emp = new EmployeePojo("Elon", "Musk", LocalDate.now());

        // POJO を JSON 形式にして データを Elasticsearch に送る
        IndexRequest indexRequest = new IndexRequest("sampleindex");
        indexRequest.id("003");
        indexRequest.source(new ObjectMapper().writeValueAsString(emp), XContentType.JSON);
        System.out.println("JSONデータ" + new ObjectMapper().writeValueAsString(emp));
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        // 正常に処理されたか確認
        System.out.println("response id: " + indexResponse.getId());
        System.out.println("response name: " + indexResponse.getResult().name());
    }
}
