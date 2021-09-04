package elasticsearch.sample;

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

// POJO を JSON 形式にして Elasticsearch のデータを更新
public class UpdatePOJOMappingsData {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // EmployeePojo クラスをインスタンス化
        EmployeePojo emp = new EmployeePojo("Elon01", "Musk01", LocalDate.now());

        // POJO を JSON 形式にして Elasticsearch のデータを更新
        IndexRequest request = new IndexRequest("employeeindex");
        request.id("001");
        request.source(new ObjectMapper().writeValueAsString(emp),
                XContentType.JSON);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        // 更新されたか id の確認
        System.out.println("response id: " + indexResponse.getId());
        System.out.println(indexResponse.getResult().name());
    }
}
