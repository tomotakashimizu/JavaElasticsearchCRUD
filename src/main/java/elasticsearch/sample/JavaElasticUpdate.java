package elasticsearch.sample;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;

public class JavaElasticUpdate {

    public static void main(String[] args) throws IOException {
        // Elasticsearch に接続
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // // String 型で Elasticsearch のデータ更新
        // UpdateRequest updateRequest = new UpdateRequest("employeeindex", "002");
        // updateRequest.doc("department", "Bussiness");
        // UpdateResponse updateResponse = client.update(updateRequest,
        // RequestOptions.DEFAULT);
        // // 更新されたか id の確認
        // System.out.println("updated response id: " + updateResponse.getId());

        // // Map を作成
        // Map<String, Object> updateMap = new HashMap<String, Object>();
        // updateMap.put("firstname", "Sundar");
        // updateMap.put("lastname", "Pichai");
        // updateMap.put("company", "Google");
        // updateMap.put("sector", "IT");
        // // Elasticsearch を Map 型でデータ更新, 追加
        // UpdateRequest request = new UpdateRequest("employeeindex",
        // "002").doc(updateMap);
        // UpdateResponse updateResponse = client.update(request,
        // RequestOptions.DEFAULT);
        // // 更新されたか id の確認
        // System.out.println("updated response id: " + updateResponse.getId());

        // // update way2 String 型で Elasticsearch のデータ更新（元からデータがあるidの場合は一新される）
        // IndexRequest request = new IndexRequest("employeeindex");
        // request.id("001");
        // request.source("company", "SpaceX");
        // IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        // // 更新されたか id の確認
        // System.out.println("response id: " + indexResponse.getId());
        // System.out.println(indexResponse.getResult().name());

        // // update way2
        // // Elasticsearch を Map 型でデータ更新（元からデータがあるidの場合は一新される）
        // Map 作成
        // Map<String, Object> updateMap = new HashMap<String, Object>();
        // updateMap.put("firstname", "Sundar");
        // updateMap.put("lastname", "Pichai");
        // updateMap.put("company", "Google");
        // updateMap.put("sector", "IT");
        // Elasticsearch を Map 型でデータ更新
        // IndexRequest request2 = new IndexRequest("employeeindex");
        // request2.id("002");
        // request2.source(updateMap);
        // IndexResponse indexResponseUpdate = client.index(request2,
        // RequestOptions.DEFAULT);
        // // 更新されたか id の確認
        // System.out.println("response id: " + indexResponseUpdate.getId());
        // System.out.println(indexResponseUpdate.getResult().name());

        // EmployeePojo emp = new EmployeePojo("Elon01", "Musk01", LocalDate.now());
        // // POJO を JSON 形式にして Elasticsearch のデータを更新
        // IndexRequest request = new IndexRequest("employeeindex");
        // request.id("001");
        // request.source(new ObjectMapper().writeValueAsString(emp),
        // XContentType.JSON);
        // IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        // // 更新されたか id の確認
        // System.out.println("response id:" + indexResponse.getId());
        // System.out.println(indexResponse.getResult().name());

        // Using API- UpdateByQueryRequest
        Map<String, Object> updateMap2 = new HashMap<String, Object>();
        updateMap2.put("firstname", "Sundar01");
        updateMap2.put("lastname", "Pichai01");
        UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest("employeeindex");
        updateByQueryRequest.setConflicts("proceed");
        updateByQueryRequest.setQuery(new TermQueryBuilder("_id", "001"));
        Script script = new Script(ScriptType.INLINE, "painless", "ctx._source = params", updateMap2);
        updateByQueryRequest.setScript(script);

        try {
            BulkByScrollResponse bulkResponse = client.updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT);
            long totalDocs = bulkResponse.getTotal();
            // 更新されたか id の確認
            System.out.println("updated response id: " + totalDocs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
